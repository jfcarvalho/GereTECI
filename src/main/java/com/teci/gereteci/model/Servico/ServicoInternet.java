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
	private String Ipantigo;
	private String Ipnovo;
	
	
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
