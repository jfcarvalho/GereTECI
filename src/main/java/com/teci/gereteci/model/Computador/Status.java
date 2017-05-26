package com.teci.gereteci.model.Computador;

public enum Status {
	
	funcionando("Funcionando normalmente"),
	manutencao("Em manutenção"),
	com_defeito_para("Baixa");
	
	private String status;
	
	Status(String status)
	{
		this.status= status;
	}
	
	public String getStatus()
	{
		return this.status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
}
