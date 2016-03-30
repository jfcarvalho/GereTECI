package com.teci.gereteci.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.teci.gereteci.repository.ServicosEmail;
import com.teci.gereteci.repository.ServicosInternet;
import com.teci.gereteci.repository.ServicosManutencao;
import com.teci.gereteci.repository.ServicosRede;
import com.teci.gereteci.repository.ServicosTelefone;
import com.teci.gereteci.repository.Usuarios;

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
	private static final String RELATORIO_PATH = "/relatorios/RelatorioMensal";
	
	@RequestMapping("/relatoriomensal")
	public ModelAndView relatorio()
	{
		ModelAndView mv = new ModelAndView(RELATORIO_PATH);
		List<ServicoManutencao> manutencao = manutencoes.findAll();
		List<ServicoInternet> internet = sinternet.findAll();
		List<ServicoRede> redes = sredes.findAll();
		List<ServicoEmail> emails = semail.findAll();
		List<ServicoTelefone> telefones = stelefone.findAll();
		
		int numeroManutencoes = manutencao.size();
		int numeroInternet = internet.size();
		int numeroRedes = redes.size();
		int numeroEmail = emails.size();
		int numeroTelefone = telefones.size();
		
		int servicostotais = numeroManutencoes + numeroInternet + numeroRedes + numeroEmail + numeroTelefone;
		mv.addObject("n_total", servicostotais);
		mv.addObject("n_manutencao", numeroManutencoes);
		mv.addObject("n_internet", numeroInternet);
		mv.addObject("n_email", numeroEmail);
		mv.addObject("n_telefone", numeroTelefone);
		mv.addObject("n_redes", numeroRedes);
		
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
	
	/*public  edicao(@PathVariable("id_servico") ServicoManutencao servicoManutencao)
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
	*/
}
