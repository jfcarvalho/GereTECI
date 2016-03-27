package com.teci.gereteci.model.Recurso;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Mouse")
public class Mouse extends Recurso {
	
	private TipoES tipoes; //Se é usb o ps2
	private boolean rolagem;
	private String cor;
	
	public TipoES getTipoes()
	{
		return this.tipoes;
	}
	
	public boolean getRolagem()
	{
		return this.rolagem;
	}
	
	public String getCor()
	{
		return this.cor;
	}
	
	
	public void setTipoes(TipoES tipo_mouse)
	{
		this.tipoes = tipo_mouse;
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
