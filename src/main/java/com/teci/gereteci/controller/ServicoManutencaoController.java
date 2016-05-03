package com.teci.gereteci.controller;

import java.text.DateFormat;
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

import com.teci.gereteci.model.Computador.Memoria;
import com.teci.gereteci.model.Computador.StatusComputador;
import com.teci.gereteci.model.Recurso.Recurso;
import com.teci.gereteci.model.Servico.DescricaoManutencao;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.ServicosManutencao;
import com.teci.gereteci.repository.Usuarios;


@Controller
@RequestMapping("/gereteci/servicosmanutencao")
public class ServicoManutencaoController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroServicoManutencao"; 
	private static final String CADASTRO_VIEW2 = "/edicoes/EdicaoServicoManutencao"; 
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private ServicosManutencao servicos;
	@Autowired
	private ServicosManutencao servicosAtendente;
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("servico", new ServicoManutencao());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated ServicoManutencao servicoManutencao, @RequestParam Integer usuario_id_usuario, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroServicoManutencao";
		}

		//Usuario user = usuarios.findOne(usuario_id_usuario);
		String array[] = new String[3];
		String protocolo = "CTB";
		long numero = servicos.count()+1;
		Date data = new Date(System.currentTimeMillis());  
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 
		//System.out.print(formatarDate.format(data).toString());
		
		array = formatarDate.format(data).toString().split("-");
		
		protocolo = protocolo + "M" + array[0] + array[1] + "-" + numero;
		if(usuario_id_usuario != null) {
			Usuario user = usuarios.findOne(usuario_id_usuario);
			servicoManutencao.setSolicitado(user);
		}
		servicoManutencao.setProtocolo(protocolo);

		servicos.save(servicoManutencao);
		attributes.addFlashAttribute("mensagem", "Serviço salvo com sucesso!");	
		return "redirect:/gereteci/servicosmanutencao/novo";
	
	}
	@RequestMapping(value="/{id_servico}/salvar1",method = RequestMethod.POST)
	public String salvar1(@Validated ServicoManutencao servicoManutencao, @RequestParam Integer usuario_id_usuario, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW2);
		if(errors.hasErrors())
		{
			return "cadastroServicoManutencao";
		}
		//ServicoManutencao servico = servicos.findOne(servicoManutencao.getId_servico());
		//servicoManutencao.setProtocolo(servico.getProtocolo()); 
		//System.out.print(formatarDate.format(data).toString());
		if(usuario_id_usuario != null) {
			Usuario user = usuarios.findOne(usuario_id_usuario);
			servicoManutencao.setSolicitado(user);
		}
		servicos.save(servicoManutencao);
		attributes.addFlashAttribute("mensagem", "Serviço salvo com sucesso!");	
		return "redirect:/gereteci/servicosmanutencao/novo";
	
	}
	
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView pesquisar(String busca, String atendenteop, String solicitante, String setor, String status, String data_ocorrencia, String data_encerramento, String descricao_problema) throws ParseException
	{
		//List<ServicoManutencao> todosServicosManutencao = servicos.findAll();
		//Usuario user = usuarios.findOne(14);
		if(atendenteop != null) {
			if(busca != null && atendenteop.equals("on")) {
				System.out.println(busca);
				System.out.println(busca);
				List<Usuario> teste = usuarios.findByNomeContaining(busca);
				Usuario teste2 = teste.get(0);
				System.out.println(teste2.getNome());
				List<ServicoManutencao> todosServicosManutencao = servicosAtendente.findByAtendente(teste2);
				//List<Computador> todosComputadores = computadores.findAll();
				List<Usuario> todosUsuarios = usuarios.findAll();
				ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosManutencao");
			    mv.addObject("servicos", todosServicosManutencao);
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
			List<ServicoManutencao> todosServicosManutencao = servicosAtendente.findBySolicitado(teste2);
			//List<Computador> todosComputadores = computadores.findAll();
			List<Usuario> todosUsuarios = usuarios.findAll();
			ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosManutencao");
		    mv.addObject("servicos", todosServicosManutencao);
			mv.addObject("usuarios", todosUsuarios);
			return mv;
		}
	}
		else
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
					List<ServicoManutencao> todosServicosManutencao = servicosAtendente.findByStatus(sServico);
					//List<Computador> todosComputadores = computadores.findAll();
					ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosManutencao");
				    mv.addObject("servicos", todosServicosManutencao);
					return mv;
				}
			}
			
			else
				if(data_ocorrencia != null)
				{
					if(busca != null && data_ocorrencia.equals("on")) 
					{	
						
						List<ServicoManutencao> servicosManutencao = servicos.findAll();
						List<ServicoManutencao> servicosManutencao2 = new ArrayList<ServicoManutencao>();
						Iterator it = servicosManutencao.iterator();
						while(it.hasNext())
						{
							ServicoManutencao serv = (ServicoManutencao) it.next();
							if(serv.getData_ocorrencia().toString().contains(busca))
							{
								servicosManutencao2.add(serv);
							}
						}	
						//List<Computador> todosComputadores = computadores.findAll();
						ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosManutencao");
					    mv.addObject("servicos", servicosManutencao2);
						return mv;
					}
				}
				else
					if(data_encerramento != null)
					{
						if(busca != null && data_encerramento.equals("on")) 
						{	
							
							List<ServicoManutencao> servicosManutencao = servicos.findAll();
							List<ServicoManutencao> servicosManutencao2 = new ArrayList<ServicoManutencao>();
							Iterator it = servicosManutencao.iterator();
							while(it.hasNext())
							{
								ServicoManutencao serv = (ServicoManutencao) it.next();
								if(serv.getData_ocorrencia().toString().contains(busca))
								{
									servicosManutencao2.add(serv);
								}
							}	
							//List<Computador> todosComputadores = computadores.findAll();
							ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosManutencao");
						    mv.addObject("servicos", servicosManutencao2);
							return mv;
						}
					}
					else
						if(setor != null)
						{
							if(busca != null && setor.equals("on")) 
							{	
								List<ServicoManutencao> servicosManutencao = servicos.findAll();
								List<ServicoManutencao> servicosManutencao2 = new ArrayList<ServicoManutencao>();
								Iterator it = servicosManutencao.iterator();
								while(it.hasNext())
								{
									ServicoManutencao serv = (ServicoManutencao) it.next();
									if(serv.getSolicitado() != null && serv.getSolicitado().getSetor() != null) {
										if(serv.getSolicitado().getSetor().getSigla().contains(busca))
										{
											servicosManutencao2.add(serv);
										}
									}	
								}
								ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosManutencao");
							    mv.addObject("servicos", servicosManutencao2);
								return mv;
							}
						}
						else
							if(descricao_problema != null)
							{
								if(busca != null && descricao_problema.equals("on")) 
								{	
									List<ServicoManutencao> servicosManutencao = servicos.findAll();
									List<ServicoManutencao> servicosManutencao2 = new ArrayList<ServicoManutencao>();
									Iterator it = servicosManutencao.iterator();
									while(it.hasNext())
									{
										ServicoManutencao serv = (ServicoManutencao) it.next();
										if(serv.getDescricao_problema().contains(busca)) {
												servicosManutencao2.add(serv);
										}	
									}
									ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosManutencao");
								    mv.addObject("servicos", servicosManutencao2);
									return mv;
								}
							}
		   List<ServicoManutencao> todosServicosManutencao = servicos.findAll();
		   List<Usuario> todosUsuarios = usuarios.findAll();
			ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosManutencao");
		/*	Comparator<ServicoManutencao> sm = new Comparator<ServicoManutencao>() {
			    public int compare(ServicoManutencao m1, ServicoManutencao m2) {
			    	if(m2.getId_servico() > m1.getId_servico()) {
						return 0;
					}
			    	if(m2.getId_servico() < m1.getId_servico()) {
						return -1;
					}
					
					return 1;
			    }
			};
			*/
			Collections.reverse(todosServicosManutencao);
			List<ServicoManutencao> novaListaLimitada = new ArrayList<ServicoManutencao>();
			int contador = 0; 
			for (ServicoManutencao servico : todosServicosManutencao)
			{
				if(contador < 10 ) {
					novaListaLimitada.add(servico);
					contador++;
				}
				else {break;}
			}
			System.out.println( todosServicosManutencao.size());
			//if(todosServicosManutencao.size() > 10) {
				//mv.addObject("servicos", todosServicosManutencao.subList(todosServicosManutencao.size()-10 ,todosServicosManutencao.size()));
			//}
			//else { mv.addObject("servicos", todosServicosManutencao); }
			mv.addObject("servicos", novaListaLimitada);
			mv.addObject("usuarios", todosUsuarios);
			
		return mv;
	}

	@RequestMapping("{id_servico}")
	public ModelAndView edicao(@PathVariable("id_servico") ServicoManutencao servicoManutencao)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		Usuario solicitante = servicoManutencao.getSolicitado();
		Usuario usuario_atendente = servicoManutencao.getAtendente();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("servico", servicoManutencao);
		mv.addObject(servicoManutencao);
		return mv;
	}
	@RequestMapping("/{id_servico}/editar1")
	public ModelAndView edicao1(@PathVariable("id_servico") ServicoManutencao servicoManutencao)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		Usuario solicitante = servicoManutencao.getSolicitado();
		Usuario usuario_atendente = servicoManutencao.getAtendente();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW2);
		mv.addObject("servico", servicoManutencao);
		mv.addObject(servicoManutencao);
		return mv;
	}
	
	@RequestMapping(value="{id_servico}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_servico, RedirectAttributes attributes)
	{
		servicos.delete(id_servico);
		attributes.addFlashAttribute("mensagem", "Serivço excluído com sucesso com sucesso!");	
		return "redirect:/servicosmanutencao";
	}
	@ModelAttribute("todosStatusServico")
	public List<StatusServico> todosStatusServico() {
		return Arrays.asList(StatusServico.values());
	}
	
	@ModelAttribute("todasDescricoesServicos")
	public List<DescricaoManutencao> todasDescricoesServico() {
		return Arrays.asList(DescricaoManutencao.values());
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
	
}
