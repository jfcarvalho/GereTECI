package com.teci.gereteci.model;

public enum Memoria {
	m128("128 MB"),
	m256("256 MB"),
	m512("512 MB"),
	m1g("1 GB"),
	m2g("2 GB"),
	m3g("3 GB"),
	m4g("4 GB");
	
	private String memoria;
	
	Memoria(String memoria)
	{
		this.memoria= memoria;
	}
	
	public String getMemoria()
	{
		return memoria;
	}
	
	public void setMemoria(String memoria)
	{
		this.memoria = memoria;
	}
}
