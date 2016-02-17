package com.teci.gereteci.model.Computador;

public enum CategoriaRecurso {
	monitor("Monitor"),
	mouse("Mouse"),
	teclado("Teclado"),
	midia_removivel("Mídia removível"),
	caixa_de_som("Caixa de Som"),
	fone("Fone"),
	outros("Outros");
	
	
	private String categoria;
	
	CategoriaRecurso(String categoria)
	{
		this.categoria = categoria;
	}
	
	public String getCategoria()
	{
		return categoria;
	}
	
	public void setCategoria(String categoria)
	{
		this.categoria= categoria;
	}
}

