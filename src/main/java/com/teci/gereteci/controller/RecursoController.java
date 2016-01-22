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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teci.gereteci.model.*;
import com.teci.gereteci.repository.*;

@Controller
@RequestMapping("/recursos")
public class RecursoController {
	private static final String CADASTRO_VIEW = "CadastroRecurso"; 
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
	public String salvar(@Validated Recurso recurso, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroRecurso";
		}
		recursos.save(recurso);
		
		attributes.addFlashAttribute("mensagem", "Recurso salvo com sucesso!");	
		return "redirect:/recursos/novo";
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Recurso> todosRecursos = recursos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaRecursos");
	    mv.addObject("recursos", todosRecursos);
		return mv;
	}
	
	@RequestMapping("{id_recurso}")
	public ModelAndView edicao(@PathVariable("id_recurso") Recurso recurso)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
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
	
	
}
