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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teci.gereteci.model.*;
import com.teci.gereteci.model.Setor.Setor;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.*;

@Controller
@RequestMapping("/setores")
public class SetorController {
	private static final String CADASTRO_VIEW = "CadastroSetor"; 
	@Autowired
	private Setores setores;
	@Autowired
	private Usuarios usuarios;
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Setor());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Setor setor, @RequestParam(value="responsavel", required=true) Integer responsavel, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroSetor";
		}
		System.out.println(">>>>>>>>> ID DO RESPONSAVEL: "+ responsavel);
		Usuario user = usuarios.findOne(responsavel);
		setor.setResponsavel(user);
		setores.save(setor);
		
		attributes.addFlashAttribute("mensagem", "Setor salvo com sucesso!");	
		return "redirect:/setores/novo";
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Setor> todosSetores= setores.findAll();
		ModelAndView mv = new ModelAndView("PesquisaSetores");
	    mv.addObject("setores", todosSetores);
		return mv;
	}
	
	@RequestMapping("{id_setor}")
	public ModelAndView edicao(@PathVariable("id_setor") Setor setor)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("set", setor);
		mv.addObject(setor);
		return mv;
	}
	
	@RequestMapping(value="{id_setor}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_setor, RedirectAttributes attributes)
	{
		setores.delete(id_setor);
		attributes.addFlashAttribute("mensagem", "Setor excluido com sucesso com sucesso!");	
		return "redirect:/setores";
	}
	
	@ModelAttribute("todosResponsaveisSetor")
	public List<Usuario> todosResponsaveisSetor()
	{
		List<Usuario> todosUsuarios= usuarios.findAll();
		return todosUsuarios;
	}
	
}
