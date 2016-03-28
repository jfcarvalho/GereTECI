package com.teci.gereteci.model.Recurso;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Teclado")
public class Teclado extends Recurso {
	
	
	private TipoES tipoes;
	private boolean funcoes;
	private String cor;
	
	public TipoES getTipoes()
	{
		return this.tipoes;
	}
	
	public boolean getFuncoes()
	{
		return this.funcoes;
	}
	
	public void setTipoes(TipoES tipo_teclado)
	{
		this.tipoes= tipo_teclado;
	}
	public void setFuncoes(boolean funcoes)
	{
		this.funcoes = funcoes;
	}
	public String getCor()
	{
		return this.cor;
	}
	public void setCor(String cor)
	{
		this.cor = cor;
	}	

}
