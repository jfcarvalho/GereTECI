package com.teci.gereteci.model;

public enum StatusComputador {
	
	com_defeito_func("Defeito, porém funcionando"),
	com_defeito_para("Defeito e parado"),
	dando_baxa("Deu baixa"),
	funcionando("Funcionando normalmente");
	private String status;
	
	StatusComputador(String status)
	{
		this.status= status;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
}
