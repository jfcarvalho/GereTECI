package com.teci.gereteci.model;

public enum Gateway {

	automatico("Automático"),
	gatewayPadrao("10.95.1.256");
	private String gateway;


 Gateway(String gateway)
{
	this.gateway= gateway;
}

public String getGateway()
{
	return gateway;
}

public void setGateway(String gateway)
{
	this.gateway = gateway;
}

}