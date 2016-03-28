package com.teci.gereteci.model.Internet;

public enum Gateway {

	gatewayPadrao("10.95.1.256"),
	automatico("Automático");
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