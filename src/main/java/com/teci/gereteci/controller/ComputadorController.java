package com.teci.gereteci.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import com.teci.gereteci.model.*;
import com.teci.gereteci.model.Computador.Arquitetura;
import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Computador.Memoria;
import com.teci.gereteci.model.Computador.Sistema;
import com.teci.gereteci.model.Computador.Status;
import com.teci.gereteci.model.Impressora.Impressora;
import com.teci.gereteci.model.Internet.Dns_alternativo;
import com.teci.gereteci.model.Internet.Dns_preferencial;
import com.teci.gereteci.model.Internet.Gateway;
import com.teci.gereteci.model.Internet.Mascara;
import com.teci.gereteci.model.Licenca.LicencaOffice;
import com.teci.gereteci.model.Recurso.CaixaDeSom;
import com.teci.gereteci.model.Recurso.Midia;
import com.teci.gereteci.model.Recurso.Monitor;
import com.teci.gereteci.model.Recurso.Mouse;
import com.teci.gereteci.model.Recurso.Recurso;
import com.teci.gereteci.model.Recurso.Teclado;
import com.teci.gereteci.model.Setor.Setor;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.*;
import com.teci.gereteci.security.AppUserDetailsService;




@Controller
@RequestMapping("/gereteci/computadores")
public class ComputadorController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroComputador"; 
	private static final String EDICAO_COMPUTADOR_VIEW = "/edicoes/EditarComputador";
	private static final String EDICAO_RECURSO = "/edicoes/EditarComputadorRecurso";
	private static final String EDICAO_USUARIO = "/edicoes/EditarComputadorUsuario";
	private static final String EDICAO_BACKUP = "/edicoes/EditarComputadorBackup";
	private static final String EDICAO_FORMATACAO = "/edicoes/EditarComputadorFormatacao";
	private static final String EDICAO_MENU = "/edicoes/PopUPComputador";
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
	@Autowired
	private LicencasOffice licencasOffice;
	

	
	
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Computador());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Computador computador, @RequestParam Integer usuario_id_usuario, @RequestParam Integer usuario_sec, @RequestParam Integer recurso_caixa, @RequestParam Integer recurso_monitor1, @RequestParam Integer recurso_monitor2, @RequestParam Integer recurso_mouse, @RequestParam Integer recurso_teclado, Errors errors, RedirectAttributes attributes)
	{
		Usuario user = new Usuario();
		Usuario user2 = new Usuario();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
	//	System.out.println(">>>>>> " + usuario_id_usuario);
	
		if(usuario_id_usuario != null)
		{
			user = usuarios.findOne(usuario_id_usuario);
			computador.setUsuario(user);
			
		}
		if(usuario_sec != null)
		{
			user2 = usuarios.findOne(usuario_sec);
			computador.setUsuario_sec(user2);
			
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
		if(recurso_monitor2 != null)
		{
			Monitor monitor = monitores.findOne(recurso_monitor2);
			monitor.setComputador(computador);
			monitores.save(monitor);
			computador.setRecurso_monitor2(monitor);
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
		Computador pc = computadores.findOne(computador.getId_computador());
		if(usuario_id_usuario != null) {
			user.setComputador(pc);
			usuarios.save(user);
		}
		if(usuario_sec != null)
		{
			user2.setComputador(pc);
			usuarios.save(user2);
		}
		
		attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		return "redirect:/gereteci/computadores/novo";
		
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
		return "redirect:/gereteci/computadores/novo";
		
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
		if(computador.getImpressoras() != null) {
			pc.setImpressoras(computador.getImpressoras());
		}
		
		if(computador.getRecurso_teclado() != null) {
			//System.out.println(">>>>>>>>>>>> "+ pc.getRecurso_teclado().getId_recurso());
			if(pc.getRecurso_teclado() != null) {
				Teclado t1 = teclados.findOne(pc.getRecurso_teclado().getId_recurso());
				t1.setComputador(null);
				teclados.save(t1);
			}
			Teclado t2 = teclados.findOne(computador.getRecurso_teclado().getId_recurso());
			pc.setRecurso_teclado(t2);
			t2.setComputador(pc);
			teclados.save(t2); //problema aqui
		}
		if(computador.getRecurso_monitor1() != null) {
			if(pc.getRecurso_monitor1() != null) {
				Monitor m1 = monitores.findOne(pc.getRecurso_monitor1().getId_recurso());
				m1.setComputador(null);
				monitores.save(m1);
			}
			Monitor m2 = monitores.findOne(computador.getRecurso_monitor1().getId_recurso());
			pc.setRecurso_monitor1(computador.getRecurso_monitor1());
			m2.setComputador(pc);
			monitores.save(m2);
		}
		if(computador.getRecurso_monitor2() != null) {
			if(pc.getRecurso_monitor2() != null) {
				Monitor m2 = monitores.findOne(pc.getRecurso_monitor2().getId_recurso());
				m2.setComputador(null);
				monitores.save(m2);
			}
			Monitor m3 = monitores.findOne(computador.getRecurso_monitor2().getId_recurso());
			pc.setRecurso_monitor2(computador.getRecurso_monitor2());
			m3.setComputador(pc);
			monitores.save(m3);
		}
		if(computador.getRecurso_mouse() != null) {
			if(pc.getRecurso_mouse() != null) {
				Mouse mo1 = mouses.findOne(pc.getRecurso_mouse().getId_recurso());
				mo1.setComputador(null);
				mouses.save(mo1);
			}
			Mouse mo2 = mouses.findOne(computador.getRecurso_mouse().getId_recurso());
			pc.setRecurso_mouse(computador.getRecurso_mouse());
			mo2.setComputador(pc);
			mouses.save(mo2);
		}
		if(computador.getRecurso_caixa() != null)
		{
			if(pc.getRecurso_caixa() != null) {
				CaixaDeSom c1 = caixas.findOne(pc.getRecurso_caixa().getId_recurso());
				c1.setComputador(null);
				caixas.save(c1);
			}
			CaixaDeSom c2 = caixas.findOne(computador.getRecurso_caixa().getId_recurso());
			pc.setRecurso_caixa(computador.getRecurso_caixa());
			c2.setComputador(pc);
			caixas.save(c2);
			
		}
		computadores.save(pc);
		attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		return "redirect:/gereteci/computadores/novo";
		
	}
	
	@RequestMapping(value="/{id_computador}/salvar3",method = RequestMethod.POST)
	public String salvar3(@Validated Computador computador, @RequestParam Integer usuario_id_usuario, @RequestParam Integer usuario_sec, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		Computador pc = computadores.findOne(computador.getId_computador());
		Usuario user = null;
		Usuario user2 = null;
		Usuario user3 = null;
		Usuario user4 = null;
		
		
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
		if(usuario_id_usuario != null) {
			user = usuarios.findOne(usuario_id_usuario); //peguei no banco de dados!
			user.setComputador(pc);
			usuarios.save(user);
	     	
		}
	
		if (usuario_sec != null) {
			user2 = usuarios.findOne(usuario_sec);
			user2.setComputador(pc);
			
			usuarios.save(user2);
		}
		
		if(pc.getUsuario() != null) {
			user3 = usuarios.findOne(pc.getUsuario().getId_usuario());
			user3.setComputador(null);
			usuarios.save(user3);
	}
			
		if(pc.getUsuario_sec() != null) {
			user4 = usuarios.findOne(pc.getUsuario_sec().getId_usuario());
			user4.setComputador(null);
			usuarios.save(user4);
		}
		
		
		pc.setUsuario(user);
		pc.setUsuario_sec(user2);
		
		
		
		computadores.save(pc);
		
		
		attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		return "redirect:/gereteci/computadores/novo";
		
	}
	
	@RequestMapping("/{id_computador}/editar1")
	
	public ModelAndView editar1(@PathVariable("id_computador") Computador computador)
	{
		ModelAndView mv = new ModelAndView(EDICAO_COMPUTADOR_VIEW);
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
		ModelAndView mv = new ModelAndView(EDICAO_RECURSO);
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
		ModelAndView mv = new ModelAndView(EDICAO_USUARIO);
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
		ModelAndView mv = new ModelAndView(EDICAO_BACKUP);
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
		ModelAndView mv = new ModelAndView(EDICAO_FORMATACAO);
	//	System.out.println(">>>>>> " + usuario_id_usuario);
	
	
		//computadores.save(computador);
		//attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		//return "redirect:/computadores/novo";
		
		mv.addObject("pc", computador);
		mv.addObject(computador);
		
		return mv;
	}
	
	/*
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
	*/
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView pesquisar(String busca, String ip, String nome, String setor, String status) throws ParseException
	{
		//List<ServicoManutencao> todosServicosManutencao = servicos.findAll();
		//Usuario user = usuarios.findOne(14);
		if(ip != null) {
			if(busca != null && ip.equals("on")) {
				List<Computador> todosComputadores = computadores.findByIpContaining(busca);
				ModelAndView mv = new ModelAndView("/pesquisa/PesquisaComputadores");
				mv.addObject("computadores", todosComputadores);
				return mv;
			}
		}
		   List<Computador> todosComputadores = computadores.findAll();
			ModelAndView mv = new ModelAndView("/pesquisa/PesquisaComputadores");
			mv.addObject("computadores", todosComputadores);
	    
		return mv;
	}
	
	
	@RequestMapping("/{id_computador}/edicaomenu")

	public ModelAndView edicaomenu(@PathVariable("id_computador") Computador computador)
	{
		//ObjectMapper mapper = new ObjectMapper();
		
		//System.out.println(">>>>>>> codigo recebido: " + computador.getId_computador());
		//System.out.println(">>>>>>> Codigo de usuario recebido: " + recursos.getDescricao());
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(EDICAO_MENU);	
		mv.addObject("pc", computador);
		mv.addObject(computador);
		
		return mv;
	}
	
	@RequestMapping("{id_computador}")

	public ModelAndView edicao(@PathVariable("id_computador") Computador computador)
	{
		//ObjectMapper mapper = new ObjectMapper();
		
		//System.out.println(">>>>>>> codigo recebido: " + computador.getId_computador());
		//System.out.println(">>>>>>> Codigo de usuario recebido: " + recursos.getDescricao());
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(EDICAO_COMPUTADOR_VIEW);	
		mv.addObject("pc", computador);
		mv.addObject(computador);
		
		return mv;
	}
	
	

	@RequestMapping(value="{id_computador}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_computador, RedirectAttributes attributes)
	{
		Computador pc = computadores.findOne(id_computador);
		if(pc.getUsuario() != null) {
			Usuario user = usuarios.findOne(pc.getUsuario().getId_usuario());
			if(user.getComputador() != null)
			{
				user.setComputador(null); 
			}
			pc.setUsuario(null);
			usuarios.save(user);
		}
		if(pc.getUsuario_sec() != null)
		{
			Usuario user2 = usuarios.findOne(pc.getUsuario_sec().getId_usuario());
			if(user2.getComputador() != null)
			{
				user2.setComputador(null); 
			}
			pc.setUsuario_sec(null);
			usuarios.save(user2);
		}
		if(pc.getRecurso_caixa() != null) {
			CaixaDeSom cs = caixas.findOne(pc.getRecurso_caixa().getId_recurso());
			if(cs.getComputador() != null)
			{
				cs.setComputador(null); 
			}
			pc.setRecurso_caixa(null);
			caixas.save(cs);
		}
		if(pc.getRecurso_teclado() != null) {
			Teclado teclado = teclados.findOne(pc.getRecurso_teclado().getId_recurso());
			if(teclado.getComputador() != null)
			{
				teclado.setComputador(null); 
			}
			pc.setRecurso_teclado(null);
			teclados.save(teclado);
		}
		if(pc.getRecurso_mouse() != null) {
			Mouse mouse = mouses.findOne(pc.getRecurso_mouse().getId_recurso());
			if(mouse.getComputador() != null)
			{
				mouse.setComputador(null); 
			}
			pc.setRecurso_mouse(null);
			mouses.save(mouse);
			
		}
		if(pc.getRecurso_monitor1() != null) {
			Monitor monitor = monitores.findOne(pc.getRecurso_monitor1().getId_recurso());
			if(monitor.getComputador() != null)
			{
				monitor.setComputador(null); 
			}
			pc.setRecurso_monitor1(null);
			monitores.save(monitor);
		}
		if(pc.getRecurso_monitor2() != null) {
			Monitor monitor = monitores.findOne(pc.getRecurso_monitor2().getId_recurso());
			if(monitor.getComputador() != null)
			{
				monitor.setComputador(null); 
			}
			pc.setRecurso_monitor2(null);
		}
		List<Impressora> print = impressoras.findAll();

		Iterator it = print.iterator();
		while(it.hasNext())
		{
			Impressora impressora = (Impressora) it.next();
			Impressora impressora2 = impressoras.findOne(impressora.getId_impressora());
			List<Computador> pcs = impressora2.getComputadores();
			Iterator it2 = pcs.iterator();
			while(it2.hasNext())
			{
				Computador pc2 = (Computador) it2.next();
				if(pc2.getId_computador().equals(id_computador))
				{
					pcs.remove(id_computador);
				}
			}
			impressoras.save(impressora2);
		}
		List<LicencaOffice> lo = licencasOffice.findAll();

		Iterator itlo = lo.iterator();
		while(itlo.hasNext())
		{
			LicencaOffice lo2 = (LicencaOffice) itlo.next();
			LicencaOffice lo3 = licencasOffice.findOne(lo2.getId_licencaoffice());
			List<Computador> pcs = lo3.getComputadores();
			Iterator itlo2 = pcs.iterator();
			while(itlo2.hasNext())
			{
				Computador pc2 = (Computador) itlo2.next();
				if(pc2.getId_computador().equals(id_computador))
				{
					pcs.remove(id_computador);
				}
			}
			licencasOffice.save(lo3);
		}
		
		//computadores.save(pc);
		computadores.delete(pc.getId_computador());
		attributes.addFlashAttribute("mensagem", "Computador excluido com sucesso com sucesso!");	
		return "redirect:/gereteci/computadores";
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
	public List<Status> todosStatusComputador() {
		return Arrays.asList(Status.values());
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
					
			}
		}
		Comparator<Usuario> comparator = new Comparator<Usuario>() {
		    public int compare(Usuario u1, Usuario u2) {
		    	if(u2.getNome().compareTo(u1.getNome()) < 0) {
					return 0;
				}
		    	if(u2.getNome().compareTo(u1.getNome()) > 0) {
					return -1;
				}
				
				return 0;
		    }
		};
		Collections.sort(todosUsuariosSemComputador, comparator);
		
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
	
	
	@ModelAttribute("todosMonitoresPrimariosDisponiveis")
	public List<Monitor> todosMonitoresPrimariosDisponiveis()
	{
		
		
		List<Monitor> todosMonitores= monitores.findAll();
		List<Monitor> todosMonitoresDisponiveis = new ArrayList<Monitor>();
		Iterator it = todosMonitores.iterator();
		while(it.hasNext())
		{
			Monitor obj = (Monitor) it.next();
			
			
			if(obj.getComputador() == null && obj.getCategoria_monitor().getCategoria().equals("Primário")) {
				//System.out.println(obj.getId_recurso() + obj.getPolegadas());
				todosMonitoresDisponiveis.add(obj);
			}
		}
		return todosMonitoresDisponiveis;
	}
	
	@ModelAttribute("todosMonitoresSecundariosDisponiveis")
	public List<Monitor> todosMonitoresSecundariosDisponiveis()
	{
		
		
		List<Monitor> todosMonitores= monitores.findAll();
		List<Monitor> todosMonitoresDisponiveis = new ArrayList<Monitor>();
		Iterator it = todosMonitores.iterator();
		while(it.hasNext())
		{
			Monitor obj = (Monitor) it.next();
			
			
			if(obj.getComputador() == null && obj.getCategoria_monitor().getCategoria().equals("Secundário")) {
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
		computador.setStatus(Status.manutencao);
		computadores.save(computador);
		return Status.manutencao.getStatus();
	}
	
	@RequestMapping(value="/{id_computador}/baixa", method=RequestMethod.PUT)
	public @ResponseBody String baixa(@PathVariable Integer id_computador)
	{
		//Isso aqui vai para camada de serviço
		Computador computador = computadores.findOne(id_computador);
		computador.setStatus(Status.com_defeito_para);
		computadores.save(computador);
		return Status.com_defeito_para.getStatus();
	}
	
	@RequestMapping(value="/{id_computador}/consertado", method=RequestMethod.PUT)
	public @ResponseBody String consertado(@PathVariable Integer id_computador)
	{
		//Isso aqui vai para camada de serviço
		Computador computador = computadores.findOne(id_computador);
		computador.setStatus(Status.funcionando);
		computadores.save(computador);
		return Status.funcionando.getStatus();
	}
	
	@ModelAttribute("home_teci")
	public boolean homeTECI() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_TECI") || AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_CADASTRAR_SERVICO");
	}
}
