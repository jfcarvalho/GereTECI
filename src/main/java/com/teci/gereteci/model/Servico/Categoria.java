package com.teci.gereteci.model.Servico;

public enum Categoria {
	Manutencao("64 bits"),
	atendimentoInterno("32 bits"),
	atendimentoExterno("32 bits"),
	atendimentoOutros("32 bits");
	
	private String descricao;
	
	Categoria(String descricao)
	{
		this.descricao= descricao;
	}
	
	public String getDescricao()
	{
		return descricao;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao= descricao;
	}
}
