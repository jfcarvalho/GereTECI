package com.teci.gereteci.model.Servico;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("Manutenção")
public class ServicoManutencao extends Servico {
	@Enumerated(EnumType.STRING)
	private DescricaoProntaMicro descricao_pronta;
	private boolean recolhimento_equipamento;
	private boolean troca_recursos;
	private String recurso_recolhido;
	private String recurso_substituto;
	
	
	public void setDescricao_pronta(DescricaoProntaMicro descricao_pronta)
	{
		this.descricao_pronta = descricao_pronta;
	}
	public DescricaoProntaMicro getDescricao_pronta()
	{
		return this.descricao_pronta;
	}
	public boolean getRecolhimento_equipamento()
	{
		return this.recolhimento_equipamento; 
	}
	public void setRecolhimento_equipamento(boolean recolhimento_equipamento)
	{
		this.recolhimento_equipamento = recolhimento_equipamento; 
	}
	
	public boolean getTroca_recursos()
	{
		return this.troca_recursos; 
	}
	public void setTroca_recursos(boolean troca_recursos)
	{
		this.troca_recursos = troca_recursos; 
	}
	
	public String getRecurso_recolhido()
	{
		return this.recurso_recolhido; 
	}
	public void setRecurso_recolhido(String recurso_trocado)
	{
		this.recurso_recolhido= recurso_trocado; 
	}
	public String getRecurso_substituto()
	{
		return this.recurso_substituto; 
	}
	public void setRecurso_substituto(String recurso_substituto)
	{
		this.recurso_substituto= recurso_substituto; 
	}
	
}
