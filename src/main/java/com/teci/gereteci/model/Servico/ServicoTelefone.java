package com.teci.gereteci.model.Servico;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.teci.gereteci.model.Usuario.Usuario;

@Entity
@DiscriminatorValue("Telefone")

public class ServicoTelefone extends Servico {
	@Enumerated(EnumType.STRING)
	private DescricaoProntaTelefone descricao_pronta;
	private boolean troca_ramal;
	private boolean visita_oi;
	private String nome_tecnico;
	@Temporal(TemporalType.DATE)
	private Date data_visita;
	private String protocolo_servico;
	private String ramais_trocados;
	 
	public void setDescricao_pronta(DescricaoProntaTelefone descricao_pronta)
	{
		this.descricao_pronta = descricao_pronta;
	}
	public DescricaoProntaTelefone getDescricao_pronta()
	{
		return this.descricao_pronta;
	}
	public void setTroca_ramal(boolean troca_ramal)
	{
		this.troca_ramal = troca_ramal;
	}
	public void setVisita_oi(boolean visita_oi)
	{
		this.visita_oi = visita_oi;
	}
	
	public boolean getVisita_oi()
	{
		return this.visita_oi;
	}
	
	public boolean getTroca_ramal()
	{
		return this.troca_ramal;
	}
	public String getNome_tecnico()
	{
		return this.nome_tecnico;
	}
	public void setNome_tecnico(String nome_tecnico)
	{
		this.nome_tecnico = nome_tecnico;
	}
	public Date getData_visita()
	{
		return this.data_visita;
	}
	public void setData_visita(Date data_visita)
	{
		this.data_visita = data_visita;
	}
	public String getProtocolo_servico()
	{
		return this.protocolo_servico;
	}
	public void setProtocolo_servico(String protocolo_servico)
	{
		this.protocolo_servico = protocolo_servico;
	}
	public String getRamais_trocados()
	{
		return this.ramais_trocados;
	}
	public void setRamais_trocados(String ramais_trocados)
	{
		this.ramais_trocados = ramais_trocados;
	}
}
