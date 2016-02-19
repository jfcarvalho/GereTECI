package com.teci.gereteci.model.Servico;

public enum Categoria {
	Manutencao("Manutenção"),
	atendimentoInterno("Atendimento Interno"),
	atendimentoExterno("Atendimento Externo"),
	atendimentoOutros("Outros");
	
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
