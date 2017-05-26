package com.teci.gereteci.model.Servico;

public enum DescricaoProntaOutro {
	desc1("Instalação de Projetor"),
	desc2("Empréstimo de material"),
	desc3("Outros");
	
	
	private String descricao;
	
	DescricaoProntaOutro(String descricao)
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
