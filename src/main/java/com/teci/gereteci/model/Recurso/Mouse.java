package com.teci.gereteci.model.Recurso;

import javax.persistence.Entity;

@Entity
public class Mouse extends Recurso {
	
	private String tipo_mouse; //Se é usb o ps2
	private boolean rolagem;
	private String cor;
	
	public String getTipo_mouse()
	{
		return this.tipo_mouse;
	}
	
	public boolean getRolagem()
	{
		return this.rolagem;
	}
	
	public String getCor()
	{
		return this.cor;
	}
	
	
	public void setTipo_mouse(String tipo_mouse)
	{
		this.tipo_mouse = tipo_mouse;
	}
	public void setRolagem(boolean rolagem)
	{
		this.rolagem = rolagem;
	}
	public void setCor(String cor)
	{
		this.cor = cor;
	}

}
