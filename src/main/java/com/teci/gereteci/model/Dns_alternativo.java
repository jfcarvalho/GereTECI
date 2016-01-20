package com.teci.gereteci.model;

public enum Dns_alternativo {
	dnspPadrao("10.2.8.187"),
	automatico("Automático");
	private String dns_alternativo;


 Dns_alternativo(String dns_alternativo)
{
	this.dns_alternativo = dns_alternativo;
}

public String getDns_alternativo()
{
	return dns_alternativo;
}

public void setDns_alternativo(String dns_alternativo)
{
	this.dns_alternativo= dns_alternativo;
}

}