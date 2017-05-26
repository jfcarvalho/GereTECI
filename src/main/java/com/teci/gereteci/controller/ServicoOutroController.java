package com.teci.gereteci.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
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

import com.teci.gereteci.Mailer;
import com.teci.gereteci.model.Computador.Memoria;
import com.teci.gereteci.model.Computador.Status;
import com.teci.gereteci.model.Servico.DescricaoProntaOutro;
import com.teci.gereteci.model.Servico.DescricaoProntaInternet;
import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Servico.ServicoOutro;
import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.ServicosEmail;
import com.teci.gereteci.repository.ServicosInternet;
import com.teci.gereteci.repository.ServicosOutros;
import com.teci.gereteci.repository.ServicosEmail;
import com.teci.gereteci.repository.Usuarios;
import com.teci.gereteci.security.AppUserDetailsService;


@Controller
@RequestMapping("/gereteci/servicosoutro")
public class ServicoOutroController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroServicoOutro"; 
	private static final String CADASTRO_VIEW2 = "/edicoes/EdicaoServicoOutro"; 
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private ServicosOutros servicos;
	@Autowired
	private ServicosOutros servicosAtendente;
	@Autowired
	private Mailer mailer;
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("servico", new ServicoOutro());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated ServicoOutro servicoOutro, @RequestParam Integer usuario_id_usuario, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroServicoOutrot";
		}
		String array[] = new String[3];
		String protocolo = "CTB";
		long numero = servicos.count()+1;
		Date data = new Date(System.currentTimeMillis());  
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 
		//System.out.print(formatarDate.format(data).toString());
		
		array = formatarDate.format(data).toString().split("-");
		
		protocolo = protocolo + "O" + array[0] + array[1] + "-" + numero;
		
		if(usuario_id_usuario != null) { 
			Usuario user = usuarios.findOne(usuario_id_usuario); 
			servicoOutro.setSolicitado(user);
		}
		servicoOutro.setProtocolo(protocolo);
		//servicoEmail.setSolicitado(user);
		//servicoEmail.setProtocolo(protocolo);
		
		//servicoEmail.setSolicitado(user);
		servicos.save(servicoOutro);
		attributes.addFlashAttribute("mensagem", "Serviço salvo com sucesso!");	
		//mailer.enviar_servico(servicoOutro.getSolicitado(), servicoOutro.getAtendente(), servicoOutro.getSolicitado().getSetor().getSigla(),(Servico) servicoOutro,"jfcarvalho@ctb.ba.gov.br");
		
		return "redirect:/gereteci/servicosoutro/novo";
	
	}
	@RequestMapping(value="/{id_servico}/salvar1",method = RequestMethod.POST)
	public String salvar1(@Validated ServicoOutro servicoOutro, @RequestParam Integer usuario_id_usuario, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW2);
		if(errors.hasErrors())
		{
			return "cadastroServicoOutro";
		}
		//ServicoEmail servico = servicos.findOne(servicoEmail.getId_servico());
		//servicoEmail.setProtocolo(servico.getProtocolo()); 
		//System.out.print(formatarDate.format(data).toString());
		
		
		if(usuario_id_usuario != null)
		{
			Usuario user = usuarios.findOne(usuario_id_usuario);
			servicoOutro.setSolicitado(user);
		}
		servicos.save(servicoOutro);
		attributes.addFlashAttribute("mensagem", "Serviço salvo com sucesso!");	
		return "redirect:/gereteci/servicosmanutencao/novo";
	
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView pesquisar(String busca, String atendenteop, String solicitante, String setor, String status, String data_ocorrencia, String data_encerramento, String descricao_problema) throws ParseException
	{
		//List<ServicoEmail> todosServicosEmail = servicos.findAll();
		//Usuario user = usuarios.findOne(14);
		if(atendenteop != null) {
			if(busca != null && atendenteop.equals("on")) {
				System.out.println(busca);
				System.out.println(busca);
				List<Usuario> teste = usuarios.findByNomeContaining(busca);
				Usuario teste2 = teste.get(0);
				System.out.println(teste2.getNome());
				List<ServicoOutro> todosServicosOutro = servicosAtendente.findByAtendente(teste2);
				//List<Computador> todosComputadores = computadores.findAll();
				List<Usuario> todosUsuarios = usuarios.findAll();
				ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosOutro");
			    mv.addObject("servicos", todosServicosOutro);
				mv.addObject("usuarios", todosUsuarios);
				return mv;
			}
		}
		else  
		if(solicitante != null) {
			if(busca != null && solicitante.equals("on"))
		{
			List<Usuario> teste = usuarios.findByNomeContaining(busca);
			Usuario teste2 = teste.get(0);
			System.out.println(teste2.getNome());
			List<ServicoOutro> todosServicosOutro = servicosAtendente.findBySolicitado(teste2);
			//List<Computador> todosComputadores = computadores.findAll();
			List<Usuario> todosUsuarios = usuarios.findAll();
			ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosOutro");
		    mv.addObject("servicos", todosServicosOutro);
			mv.addObject("usuarios", todosUsuarios);
			return mv;
		}
	}
		/*else
			if(status != null)
			{
				if(busca != null && status.equals("on")) 
				{
					StatusServico sServico; 
					if(busca.equals("Fechado"))
					{
						sServico = StatusServico.fechado;
					}
						else if(busca.equals("Em andamento"))
						{
							sServico = StatusServico.em_andamento;
						}
						else{
							sServico = StatusServico.aberto;
						}
					List<ServicoEmail> todosServicosEmail = servicosAtendente.findByStatus(sServico);
					//List<Computador> todosComputadores = computadores.findAll();
					ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosEmail");
				    mv.addObject("servicos", todosServicosEmail);
					return mv;
				}
			}
			*/
			else
				if(data_ocorrencia != null)
				{
					if(busca != null && data_ocorrencia.equals("on")) 
					{	
						
						List<ServicoOutro> servicosOutro = servicos.findAll();
						List<ServicoOutro> servicosOutro2 = new ArrayList<ServicoOutro>();
						Iterator it = servicosOutro.iterator();
						while(it.hasNext())
						{
							ServicoOutro serv = (ServicoOutro) it.next();
							if(serv.getData_ocorrencia().toString().contains(busca))
							{
								servicosOutro2.add(serv);
							}
						}	
						//List<Computador> todosComputadores = computadores.findAll();
						ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosOutro");
					    mv.addObject("servicos", servicosOutro2);
						return mv;
					}
				}
				else
					if(data_encerramento != null)
					{
						if(busca != null && data_encerramento.equals("on")) 
						{	
							
							List<ServicoOutro> servicosOutro = servicos.findAll();
							List<ServicoOutro> servicosOutro2 = new ArrayList<ServicoOutro>();
							Iterator it = servicosOutro.iterator();
							while(it.hasNext())
							{
								ServicoOutro serv = (ServicoOutro) it.next();
								if(serv.getData_ocorrencia().toString().contains(busca))
								{
									servicosOutro2.add(serv);
								}
							}	
							//List<Computador> todosComputadores = computadores.findAll();
							ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosOutro");
						    mv.addObject("servicos", servicosOutro2);
							return mv;
						}
					}
					else
						if(setor != null)
						{
							if(busca != null && setor.equals("on")) 
							{	
								List<ServicoOutro> servicosOutro = servicos.findAll();
								List<ServicoOutro> servicosOutro2 = new ArrayList<ServicoOutro>();
								Iterator it = servicosOutro.iterator();
								while(it.hasNext())
								{
									ServicoOutro serv = (ServicoOutro) it.next();
									if(serv.getSolicitado() != null && serv.getSolicitado().getSetor() != null) {
										if(serv.getSolicitado().getSetor().getSigla().contains(busca))
										{
											servicosOutro2.add(serv);
										}
									}	
								}
								ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosOutro");
							    mv.addObject("servicos", servicosOutro2);
								return mv;
							}
						}
						else
							if(descricao_problema != null)
							{
								if(busca != null && descricao_problema.equals("on")) 
								{	
									List<ServicoOutro> servicosOutro = servicos.findAll();
									List<ServicoOutro> servicosOutro2 = new ArrayList<ServicoOutro>();
									Iterator it = servicosOutro.iterator();
									while(it.hasNext())
									{
										ServicoOutro serv = (ServicoOutro) it.next();
										if(serv.getDescricao_problema().contains(busca)) {
												servicosOutro2.add(serv);
										}	
									}
									ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosOutro");
								    mv.addObject("servicos", servicosOutro2);
									return mv;
								}
							}
		   List<ServicoOutro> todosServicosOutro = servicos.findAll();
		   List<Usuario> todosUsuarios = usuarios.findAll();
			ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosOutro");
			Collections.reverse(todosServicosOutro);
			if(todosServicosOutro.size() > 10) {
			
			List<ServicoOutro> novaListaLimitada = new ArrayList<ServicoOutro>();
			int contador = 0; 
			for (ServicoOutro servico : todosServicosOutro)
			{
				if(contador < 10 ) {
					novaListaLimitada.add(servico);
					contador++;
				}
				else {break;}
			}
			//if(todosServicosManutencao.size() > 10) {
				//mv.addObject("servicos", todosServicosManutencao.subList(todosServicosManutencao.size()-10 ,todosServicosManutencao.size()));
			//}
			//else { mv.addObject("servicos", todosServicosManutencao); }
				mv.addObject("servicos", novaListaLimitada);
			}
			else {mv.addObject("servicos", todosServicosOutro); }
			mv.addObject("usuarios", todosUsuarios);
			
		return mv;
	}

	@RequestMapping("{id_servico}")
	public ModelAndView edicao(@PathVariable("id_servico") ServicoOutro servicoOutro)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		Usuario solicitante = servicoOutro.getSolicitado();
		Usuario usuario_atendente = servicoOutro.getAtendente();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("servico", servicoOutro);
		mv.addObject(servicoOutro);
		return mv;
	}
	
	@RequestMapping("/{id_servico}/editar1")
	public ModelAndView edicao1(@PathVariable("id_servico") ServicoOutro servicoOutro)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		Usuario solicitante = servicoOutro.getSolicitado();
		Usuario usuario_atendente = servicoOutro.getAtendente();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW2);
		mv.addObject("servico", servicoOutro);
		mv.addObject(servicoOutro);
		return mv;
	}
	
	@RequestMapping(value="{id_servico}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_servico, RedirectAttributes attributes)
	{
		servicos.delete(id_servico);
		attributes.addFlashAttribute("mensagem", "Serivço excluído com sucesso com sucesso!");	
		return "redirect:/servicosoutro";
	}
	@ModelAttribute("todosStatusServico")
	public List<StatusServico> todosStatusServico() {
		return Arrays.asList(StatusServico.values());
	}
	
	@ModelAttribute("todasDescricoesServicos")
	public List<DescricaoProntaOutro> todasDescricoesServico() {
		return Arrays.asList(DescricaoProntaOutro.values());
	}
	@ModelAttribute("todosUsuarios")
	public List<Usuario> todosAtendentes() {
		List<Usuario> users = usuarios.findAll();
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
		Collections.sort(users, comparator);
		return users;
	}
	
	@ModelAttribute("todosUsuariosTECI")
	public List<Usuario> todosAtendentesTECI() {
		List<Usuario> users = usuarios.findAll();
		List<Usuario> todosUsuariosTECI = new ArrayList<Usuario>();
		Iterator it = users.iterator();
		while(it.hasNext())
		{
			Usuario obj = (Usuario) it.next();
			if(obj.getNivel_acesso().getNivel().equals("Administrador") || obj.getNivel_acesso().getNivel().equals("Usuário TECI"))
				todosUsuariosTECI.add(obj);
			
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
		Collections.sort(todosUsuariosTECI, comparator);
		return todosUsuariosTECI;
	}
	
	@ModelAttribute("home_teci")
	public boolean homeTECI() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_TECI") || AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_CADASTRAR_SERVICO");
	}
}
