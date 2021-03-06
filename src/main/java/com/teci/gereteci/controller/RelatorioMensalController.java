package com.teci.gereteci.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Impressora.Impressora;
import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Servico.ServicoEmail;
import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.ServicoRede;
import com.teci.gereteci.model.Servico.ServicoTelefone;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.Servicos;
import com.teci.gereteci.repository.ServicosEmail;
import com.teci.gereteci.repository.ServicosInternet;
import com.teci.gereteci.repository.ServicosManutencao;
import com.teci.gereteci.repository.ServicosRede;
import com.teci.gereteci.repository.ServicosTelefone;
import com.teci.gereteci.repository.Usuarios;
import com.teci.gereteci.security.AppUserDetailsService;

@Controller



public class RelatorioMensalController {
	@Autowired
	private ServicosManutencao manutencoes;
	@Autowired
	private ServicosInternet sinternet;
	@Autowired
	private ServicosRede sredes;
	@Autowired
	private ServicosEmail semail;
	@Autowired
	private ServicosTelefone stelefone;
	@Autowired
	private Servicos servicosGerais;

	private static final String RELATORIO_PATH = "/relatorios/RelatorioMensal";
	private static final String RELATORIO_PATH2 = "/relatorios/GerarRelatorio";
	@RequestMapping(value="/gereteci/relatoriomensal", method= RequestMethod.GET)
	public ModelAndView relatorio(String mes, String ano)
	{
		ModelAndView mv = new ModelAndView(RELATORIO_PATH);
		
		String periodo = ano + "-" + mes; 
		
		mv.addObject("mes_relatorio", mes);
		mv.addObject("ano_relatorio", ano);
		
		List<ServicoManutencao> manutencao = manutencaoPorPeriodo(manutencoes.findAll(), periodo);
		List<ServicoInternet> internet = internetPorPeriodo(sinternet.findAll(), periodo);
		List<ServicoRede> redes = redePorPeriodo(sredes.findAll(), periodo);
		List<ServicoEmail> emails = emailPorPeriodo(semail.findAll(), periodo);
		List<ServicoTelefone> telefones = telefonePorPeriodo(stelefone.findAll(), periodo);
		
		List<Servico> sgLista = servicosGerais.findAll();
		
		System.out.println(sgLista.size());
		
		int numeroManutencoes = manutencao.size();
		int numeroInternet = internet.size();
		int numeroRedes = redes.size();
		int numeroEmail = emails.size();
		int numeroTelefone = telefones.size();
		//int numeroGerais = sgLista.size();
		
		
		int servicostotais = numeroManutencoes + numeroInternet + numeroRedes + numeroEmail + numeroTelefone;
		mv.addObject("n_total", servicostotais);
		mv.addObject("n_manutencao", numeroManutencoes);
		mv.addObject("n_internet", numeroInternet);
		mv.addObject("n_email", numeroEmail);
		mv.addObject("n_telefone", numeroTelefone);
		mv.addObject("n_redes", numeroRedes);
		
		
		mv.addObject("servicos_gerais_abertos", servicosGerais(sgLista, 0));
		mv.addObject("servicos_gerais_andamento", servicosGerais(sgLista, 1));
		mv.addObject("servicos_gerais_fechados", servicosGerais(sgLista, 2));
		
		List<ServicoManutencao> servicosArianaManutencao = servicosManutencaoAtendente(manutencao, "Ariana Souza Silva");
		List<ServicoRede> servicosArianaRede = servicosRedeAtendente(redes, "Ariana Souza Silva");
		List<ServicoInternet> servicosArianaInternet = servicosInternetAtendente(internet, "Ariana Souza Silva");
		
		List<ServicoManutencao> servicosUiranManutencao = servicosManutencaoAtendente(manutencao, "Uiran Passos");
		List<ServicoRede> servicosUiranRede = servicosRedeAtendente(redes, "Uiran Passos");
		List<ServicoInternet> servicosUiranInternet = servicosInternetAtendente(internet, "Uiran Passos");
		
		List<ServicoManutencao> servicosJeanManutencao = servicosManutencaoAtendente(manutencao, "Jean Carvalho");
		List<ServicoRede> servicosJeanRede = servicosRedeAtendente(redes, "Jean Carvalho");
		List<ServicoInternet> servicosJeanInternet = servicosInternetAtendente(internet, "Jean Carvalho");
		List<ServicoEmail> servicosJeanEmail = servicosEmailAtendente(emails, "Jean Carvalho");
		
		List<ServicoManutencao> servicosRomeuManutencao = servicosManutencaoAtendente(manutencao, "Romeu Oliveira");
		List<ServicoRede> servicosRomeuRede = servicosRedeAtendente(redes, "Romeu Oliveira");
		List<ServicoInternet> servicosRomeuInternet = servicosInternetAtendente(internet, "Romeu Oliveira");
		List<ServicoEmail> servicosRomeuEmail = servicosEmailAtendente(emails, "Romeu Oliveira");
		List<ServicoTelefone> servicosRomeuTelefone = servicosTelefoneAtendente(telefones, "Romeu Oliveira");
		
		//List<ServicoManutencao> servicosArianaManutencao = servicosManutencaoAtendente(manutencao, "Romeu Oliveira");
		
		List<ServicoManutencao> abertosManutencao = servicosManutencao(manutencao, 0);
		List<ServicoManutencao> emAndamentoManutencao = servicosManutencao(manutencao, 1);
		List<ServicoManutencao> fechadoManutencao = servicosManutencao(manutencao, 2);
		
		List<ServicoInternet> abertosInternet = servicosInternet(internet, 0);
		List<ServicoInternet> emAndamentoInternet = servicosInternet(internet, 1);
		List<ServicoInternet> fechadoInternet = servicosInternet(internet, 2);
		
		
		List<ServicoRede> abertosRede = servicosRede(redes, 0);
		List<ServicoRede> emAndamentoRede = servicosRede(redes, 1);
		List<ServicoRede> fechadoRede = servicosRede(redes, 2);
		
		List<ServicoTelefone> abertosTelefone = servicosTelefone(telefones, 0);
		List<ServicoTelefone> emAndamentoTelefone = servicosTelefone(telefones, 1);
		List<ServicoTelefone> fechadoTelefone = servicosTelefone(telefones, 2);
		
		List<ServicoEmail> abertosEmail = servicosEmail(emails, 0);
		List<ServicoEmail> emAndamentoEmail = servicosEmail(emails, 1);
		List<ServicoEmail> fechadoEmail = servicosEmail(emails, 2);
		
		
		
		List<ServicoInternet> servicosVisitaOi = servicosInternetVistaOi(internet);
		List<ServicoInternet> servicosAberturaRds = servicosInternetAberturaRDS(internet);
		List<ServicoEmail> emailsAberturaRds = servicosEmailAberturaRDS(emails);
		
		int nManutencoesAbertos = abertosManutencao.size();
		int nManutencoesEmAndamento = emAndamentoManutencao.size();
		int nManutencoesFechados = fechadoManutencao.size();
		
		int nInternetAbertos = abertosInternet.size();
		int nInternetEmAndamento = emAndamentoInternet.size();
		int nInternetFechados = fechadoInternet.size();
		
		int nRedesAbertos = abertosRede.size();
		int nRedesEmAndamento = emAndamentoRede.size();
		int nRedesFechados = fechadoRede.size();
		
		int nTelefonesAbertos = abertosTelefone.size();
		int nTelefonesEmAndamento = emAndamentoTelefone.size();
		int nTelefonesFechados = fechadoTelefone.size();
		
		int nEmailsAbertos = abertosEmail.size();
		int nEmailsEmAndamento = emAndamentoEmail.size();
		int nEmailsFechados = fechadoEmail.size();
		int numeroServicosVisitaOi = servicosVisitaOi.size();
		int numeroServicosAberturaRds = servicosAberturaRds.size();
		int numeroEmailsAberturaRds = emailsAberturaRds.size();
		
		mv.addObject("n_servicos_visita_oi", numeroServicosVisitaOi);
		mv.addObject("n_servicos_rds",numeroServicosAberturaRds + numeroEmailsAberturaRds);
		
		mv.addObject("n_servicos_abertos_manutencao", nManutencoesAbertos);
		mv.addObject("n_servicos_andamento_manutencao",nManutencoesEmAndamento);
		mv.addObject("n_servicos_fechados_manutencao", nManutencoesFechados);
		
		mv.addObject("n_servicos_abertos_internet", nInternetAbertos);
		mv.addObject("n_servicos_andamento_internet",nInternetEmAndamento);
		mv.addObject("n_servicos_fechados_internet", nInternetFechados);
		
		mv.addObject("n_servicos_abertos_rede", nRedesAbertos);
		mv.addObject("n_servicos_andamento_rede",nRedesEmAndamento);
		mv.addObject("n_servicos_fechados_rede", nRedesFechados);
		
		mv.addObject("n_servicos_abertos_telefone", nTelefonesAbertos);
		mv.addObject("n_servicos_andamento_telefone",nTelefonesEmAndamento);
		mv.addObject("n_servicos_fechados_telefone", nTelefonesFechados);
		
		mv.addObject("n_servicos_abertos_email", nEmailsAbertos);
		mv.addObject("n_servicos_andamento_email",nEmailsEmAndamento);
		mv.addObject("n_servicos_fechados_email", nEmailsFechados);
		
		mv.addObject("servicos_ariana_manutencao", servicosArianaManutencao);
		mv.addObject("servicos_ariana_rede", servicosArianaRede);
		mv.addObject("servicos_ariana_internet", servicosArianaInternet);
		
		mv.addObject("servicos_romeu_manutencao", servicosRomeuManutencao);
		mv.addObject("servicos_romeu_rede", servicosRomeuRede);
		mv.addObject("servicos_romeu_internet", servicosRomeuInternet);
		mv.addObject("servicos_romeu_email", servicosRomeuEmail);
		mv.addObject("servicos_romeu_telefone", servicosRomeuTelefone);
		
		mv.addObject("servicos_uiran_manutencao", servicosUiranManutencao);
		mv.addObject("servicos_uiran_rede", servicosUiranRede);
		mv.addObject("servicos_uiran_internet", servicosUiranInternet);
		
		mv.addObject("servicos_jean_manutencao", servicosJeanManutencao);
		mv.addObject("servicos_jean_rede", servicosJeanRede);
		mv.addObject("servicos_jean_internet", servicosJeanInternet);
		mv.addObject("servicos_jean_email", servicosJeanEmail);
		
		
		//mv.addObject("sabertos", sAbertos);
		//mv.addObject("sandamento", sAndamento);
		mv.addObject("svisita_oi", servicosVisitaOi);
		mv.addObject("saberturards", servicosAberturaRds);
		mv.addObject("semailaberturards", emailsAberturaRds);
		
		
		List<ServicoEmail> contasCriadas = servicosEmailContas(emails, 0);
		List<ServicoEmail> contasEditadas = servicosEmailContas(emails, 1);
		List<ServicoEmail> contasExcluidas = servicosEmailContas(emails, 2);
		List<ServicoEmail> contasSenhaTrocada = servicosEmailContas(emails, 3);
		List<ServicoEmail> contasMudancaPlanos = servicosEmailContas(emails, 4);
	
		int nContasCriadas = contasCriadas.size();
		int nContasEditadas = contasEditadas.size();
		int nContasExcluidas = contasExcluidas.size();
		int nContasSenhasTrocadas = contasSenhaTrocada.size();
		int nContasMudancaPlanos = contasMudancaPlanos.size();
		
		mv.addObject("n_contas_criadas", nContasCriadas);
		mv.addObject("n_contas_editadas", nContasEditadas);
		mv.addObject("n_contas_excluidas", nContasExcluidas);
		mv.addObject("n_contas_senhas_trocadas", nContasSenhasTrocadas);
		mv.addObject("n_contas_mudanca_planos", nContasMudancaPlanos);
		
		mv.addObject("contas_criadas", contasCriadas);
		mv.addObject("contas_editadas", contasEditadas);
		mv.addObject("contas_excluidas", contasExcluidas);
		mv.addObject("contas_senha_trocada", contasSenhaTrocada);
		mv.addObject("contas_mudanca_plano", contasMudancaPlanos);
		mv.addObject("n_contas_excluidas", nContasExcluidas);
		mv.addObject("n_contas_senhas_trocadas", nContasSenhasTrocadas);
		mv.addObject("n_contas_mudanca_planos", nContasMudancaPlanos);
		
		//Serviços Ariana Manutencao
		
		//serviços abertos
		mv.addObject("n_ariana_abertos", servicosManutencao(manutencao, 0, "Ariana Souza Silva").size() + servicosInternet(internet, 0, "Ariana Souza Silva").size() +servicosRede(redes, 0, "Ariana Souza Silva").size() );
		//servicos em andamento
		mv.addObject("n_ariana_andamento", servicosManutencao(manutencao, 1, "Ariana Souza Silva").size() + servicosInternet(internet, 1, "Ariana Souza Silva").size() + servicosRede(redes, 1, "Ariana Souza Silva").size());
		//servicos fehcados
		mv.addObject("n_ariana_fechados", servicosManutencao(manutencao, 2, "Ariana Souza Silva").size() + servicosInternet(internet, 2, "Ariana Souza Silva").size() + servicosRede(redes, 2, "Ariana Souza Silva").size());
		//mv.addObject("n_ariana_total", )
	
		
		//Serviços Uiran Manutencao
		mv.addObject("n_uiran_abertos",servicosManutencao(manutencao, 0, "Uiran Passos").size()+ servicosInternet(internet, 0, "Uiran Passos").size() + servicosRede(redes, 0, "Uiran Passos").size());
		mv.addObject("n_uiran_andamento", servicosManutencao(manutencao, 1, "Uiran Passos").size() + servicosInternet(internet, 1, "Uiran Passos").size() + servicosRede(redes, 1, "Uiran Passos").size());
		mv.addObject("n_uiran_fechados", servicosManutencao(manutencao, 2, "Uiran Passos").size() + servicosInternet(internet, 2, "Uiran Passos").size() +  servicosRede(redes, 2, "Uiran Passos").size());
		
		
				//Serviços Jean Manutencao
		mv.addObject("n_jean_abertos", servicosManutencao(manutencao, 0, "Jean Carvalho").size() + servicosInternet(internet, 0, "Jean Carvalho").size() + servicosRede(redes, 0, "Jean Carvalho").size() + servicosEmail(emails, 0, "Jean Carvalho").size());		
		mv.addObject("n_jean_andamento", servicosManutencao(manutencao, 1, "Jean Carvalho").size() + servicosInternet(internet, 1, "Jean Carvalho").size() + servicosRede(redes, 1, "Jean Carvalho").size() + servicosEmail(emails, 1, "Jean Carvalho").size());
		mv.addObject("n_jean_fechados", servicosManutencao(manutencao, 2, "Jean Carvalho").size() + servicosInternet(internet, 2, "Jean Carvalho").size() + servicosRede(redes, 2, "Jean Carvalho").size()+ servicosEmail(emails, 2, "Jean Carvalho").size());	
		//Serviços Romeu
		mv.addObject("n_jean_abertos", servicosManutencao(manutencao, 0, "Jean Carvalho").size() + servicosInternet(internet, 0, "Jean Carvalho").size() + servicosRede(redes, 0, "Jean Carvalho").size() + servicosEmail(emails, 0, "Jean Carvalho").size());		
		mv.addObject("n_jean_andamento", servicosManutencao(manutencao, 1, "Jean Carvalho").size() + servicosInternet(internet, 1, "Jean Carvalho").size() + servicosRede(redes, 1, "Jean Carvalho").size() + servicosEmail(emails, 1, "Jean Carvalho").size());
		mv.addObject("n_jean_fechados", servicosManutencao(manutencao, 2, "Jean Carvalho").size() + servicosInternet(internet, 2, "Jean Carvalho").size() + servicosRede(redes, 2, "Jean Carvalho").size()+ servicosEmail(emails, 2, "Jean Carvalho").size());		
		
		mv.addObject("n_romeu_abertos", servicosManutencao(manutencao, 0, "Romeu Oliveira").size() + servicosInternet(internet, 0, "Romeu Oliveira").size()+  servicosRede(redes, 0, "Romeu Oliveira").size() + servicosEmail(emails, 0, "Romeu Oliveira").size() + servicosTelefone(telefones, 0, "Romeu Oliveira").size());
		mv.addObject("n_romeu_andamento", servicosManutencao(manutencao, 1, "Romeu Oliveira").size() + servicosInternet(internet, 1, "Romeu Oliveira").size()+  servicosRede(redes, 1, "Romeu Oliveira").size() + servicosEmail(emails, 1, "Romeu Oliveira").size() + servicosTelefone(telefones, 1, "Romeu Oliveira").size());
		mv.addObject("n_romeu_fechados", servicosManutencao(manutencao, 2, "Romeu Oliveira").size() + servicosInternet(internet, 2, "Romeu Oliveira").size()+  servicosRede(redes, 2, "Romeu Oliveira").size() + servicosEmail(emails, 2, "Romeu Oliveira").size() + servicosTelefone(telefones, 2, "Romeu Oliveira").size());

	
		
		//mv.addObject("servicos_ariana_rede", servicosArianaRede);
		
		return mv;
	}
	@RequestMapping(value="/gereteci/gerarrelatorio")
	public ModelAndView gerarRelatorio()
	{
		ModelAndView mv = new ModelAndView(RELATORIO_PATH2);
		return mv;
	}
	
