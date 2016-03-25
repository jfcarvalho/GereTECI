package com.teci.gereteci.model.Recurso;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Teclado")
public class Teclado extends Recurso {
	
	
	private TipoES tipo_teclado;
	private boolean funcoes;
	private String cor;
	
	public TipoES getTipo_teclado()
	{
		return this.tipo_teclado;
	}
	
	public boolean getFuncoes()
	{
		return this.funcoes;
	}
	
	public void setTipo_teclado(TipoES tipo_teclado)
	{
		this.tipo_teclado= tipo_teclado;
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
