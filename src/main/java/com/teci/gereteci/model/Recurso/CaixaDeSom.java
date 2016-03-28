package com.teci.gereteci.model.Recurso;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("CaixaDeSom")
public class CaixaDeSom extends Recurso {
	
	private String tipoes;
	private String cor;
	
	public String getTipoes()
	{
		return this.tipoes;
	}
	
	public String getCor()
	{
		return this.cor;
	}
	
	public void setTipoes(String tipo_cs)
	{
		this.tipoes= tipo_cs;
	}
	public void setCor(String cor)
	{
		this.cor= cor;
	}

}
