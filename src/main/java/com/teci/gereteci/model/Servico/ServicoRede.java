package com.teci.gereteci.model.Servico;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.teci.gereteci.model.Computador.Computador;

@Entity
@DiscriminatorValue("Rede")

public class ServicoRede extends Servico {
	private boolean criacao_usuario;
	private boolean alteracao_usuario;
	private boolean exclusao_usuario;
	private boolean senha_expirada;
	private String usuario;
	
	@Enumerated(EnumType.STRING)
	private DescricaoProntaRede descricao_pronta;
	
		
	public void setDescricao_pronta(DescricaoProntaRede descricao_pronta)
	{
		this.descricao_pronta = descricao_pronta;
	}
	public DescricaoProntaRede getDescricao_pronta()
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
	public boolean getSenha_expirada()
	{
		return this.senha_expirada;
	}
	public void setSenha_expirada(boolean senha_expirada)
	{
		this.senha_expirada = senha_expirada;
	}
	public void setAlteracao_usuario(boolean alteracao_usuario)
	{
		this.alteracao_usuario = alteracao_usuario;
	}
	
	public String getUsuario()
	{
		return this.usuario;
	}
	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}
	
	
	
}
