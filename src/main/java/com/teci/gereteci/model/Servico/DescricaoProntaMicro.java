package com.teci.gereteci.model.Servico;

public enum DescricaoProntaMicro {
	desc1("Recurso com problema"),
	desc2("Problema na placa ou driver de rede"),
	desc3("Java desatualizado"),
	desc4("SIMPAS desatualizado"),
	desc5("Impressora infuncional"),
	desc11("Outros");
	
	private String descricao;
	
	DescricaoProntaMicro(String descricao)
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
