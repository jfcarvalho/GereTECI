package com.teci.gereteci.model.Servico;


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
