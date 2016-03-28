package com.teci.gereteci.model.Recurso;

public enum TipoES {
	usb("USB"),
	ps2("PS2"),
	outros("Outro");
	
	private String tipo;
	
	TipoES(String tipo)
	{
		this.tipo = tipo;
	}
	
	public String getTipo()
	{
		return tipo;
	}
	
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}
}
