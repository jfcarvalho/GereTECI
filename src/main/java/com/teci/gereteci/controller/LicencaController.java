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
import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Licenca.Licenca;
import com.teci.gereteci.repository.*;

@Controller
@RequestMapping("/gereteci/licencas")
public class LicencaController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroLicenca"; 
	@Autowired
	private Licencas licencas;
	@Autowired
	private Computadores computadores;
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Licenca());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Licenca licenca, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroLicenca";
		}
		Computador computer = computadores.findOne(computador_id_computador);
		licenca.setComputador(computer);
		
		licencas.save(licenca);
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");	
		return "redirect:/gereteci/licencas/novo";
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Licenca> todosLicencas = licencas.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaLicencas");
	    mv.addObject("licencas", todosLicencas);
		return mv;
	}
	
	@RequestMapping("{id_licenca}")
	public ModelAndView edicao(@PathVariable("id_licenca") Licenca licenca)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_licenca);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("lpc", licenca);
		mv.addObject(licenca);
		return mv;
	}
	
	@RequestMapping(value="{id_licenca}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_licenca, RedirectAttributes attributes)
	{
		licencas.delete(id_licenca);
		attributes.addFlashAttribute("mensagem", "Licença excluida com sucesso com sucesso!");	
		return "redirect:/gereteci/licencas";
	}


	@ModelAttribute("todosComputadoresLicenca")
	public List<Computador> todosComputadoresLicenca()
	{
		List<Computador> todosComputadores= computadores.findAll();
		return todosComputadores;
	}
	
	
}
