package com.teci.gereteci.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Servico.ServicoEmail;
import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.ServicoRede;
import com.teci.gereteci.model.Servico.ServicoTelefone;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.ServicosEmail;
import com.teci.gereteci.repository.ServicosInternet;
import com.teci.gereteci.repository.ServicosManutencao;
import com.teci.gereteci.repository.ServicosRede;
import com.teci.gereteci.repository.ServicosTelefone;
import com.teci.gereteci.repository.Usuarios;

@Controller






public class RelatorioIndividualController {
	
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private ServicosManutencao smanutencao;
	@Autowired
	private ServicosInternet sinternet;
	@Autowired
	private ServicosRede sredes;
	@Autowired
	private ServicosTelefone stelefone;
	@Autowired
	private ServicosEmail semail;
	
	
	
	private static final String GERAR_RELATORIO = "/relatorios/GerarRelatorioIndividual"; 
	private static final String RELATORIO_INDIVIDUAL = "/relatorios/RelatorioIndividual";
	
	@RequestMapping(value="/gereteci/relatorio_individual", method= RequestMethod.GET)
	public ModelAndView relatorio(Integer user, String tServico, String mes, String ano)
	{
		ModelAndView mv = new ModelAndView(RELATORIO_INDIVIDUAL);
		Usuario u = usuarios.findOne(user);
		Computador c = u.getComputador();
		mv.addObject("usuario", u);
		mv.addObject("computador", c);
		if(c != null) {
			mv.addObject("monitor", c.getRecurso_monitor1());
			mv.addObject("mouse", c.getRecurso_mouse());
			mv.addObject("teclado", c.getRecurso_teclado());
			mv.addObject("caixas", c.getRecurso_caixa());
		}
		mv.addObject("servicos_manutencao", todosManutencaoUsuario(user));
		mv.addObject("servicos_internet", todosInternetUsuario(user));
		mv.addObject("servicos_rede", todosRedesUsuario(user));
		mv.addObject("servicos_email", todosEmailUsuario(user));
		mv.addObject("servicos_telefone", todosTelefoneUsuario(user));
		return mv;
	}
	
	@RequestMapping(value="/gereteci/gerar_relatorio_individual", method= RequestMethod.GET)
	public ModelAndView gerarRelatorio()
	{
		ModelAndView mv = new ModelAndView(GERAR_RELATORIO);
		return mv;
	}
	
	@ModelAttribute("todosUsuarios")
	public List <Usuario> todosUsuarios()
	{
		return usuarios.findAll();
	}
	
	public List<ServicoManutencao> todosManutencaoUsuario(Integer usuario)
	{
		List<ServicoManutencao> todosManutencao = smanutencao.findAll();
		List<ServicoManutencao> todosMUsuario = new ArrayList<ServicoManutencao>();
		
		for(ServicoManutencao n: todosManutencao)
		{
			if(n.getSolicitado() != null) {
				if(n.getSolicitado().getId_usuario() == usuario)
				{
					todosMUsuario.add(n);
				}
			}
		}
		
		return todosMUsuario;
	}
	
	public List<ServicoInternet> todosInternetUsuario(Integer usuario)
	{
		List<ServicoInternet> todosInternet = sinternet.findAll();
		List<ServicoInternet> todosIUsuario = new ArrayList<ServicoInternet>();
		
		for(ServicoInternet i: todosInternet)
		{
			if(i.getSolicitado() != null) {
				if(i.getSolicitado().getId_usuario() == usuario)
				{
					todosIUsuario.add(i);
				}
			}
		}
		
		return todosIUsuario;
	}
	
	public List<ServicoRede> todosRedesUsuario(Integer usuario)
	{
		List<ServicoRede> todosRede= sredes.findAll();
		List<ServicoRede> todosRUsuario = new ArrayList<ServicoRede>();
		
		for(ServicoRede r: todosRede)
		{
			if(r.getSolicitado() != null) {
				if(r.getSolicitado().getId_usuario() == usuario)
				{
					todosRUsuario.add(r);
				}
			}
		}
		
		return todosRUsuario;
	}
	
	public List<ServicoEmail> todosEmailUsuario(Integer usuario)
	{
		List<ServicoEmail> todosEmail= semail.findAll();
		List<ServicoEmail> todosEUsuario = new ArrayList<ServicoEmail>();
		
		for(ServicoEmail e: todosEmail)
		{
			if(e.getSolicitado() != null) {
				if(e.getSolicitado().getId_usuario() == usuario)
				{
					todosEUsuario.add(e);
				}
			}
		}
		return todosEUsuario;
	}
	
	public List<ServicoTelefone> todosTelefoneUsuario(Integer usuario)
	{
		List<ServicoTelefone> todosTelefone= stelefone.findAll();
		List<ServicoTelefone> todosTUsuario = new ArrayList<ServicoTelefone>();
		
		for(ServicoTelefone t: todosTelefone)
		{
			if(t.getSolicitado() != null) {
				if(t.getSolicitado().getId_usuario() == usuario)
				{
					todosTUsuario.add(t);
				}
			}
		}
		return todosTUsuario;
	}
	
	
	
	
	
	
}
