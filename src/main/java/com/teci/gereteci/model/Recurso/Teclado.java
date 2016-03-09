package com.teci.gereteci.model.Recurso;

public class Teclado extends Recurso {
	
	private String tipo_teclado;
	private boolean funcoes;
	private String cor;
	
	public String getTipo_teclado()
	{
		return this.tipo_teclado;
	}
	
	public boolean getFuncoes()
	{
		return this.funcoes;
	}
	
	public void setTipo_teclado(String tipo_teclado)
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
