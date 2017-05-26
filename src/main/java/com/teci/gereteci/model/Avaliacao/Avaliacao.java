package com.teci.gereteci.model.Avaliacao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.teci.gereteci.model.Requisicao.Requisicao;
import com.teci.gereteci.model.Usuario.Usuario;

@Entity

public class Avaliacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_avaliacao;
	private String nota_atendimento;
	private String observacao;
	
	@OneToOne
	@JoinColumn(name="avaliacao_id_requisicao")
	
	private Requisicao requisicao;

	public Integer getId_avaliacao()
	{
		return this.id_avaliacao;
	}
	
	public void setId_avaliacao(Integer id_avaliacao)
	{
		this.id_avaliacao = id_avaliacao;
	}
	
	public String getNota_atendimento() {
		return nota_atendimento;
	}

	public void setNota_atendimento(String nota_atendimento) {
		this.nota_atendimento = nota_atendimento;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
	
	

}
