package com.teci.gereteci.model.Recurso;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Monitor")

public class Monitor extends Recurso {
	
	
	private String polegadas;
	private boolean dvi;
	@Enumerated(EnumType.STRING)
	private CategoriaMonitor categoria_monitor;
	
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
	
	public CategoriaMonitor getCategoria_monitor()
	{
		return this.categoria_monitor;
	}
	
	public void setCategoria_monitor(CategoriaMonitor categoria_monitor)
	{
		this.categoria_monitor = categoria_monitor;
	}

}
