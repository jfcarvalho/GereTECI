package com.teci.gereteci.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Computador.StatusComputador;
import com.teci.gereteci.model.Recurso.Monitor;
import com.teci.gereteci.model.Recurso.Teclado;
import com.teci.gereteci.repository.Computadores;
import com.teci.gereteci.repository.Monitores;
import com.teci.gereteci.repository.Teclados;

@Controller
@RequestMapping("/teclados")

public class TecladoController {
	private static final String CADASTRO_VIEW_TECLADO = "/cadastro/CadastroTeclado"; 
	@Autowired
	private Teclados teclados;
	@Autowired
	private Computadores computadores;
	
	@RequestMapping("/novo")
	public ModelAndView novoTeclado()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_TECLADO);
		mv.addObject(new Teclado());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Teclado teclado, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_TECLADO);
		if(errors.hasErrors())
		{
			return "cadastroTeclado";
		}
		if(computador_id_computador != null)
		{
			Computador computer= computadores.findOne(computador_id_computador);
			teclado.setComputador(computer);
			//List<Recurso> resources = computer.getRecursos();
			//resources.add(recurso);
			//computer.setRecursos(resources);
		}
		teclados.save(teclado);
		attributes.addFlashAttribute("mensagem", "Teclado salvo com sucesso!");	
		return "redirect:/teclados/novo";
	}
	
	public ModelAndView pesquisar()
	{
		List<Teclado> todosTeclados= teclados.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaTeclados");
	    mv.addObject("teclados", todosTeclados);
		return mv;
	}
	
	@RequestMapping("{id_recurso}")
	public ModelAndView edicao(@PathVariable("id_recurso") Teclado teclado)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_TECLADO);
		
		mv.addObject("teclado", teclado);
		mv.addObject(teclado);
		return mv;
	}
	
	@RequestMapping(value="{id_recurso}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_recurso, RedirectAttributes attributes)
	{
		teclados.delete(id_recurso);
		attributes.addFlashAttribute("mensagem", "Teclado excluido com sucesso com sucesso!");	
		return "redirect:/teclados";
	}
	
	@ModelAttribute("todosComputadoresDisponiveis")
	public List<Computador> todosComputadoresDisponiveis()
	{
		List<Computador> todosComputadores= computadores.findAll();
		List<Computador> todosComputadoresDisponiveis = new ArrayList<Computador>();
		Iterator it = todosComputadores.iterator();
		while(it.hasNext())
		{
			Computador obj = (Computador) it.next();
		/*	if(obj.getRecurso_teclado() == null) {
				//System.out.println(obj.getId_impressao());
				todosComputadoresDisponiveis.add(obj);
			}
		*/	
		}
		
		return todosComputadoresDisponiveis;
	}
	
	@ModelAttribute("todosStatusTeclado")
	public List<StatusComputador> todosStatusMonitor() {
		return Arrays.asList(StatusComputador.values());
	}
}
