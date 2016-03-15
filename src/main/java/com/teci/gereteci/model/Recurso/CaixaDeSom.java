package com.teci.gereteci.model.Recurso;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("CaixaDeSom")
public class CaixaDeSom extends Recurso {
	
	private String tipo_cs;
	private String cor;
	
	public String getTipo_cs()
	{
		return this.tipo_cs;
	}
	
	public String getCor()
	{
		return this.cor;
	}
	
	public void setTipo_cs(String tipo_cs)
	{
		this.tipo_cs= tipo_cs;
	}
	public void setCor(String cor)
	{
		this.cor= cor;
	}

}
