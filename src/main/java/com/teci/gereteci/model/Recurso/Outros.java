package com.teci.gereteci.model.Recurso;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Outros")

public class Outros extends Recurso {
	
	
	private TipoES tipoes;
	private String especificacoes;
	
	public TipoES getTipoes()
	{
		return this.tipoes;
	}
	
	public String getEspecificacoes()
	{
		return this.especificacoes;
	}
	
	public void setTipoes(TipoES tipoes)
	{
		this.tipoes= tipoes;
	}
	public void setEspecificacoes(String especificacoes)
	{
		this.especificacoes= especificacoes;
	}

}
