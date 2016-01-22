package com.teci.gereteci.model;

public enum PlanoOffice {
	Basico("Básico"),
	Intermediario("Intermediário"),
	Avancado("Avançado"),
	AvancadoProdeb("Avançado Prodeb");
	private String plano;
	
	PlanoOffice(String plano)
	{
		this.plano = plano;
	}
	
	public String getPlano()
	{
		return plano;
	}
	
	public void setPlano(String plano)
	{
		this.plano = plano;
	}
}
