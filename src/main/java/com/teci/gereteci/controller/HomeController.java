package com.teci.gereteci.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teci.gereteci.repository.*;
import com.teci.gereteci.model.*;
import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Servico.ServicoEmail;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.security.AppUserDetailsService;

@Controller

public class HomeController {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private ServicosManutencao manutencao;
	
	@Autowired
	private ServicosInternet internet;
	
	@Autowired
	private ServicosRede redes;
	
	@Autowired
	private ServicosEmail email;
	
	@Autowired
	private ServicosTelefone telefone;
	
	@Autowired
	private Servicos servicos;

	private static final String TESTE_PATH = "/mail/ServicosAA";
	
	
	@RequestMapping("/gereteci")
	public String index(HttpServletRequest request)
	{

		
		return "index";
	}
	
	@RequestMapping("/gereteci/teste")
	public ModelAndView teste()
	{
		ModelAndView mv = new ModelAndView(TESTE_PATH);
		return mv;
	}
	
	@ModelAttribute("home_teci")
	public boolean homeTECI() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_TECI") || AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_CADASTRAR_SERVICO");
	}
	
	@ModelAttribute("home_comum")
	public boolean homeComum() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_COMUM");
	}
	
	@ModelAttribute("servicos_total")
	public Integer total_s()
	{
		List<Servico> srv = servicos.findAll(); 
		List<Servico> listaLimitada = new ArrayList<Servico>();
		for(Servico s :srv)
		{
			String user = AppUserDetailsService.cusuario.getUsername();
			System.out.println(s.getAtendente().getNome());
			if(s.getAtendente().getMatricula().equals(user)) {
					listaLimitada.add(s);
			}
			//System.out.println(s.getCategoria());
			//ServicoEmail se = (ServicoEmail) s;
			//System.out.println(se.getConta());
		}
		return	listaLimitada.size();			// AppUserDetailsService.cusuario.getUsername()
	}
	
	@ModelAttribute("servicos_abertos")
	public Integer total_abertos()
	{
		List<Servico> srv = servicos.findAll(); 
		List<Servico> listaLimitada = new ArrayList<Servico>();
		for(Servico s :srv)
		{
			String user = AppUserDetailsService.cusuario.getUsername();
			if(s.getAtendente().getMatricula().equals(user)) {
					if(s.getStatus().getDescricao().toString().equals("Aberto"))
						listaLimitada.add(s);
			}
			//System.out.println(s.getCategoria());
			//ServicoEmail se = (ServicoEmail) s;
			//System.out.println(se.getConta());
		}
		return	listaLimitada.size();			// AppUserDetailsService.cusuario.getUsername()
	}
	
	@ModelAttribute("ultimos_servicos_abertos")
	public List<Servico> ultimos10_abertos()
	{
		List<Servico> srv = servicos.findAll(); 
		List<Servico> listaLimitada = new ArrayList<Servico>();

		 Comparator<Servico> cmp = new Comparator<Servico>() {
		        public int compare(Servico s1, Servico s2) {
		          return s2.getData_ocorrencia().compareTo(s1.getData_ocorrencia());
		        }
	};
	
		for(Servico s :srv)
		{
			String user = AppUserDetailsService.cusuario.getUsername();
			if(s.getAtendente().getMatricula().equals(user)) {
					if(s.getStatus().getDescricao().toString().equals("Aberto"))
						listaLimitada.add(s);
			}
			//System.out.println(s.getCategoria());
			//ServicoEmail se = (ServicoEmail) s;
			//System.out.println(se.getConta());
		}
		listaLimitada.sort(cmp);
		if (listaLimitada.size() > 10)
			return	listaLimitada.subList(0, 10);		// AppUserDetailsService.cusuario.getUsername()
		return listaLimitada;
	}
	
	@ModelAttribute("servicos_andamento")
	public Integer total_andamentos()
	{
		List<Servico> srv = servicos.findAll(); 
		List<Servico> listaLimitada = new ArrayList<Servico>();
		for(Servico s :srv)
		{
			String user = AppUserDetailsService.cusuario.getUsername();
			if(s.getAtendente().getMatricula().equals(user)) {
					if(s.getStatus().getDescricao().toString().equals("Em andamento"))
						listaLimitada.add(s);
			}
			//System.out.println(s.getCategoria());
			//ServicoEmail se = (ServicoEmail) s;
			//System.out.println(se.getConta());
		}
		return	listaLimitada.size();			// AppUserDetailsService.cusuario.getUsername()
	}
	
	@ModelAttribute("ultimos_servicos_andamento")
	public List<Servico> ultimos10_andamento()
	{
		List<Servico> srv = servicos.findAll(); 
		List<Servico> listaLimitada = new ArrayList<Servico>();
		//List<Servico> listaLimitada2 = new ArrayList<Servico>();
		
		 Comparator<Servico> cmp = new Comparator<Servico>() {
		        public int compare(Servico s1, Servico s2) {
		          return s2.getData_ocorrencia().compareTo(s1.getData_ocorrencia());
		        }
	};
	
	
		for(Servico s :srv)
		{
			String user = AppUserDetailsService.cusuario.getUsername();
			if(s.getAtendente().getMatricula().equals(user)) {
					if(s.getStatus().getDescricao().toString().equals("Em andamento"))
						listaLimitada.add(s);
			}
			//System.out.println(s.getCategoria());
			//ServicoEmail se = (ServicoEmail) s;
			//System.out.println(se.getConta());
		}
		listaLimitada.sort(cmp);
		if (listaLimitada.size() > 10)
			return	listaLimitada.subList(0, 10);
		return listaLimitada; // AppUserDetailsService.cusuario.getUsername()
	}
	
	
	@ModelAttribute("servicos_concluidos")
	public Integer total_concluidos()
	{
		List<Servico> srv = servicos.findAll(); 
		List<Servico> listaLimitada = new ArrayList<Servico>();
		for(Servico s :srv)
		{
			String user = AppUserDetailsService.cusuario.getUsername();
			if(s.getAtendente().getMatricula().equals(user)) {
					if(s.getStatus().getDescricao().toString().equals("Fechado"))
						listaLimitada.add(s);
			}
			//System.out.println(s.getCategoria());
			//ServicoEmail se = (ServicoEmail) s;
			//System.out.println(se.getConta());
		}
		return	listaLimitada.size();			// AppUserDetailsService.cusuario.getUsername()
	}
	
	@ModelAttribute("ultimos_servicos_concluidos")
	public List<Servico> ultimos10_concluidos()
	{
		List<Servico> srv = servicos.findAll(); 
		List<Servico> listaLimitada = new ArrayList<Servico>();
		for(Servico s :srv)
		{
			String user = AppUserDetailsService.cusuario.getUsername();
			if(s.getAtendente().getMatricula().equals(user)) {
					if(s.getStatus().getDescricao().toString().equals("Fechado"))
						listaLimitada.add(s);
			}
			//System.out.println(s.getCategoria());
			//ServicoEmail se = (ServicoEmail) s;
			//System.out.println(se.getConta());
		}
		Comparator<Servico> cmp = new Comparator<Servico>() {
	        public int compare(Servico s1, Servico s2) {
	          return s2.getData_ocorrencia().compareTo(s1.getData_ocorrencia());
	        }
};

		listaLimitada.sort(cmp);
		
		if (listaLimitada.size() > 10)
			return	listaLimitada.subList(0, 10);
		return listaLimitada;		// AppUserDetailsService.cusuario.getUsername()
	}
	
	@ModelAttribute("nome_user")
	public String nome_us()
	{
		Usuario user = usuarios.findByMatricula(AppUserDetailsService.cusuario.getUsername());
		return user.getNome();
	}
	
	@ModelAttribute("ultimos_andamento")
	public List<Servico> ultimos_andamento()
	{
		List<Servico> srv = servicos.findAll(); 
		List<Servico> listaLimitada = new ArrayList<Servico>();
		
		 Comparator<Servico> cmp = new Comparator<Servico>() {
		        public int compare(Servico s1, Servico s2) {
		          return s2.getData_ocorrencia().compareTo(s1.getData_ocorrencia());
		        }
	};
	
	
		for(Servico s :srv)
		{
			if(s.getStatus().getDescricao().toString().equals("Em andamento"))
			listaLimitada.add(s);
			
		}
		listaLimitada.sort(cmp);
		return listaLimitada; 
	}
	
	@ModelAttribute("ultimos_abertos")
	public List<Servico> ultimos_abertos()
	{
		List<Servico> srv = servicos.findAll(); 
		List<Servico> listaLimitada = new ArrayList<Servico>();
		
		 Comparator<Servico> cmp = new Comparator<Servico>() {
		        public int compare(Servico s1, Servico s2) {
		          return s2.getData_ocorrencia().compareTo(s1.getData_ocorrencia());
		        }
	};
	
	
		for(Servico s :srv)
		{
			if(s.getStatus().getDescricao().toString().equals("Aberto"))
			listaLimitada.add(s);
			
		}
		listaLimitada.sort(cmp);
		return listaLimitada; 
	}
	
	
	
}
