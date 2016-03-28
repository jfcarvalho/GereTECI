package com.teci.gereteci.model.Recurso;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Midia")

public class Midia extends Recurso {
	
	
	private TipoES tipoes;
	private String capacidade;
	
	public TipoES getTipoes()
	{
		return this.tipoes;
	}
	
	public String getCapacidade()
	{
		return this.capacidade;
	}
	
	public void setTipoes(TipoES tipoes)
	{
		this.tipoes= tipoes;
	}
	public void setCapacidade(String capacidade)
	{
		this.capacidade= capacidade;
	}

}
