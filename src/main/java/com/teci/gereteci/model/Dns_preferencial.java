package com.teci.gereteci.model;

public enum Dns_preferencial {
	
	retiro("10.95.2.1"),
	automatico("Automático"),
	dnspPadrao("10.95.1.2");
	
	private String dns_preferencial;


 Dns_preferencial(String dns_preferencial)
{
	this.dns_preferencial= dns_preferencial;
}

public String getDns_preferencial()
{
	return dns_preferencial;
}

public void setDns_preferencial(String dns_preferencial)
{
	this.dns_preferencial = dns_preferencial;
}

}