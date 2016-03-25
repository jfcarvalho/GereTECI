package com.teci.gereteci.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {

	@RequestMapping("/gereteci")
	public String index(HttpServletRequest request)
	{
		System.out.println(request.getServletContext());
		return "index";
	}
}
