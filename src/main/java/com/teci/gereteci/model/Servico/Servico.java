package com.teci.gereteci.model.Servico;

import java.util.Date;
import java.util.List;

import com.teci.gereteci.model.Usuario.Usuario;

public abstract class Servico {
	private Date dataOcorrencia;
	private List<Usuario> solicitados;
	private Usuario atendente;
	private String descricaoProblema;
	private Categoria categoria;
	private StatusServico status;
	private String identificador;
	
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
