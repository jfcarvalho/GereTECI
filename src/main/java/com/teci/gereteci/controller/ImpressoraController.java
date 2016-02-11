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
import com.teci.gereteci.repository.*;

@Controller
@RequestMapping("/impressoras")
public class ImpressoraController {
	private static final String CADASTRO_VIEW = "CadastroImpressora"; 
	@Autowired
	private Impressoras impressoras;
	@Autowired
	private Setores setores;
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Impressora());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Impressora impressora, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroImpressora";
		}
		impressoras.save(impressora);
		
		attributes.addFlashAttribute("mensagem", "Impressora salvo com sucesso!");	
		return "redirect:/impressoras/novo";
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Impressora> todasImpressoras = impressoras.findAll();
		ModelAndView mv = new ModelAndView("PesquisaImpressoras");
	    mv.addObject("impressoras", todasImpressoras);
		return mv;
	}
	
	@RequestMapping("{id_impressora}")
	public ModelAndView edicao(@PathVariable("id_impressora") Impressora impressora)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("print", impressora);
		mv.addObject(impressora);
		return mv;
	}
	
	@RequestMapping(value="{id_impressora}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_impressora, RedirectAttributes attributes)
	{
		impressoras.delete(id_impressora);
		attributes.addFlashAttribute("mensagem", "Impressora excluida com sucesso com sucesso!");	
		return "redirect:/impressoras";
	}
	
	@ModelAttribute("todosStatusImpressora")
	public List<StatusComputador> todosStatusImpressora() {
		return Arrays.asList(StatusComputador.values());
	}
	
	@ModelAttribute("todosSetoresImpressora")
	public List<Setor> todosSetoresImpressora()
	{
		List<Setor> todosSetores= setores.findAll();
		return todosSetores;
	}
	
	@ModelAttribute("todasMascarasImpressora")
	public List<Mascara> todasMascarasImpressora() {
		return Arrays.asList(Mascara.values());
	}
	@ModelAttribute("todosGatewaysImpressora")
	public List<Gateway> todosGatewaysImpressora() {
		return Arrays.asList(Gateway.values());
	}
	@ModelAttribute("todosDNSPImpressora")
	public List<Dns_preferencial> todosDNSPImpressora() {
		return Arrays.asList(Dns_preferencial.values());
	}
	
	@ModelAttribute("todosDNSAImpressora")
	public List<Dns_alternativo> todosDNSAImpressora() {
		return Arrays.asList(Dns_alternativo.values());
	}
	
}
