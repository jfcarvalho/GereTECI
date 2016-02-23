package com.teci.gereteci.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teci.gereteci.model.*;
import com.teci.gereteci.model.Computador.CategoriaRecurso;
import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Computador.Recurso;
import com.teci.gereteci.model.Computador.StatusComputador;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.*;

@Controller
@RequestMapping("/recursos")
public class RecursoController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroRecurso"; 
	@Autowired
	private Recursos recursos;
	@Autowired
	private Computadores computadores;
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Recurso());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Recurso recurso, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroRecurso";
		}
		if(computador_id_computador != null)
		{
			Computador computer= computadores.findOne(computador_id_computador);
			recurso.setComputador(computer);
			List<Recurso> resources = computer.getRecursos();
			resources.add(recurso);
			computer.setRecursos(resources);
		}
		
		
		
		
		recursos.save(recurso);
		
		attributes.addFlashAttribute("mensagem", "Recurso salvo com sucesso!");	
		return "redirect:/recursos/novo";
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Recurso> todosRecursos = recursos.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaRecursos");
	    mv.addObject("recursos", todosRecursos);
		return mv;
	}
	
	@RequestMapping("{id_recurso}")
	public ModelAndView edicao(@PathVariable("id_recurso") Recurso recurso)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		
		mv.addObject("rec", recurso);
		mv.addObject(recurso);
		return mv;
	}
	
	@RequestMapping(value="{id_recurso}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_recurso, RedirectAttributes attributes)
	{
		recursos.delete(id_recurso);
		attributes.addFlashAttribute("mensagem", "Recurso excluido com sucesso com sucesso!");	
		return "redirect:/recursos";
	}
	@ModelAttribute("todosComputadoresRecurso")
	public List<Computador> todosComputadoresRecurso()
	{
		List<Computador> todosComputadores= computadores.findAll();
		return todosComputadores;
	}
	
	@ModelAttribute("todosStatusRecurso")
	public List<StatusComputador> todosStatusRecurso() {
		return Arrays.asList(StatusComputador.values());
	}
	@ModelAttribute("todasCategoriasRecurso")
	public List<CategoriaRecurso> todasCategoriasRecurso() {
		return Arrays.asList(CategoriaRecurso.values());
	}
	
	@RequestMapping(value="/{id_recurso}/manutencao", method=RequestMethod.PUT)
	public @ResponseBody String manutencao(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Recurso recurso = recursos.findOne(id_recurso);
		recurso.setStatus(StatusComputador.manutencao);
		recursos.save(recurso);
		return StatusComputador.manutencao.getStatus();
	}
	
	@RequestMapping(value="/{id_recurso}/baixa", method=RequestMethod.PUT)
	public @ResponseBody String baixa(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Recurso recurso = recursos.findOne(id_recurso);
		recurso.setStatus(StatusComputador.com_defeito_para);
		recursos.save(recurso);
		return StatusComputador.com_defeito_para.getStatus();
	}
	
	@RequestMapping(value="/{id_recurso}/consertado", method=RequestMethod.PUT)
	public @ResponseBody String consertado(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Recurso recurso= recursos.findOne(id_recurso);
		recurso.setStatus(StatusComputador.funcionando);
		recursos.save(recurso);
		return StatusComputador.funcionando.getStatus();
	}
	
}
