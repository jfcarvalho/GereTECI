package com.teci.gereteci.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teci.gereteci.model.*;
import com.teci.gereteci.model.Computador.Arquitetura;
import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Computador.Memoria;
import com.teci.gereteci.model.Computador.Sistema;
import com.teci.gereteci.model.Computador.StatusComputador;
import com.teci.gereteci.model.Impressora.Impressora;
import com.teci.gereteci.model.Internet.Dns_alternativo;
import com.teci.gereteci.model.Internet.Dns_preferencial;
import com.teci.gereteci.model.Internet.Gateway;
import com.teci.gereteci.model.Internet.Mascara;
import com.teci.gereteci.model.Recurso.CaixaDeSom;
import com.teci.gereteci.model.Recurso.Midia;
import com.teci.gereteci.model.Recurso.Monitor;
import com.teci.gereteci.model.Recurso.Mouse;
import com.teci.gereteci.model.Recurso.Recurso;
import com.teci.gereteci.model.Recurso.Teclado;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.*;




@Controller
@RequestMapping("/computadores")
public class ComputadorController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroComputador"; 
	@Autowired
	private Computadores computadores;
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private Impressoras impressoras;
	@Autowired
	private Recursos recursos;
	
	List<Usuario> todosUsuariosSemComputador = new ArrayList<Usuario>();
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Computador());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Computador computador, @RequestParam Integer usuario_id_usuario, @RequestParam Integer recurso_monitor, @RequestParam Integer recurso_teclado, @RequestParam Integer recurso_mouse, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
	//	System.out.println(">>>>>> " + usuario_id_usuario);
	
		if(usuario_id_usuario != null)
		{
			Usuario user = usuarios.findOne(usuario_id_usuario);
			//computador.setUsuario(user);
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
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaComputadores");
	    mv.addObject("computadores", todosComputadores);
		mv.addObject("usuarios", todosUsuarios);
	    return mv;
	}

	@RequestMapping("{id_computador}")

	public ModelAndView edicao(@PathVariable("id_computador") Computador computador, @RequestParam Integer id_recurso, @RequestParam Integer recurso_teclado, @RequestParam Integer recurso_mouse)
	{
		//ObjectMapper mapper = new ObjectMapper();
		
		System.out.println(">>>>>>> codigo recebido: " + computador.getId_computador());
		//System.out.println(">>>>>>> Codigo de usuario recebido: " + recursos.getDescricao());
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);	
		mv.addObject("pc", computador);
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
	
	@ModelAttribute("todasMemoriasComputador")
	public List<Memoria> todosMemoriasComputador() {
		return Arrays.asList(Memoria.values());
	}
	
	@ModelAttribute("todosUsuariosComputador") //Lista todos os usuários que ainda nao tem computador
	public List<Usuario> todosUsuariosComputador()
	{
		List<Usuario> todosUsuarios = usuarios.findAll();
		
		Iterator it = todosUsuarios.iterator();
		while(it.hasNext())
		{
			Usuario user = (Usuario) it.next();
			if(user.getComputador() == null && !(todosUsuariosSemComputador.contains(user)))
			{
					todosUsuariosSemComputador.add(user);
					System.out.println(todosUsuariosSemComputador.size());
			}
		}
		return todosUsuariosSemComputador; 
		//return todosUsuarios;
	}
	
	@ModelAttribute("todasImpressorasComputador")
	public List<Impressora> todasImpressorasComputador()
	{
		List<Impressora> todasImpressoras= impressoras.findAll();
		return todasImpressoras;
	}
	
	@ModelAttribute("todasImpressorasDisponiveisComputador")
	public List<Impressora> todasImpressorasDisponiveisComputador()
	{
		List<Impressora> todasImpressoras= impressoras.findAll();
		List<Impressora> todasImpressorasDisponiveis = new ArrayList<Impressora>();
		Iterator it = todasImpressoras.iterator();
		while(it.hasNext())
		{
			Impressora obj = (Impressora) it.next();
			
			if(obj.getComputadores().isEmpty())
			{
					todasImpressorasDisponiveis.add(obj);
			}
		}
		return todasImpressorasDisponiveis;
		
		
	}
	
	
	@ModelAttribute("todosMonitoresDisponiveis")
	public List<Recurso> todasRecursosMonitorDisponiveis()
	{
		
		
		List<Recurso> todosMonitores= recursos.findAll();
		List<Recurso> todosMonitoresDisponiveis = new ArrayList<Recurso>();
		Iterator it = todosMonitores.iterator();
		while(it.hasNext())
		{
			Recurso obj = (Recurso) it.next();
			System.out.println(obj.getTipo_recurso());
			if(obj.getComputador() == null && obj.getTipo_recurso().equals("Monitor")) {
				//System.out.println(obj.getId_recurso() + obj.getPolegadas());
				todosMonitoresDisponiveis.add(obj);
			}
		}
		return todosMonitoresDisponiveis;
	}
	
	
	
	@ModelAttribute("todosMonitores")
	public List<Recurso> todosMonitores()
	{
		List<Recurso> todosMonitores= recursos.findAll();
		return todosMonitores;
	}
	
	/*
	@ModelAttribute("todosMouses")
	public List<Mouse> todosMouses()
	{
		List<Mouse> todosMouses= mouses.findAll();
		return todosMouses;
	}
	
	@ModelAttribute("todosMousesDisponiveis")
	public List<Mouse> todasMousesDisponiveis()
	{
		
		
		List<Mouse> todosMouses= mouses.findAll();
		List<Mouse> todosMousesDisponiveis = new ArrayList<Mouse>();
		Iterator it = todosMouses.iterator();
		while(it.hasNext())
		{
			Mouse obj = (Mouse) it.next();
			if(obj.getComputador() == null)
				todosMousesDisponiveis.add(obj);
			
		}
		return todosMousesDisponiveis;
	}
	
	@ModelAttribute("todosTeclados")
	public List<Teclado> todaoTeclados()
	{

		List<Teclado> todosTeclados= teclados.findAll();	
		return todosTeclados;
	}
	
	@ModelAttribute("todosTecladosDisponiveis")
	public List<Teclado> todasTecladosDisponiveis()
	{
		
		
		List<Teclado> todosRecursos= teclados.findAll();
		List<Teclado> todosTecladosDisponiveis = new ArrayList<Teclado>();
		Iterator it = todosRecursos.iterator();
		while(it.hasNext())
		{
			Teclado obj = (Teclado) it.next();
			if(obj.getComputador() == null)
				todosTecladosDisponiveis.add(obj);
			
		}
		return todosTecladosDisponiveis;
	}
	
	@ModelAttribute("todasMR")
	public List<Midia> todasRecursosMR()
	{
		List<Midia> todasMR= midias.findAll();
		return todasMR;
	}
	/*
	@ModelAttribute("todasMRDisponiveis")
	public List<Midia> todasMRDisponiveis()
	{
		
		
		List<Midia> todasMR= midias.findAll();
		List<Midia> todosMRDisponiveis = new ArrayList<Midia>();
		Iterator it = todasMR.iterator();
		while(it.hasNext())
		{
			Midia obj = (Midia) it.next();
			if(obj.getComputador() == null)
				todosMRDisponiveis.add(obj);
		}
		return todosMRDisponiveis;
	}
	*/
	/*
	@ModelAttribute("todasCS")
	public List<CaixaDeSom> todasCS()
	{
		
		List<CaixaDeSom> todosCS= cs.findAll();
		return todosCS;
	}
	*/
	/*
	@ModelAttribute("todosCSDisponiveis")
	public List<CaixaDeSom> todasCSDisponiveis()
	{
		
		
		List<CaixaDeSom> todasCS= cs.findAll();
		List<CaixaDeSom> todosCSDisponiveis = new ArrayList<CaixaDeSom>();
		Iterator it = todasCS.iterator();
		while(it.hasNext())
		{
			CaixaDeSom obj = (CaixaDeSom) it.next();
			if(obj.getComputador() == null)
				todosCSDisponiveis.add(obj);
			
		}
		return todosCSDisponiveis;
	}
	*/
	@RequestMapping(value="/{id_computador}/manutencao", method=RequestMethod.PUT)
	public @ResponseBody String manutencao(@PathVariable Integer id_computador)
	{
		//Isso aqui vai para camada de serviço
		Computador computador = computadores.findOne(id_computador);
		computador.setStatus(StatusComputador.manutencao);
		computadores.save(computador);
		return StatusComputador.manutencao.getStatus();
	}
	
	@RequestMapping(value="/{id_computador}/baixa", method=RequestMethod.PUT)
	public @ResponseBody String baixa(@PathVariable Integer id_computador)
	{
		//Isso aqui vai para camada de serviço
		Computador computador = computadores.findOne(id_computador);
		computador.setStatus(StatusComputador.com_defeito_para);
		computadores.save(computador);
		return StatusComputador.com_defeito_para.getStatus();
	}
	
	@RequestMapping(value="/{id_computador}/consertado", method=RequestMethod.PUT)
	public @ResponseBody String consertado(@PathVariable Integer id_computador)
	{
		//Isso aqui vai para camada de serviço
		Computador computador = computadores.findOne(id_computador);
		computador.setStatus(StatusComputador.funcionando);
		computadores.save(computador);
		return StatusComputador.funcionando.getStatus();
	}
}
