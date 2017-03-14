package com.teci.gereteci.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teci.gereteci.repository.*;
import com.teci.gereteci.model.*;
import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.security.AppUserDetailsService;

@Controller

public class HomeController {
	
	@Autowired
	private Usuarios usuarios;

	@RequestMapping("/gereteci")
	public String index(HttpServletRequest request)
	{
		System.out.println(request.getServletContext());
		return "index";
	}
	
	@ModelAttribute("home_teci")
	public boolean homeTECI() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_TECI");
	}
	
	@ModelAttribute("home_comum")
	public boolean homeComum() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_COMUM");
	}
	
	@ModelAttribute("servicos_total")
	public Integer total_s()
	{
		List<Servico> services = usuarios.findByEmail(AppUserDetailsService.cusuario.getUsername().toString()).getServicos_manutencao(); 

		return	services.size();			// AppUserDetailsService.cusuario.getUsername()
	}
}
