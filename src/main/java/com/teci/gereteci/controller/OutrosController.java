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
import com.teci.gereteci.model.Recurso.Midia;
import com.teci.gereteci.model.Recurso.Monitor;
import com.teci.gereteci.model.Recurso.Outros;
import com.teci.gereteci.model.Recurso.TipoES;
import com.teci.gereteci.repository.Computadores;
import com.teci.gereteci.repository.Midias;
import com.teci.gereteci.repository.Outross;

@Controller
@RequestMapping("/gereteci/outros")


public class OutrosController {
	private static final String CADASTRO_VIEW_OUTROS = "/cadastro/CadastroOutros"; 
	@Autowired
	private Outross outross;
	@Autowired
	private Computadores computadores;
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_OUTROS);
		mv.addObject(new Outros());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Outros outros,  Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_OUTROS);
		if(errors.hasErrors())
		{
			return "cadastroOutros";
		}
		
		outross.save(outros);
		attributes.addFlashAttribute("mensagem", "Midia salva com sucesso!");	
		return "redirect:/gereteci/outros/novo";
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Outros> todasMidias= outross.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaOutros");
	    mv.addObject("outros", todasMidias);
		return mv;
	}
	
	@RequestMapping("{id_recurso}")
	public ModelAndView edicao(@PathVariable("id_recurso") Outros outro)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_OUTROS);
		
		mv.addObject("rec", outro);
		mv.addObject(outro);
		return mv;
	}
	
	@RequestMapping(value="{id_recurso}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_recurso, RedirectAttributes attributes)
	{
		outross.delete(id_recurso);
		attributes.addFlashAttribute("mensagem", "Recurso excluído com sucesso com sucesso!");	
		return "redirect:/gereteci/outros";
	}


	@ModelAttribute("todosStatusOutros")
	public List<StatusComputador> todosStatusMidia() {
		return Arrays.asList(StatusComputador.values());
	}
	@ModelAttribute("todosTiposOutros")
	public List<TipoES> todosTiposMidia() {
		return Arrays.asList(TipoES.values());
	}
	
	
}
