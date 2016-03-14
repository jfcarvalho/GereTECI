package com.teci.gereteci.model.Recurso;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Midia extends Recurso {
	
	
	private String tipo_midia;
	private String capacidade;
	
	public String getTipo_midia()
	{
		return this.tipo_midia;
	}
	
	public String getCapacidade()
	{
		return this.capacidade;
	}
	
	public void setTipo_midia(String tipo_midia)
	{
		this.tipo_midia= tipo_midia;
	}
	public void setCapacidade(String capacidade)
	{
		this.capacidade= capacidade;
	}

}
