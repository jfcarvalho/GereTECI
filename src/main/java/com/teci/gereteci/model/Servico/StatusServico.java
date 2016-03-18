package com.teci.gereteci.model.Servico;

public enum StatusServico {
	aberto("Aberto"),
	em_andamento("Em andamento"),
	fechado("Fechado");
	
	
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
