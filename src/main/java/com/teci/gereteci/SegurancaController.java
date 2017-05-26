package com.teci.gereteci;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SegurancaController {
	
	/*@GetMapping("/login")*/
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@AuthenticationPrincipal User user) {
		System.out.println(user);
		if (user != null) {
			return "redirect:/gereteci/";
		}
		
		return "login";
	}
	
}