	public List<ServicoManutencao> servicosManutencao(List<ServicoManutencao> servicos, int flag)
	{
		Iterator it = servicos.iterator();
		List<ServicoManutencao> smlist = new ArrayList<ServicoManutencao>(); 
		while(it.hasNext())
		{
			ServicoManutencao sm = (ServicoManutencao) it.next();
			switch(flag) {
			case 0:
				if(sm.getStatus().getDescricao().equals("Aberto"))
				{
					smlist.add(sm);
				}
			break;
			case 1:
				if(sm.getStatus().getDescricao().equals("Em andamento"))
				{
					smlist.add(sm);
				}
			break;
			case 2:
				if(sm.getStatus().getDescricao().equals("Fechado"))
				{
					smlist.add(sm);
				}
			break;
			}
			
		}
		return smlist;
	}
	
	
	
	public List<ServicoManutencao> servicosManutencao(List<ServicoManutencao> servicos, int flag, String Atendente)
	{
		Iterator it = servicos.iterator();
		List<ServicoManutencao> smlist = new ArrayList<ServicoManutencao>(); 
		while(it.hasNext())
		{
			ServicoManutencao sm = (ServicoManutencao) it.next();
			switch(flag) {
			case 0:
				if(sm.getStatus().getDescricao().equals("Aberto") && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			case 1:
				if(sm.getStatus().getDescricao().equals("Em andamento")  && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			case 2:
				if(sm.getStatus().getDescricao().equals("Fechado")  && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			}
			
		}
		return smlist;
	}
	
	
	
	
	public List<ServicoInternet> servicosInternetVistaOi(List<ServicoInternet> servicos)
	{
		Iterator<ServicoInternet> it = servicos.iterator();
		List<ServicoInternet> srvlist = new ArrayList<ServicoInternet>(); 
		while(it.hasNext())
		{
			ServicoInternet srv = (ServicoInternet) it.next();
			
			if(srv.getVisita_oi() == true)
			{
				//System.out.println(srv.getStatus().getDescricao());
				srvlist.add(srv);
			}
			
		}
		return srvlist;
	}
	
	public List<ServicoInternet> servicosInternetAberturaRDS(List<ServicoInternet> servicos)
	{
		Iterator<ServicoInternet> it = servicos.iterator();
		List<ServicoInternet> srvlist = new ArrayList<ServicoInternet>(); 
		while(it.hasNext())
		{
			ServicoInternet srv = (ServicoInternet) it.next();
			
			if(srv.getRds_aberto() == true)
			{
				//System.out.println(srv.getStatus().getDescricao());
				srvlist.add(srv);
			}
			
		}
		return srvlist;
	}
	
	public List<ServicoEmail> servicosEmailAberturaRDS(List<ServicoEmail> servicos)
	{
		Iterator<ServicoEmail> it = servicos.iterator();
		List<ServicoEmail> srvlist = new ArrayList<ServicoEmail>(); 
		while(it.hasNext())
		{
			ServicoEmail srv = (ServicoEmail) it.next();
			
			if(srv.getRds_aberto() == true)
			{
				//System.out.println(srv.getStatus().getDescricao());
				srvlist.add(srv);
			}
			
		}
		return srvlist;
	}
	
	public List<ServicoEmail> servicosEmailContas(List<ServicoEmail> servicos, int flag)
	{
		Iterator<ServicoEmail> it = servicos.iterator();
		List<ServicoEmail> emailsCriados = new ArrayList<ServicoEmail>(); 
		List<ServicoEmail> emailsAlterados = new ArrayList<ServicoEmail>(); 
		List<ServicoEmail> emailsExcluidos = new ArrayList<ServicoEmail>(); 
		List<ServicoEmail> emailsSenhasTrocadas = new ArrayList<ServicoEmail>(); 
		List<ServicoEmail> emailsPlanosMudados = new ArrayList<ServicoEmail>();
		while(it.hasNext())
		{
			ServicoEmail se = (ServicoEmail) it.next();
			switch(flag) {
			case 0:
				if(se.getCriacao_usuario() == true)
				{
					emailsCriados.add(se);
				}
			break;
			case 1:
				if(se.getAlteracao_usuario() == true)
				{
					emailsAlterados.add(se);
				}
			break;
			case 2:
				if(se.getExclusao_usuario() == true)
				{
					emailsExcluidos.add(se);
				}
			break;
			
			case 3:
				if(se.getTroca_senha() == true)
				{
					emailsSenhasTrocadas.add(se);
				}
			break;
			case 4:
				if(se.getMudanca_plano() == true)
				{
					emailsPlanosMudados.add(se);
				}
			break;
			}
			
		}
		if(flag == 0)
			return emailsCriados;
		else if(flag == 1)
			return emailsAlterados;
		else if(flag == 2)
			return emailsExcluidos;
		else if(flag == 3)
			return emailsSenhasTrocadas;
		else if(flag == 4)
			return emailsPlanosMudados;
		
		return null;
	}
	

	public List<Servico> servicosGerais(List<Servico> servicos, int flag)
	{
		Iterator it = servicos.iterator();
		List<Servico> smlist = new ArrayList<Servico>(); 
		while(it.hasNext())
		{
			Servico sm = (Servico) it.next();
			switch(flag) {
			case 0:
				if(sm.getStatus().getDescricao().equals("Aberto"))
				{
					smlist.add(sm);
				}
			break;
			case 1:
				if(sm.getStatus().getDescricao().equals("Em andamento"))
				{
					smlist.add(sm);
				}
			break;
			case 2:
				if(sm.getStatus().getDescricao().equals("Fechado"))
				{
					smlist.add(sm);
				}
			break;
			}
			
		}
		return smlist;
	}
	
	public List<ServicoManutencao> servicosManutencaoAtendente(List<ServicoManutencao> servicos, String Atendente)
	{
		Iterator it = servicos.iterator();
		List<ServicoManutencao> smlist = new ArrayList<ServicoManutencao>(); 
		while(it.hasNext())
		{
			ServicoManutencao sm = (ServicoManutencao) it.next();
			
			if (sm.getAtendente().getNome().equals(Atendente))
			{
				
				smlist.add(sm);
			}
			
		}
		return smlist;
	}
	
	public List<ServicoInternet> servicosInternetAtendente(List<ServicoInternet> servicos, String Atendente)
	{
		Iterator it = servicos.iterator();
		List<ServicoInternet> silist = new ArrayList<ServicoInternet>(); 
		while(it.hasNext())
		{
			ServicoInternet si = (ServicoInternet) it.next();
			
			if (si.getAtendente().getNome().equals(Atendente))
			{
				
				silist.add(si);
			}
			
		}
		return silist;
	}
	
	public List<ServicoRede> servicosRedeAtendente(List<ServicoRede> servicos, String Atendente)
	{
		Iterator it = servicos.iterator();
		List<ServicoRede> srlist = new ArrayList<ServicoRede>(); 
		while(it.hasNext())
		{
			ServicoRede sr = (ServicoRede) it.next();
			
			if (sr.getAtendente().getNome().equals(Atendente))
			{
				srlist.add(sr);
			}
			
		}
		return srlist;
	}
	
	public List<ServicoTelefone> servicosTelefoneAtendente(List<ServicoTelefone> servicos, String Atendente)
	{
		Iterator it = servicos.iterator();
		List<ServicoTelefone> srlist = new ArrayList<ServicoTelefone>(); 
		while(it.hasNext())
		{
			ServicoTelefone sr = (ServicoTelefone) it.next();
			
			if (sr.getAtendente().getNome().equals(Atendente))
			{
				srlist.add(sr);
			}
			
		}
		return srlist;
	}
	
	public List<ServicoEmail> servicosEmailAtendente(List<ServicoEmail> servicos, String Atendente)
	{
		Iterator it = servicos.iterator();
		List<ServicoEmail> selist = new ArrayList<ServicoEmail>(); 
		while(it.hasNext())
		{
			ServicoEmail se = (ServicoEmail) it.next();
			
			if (se.getAtendente().getNome().equals(Atendente))
			{
				selist.add(se);
			}
			
		}
		return selist;
	}
	
	
	public List<ServicoRede> servicosRede(List<ServicoRede> servicos, int flag)
	{
		Iterator it = servicos.iterator();
		List<ServicoRede> smlist = new ArrayList<ServicoRede>(); 
		while(it.hasNext())
		{
			ServicoRede sm = (ServicoRede) it.next();
			switch(flag) {
			case 0:
				if(sm.getStatus().getDescricao().equals("Aberto"))
				{
					smlist.add(sm);
				}
			break;
			case 1:
				if(sm.getStatus().getDescricao().equals("Em andamento"))
				{
					smlist.add(sm);
				}
			break;
			case 2:
				if(sm.getStatus().getDescricao().equals("Fechado"))
				{
					smlist.add(sm);
				}
			break;
			}
			
		}
		return smlist;
	}
	
	public List<ServicoRede> servicosRede(List<ServicoRede> servicos, int flag, String Atendente)
	{
		Iterator it = servicos.iterator();
		List<ServicoRede> smlist = new ArrayList<ServicoRede>(); 
		while(it.hasNext())
		{
			ServicoRede sm = (ServicoRede) it.next();
			switch(flag) {
			case 0:
				if(sm.getStatus().getDescricao().equals("Aberto")  && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			case 1:
				if(sm.getStatus().getDescricao().equals("Em andamento")  && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			case 2:
				if(sm.getStatus().getDescricao().equals("Fechado")  && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			}
			
		}
		return smlist;
	}
	
	public List<ServicoInternet> servicosInternet(List<ServicoInternet> servicos, int flag)
	{
		Iterator it = servicos.iterator();
		List<ServicoInternet> smlist = new ArrayList<ServicoInternet>(); 
		while(it.hasNext())
		{
			ServicoInternet sm = (ServicoInternet) it.next();
			switch(flag) {
			case 0:
				if(sm.getStatus().getDescricao().equals("Aberto"))
				{
					smlist.add(sm);
				}
			break;
			case 1:
				if(sm.getStatus().getDescricao().equals("Em andamento"))
				{
					smlist.add(sm);
				}
			break;
			case 2:
				if(sm.getStatus().getDescricao().equals("Fechado"))
				{
					smlist.add(sm);
				}
			break;
			}
			
		}
		return smlist;
	}
	
	public List<ServicoInternet> servicosInternet(List<ServicoInternet> servicos, int flag, String Atendente)
	{
		Iterator it = servicos.iterator();
		List<ServicoInternet> smlist = new ArrayList<ServicoInternet>(); 
		while(it.hasNext())
		{
			ServicoInternet sm = (ServicoInternet) it.next();
			switch(flag) {
			case 0:
				if(sm.getStatus().getDescricao().equals("Aberto") && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			case 1:
				if(sm.getStatus().getDescricao().equals("Em andamento") && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			case 2:
				if(sm.getStatus().getDescricao().equals("Fechado") && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			}
			
		}
		return smlist;
	}
	
	public List<ServicoEmail> servicosEmail(List<ServicoEmail> servicos, int flag)
	{
		Iterator it = servicos.iterator();
		List<ServicoEmail> smlist = new ArrayList<ServicoEmail>(); 
		while(it.hasNext())
		{
			ServicoEmail sm = (ServicoEmail) it.next();
			switch(flag) {
			case 0:
				if(sm.getStatus().getDescricao().equals("Aberto"))
				{
					smlist.add(sm);
				}
			break;
			case 1:
				if(sm.getStatus().getDescricao().equals("Em andamento"))
				{
					smlist.add(sm);
				}
			break;
			case 2:
				if(sm.getStatus().getDescricao().equals("Fechado"))
				{
					smlist.add(sm);
				}
			break;
			}
			
		}
		return smlist;
	}
	
	public List<ServicoEmail> servicosEmail(List<ServicoEmail> servicos, int flag, String Atendente)
	{
		Iterator it = servicos.iterator();
		List<ServicoEmail> smlist = new ArrayList<ServicoEmail>(); 
		while(it.hasNext())
		{
			ServicoEmail sm = (ServicoEmail) it.next();
			switch(flag) {
			case 0:
				if(sm.getStatus().getDescricao().equals("Aberto") && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			case 1:
				if(sm.getStatus().getDescricao().equals("Em andamento") && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			case 2:
				if(sm.getStatus().getDescricao().equals("Fechado") && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			}
			
		}
		return smlist;
	}
	
	public List<ServicoTelefone> servicosTelefone(List<ServicoTelefone> servicos, int flag)
	{
		Iterator it = servicos.iterator();
		List<ServicoTelefone> smlist = new ArrayList<ServicoTelefone>(); 
		while(it.hasNext())
		{
			ServicoTelefone sm = (ServicoTelefone) it.next();
			switch(flag) {
			case 0:
				if(sm.getStatus().getDescricao().equals("Aberto"))
				{
					smlist.add(sm);
				}
			break;
			case 1:
				if(sm.getStatus().getDescricao().equals("Em andamento"))
				{
					smlist.add(sm);
				}
			break;
			case 2:
				if(sm.getStatus().getDescricao().equals("Fechado"))
				{
					smlist.add(sm);
				}
			break;
			}
			
		}
		return smlist;
	}
	
	public List<ServicoTelefone> servicosTelefone(List<ServicoTelefone> servicos, int flag, String Atendente)
	{
		Iterator it = servicos.iterator();
		List<ServicoTelefone> smlist = new ArrayList<ServicoTelefone>(); 
		while(it.hasNext())
		{
			ServicoTelefone sm = (ServicoTelefone) it.next();
			switch(flag) {
			case 0:
				if(sm.getStatus().getDescricao().equals("Aberto")  && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			case 1:
				if(sm.getStatus().getDescricao().equals("Em andamento")  && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			case 2:
				if(sm.getStatus().getDescricao().equals("Fechado")  && sm.getAtendente().getNome().equals(Atendente))
				{
					smlist.add(sm);
				}
			break;
			}
			
		}
		return smlist;
	}
	
	public List<ServicoManutencao> manutencaoPorPeriodo(List<ServicoManutencao> manutencao, String data)
	{
		
		List<ServicoManutencao> servicosManutencaoPeriodo = new ArrayList<ServicoManutencao>();
	Iterator it = manutencao.iterator();
	while(it.hasNext())
	{
		ServicoManutencao serv = (ServicoManutencao) it.next();
		if(serv.getData_ocorrencia().toString().contains(data))
		{
			servicosManutencaoPeriodo.add(serv);
		}
	}	
	//List<Computador> todosComputadores = computadores.findAll();
		return servicosManutencaoPeriodo;
	}
	
	public List<ServicoInternet> internetPorPeriodo(List<ServicoInternet> internet, String data)
	{
		
		List<ServicoInternet> servicosInternetPeriodo = new ArrayList<ServicoInternet>();
	Iterator it = internet.iterator();
	while(it.hasNext())
	{
		ServicoInternet serv = (ServicoInternet) it.next();
		if(serv.getData_ocorrencia().toString().contains(data))
		{
			servicosInternetPeriodo.add(serv);
		}
	}	
	//List<Computador> todosComputadores = computadores.findAll();
		return servicosInternetPeriodo;
	}
	
	public List<ServicoRede> redePorPeriodo(List<ServicoRede> rede, String data)
	{
		
		List<ServicoRede> servicosRedePeriodo = new ArrayList<ServicoRede>();
	Iterator it = rede.iterator();
	while(it.hasNext())
	{
		ServicoRede serv = (ServicoRede) it.next();
		if(serv.getData_ocorrencia().toString().contains(data))
		{

			servicosRedePeriodo.add(serv);
		}
	}	
	//List<Computador> todosComputadores = computadores.findAll();
		return servicosRedePeriodo;
	}
	
	public List<ServicoTelefone> telefonePorPeriodo(List<ServicoTelefone> telefone, String data)
	{
		
		List<ServicoTelefone> servicosTelefonePeriodo = new ArrayList<ServicoTelefone>();
	Iterator it = telefone.iterator();
	while(it.hasNext())
	{
		ServicoTelefone serv = (ServicoTelefone) it.next();
		if(serv.getData_ocorrencia().toString().contains(data))
		{
			
			servicosTelefonePeriodo.add(serv);
		}
	}	
	//List<Computador> todosComputadores = computadores.findAll();
		return servicosTelefonePeriodo;
	}
	
	public List<ServicoEmail> emailPorPeriodo(List<ServicoEmail> email, String data)
	{
		
		List<ServicoEmail> servicosEmailPeriodo = new ArrayList<ServicoEmail>();
	Iterator it = email.iterator();
	while(it.hasNext())
	{
		ServicoEmail serv = (ServicoEmail) it.next();
		if(serv.getData_ocorrencia().toString().contains(data))
		{
			servicosEmailPeriodo.add(serv);
		}
	}	
	//List<Computador> todosComputadores = computadores.findAll();
		return servicosEmailPeriodo;
	}
	
	@ModelAttribute("home_teci")
	public boolean homeTECI() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_TECI") || AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_CADASTRAR_SERVICO");
	}
}
