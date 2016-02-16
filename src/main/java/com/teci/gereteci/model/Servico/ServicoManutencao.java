package com.teci.gereteci.model.Servico;

import java.util.List;

import com.teci.gereteci.model.Usuario.Usuario;

public class ServicoManutencao extends Servico {
	private DescricaoProntaMicro descricaoPronta;
	
	
	public void setDescricaoPronta(DescricaoProntaMicro descricaoPronta)
	{
		this.descricaoPronta = descricaoPronta;
	}
	public DescricaoProntaMicro getDescricaoPronta()
	{
		return this.descricaoPronta;
	}
	
}
