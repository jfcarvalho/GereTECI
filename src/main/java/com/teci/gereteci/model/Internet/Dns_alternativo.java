package com.teci.gereteci.model.Internet;

public enum Dns_alternativo {
	
	automatico("Automático"),
	nenhum("Nenhum"),
	dnsaPadrao("10.2.8.187");
	private String dns_alternativo;


 Dns_alternativo(String dns_alternativo)
{
	this.dns_alternativo = dns_alternativo;
}

public String getDns_alternativo()
{
	return this.dns_alternativo;
}

public void setDns_alternativo(String dns_alternativo)
{
	this.dns_alternativo= dns_alternativo;
}

}