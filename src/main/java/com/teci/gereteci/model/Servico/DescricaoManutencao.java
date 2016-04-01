package com.teci.gereteci.model.Servico;



public enum DescricaoManutencao {
	desc1("Recurso com defeito"),
	desc2("Atualização de software"),
	desc3("Software não funcionando corretamente"),
	desc4("Instalação de Software"),
	desc5("Hardware"),
	desc6("Outros");
	
	private String descricao;
	
	DescricaoManutencao(String descricao)
	{
		this.descricao= descricao;
	}
	
	public String getDescricao()
	{
		return this.descricao;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao= descricao;
	}
}
