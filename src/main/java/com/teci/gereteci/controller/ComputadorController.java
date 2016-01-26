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
@RequestMapping("/computadores")
public class ComputadorController {
	private static final String CADASTRO_VIEW = "CadastroComputador"; 
	@Autowired
	private Computadores computadores;
	@Autowired
	private Usuarios usuarios;
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Computador());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Computador computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
		computadores.save(computador);
		
		attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		return "redirect:/computadores/novo";
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Computador> todosComputadores = computadores.findAll();
		List<Usuario> todosUsuarios = usuarios.findAll();
		ModelAndView mv = new ModelAndView("PesquisaComputadores");
	    mv.addObject("computadores", todosComputadores);
		mv.addObject("usuarios", todosUsuarios);
	    return mv;
	}
	
	@RequestMapping("{id_computador}")
	public ModelAndView edicao(@PathVariable("id_computador") Computador computador)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(computador);
		return mv;
	}
	
	@RequestMapping(value="{id_computador}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_computador, RedirectAttributes attributes)
	{
		computadores.delete(id_computador);
		attributes.addFlashAttribute("mensagem", "Computador excluido com sucesso com sucesso!");	
		return "redirect:/computadores";
	}
	@ModelAttribute("todosSistemasComputador")
	public List<Sistema> todosSistemasComputador() {
		return Arrays.asList(Sistema.values());
	}
	@ModelAttribute("todasArquiteturasComputador")
	public List<Arquitetura> todasArquiteturasComputador() {
		return Arrays.asList(Arquitetura.values());
	}
	@ModelAttribute("todasMascarasComputador")
	public List<Mascara> todasMascarasComputador() {
		return Arrays.asList(Mascara.values());
	}
	@ModelAttribute("todosGatewaysComputador")
	public List<Gateway> todosGatewaysComputador() {
		return Arrays.asList(Gateway.values());
	}
	@ModelAttribute("todosDNSPComputador")
	public List<Dns_preferencial> todosDNSPComputador() {
		return Arrays.asList(Dns_preferencial.values());
	}
	
	@ModelAttribute("todosDNSAComputador")
	public List<Dns_alternativo> todosDNSAComputador() {
		return Arrays.asList(Dns_alternativo.values());
	}
	
	@ModelAttribute("todosStatusComputador")
	public List<StatusComputador> todosStatusComputador() {
		return Arrays.asList(StatusComputador.values());
	}
	
	@ModelAttribute("todosUsuariosComputador")
	public List<Usuario> todosUsuariosComputador()
	{
		List<Usuario> todosUsuarios = usuarios.findAll();
	//	ModelAndView mv = new ModelAndView("PesquisaComputadores");
	   // mv.addObject("usuarios", todosUsuarios);
		return todosUsuarios;
	}
	
	
}
