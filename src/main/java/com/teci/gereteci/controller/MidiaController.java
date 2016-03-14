package com.teci.gereteci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Recurso.Midia;
import com.teci.gereteci.model.Recurso.Monitor;
import com.teci.gereteci.repository.Computadores;
import com.teci.gereteci.repository.Midias;

@Controller
@RequestMapping("/midias")


public class MidiaController {
	private static final String CADASTRO_VIEW_MIDIA = "/cadastro/CadastroMidia"; 
	@Autowired
	private Midias midias;
	@Autowired
	private Computadores computadores;
	
	@RequestMapping("/midia_removivel/novo")
	public ModelAndView novaMidia()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MIDIA);
		mv.addObject(new Midia());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Midia midia, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MIDIA);
		if(errors.hasErrors())
		{
			return "cadastroMidia";
		}
		if(computador_id_computador != null)
		{
			Computador computer= computadores.findOne(computador_id_computador);
			midia.setComputador(computer);
			//List<Recurso> resources = computer.getRecursos();
			//resources.add(recurso);
			//computer.setRecursos(resources);
		}
		midias.save(midia);
		attributes.addFlashAttribute("mensagem", "Midia salva com sucesso!");	
		return "redirect:/midias/novo";
	}
	
	public ModelAndView pesquisar()
	{
		List<Midia> todasMidias= midias.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaMidias");
	    mv.addObject("midias", todasMidias);
		return mv;
	}
	
	@RequestMapping("{id_recurso}")
	public ModelAndView edicao(@PathVariable("id_recurso") Midia midia)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MIDIA);
		
		mv.addObject("midia", midia);
		mv.addObject(midia);
		return mv;
	}
	
	@RequestMapping(value="{id_recurso}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_recurso, RedirectAttributes attributes)
	{
		midias.delete(id_recurso);
		attributes.addFlashAttribute("mensagem", "Midia Removível excluída com sucesso com sucesso!");	
		return "redirect:/midias";
	}

}
