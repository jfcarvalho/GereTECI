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
import com.teci.gereteci.model.Usuario.Usuario;

@Entity
@DiscriminatorValue("Email")

public class ServicoEmail extends Servico {

	private boolean criacao_usuario;
	private boolean alteracao_usuario;
	private boolean exclusao_usuario;
	private boolean troca_senha;
	private boolean mudanca_plano;
	private boolean rds_aberto;
	private String conta;
	private String numero_rds;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data_rds;
	@Enumerated(EnumType.STRING)
	private DescricaoProntaEmail descricao_pronta;
	
		
	public void setDescricao_pronta(DescricaoProntaEmail descricao_pronta)
	{
		this.descricao_pronta = descricao_pronta;
	}
	public DescricaoProntaEmail getDescricao_pronta()
	{
		return this.descricao_pronta;
	}
	public boolean getCriacao_usuario()
	{
		return this.criacao_usuario;
	}
	public void setCriacao_usuario(boolean criacao_usuario)
	{
		this.criacao_usuario = criacao_usuario;
	}
	public boolean getExclusao_usuario()
	{
		return this.exclusao_usuario;
	}
	public void setExclusao_usuario(boolean exclusao_usuario)
	{
		this.exclusao_usuario = exclusao_usuario;
	}
	public boolean getAlteracao_usuario()
	{
		return this.alteracao_usuario;
	}
	public boolean getTroca_senha()
	{
		return this.troca_senha;
	}
	public void setTroca_senha(boolean troca_senha)
	{
		this.troca_senha = troca_senha;
	}
	public void setAlteracao_usuario(boolean alteracao_usuario)
	{
		this.alteracao_usuario = alteracao_usuario;
	}
	
	public boolean getMudanca_plano()
	{
		return this.mudanca_plano;
	}
	public void setMudanca_plano(boolean mudanca_plano)
	{
		this.mudanca_plano = mudanca_plano;
	}
	
	public String getConta()
	{
		return this.conta;
	}
	public void setConta(String usuario)
	{
		this.conta = usuario;
	}
	
	public String getNumero_rds()
	{
		return this.numero_rds;
	}
	
	public void setRds_aberto(boolean rds_aberto)
	{
		this.rds_aberto = rds_aberto;
	}

	public void setNumero_rds(String numero_rds)
	{
		this.numero_rds = numero_rds;
	}
	public boolean getRds_aberto()
	{
		return this.rds_aberto;
	}
	
	public Date getData_rds()
	{
		return this.data_rds;
	}
	
	public void setData_rds(Date data_rds)
	{
		this.data_rds = data_rds;
	}
	
}
