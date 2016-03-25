package com.teci.gereteci.model.Internet;

public enum Mascara {
	
	mascaraPadrao("255.255.255.0"),
	automatico("Automático");
	
	private String mascara;


 Mascara(String mascara)
{
	this.mascara= mascara;
}

public String getMascara()
{
	return mascara;
}

public void setMascara(String mascara)
{
	this.mascara = mascara;
}

}