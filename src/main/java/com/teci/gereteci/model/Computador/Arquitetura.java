package com.teci.gereteci.model.Computador;

public enum Arquitetura {
	x64Bits("64 bits"),
	x32Bits("32 bits");
	
	private String arquitetura;
	
	Arquitetura(String arquitetura)
	{
		this.arquitetura = arquitetura;
	}
	
	public String getArquitetura()
	{
		return arquitetura;
	}
	
	public void setArquitetura(String arquitetura)
	{
		this.arquitetura = arquitetura;
	}
}
