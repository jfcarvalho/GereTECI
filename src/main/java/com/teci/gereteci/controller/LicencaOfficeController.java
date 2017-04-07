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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teci.gereteci.model.*;
import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Licenca.LicencaOffice;
import com.teci.gereteci.model.Licenca.PlanoOffice;
import com.teci.gereteci.repository.*;
import com.teci.gereteci.security.AppUserDetailsService;

@Controller
@RequestMapping("/gereteci/licencasoffice")
public class LicencaOfficeController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroLicencaOffice"; 
	@Autowired
	private LicencasOffice licencasOffice;
	@Autowired
	private Computadores computadores;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new LicencaOffice());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated LicencaOffice licencaOffice, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "CadastroLicencaOffice";
		}
		licencasOffice.save(licencaOffice);
		
		attributes.addFlashAttribute("mensagem", "Licença Office salvo com sucesso!");	
		return "redirect:/gereteci/licencasoffice/novo";
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<LicencaOffice> todasLicencasOffice = licencasOffice.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaLicencasOffice");
	    mv.addObject("licencasOffice", todasLicencasOffice);
		return mv;
	}
	
	@RequestMapping("{id_licencaoffice}")
	public ModelAndView edicao(@PathVariable("id_licencaoffice") LicencaOffice licencaOffice)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_licenca);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("lo", licencaOffice);
		mv.addObject(licencaOffice);
		return mv;
	}
	
	@RequestMapping(value="{id_licencaoffice}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_licencaoffice, RedirectAttributes attributes)
	{
		licencasOffice.delete(id_licencaoffice);
		attributes.addFlashAttribute("mensagem", "Licença Office excluida com sucesso com sucesso!");	
		return "redirect:/gereteci/licencasoffice";
	}
	@ModelAttribute("todosPlanosLicencaOffice")
	public List<PlanoOffice> todosPlanosLicencaOffice() {
		return Arrays.asList(PlanoOffice.values());
	}
	@ModelAttribute("todosComputadoresLicencasOffice")
	public List<Computador> todosComputadoresLicencasOffice()
	{
		List<Computador> todosComputadores= computadores.findAll();
		return todosComputadores;
	}
	
	@ModelAttribute("home_teci")
	public boolean homeTECI() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_TECI") || AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_CADASTRAR_SERVICO");
	}
	
	
}
