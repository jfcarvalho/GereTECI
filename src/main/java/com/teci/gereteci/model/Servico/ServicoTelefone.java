package com.teci.gereteci.model.Servico;

import java.util.List;

import com.teci.gereteci.model.Usuario.Usuario;

public class ServicoTelefone extends Servico {
	private List<Usuario> usuarios;
	private DescricaoProntaTelefone descricaoPronta;
	
	public void setUsuarios(List<Usuario> usuarios)
	{
		this.usuarios= usuarios;
	}
	public List<Usuario> getUsuarios()
	{
		return this.usuarios;
	}
	
	public void setDescricaoPronta(DescricaoProntaTelefone descricaoPronta)
	{
		this.descricaoPronta = descricaoPronta;
	}
	public DescricaoProntaTelefone getDescricaoPronta()
	{
		return this.descricaoPronta;
	}
}
