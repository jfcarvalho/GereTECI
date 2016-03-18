package com.teci.gereteci.model.Servico;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.teci.gereteci.model.Usuario.Usuario;

@Inheritance
@DiscriminatorColumn(name="categoria")
@Entity
public abstract class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id_servico;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data_ocorrencia;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data_encerramento;
	@ManyToOne
	@JoinColumn(name="usuario_id_usuario")
	private Usuario solicitado;
	
	@Size(min=1, max=100, message="O tamanho do campo nome tem que ser entre 1 e 20")
	private String descricao_problema;
	@Column(name = "categoria", insertable=false, updatable=false)
	private String categoria;
	@Enumerated(EnumType.STRING)
	private StatusServico status;
	private String protocolo;
	@OneToOne
	@JoinColumn(name="atendente")
	private Usuario atendente;
	private String descricao_solucao;
	
	public Integer getId_servico()
	{
		return this.id_servico;
	}
	public void setId_servico(Integer Id_servico)
	{
		this.id_servico = Id_servico;
	}
	public void setData_ocorrencia(Date dataOcorrencia)
	{
		this.data_ocorrencia = dataOcorrencia;
	}
	public Date getData_ocorrencia()
	{
		return this.data_ocorrencia;
	}
	
	
	public void setSolicitado(Usuario solicitado)
	{
		this.solicitado = solicitado;
	}
	public Usuario getSolicitado()
	{
		return this.solicitado;
	}
	public void setAtendente(Usuario atendente)
	{
		this.atendente = atendente;
	}
	public Usuario getAtendente()
	{
		return this.atendente;
	}

	public String getProtocolo()
	{
		return this.protocolo;
	}
	public void setProtocolo(String protocolo)
	{
		this.protocolo = protocolo;
	}
	public void setCategoria(String categoria)
	{
		this.categoria= categoria;
	}
	public String getCategoria()
	{
		return this.categoria;
	}
	public void setStatus(StatusServico status)
	{
		this.status = status;
	}
	public StatusServico getStatus()
	{
		return this.status;
	}
	
	public void setDescricao_problema(String descricaoProblema)
	{
		this.descricao_problema = descricaoProblema;
	}
	
	public String getDescricao_problema()
	{
		return this.descricao_problema;
	}

	public void setData_encerramento(Date dataEncerramento)
	{
		this.data_encerramento = dataEncerramento;
	}
	public Date getData_encerramento()
	{
		return this.data_encerramento;
	}
	
	public String getDescricao_solucao()
	{
		return this.descricao_solucao;
	}
	public void setDescricao_solucao(String solucao)
	{
		this.descricao_solucao = solucao;
	}
}
