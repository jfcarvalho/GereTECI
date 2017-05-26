package com.teci.gereteci.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import com.teci.gereteci.model.Avaliacao.Avaliacao;
import com.teci.gereteci.model.Mensagem.Mensagem;
import com.teci.gereteci.model.Recurso.Monitor;
import com.teci.gereteci.model.Requisicao.Requisicao;
import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Servico.ServicoEmail;
import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.ServicoRede;
import com.teci.gereteci.model.Servico.ServicoTelefone;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.Avaliacoes;
import com.teci.gereteci.repository.Mensagens;
import com.teci.gereteci.repository.Requisicoes;
import com.teci.gereteci.repository.Servicos;
import com.teci.gereteci.repository.ServicosEmail;
import com.teci.gereteci.repository.ServicosInternet;
import com.teci.gereteci.repository.ServicosManutencao;
import com.teci.gereteci.repository.ServicosRede;
import com.teci.gereteci.repository.ServicosTelefone;
import com.teci.gereteci.repository.Usuarios;
import com.teci.gereteci.security.AppUserDetailsService;


@Controller
@RequestMapping("/gereteci/requisicoes")

public class RequisicaoController {
	
	private static final String CADASTRO_VIEW = "/cadastro/CadastroRequisicao"; 
	private static final String ATENDER_VIEW = "/visualizacao/AtenderSolicitacao"; 
	private static final String MENSAGEM_VIEW = "/cadastro/CadastroMensagem";
	private static final String AVALIACAO_VIEW = "/cadastro/CadastroAvaliacao";
	
	
	@Autowired
	private ServicosManutencao servicosManutencao;
	
	@Autowired
	private ServicosInternet servicosInternet;
	
	@Autowired
	private ServicosRede servicosRede;
	
	@Autowired
	private ServicosEmail servicosEmail;
	
	@Autowired
	private ServicosTelefone servicosTelefone;
	
	@Autowired
	private Servicos servicos;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Requisicoes requisicoes;
	
	@Autowired
	private Mensagens mensagens;
	
	@Autowired
	private Avaliacoes avaliacoes;
	
