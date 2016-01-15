package com.teci.gereteci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teci.gereteci.model.*;
import com.teci.gereteci.repository.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private Usuarios usuarios;
	@RequestMapping("/novo")
	public String novo()
	{
		return "CadastroUsuario";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Usuario usuario)
	{
		usuarios.save(usuario);
		return "CadastroUsuario";
	}

}
