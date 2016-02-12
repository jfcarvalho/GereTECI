package com.teci.gereteci.model.Servico;

public enum StatusServico {
	Aberto("Aberto"),
	Em_andamento("Em andamento"),
	Fechado("Fechado");
	
	
	private String descricao;
	
	StatusServico(String descricao)
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
