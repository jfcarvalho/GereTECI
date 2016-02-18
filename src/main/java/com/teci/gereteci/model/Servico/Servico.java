package com.teci.gereteci.model.Servico;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.teci.gereteci.model.Usuario.Usuario;

@Entity
public abstract class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id_servico;
	private Date dataOcorrencia;
	@OneToMany(mappedBy="servico")
	private List<Usuario> solicitados;
	
	@Size(min=1, max=100, message="O tamanho do campo nome tem que ser entre 1 e 20")
	private String descricaoProblema;
	
	private Categoria categoria;
	private StatusServico status;
	private String identificador;
	@OneToOne
	@JoinColumn(name="atendente")
	private Usuario atendente;
	
	public void setDataOcorrencia(Date dataOcorrencia)
	{
		this.dataOcorrencia = dataOcorrencia;
	}
	public Date getDataOcorrencia()
	{
		return this.dataOcorrencia;
	}
	public void setSolicitados(List<Usuario> solicitados)
	{
		this.solicitados = solicitados;
	}
	public List<Usuario> getSolicitados()
	{
		return this.solicitados;
	}
	public void setAtendente(Usuario atendente)
	{
		this.atendente = atendente;
	}
	public Usuario getAtendente()
	{
		return this.atendente;
	}
	public void setIdentificador(String identificador)
	{
		this.identificador = identificador;
	}
	public String getIdentificador()
	{
		return this.identificador;
	}
	public void setCategoria(Categoria categoria)
	{
		this.categoria= categoria;
	}
	public Categoria getCategoria()
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
	
	public void setDescricaoProblem(String descricaoProblema)
	{
		this.descricaoProblema = descricaoProblema;
	}
	
	public String getDescricaoProblema()
	{
		return this.descricaoProblema;
	}

	
	
}
