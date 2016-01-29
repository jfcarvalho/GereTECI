package com.teci.gereteci.model;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Size;

@Entity


public class Setor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_setor;
	@Size(min=1, max=70, message="O tamanho do campo Nome do setor tem que ser entre 1 e 20")
	private String sigla;
	@Size(min=1, max=70, message="O tamanho do campo nome tem que ser entre 1 e 20")
	private String nome;
	private Integer responsavel; 
	@OneToMany(mappedBy="setorUsuario")
	private List<Usuario> usuarios;
	
	
	
	
	public Integer getId_setor()
	{
		return this.id_setor;
	}
	
	public void setId_setor(Integer id_setor)
	{
		this.id_setor = id_setor;
	}
	
	public String getSigla()
	{
		return this.sigla;
	}
	
	public void setSigla(String sigla)
	{
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public Integer getResponsavel()
	{
		return this.responsavel;
	}
	
	public void setResponsavel(Integer responsavel)
	{
		this.responsavel = responsavel;
	}
	
	
	
	
	
}
