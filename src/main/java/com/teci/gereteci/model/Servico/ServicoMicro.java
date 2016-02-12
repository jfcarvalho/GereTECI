package com.teci.gereteci.model.Servico;

import java.util.List;

import com.teci.gereteci.model.Computador.Computador;

public class ServicoMicro extends Servico {
	private DescricaoProntaMicro descricaoPronta;
	private List<Computador> computadores;
	
	public void setComputadores(List<Computador> computadores)
	{
		this.computadores = computadores;
	}
	public List<Computador> getComputadores()
	{
		return this.computadores;
	}
	
	public void setDescricaoPronta(DescricaoProntaMicro descricaoPronta)
	{
		this.descricaoPronta = descricaoPronta;
	}
	public DescricaoProntaMicro getDescricaoPronta()
	{
		return this.descricaoPronta;
	}
	
}
