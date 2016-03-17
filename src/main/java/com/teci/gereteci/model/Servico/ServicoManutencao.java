package com.teci.gereteci.model.Servico;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Manutenção")
public class ServicoManutencao extends Servico {
	private DescricaoProntaMicro descricaoPronta;
	private boolean recolhimentoEquipamento;
	private boolean trocaRecursos;
	private String recursoTrocado;
	
	
	public void setDescricaoPronta(DescricaoProntaMicro descricaoPronta)
	{
		this.descricaoPronta = descricaoPronta;
	}
	public DescricaoProntaMicro getDescricaoPronta()
	{
		return this.descricaoPronta;
	}
	public boolean getRecolhimentoEquipamento()
	{
		return this.recolhimentoEquipamento; 
	}
	public void setRecolhimentoEquipamento(boolean recolhimentoEquipamento)
	{
		this.recolhimentoEquipamento = recolhimentoEquipamento; 
	}
	
	public boolean getTrocaRecursos()
	{
		return this.trocaRecursos; 
	}
	public void setTrocaRecursos(boolean trocaRecursos)
	{
		this.trocaRecursos = trocaRecursos; 
	}
	
	public String getRecursoTrocado()
	{
		return this.recursoTrocado; 
	}
	public void setRecursoTrocado(String recursoTrocado)
	{
		this.recursoTrocado= recursoTrocado; 
	}
	
}
