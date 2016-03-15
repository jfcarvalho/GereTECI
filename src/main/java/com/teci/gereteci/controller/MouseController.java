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
import com.teci.gereteci.model.Recurso.Mouse;
import com.teci.gereteci.repository.Computadores;
import com.teci.gereteci.repository.Monitores;
import com.teci.gereteci.repository.Mouses;

@Controller
@RequestMapping("/mouses")

public class MouseController {
	private static final String CADASTRO_VIEW_MOUSE = "/cadastro/CadastroMouse"; 
	@Autowired
	private Mouses mouses;
	@Autowired
	private Computadores computadores;
	
	@RequestMapping("/novo")
	public ModelAndView novoMouse()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MOUSE);
		mv.addObject(new Mouse());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Mouse mouse, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MOUSE);
		if(errors.hasErrors())
		{
			return "cadastroMouse";
		}
		if(computador_id_computador != null)
		{
			Computador computer= computadores.findOne(computador_id_computador);
			mouse.setComputador(computer);
			//List<Recurso> resources = computer.getRecursos();
			//resources.add(recurso);
			//computer.setRecursos(resources);
		}
		mouses.save(mouse);
		attributes.addFlashAttribute("mensagem", "Mouse salvo com sucesso!");	
		return "redirect:/mouses/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Mouse> todosMouses = mouses.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaMouses");
	    mv.addObject("mouses", todosMouses);
		return mv;
	}
	
	@RequestMapping("{id_recurso}")
	public ModelAndView edicao(@PathVariable("id_recurso") Mouse mouse)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MOUSE);
		
		mv.addObject("rec", mouse);
		mv.addObject(mouse);
		return mv;
	}
	
	@RequestMapping(value="{id_recurso}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_recurso, RedirectAttributes attributes)
	{
		mouses.delete(id_recurso);
		attributes.addFlashAttribute("mensagem", "Mouse excluido com sucesso com sucesso!");	
		return "redirect:/mouses";
	}
	
	@ModelAttribute("todosComputadoresDisponiveis")
	public List<Computador> todosComputadoresDisponiveis()
	{
		List<Computador> todosComputadores = computadores.findAll();
		List<Computador> todosComputadoresDisponiveis = new ArrayList<Computador>();
		Iterator it = todosComputadores.iterator();
		while(it.hasNext())
		{
			Computador obj = (Computador) it.next();
			if(obj.getRecurso_mouse() == null) {
				System.out.println(obj.getId_impressao());
				todosComputadoresDisponiveis.add(obj);
			}
		}
		
		return todosComputadoresDisponiveis;
	}
	
	@ModelAttribute("todosStatusMouse")
	public List<StatusComputador> todosStatusMouses() {
		return Arrays.asList(StatusComputador.values());
	}
}
