package com.teci.gereteci.model.Servico;

import java.util.List;

import com.teci.gereteci.model.Computador.Computador;

public class ServicoRede extends Servico {
	private DescricaoProntaRede descricaoPronta;
	private List<Computador> computadores;
	
	public void setComputadores(List<Computador> computadores)
	{
		this.computadores = computadores;
	}
	public List<Computador> getComputadores()
	{
		return this.computadores;
	}
	
	public void setDescricaoPronta(DescricaoProntaRede descricaoPronta)
	{
		this.descricaoPronta = descricaoPronta;
	}
	public DescricaoProntaRede getDescricaoPronta()
	{
		return this.descricaoPronta;
	}
	
}
