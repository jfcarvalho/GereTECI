package com.teci.gereteci.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.teci.gereteci.model.*;
import com.teci.gereteci.repository.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private Usuarios usuarios;
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView("CadastroUsuario");
		mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Usuario usuario)
	{
		usuarios.save(usuario);
	
		ModelAndView mv = new ModelAndView("CadastroUsuario");
		
		return mv;
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Usuario> todosUsuarios = usuarios.findAll();
		ModelAndView mv = new ModelAndView("PesquisaUsuarios");
	    mv.addObject("usuarios", todosUsuarios);
		return mv;
	}
	
	@ModelAttribute("todosNiveisUsuario")
	public List<Nivel> todosNiveisUsuario() {
		return Arrays.asList(Nivel.values());
	}
}
