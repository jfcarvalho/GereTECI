package com.teci.gereteci.model.Recurso;

public class Monitor extends Recurso {
	
	private String polegadas;
	private boolean dvi;
	
	public String getPolegadas()
	{
		return this.polegadas;
	}
	
	public boolean getDvi()
	{
		return this.dvi;
	}
	
	public void setPolegadas(String polegadas)
	{
		this.polegadas = polegadas;
	}
	public void setDvi(boolean dvi)
	{
		this.dvi = dvi;
	}

}
