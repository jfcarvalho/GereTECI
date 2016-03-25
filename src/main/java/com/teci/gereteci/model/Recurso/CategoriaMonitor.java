package com.teci.gereteci.model.Recurso;

public enum CategoriaMonitor {
	primario("Primário"),
	secundario("Secundário");
	
	private String categoria;
	
	CategoriaMonitor(String categoria)
	{
		this.categoria = categoria;
	}
	
	public String getCategoria()
	{
		return categoria;
	}
	
	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}
}
