package com.teci.gereteci.model.Servico;

import java.util.List;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Computador.Recurso;

public class ServicoInternet extends Servico {
	private DescricaoProntaInternet descricaoPronta;
	private List<Computador> computadores;
	private List<Recurso> recursos;
	
	public void setComputadores(List<Computador> computadores)
	{
		this.computadores = computadores;
	}
	public List<Computador> getComputadores()
	{
		return this.computadores;
	}
	
	public void setDescricaoPronta(DescricaoProntaInternet descricaoPronta)
	{
		this.descricaoPronta = descricaoPronta;
	}
	public DescricaoProntaInternet getDescricaoPronta()
	{
		return this.descricaoPronta;
	}
}