	@Autowired
	private Mailer mailer;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("requisicao", new Requisicao());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
	@RequestMapping("/{id_requisicao}/mensagem/novo")
	public ModelAndView nova_mensagem(@PathVariable("id_requisicao") Integer req)
	{
		ModelAndView mv = new ModelAndView(MENSAGEM_VIEW);
		Requisicao requisicao = requisicoes.findOne(req);
		Mensagem m = new Mensagem();
		//m.setRequisicao(requisicao);
		
		mv.addObject("msg", m);
		
		mv.addObject("requisicao", requisicao);
		mv.addObject("msgs", mensagens_usuario(requisicao));
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
	@RequestMapping("/{id_requisicao}/avaliacao/novo")
	public ModelAndView nova_avaliacao(@PathVariable("id_requisicao") Integer req)
	{
		ModelAndView mv = new ModelAndView(AVALIACAO_VIEW);
		Requisicao requisicao = requisicoes.findOne(req);
		Avaliacao a = new Avaliacao();
		//m.setRequisicao(requisicao);
		
		mv.addObject("avaliacao", a);
		
		mv.addObject("requisicao", requisicao);
		
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
	@RequestMapping(value = "/avaliacao/salvar/", method=RequestMethod.POST)
	public ModelAndView registrar_avaliacao(Avaliacao av, @RequestParam Integer avaliacao_id_requisicao)
	{
		ModelAndView mv = new ModelAndView(AVALIACAO_VIEW);
		Requisicao r = requisicoes.findOne(avaliacao_id_requisicao);
		mv.addObject("requisicao", r);
		av.setRequisicao(r);
		avaliacoes.save(av);
		r.setAvaliacao(av);
		requisicoes.save(r);
		
		mv.addObject("mensagem", "A sua avaliação foi cadastrada com sucesso!!");
	//	mv.addObject("msgs", mensagens_usuario(r));
		return mv;
	}
	
	@RequestMapping(value = "/mensagem/salvar/", method=RequestMethod.POST)
	public ModelAndView registrar_mensagem(Mensagem msg, @RequestParam Integer mensagem_id_requisicao)
	{
		ModelAndView mv = new ModelAndView(MENSAGEM_VIEW);
		Requisicao r = requisicoes.findOne(mensagem_id_requisicao);
		mv.addObject("requisicao", r);
		//System.out.println("VAlor de requisicao:" + req);
		Date dt = new Date();
		msg.setData(dt);
		Usuario de = usuarios.findByMatricula(AppUserDetailsService.cusuario.getUsername());
		msg.setDe(de);
		msg.setPara(r.getAtendente());
		msg.setRequisicao(r);
		mensagens.save(msg);
		mv.addObject("msg", msg);
		mv.addObject("msgs", mensagens_usuario(r));
		mailer.mensagem_nova_requisicao(r.getId_requisicao().toString(), r.getAtendente().getNome(), r.getAtendente().getMatricula(), r.getProtocolo(), r.getData(), "", r.getRequisitante().getEmail(), r.getAtendente().getEmail());
		return mv;
	}
	
	@RequestMapping( "/gerar_atendimento/{id_requisicao}")
	public ModelAndView gerar_atendimento(@PathVariable("id_requisicao") Integer requisicao)
	{
		ModelAndView mv = new ModelAndView(ATENDER_VIEW);
		Requisicao requi = requisicoes.findOne(requisicao);
		mv.addObject("requisicao", requi);
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}

	@RequestMapping( "/atender/{id_requisicao}")
	public ModelAndView atender(@PathVariable("id_requisicao") Requisicao req)
	{
		ModelAndView mv = new ModelAndView(ATENDER_VIEW);
		mv.addObject("requisicao", req);
		Usuario user = usuarios.findByMatricula(AppUserDetailsService.cusuario.getUsername());
		req.setAtendente(user);
		req.getServico().setStatus(StatusServico.em_andamento);
		req.getServico().setAtendente(user);
		
		
		requisicoes.save(req);
		mailer.requisicao_atendida(user.getNome(), user.getMatricula(),req.getProtocolo(), req.getData(), req.getServico().getCategoria(), req.getRequisitante().getEmail(), req.getAtendente().getEmail());
		mv.addObject("mensagem", "Requisição atendida com sucesso, agora você será o responsável pelo serviço");
		return mv;
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Requisicao req, @RequestParam Integer tipo, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		String protocolo = "CTB";
		String array[] = new String[3];
		Date data = new Date(System.currentTimeMillis());  
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 
		Usuario user = usuarios.findByMatricula(AppUserDetailsService.cusuario.getUsername());
		List<Servico> servs = servicos.findAll();
		Integer ultimo;
		if(servs.size() == 0)
		{
			ultimo = 0;
		}
		else {ultimo = servs.get(servs.size()-1).getId_servico();}
		long numero;
		req.setData(data);
		
		switch(tipo)
		{
			case 1:
				ServicoManutencao servM = new ServicoManutencao();
				Integer tReqM = requisicoes.findAll().size() + 1;
				servM.setId_servico(ultimo+1);
				numero  = servicosManutencao.count()+1;
				
					
					array = formatarDate.format(data).toString().split("-");
					protocolo = protocolo + "M" + array[0] + array[1] + "-" + numero;
					
					servM.setProtocolo(protocolo);
					servM.setDescricao_problema(req.getProblema());
					servM.setStatus(StatusServico.aberto);
					servM.setSolicitado(user);
					servM.setCategoria("Manutenção");
					req.setRequisitante(user);
					req.setId_requisicao(tReqM);
					servM.setData_ocorrencia(data);
					req.setProtocolo(protocolo);
					servicosManutencao.save(servM);
					requisicoes.save(req);
					Requisicao reqsalvom = requisicoes.findOne(req.getId_requisicao());
					ServicoManutencao servMSalvo = servicosManutencao.findOne(servM.getId_servico()); //aqui ta vindo nulo
					servMSalvo.setRequisicao(reqsalvom); //problema aqui
					servicosManutencao.save(servMSalvo);
					
					reqsalvom.setServico(servMSalvo);

					requisicoes.save(reqsalvom);
					mailer.enviar_requisicao(user.getNome(), reqsalvom.getProtocolo(), user.getSetor().getSigla(), reqsalvom.getData(), reqsalvom.getData_abertura(), reqsalvom.getServico().getCategoria(), reqsalvom.getProblema(), user.getEmail());

				
				
			break;
			case 2:
				
				ServicoInternet servI = new ServicoInternet();
				Integer tReqI = requisicoes.findAll().size() + 1;
				servI.setId_servico(ultimo+1);
				numero  = servicosInternet.count()+1;
				
					
					array = formatarDate.format(data).toString().split("-");
					protocolo = protocolo + "I" + array[0] + array[1] + "-" + numero;
					
					servI.setProtocolo(protocolo);
					servI.setDescricao_problema(req.getProblema());
					servI.setStatus(StatusServico.aberto);
					servI.setSolicitado(user);
					servI.setCategoria("Internet");
					req.setRequisitante(user);
					req.setId_requisicao(tReqI);
					
					servI.setData_ocorrencia(data);
					req.setProtocolo(protocolo);
					servicosInternet.save(servI);
					requisicoes.save(req);
					Requisicao reqsalvoi = requisicoes.findOne(req.getId_requisicao());
					ServicoInternet servISalvo = servicosInternet.findOne(servI.getId_servico()); //aqui ta vindo nulo
					servISalvo.setRequisicao(reqsalvoi); //problema aqui
					servicosInternet.save(servISalvo);
					
					reqsalvoi.setServico(servISalvo);

					requisicoes.save(reqsalvoi);
					mailer.enviar_requisicao(user.getNome(), reqsalvoi.getProtocolo(), user.getSetor().getSigla(), reqsalvoi.getData(), reqsalvoi.getData_abertura(), reqsalvoi.getServico().getCategoria(), reqsalvoi.getProblema(), user.getEmail());
				
			break;
			case 3:
				
				ServicoRede servR = new ServicoRede();
				//Integer tServR = servicos.findAll().size() + 1;
				Integer tReqR = requisicoes.findAll().size() + 1;
				servR.setId_servico(ultimo+1);
				
			
				numero  = servicosRede.count()+1;
			//	Date data = new Date(System.currentTimeMillis());  
//				SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 
				//System.out.print(formatarDate.format(data).toString());
				array = formatarDate.format(data).toString().split("-");
				protocolo = protocolo + "R" + array[0] + array[1] + "-" + numero;
				servR.setProtocolo(protocolo);
				servR.setDescricao_problema(req.getProblema());
				servR.setStatus(StatusServico.aberto);
				servR.setSolicitado(user);
				servR.setCategoria("Rede");
				req.setRequisitante(user);
				req.setId_requisicao(tReqR);
				
				servR.setData_ocorrencia(data);
				req.setProtocolo(protocolo);
				servicosRede.save(servR);
				requisicoes.save(req);
				Requisicao reqsalvo = requisicoes.findOne(req.getId_requisicao());
				ServicoRede servRSalvo = servicosRede.findOne(servR.getId_servico()); //aqui ta vindo nulo
				servRSalvo.setRequisicao(reqsalvo); //problema aqui
				servicosRede.save(servRSalvo);
				
				reqsalvo.setServico(servRSalvo);

				requisicoes.save(reqsalvo);
				
				mailer.enviar_requisicao(user.getNome(), reqsalvo.getProtocolo(), user.getSetor().getSigla(), reqsalvo.getData(), reqsalvo.getData_abertura(), reqsalvo.getServico().getCategoria(), reqsalvo.getProblema(), user.getEmail());
			
				
			break;
			case 4:
				
				ServicoEmail servE = new ServicoEmail();
				//Integer tServE = servicosEmail.findAll().size() + 1;
				Integer tReqE = requisicoes.findAll().size() + 1;
				servE.setId_servico(ultimo+1);
				
			
				numero  = servicosEmail.count()+1;
			//	Date data = new Date(System.currentTimeMillis());  
//				SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 
				//System.out.print(formatarDate.format(data).toString());
			
				
				array = formatarDate.format(data).toString().split("-");
				protocolo = protocolo + "E" + array[0] + array[1] + "-" + numero;
				/*
				servE.setProtocolo(protocolo);
				servE.setDescricao_problema(req.getProblema());
				
				servE.setSolicitado(user);
				req.setRequisitante(user);
				req.setData_abertura(data);
				servE.setData_ocorrencia(data);
				req.setProtocolo(protocolo);
				servE.setStatus(StatusServico.aberto);
				servE.setRequisicao(req);
				servicosEmail.save(servE);
				requisicoes.save(req);
				*/
				
				servE.setProtocolo(protocolo);
				servE.setDescricao_problema(req.getProblema());
				servE.setStatus(StatusServico.aberto);
				servE.setSolicitado(user);
				servE.setCategoria("Email");
				req.setRequisitante(user);
				req.setId_requisicao(tReqE);
				
				servE.setData_ocorrencia(data);
				req.setProtocolo(protocolo);
				servicosEmail.save(servE);
				requisicoes.save(req);
				Requisicao reqsalvoe = requisicoes.findOne(req.getId_requisicao());
				ServicoEmail servESalvo = servicosEmail.findOne(servE.getId_servico()); //aqui ta vindo nulo
				servESalvo.setRequisicao(reqsalvoe); //problema aqui
				servicosEmail.save(servESalvo);
				
				reqsalvoe.setServico(servESalvo);

				requisicoes.save(reqsalvoe);
				
				mailer.enviar_requisicao(user.getNome(), reqsalvoe.getProtocolo(), user.getSetor().getSigla(), reqsalvoe.getData(), reqsalvoe.getData_abertura(), reqsalvoe.getServico().getCategoria(), reqsalvoe.getProblema(), user.getEmail());
				
				
				
			break;
			case 5:
				ServicoTelefone servT = new ServicoTelefone();
				Integer tReqT = requisicoes.findAll().size() + 1;
				servT.setId_servico(ultimo+1);
				numero  = servicosEmail.count()+1;
				
					
					array = formatarDate.format(data).toString().split("-");
					protocolo = protocolo + "E" + array[0] + array[1] + "-" + numero;
					
					servT.setProtocolo(protocolo);
					servT.setDescricao_problema(req.getProblema());
					servT.setStatus(StatusServico.aberto);
					servT.setSolicitado(user);
					servT.setCategoria("Telefone");
					req.setRequisitante(user);
					req.setId_requisicao(tReqT);
					
					servT.setData_ocorrencia(data);
					req.setProtocolo(protocolo);
					servicosTelefone.save(servT);
					requisicoes.save(req);
					Requisicao reqsalvot = requisicoes.findOne(req.getId_requisicao());
					ServicoTelefone servTSalvo = servicosTelefone.findOne(servT.getId_servico()); //aqui ta vindo nulo
					servTSalvo.setRequisicao(reqsalvot); //problema aqui
					servicosTelefone.save(servTSalvo);
					
					reqsalvot.setServico(servTSalvo);

					requisicoes.save(reqsalvot);
					mailer.enviar_requisicao(user.getNome(), reqsalvot.getProtocolo(), user.getSetor().getSigla(), reqsalvot.getData(), reqsalvot.getData_abertura(), reqsalvot.getServico().getCategoria(), reqsalvot.getProblema(), user.getEmail());
	
				break;
		}
		mv.addObject("mensagem", "A sua requisição foi aberta com sucesso. Por favor aguarde um retorno do suporte");
		return mv;
	}
	
	public List<Mensagem> mensagens_usuario(Requisicao req)
	{
		List<Mensagem> msgs = mensagens.findAll();
		List<Mensagem> msgs_list = new ArrayList<Mensagem>();
		 Comparator<Mensagem> cmp = new Comparator<Mensagem>() {
		        public int compare(Mensagem s1, Mensagem s2) {
		          return s2.getId_mensagem().compareTo(s1.getId_mensagem());
		        }
	};
		
		for(Mensagem mg: msgs)
		{
			if(mg.getRequisicao().getId_requisicao() == req.getId_requisicao())
			{
				msgs_list.add(mg);
			}
		}
		Collections.sort(msgs_list, cmp);
		return msgs_list;
	}
	
	
	public List<Requisicao> requisicoes_usuario()
	{
		List<Requisicao> reqs = requisicoes.findAll();
		List<Requisicao> reqs_usuario = new ArrayList<Requisicao>();
		Usuario us = usuarios.findByMatricula(AppUserDetailsService.cusuario.getUsername());
		 Comparator<Requisicao> cmp = new Comparator<Requisicao>() {
		        public int compare(Requisicao s1, Requisicao s2) {
		          return s2.getData().compareTo(s1.getData());
		        }
	};
		
		for(Requisicao req:reqs)
		{
			if(us.getMatricula().equals(req.getRequisitante().getMatricula()) == true)
			{
				System.out.println(req.getRequisitante().getMatricula());
				System.out.println(us.getMatricula());
				System.out.println(us.getMatricula().equals(req.getRequisitante().getMatricula()));
				reqs_usuario.add(req);
			}
		}
		Collections.sort(reqs_usuario, cmp);
		return reqs_usuario;
	}
	
	@RequestMapping
	public ModelAndView pesquisar(String busca, boolean proto, boolean docorre, boolean cat, boolean prob)
	{
		
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaRequisicoes");
		List<Requisicao> reqs = requisicoes_usuario();
		List<Requisicao> reqs_filtradas = new ArrayList<Requisicao>();
		List<Requisicao> reqs_total = requisicoes.findAll();
		 Comparator<Requisicao> cmp = new Comparator<Requisicao>() {
		        public int compare(Requisicao s1, Requisicao s2) {
		          return s2.getData().compareTo(s1.getData());
		        }
	};
		
		if(AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_TECI"))
		{
			Collections.sort(reqs_total, cmp);
			if(reqs_total.size() <20) 
			{
				mv.addObject("requisicoes", reqs_total);
				return mv;
			}
			else
			{
				mv.addObject("requisicoes", reqs_total.subList(0, 19));
				return mv;
			}
		}
		else{
			if(busca == null) {
				
				if(reqs.size() < 20)
				{
					mv.addObject("requisicoes", reqs);
					return mv;
				}
				else
				{
					mv.addObject("requisicoes", reqs.subList(0, 19));
					return mv;
				}
			}
			else{
				if(proto == true)
				{
					for(Requisicao req:reqs)
					{
						if(req.getProtocolo().contains(busca))
						{
							reqs_filtradas.add(req);
						}
					}
					mv.addObject("requisicoes", reqs_filtradas);
					return mv;
					
				}
				else if(docorre == true)
				{
					for(Requisicao req:reqs)
					{
						if(req.getData_abertura().toString().contains(busca))
						{
							reqs_filtradas.add(req);
						}
					}
					mv.addObject("requisicoes", reqs_filtradas);
					return mv;
				}
				else if(cat == true)
				{
					for(Requisicao req:reqs)
					{
						if(req.getServico().getCategoria().contains(busca))
						{
							reqs_filtradas.add(req);
						}
					}
					mv.addObject("requisicoes", reqs_filtradas);
					return mv;
				}
				else if(prob)
				{
					for(Requisicao req:reqs)
					{
						if(req.getServico().getCategoria().contains(busca))
						{
							reqs_filtradas.add(req);
						}
					}
					mv.addObject("requisicoes", reqs_filtradas);
					return mv;
				}
			}
		}
			return mv;
	}
	
	@ModelAttribute("home_teci")
	public boolean homeTECI() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_TECI") || AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_CADASTRAR_SERVICO");
	}
	
	
	

}
