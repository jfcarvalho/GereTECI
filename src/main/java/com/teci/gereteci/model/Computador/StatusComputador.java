package com.teci.gereteci.model.Computador;

public enum StatusComputador {
	
	manutencao("Em manutenção"),
	com_defeito_para("Defeito e parado"),
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
