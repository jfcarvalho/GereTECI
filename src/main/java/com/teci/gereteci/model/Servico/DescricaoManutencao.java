package com.teci.gereteci.model.Servico;



public enum DescricaoManutencao {
	desc1("Recurso com defeito"),
	desc2("Problema na placa ou driver de rede"),
	desc3("Java desatualizado"),
	desc4("SIMPAS desatualizado"),
	desc5("Impressora infuncional"),
	desc11("Atualização de software"),
	desc12("Software não funcionando corretamente"),
	desc13("Instalação de Software"),
	desc14("Hardware"),
	desc15("Outros");
	
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
