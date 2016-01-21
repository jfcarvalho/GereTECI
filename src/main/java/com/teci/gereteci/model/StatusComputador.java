package com.teci.gereteci.model;

public enum StatusComputador {
	funcionando("Funcionando normalmente"),
	com_defeito_func("Defeito, porém funcionando"),
	com_defeito_para("Defeito e parado");
	
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
