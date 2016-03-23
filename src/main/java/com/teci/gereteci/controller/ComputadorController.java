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
	private static final String EDICAO1_VIEW = "/edicoes/EditarComputador";
	private static final String EDICAO2_VIEW = "/edicoes/EditarComputadorRecurso";
	private static final String EDICAO3_VIEW = "/edicoes/EditarComputadorUsuario";
	private static final String EDICAO4_VIEW = "/edicoes/EditarComputadorBackup";
	private static final String EDICAO5_VIEW = "/edicoes/EditarComputadorFormatacao";
	@Autowired
	private Computadores computadores;
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private Impressoras impressoras;
	@Autowired
	private Recursos recursos;
	@Autowired
	private CaixasDeSom caixas;
	@Autowired
	private Monitores monitores;
	@Autowired
	private Teclados teclados;
	@Autowired
	private Mouses mouses;
	

	
	
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Computador());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Computador computador, @RequestParam Integer usuario_id_usuario, @RequestParam Integer recurso_caixa, @RequestParam Integer recurso_monitor1, @RequestParam Integer recurso_mouse, @RequestParam Integer recurso_teclado, Errors errors, RedirectAttributes attributes)
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
			computador.setUsuario(user);
		}
		if(recurso_caixa != null)
		{
			CaixaDeSom caixa = caixas.findOne(recurso_caixa);
			caixa.setComputador(computador); 
			caixas.save(caixa);
			computador.setRecurso_caixa(caixa);
		}
		if(recurso_monitor1 != null)
		{
			Monitor monitor = monitores.findOne(recurso_monitor1);
			monitor.setComputador(computador);
			monitores.save(monitor);
			computador.setRecurso_monitor1(monitor);
		}
		if(recurso_teclado != null)
		{
			Teclado teclado = teclados.findOne(recurso_teclado);
			teclado.setComputador(computador);
			teclados.save(teclado);
			computador.setRecurso_teclado(teclado);
		}
		if(recurso_mouse != null)
		{
			Mouse mouse = mouses.findOne(recurso_mouse);
			mouse.setComputador(computador);
			mouses.save(mouse);
			computador.setRecurso_mouse(mouse);
		}
		computadores.save(computador);
		attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		return "redirect:/computadores/novo";
		
	}
	@RequestMapping(value="/{id_computador}/salvar1",method = RequestMethod.POST)
	public String salvar1(@Validated Computador computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
	//	System.out.println(">>>>>> " + usuario_id_usuario);
		Computador pc = computadores.findOne(computador.getId_computador());
		pc.setPatrimonio(computador.getPatrimonio());
		pc.setSistema(computador.getSistema());
		pc.setArquitetura(computador.getArquitetura());
		pc.setIp(computador.getIp());
		pc.setMascara(computador.getMascara());
		pc.setGateway(computador.getGateway());
		pc.setDns_preferencial(computador.getDns_preferencial());
		pc.setDns_alternativo(computador.getDns_alternativo());
		pc.setVersao_java(computador.getVersao_java());
		pc.setData_compra(computador.getData_compra());
		pc.setId_impressao(computador.getId_impressao());
		pc.setMemoria(computador.getMemoria());
		pc.setProcessador(computador.getProcessador());
		pc.setStatus(computador.getStatus());
		computadores.save(pc);
		attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		return "redirect:/computadores/novo";
		
	}
	@RequestMapping(value="/{id_computador}/salvar2",method = RequestMethod.POST)
	public String salvar2(@Validated Computador computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
	//	System.out.println(">>>>>> " + usuario_id_usuario);
		Computador pc = computadores.findOne(computador.getId_computador());
		if(computador.getImpressoras() != null)
			pc.setImpressoras(computador.getImpressoras());
		if(computador.getRecurso_teclado() != null)
			pc.setRecurso_teclado(computador.getRecurso_teclado());
		if(computador.getRecurso_monitor1() != null)
			pc.setRecurso_monitor1(computador.getRecurso_monitor1());
		if(computador.getRecurso_mouse() != null)
			pc.setRecurso_mouse(computador.getRecurso_mouse());
		if(computador.getRecurso_caixa() != null)
		pc.setRecurso_caixa(computador.getRecurso_caixa());
		
		computadores.save(pc);
		attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		return "redirect:/computadores/novo";
		
	}
	@RequestMapping("/{id_computador}/editar1")
	
	public ModelAndView editar1(@PathVariable("id_computador") Computador computador)
	{
		ModelAndView mv = new ModelAndView(EDICAO1_VIEW);
	//	System.out.println(">>>>>> " + usuario_id_usuario);
	
	
		//computadores.save(computador);
		//attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		//return "redirect:/computadores/novo";
		
		mv.addObject("pc", computador);
		mv.addObject(computador);
		
		return mv;
	}
	@RequestMapping("/{id_computador}/editar2")
	public ModelAndView editar2(@PathVariable("id_computador") Computador computador)
	{
		ModelAndView mv = new ModelAndView(EDICAO2_VIEW);
	//	System.out.println(">>>>>> " + usuario_id_usuario);
	
	
		//computadores.save(computador);
		//attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		//return "redirect:/computadores/novo";
		
		mv.addObject("pc", computador);
		mv.addObject(computador);
		
		return mv;
	}
	
	@RequestMapping("/{id_computador}/editar3")
	public ModelAndView editar3(@PathVariable("id_computador") Computador computador)
	{
		ModelAndView mv = new ModelAndView(EDICAO3_VIEW);
	//	System.out.println(">>>>>> " + usuario_id_usuario);
	
	
		//computadores.save(computador);
		//attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		//return "redirect:/computadores/novo";
		
		mv.addObject("pc", computador);
		mv.addObject(computador);
		
		return mv;
	}
	
	@RequestMapping("/{id_computador}/editar4")
	public ModelAndView editar4(@PathVariable("id_computador") Computador computador)
	{
		ModelAndView mv = new ModelAndView(EDICAO4_VIEW);
	//	System.out.println(">>>>>> " + usuario_id_usuario);
	
	
		//computadores.save(computador);
		//attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		//return "redirect:/computadores/novo";
		
		mv.addObject("pc", computador);
		mv.addObject(computador);
		
		return mv;
	}
	
	@RequestMapping("/{id_computador}/editar5")
	public ModelAndView editar5(@PathVariable("id_computador") Computador computador)
	{
		ModelAndView mv = new ModelAndView(EDICAO5_VIEW);
	//	System.out.println(">>>>>> " + usuario_id_usuario);
	
	
		//computadores.save(computador);
		//attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		//return "redirect:/computadores/novo";
		
		mv.addObject("pc", computador);
		mv.addObject(computador);
		
		return mv;
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

	public ModelAndView edicao(@PathVariable("id_computador") Computador computador)
	{
		//ObjectMapper mapper = new ObjectMapper();
		
		//System.out.println(">>>>>>> codigo recebido: " + computador.getId_computador());
		//System.out.println(">>>>>>> Codigo de usuario recebido: " + recursos.getDescricao());
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(EDICAO1_VIEW);	
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
		List<Usuario> todosUsuariosSemComputador = new ArrayList<Usuario>();
		Iterator it = todosUsuarios.iterator();
		while(it.hasNext())
		{
			Usuario user = (Usuario) it.next();
			if(user.getComputador() == null)
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
	
	@ModelAttribute("todasImpressorasPertencentesComputador")
	public List<Impressora> todasImpressorasPertencentesComputador()
	{
		List<Impressora> todasImpressoras= impressoras.findAll();
		List<Impressora> todasImpressorasDisponiveis = new ArrayList<Impressora>();
		List<Computador> todosComputadores = computadores.findAll();
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
	
	@ModelAttribute("todosTecladosDisponiveis")
	public List<Recurso> todasTecladosDisponiveis()
	{
		
		
		List<Recurso> todosRecursos= recursos.findAll();
		List<Recurso> todosTecladosDisponiveis = new ArrayList<Recurso>();
		Iterator it = todosRecursos.iterator();
		while(it.hasNext())
		{
			Recurso obj = (Recurso) it.next();
			if(obj.getComputador() == null && obj.getTipo_recurso().equals("Teclado"))
				todosTecladosDisponiveis.add(obj);
			
		}
		return todosTecladosDisponiveis;
	}
	
	@ModelAttribute("todosMousesDisponiveis")
	public List<Recurso> todasMousesDisponiveis()
	{
		
		
		List<Recurso> todosRecursos= recursos.findAll();
		List<Recurso> todosMousesDisponiveis = new ArrayList<>();
		Iterator it = todosRecursos.iterator();
		while(it.hasNext())
		{
			Recurso obj = (Recurso) it.next();
			if(obj.getComputador() == null && obj.getTipo_recurso().equals("Mouse"))
				todosMousesDisponiveis.add(obj);
			
		}
		return todosMousesDisponiveis;
	}
	@ModelAttribute("todasCSDisponiveis")
	
	public List<Recurso> todasCsDisponiveis()
	{
		
		
		List<Recurso> todosRecursos= recursos.findAll();
		List<Recurso> todasCSDisponiveis = new ArrayList<>();
		Iterator it = todosRecursos.iterator();
		//System.out.println(usuario_id_usuario);
		while(it.hasNext())
		{
			Recurso obj = (Recurso) it.next();
			if(obj.getComputador() == null && obj.getTipo_recurso().equals("CaixaDeSom"))
			{
				todasCSDisponiveis.add(obj);
				
			}
		}
		return todasCSDisponiveis;
	}
	
	
	@ModelAttribute("todosMonitores")
	public List<Recurso> todosMonitores()
	{
		List<Recurso> todosMonitores= recursos.findAll();
		return todosMonitores;
	}
	
	
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
