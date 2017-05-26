package com.teci.gereteci.model.Mensagem;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Requisicao.Requisicao;
import com.teci.gereteci.model.Usuario.Usuario;

@Entity
public class Mensagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_mensagem;
	
	@OneToOne
	@JoinColumn(name="mensagem_id_de")
	private Usuario de;
	@OneToOne
	@JoinColumn(name="mensagem_id_para")
	private Usuario para;
	
	private String corpo;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="mensagem_id_requisicao")
	private Requisicao requisicao;

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	public Integer getId_mensagem() {
		return id_mensagem;
	}

	public void setId_mensagem(Integer id_mensagem) {
		this.id_mensagem = id_mensagem;
	}
	
	
	public Usuario getDe() {
		return de;
	}

	public void setDe(Usuario de) {
		this.de = de;
	}
	
	public Usuario getPara() {
		return para;
	}

	public void setPara(Usuario para) {
		this.para = para;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
	
	
	
}
