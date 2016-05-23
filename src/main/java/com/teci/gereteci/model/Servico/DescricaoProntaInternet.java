package com.teci.gereteci.model.Servico;

public enum DescricaoProntaInternet {
	desc1("Problema no Switch(Exclamação amarela)"),
	desc2("Problema no Switch(X vermelho)"),
	desc3("Problema no Proxy"),
	desc4("Problema na página da CTB"),
	desc5("Atualização de conteúdo da página da CTB"),
	desc6("Suporte FIPLAN");
	
	private String descricao;
	
	DescricaoProntaInternet(String descricao)
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
