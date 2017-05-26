package com.teci.gereteci.model.Requisicao;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.teci.gereteci.model.Avaliacao.Avaliacao;
import com.teci.gereteci.model.Mensagem.Mensagem;
import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Usuario.Usuario;

@Entity
@DiscriminatorValue("Requisicao")

public class Requisicao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id_requisicao;
	
	@OneToOne
	@JoinColumn(name="requisicao_id_requisitante")
	private Usuario requisitante;
	@OneToOne
	@JoinColumn(name="requisicao_id_atendente")
	private Usuario atendente;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	
	private Date data_abertura;
	private Date data_fechamento;
	
	private String problema;
	private String protocolo;
	
	private Integer tipo;
	@OneToOne
	@JoinColumn(name="requisicao_id_servico")
	private Servico servico;
	
	@OneToOne
	@JoinColumn(name="requisicao_id_avaliacao")
	private Avaliacao avaliacao;

	
	@OneToMany(mappedBy="requisicao", fetch = FetchType.LAZY,  cascade = {CascadeType.ALL})
	private List<Mensagem> mensagens;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	
	
	
	

	
	public Usuario getRequisitante() {
		return requisitante;
	}
	public void setRequisitante(Usuario requisitante) {
		this.requisitante = requisitante;
	}
	public Usuario getAtendente() {
		return atendente;
	}
	public void setAtendente(Usuario atendente) {
		this.atendente = atendente;
	}
	public Date getData_abertura() {
		return data_abertura;
	}
	public void setData_abertura(Date data_abertura) {
		this.data_abertura = data_abertura;
	}
	public Date getData_fechamento() {
		return data_fechamento;
	}
	public void setData_fechamento(Date data_fechamento) {
		this.data_fechamento = data_fechamento;
	}
	public String getProblema() {
		return problema;
	}
	public void setProblema(String problema) {
		this.problema = problema;
	}
	
	public Integer getId_requisicao()
	{
		return this.id_requisicao;
	}
	
	public void setId_requisicao(Integer id_requisicao)
	{
		this.id_requisicao = id_requisicao;
	}
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Servico getServico()
	{
		return this.servico;
	}
	
	public void setServico(Servico servico)
	{
		this.servico = servico;
	}
	
	public List<Mensagem> getMensagens()
	{
		return this.mensagens;
	}
	
	public void setMensagens(List<Mensagem> mensagens)
	{
		this.mensagens = mensagens;
	}
	
	public Avaliacao getAvaliacao()
	{
		return this.avaliacao;
	}
	public void setAvaliacao(Avaliacao avaliacao)
	{
		this.avaliacao = avaliacao;
	}
	
	public Date getData()
	{
		return this.data;
	}

	public void setData(Date data)
	{
		
		this.data = data;
	}
}
