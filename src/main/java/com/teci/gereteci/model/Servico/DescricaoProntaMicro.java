package com.teci.gereteci.model.Servico;

public enum DescricaoProntaMicro {
	desc1("Recurso com problema"),
	desc2("Problema na placa ou driver de rede"),
	desc3("Java desatualizado"),
	desc4("SIMPAS desatualizado"),
	desc5("Impressora infuncional"),
	desc6("Computador fora da Internet"),
	desc7("Computador fora da Rede Interna"),
	desc8("Computador fora da Internet e Rede Interna"),
	desc9("Trocar senha do email corporativo"),
	desc10("Trocar senha de acesso a rede"),
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
