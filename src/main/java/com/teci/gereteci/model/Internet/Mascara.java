package com.teci.gereteci.model.Internet;

public enum Mascara {
	
	automatico("Automático"),
	mascaraPadrao("255.255.255.0");
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