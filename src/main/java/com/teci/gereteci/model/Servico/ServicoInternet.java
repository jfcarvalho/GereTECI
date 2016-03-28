package com.teci.gereteci.model.Servico;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
	private String visitante_oi;
	private String protocolo_oi;
	private String numero_rds
	private Date data_visita;
	private Date data_rds;
	
	public boolean getVisita_oi()
	{
		return this.visita_oi;
	}


	public boolean getRds_aberto()
	{
		return this.visita_oi;
	}

	public void setVisita_oi(boolean visita_oi)
	{
		this.visita_oi = visita_oi;
	}
	public void setRds_aberto(boolean rds_aberto)
	{
		this.rds_aberto = rds_aberto;
	}

	public String getVisitante_oi()
	{
		return this.visitante_oi;
	}

	public String getProtocolo_oi()
	{
		return this.protocolo_oi;
	}
	public void setProtocolo_oi(String protocolo_oi)
	{
		return this.protocolo_oi;
	}

	public String getNumero_rds()
	{
		return this.numero_rds;
	}

	public void setVisita_oi(boolean visita_oi)
	{
		this.visita_oi = visita_oi;
	}
	public void setRds_aberto(boolean rds_aberto)
	{
		this.rds_aberto = rds_aberto;
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
