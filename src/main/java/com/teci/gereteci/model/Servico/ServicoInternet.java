package com.teci.gereteci.model.Servico;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Recurso.Recurso;

@Entity
@DiscriminatorValue("Internet")
public class ServicoInternet extends Servico {
	@Enumerated(EnumType.STRING)
	private DescricaoProntaInternet descricao_pronta;
	private boolean trocaip;
	private boolean proxy;
	private boolean visita_oi;
	private boolean rds_aberto;
	private String Ipantigo;
	private String Ipnovo;
	private String nome_tecnico;
	private String protocolo_servico;
	private String numero_rds;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data_visita;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)

	private Date data_rds;
	
	public boolean getVisita_oi()
	{
		return this.visita_oi;
	}
	
	public Date getData_visita()
	{
		return this.data_visita;
	}
	
	public void setData_visita(Date data_visita)
	{
		this.data_visita = data_visita; 
	}

	public boolean getRds_aberto()
	{
		return this.rds_aberto;
	}

	public void setVisita_oi(boolean visita_oi)
	{
		this.visita_oi = visita_oi;
	}
	public void setRds_aberto(boolean rds_aberto)
	{
		this.rds_aberto = rds_aberto;
	}

	public void setNumero_rds(String numero_rds)
	{
		this.numero_rds = numero_rds;
	}
	public String getNome_tecnico()
	{
		return this.nome_tecnico;
	}
	
	public void setNome_tecnico(String nome_tecnico)
	{
		this.nome_tecnico = nome_tecnico;
	}

	public String getProtocolo_servico()
	{
		return this.protocolo_servico;
	}
	public void setProtocolo_servico(String protocolo_servico)
	{
		this.protocolo_servico = protocolo_servico;
	}

	public String getNumero_rds()
	{
		return this.numero_rds;
	}

	public void setDescricao_pronta(DescricaoProntaInternet descricaoPronta)
	{
		this.descricao_pronta = descricaoPronta;
	}
	public DescricaoProntaInternet getDescricao_pronta()
	{
		return this.descricao_pronta;
	}
	
	public boolean getTrocaip()
	{
		return this.trocaip;
	}
	
	public void setTrocaip(boolean trocaip)
	{
		this.trocaip = trocaip;
	}
	
	public Date getData_rds()
	{
		return this.data_rds;
	}
	
	public void setData_rds(Date data_rds)
	{
		this.data_rds = data_rds;
	}
	
	public String getIpantigo()
	{
		return this.Ipantigo;
	}
	public void setIpantigo(String Ipantigo)
	{
		this.Ipantigo = Ipantigo;
	}
	public String getIpnovo()
	{
		return this.Ipnovo;
	}
	public void setIpnovo(String Ipnovo)
	{
		this.Ipnovo = Ipnovo;
	}
	
	public boolean getProxy()
	{
		return this.proxy;
	}
	public void setProxy(boolean proxy)
	{
		this.proxy = proxy;
	}
	
}
