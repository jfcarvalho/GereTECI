package com.teci.gereteci.model.Setor;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.validation.constraints.Size;

import com.teci.gereteci.model.Usuario.Usuario;

@Entity


public class Setor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_setor;
	@Size(min=1, max=100, message="O tamanho do campo Nome do setor tem que ser entre 1 e 20")
	private String sigla;
	@Size(min=1, max=100, message="O tamanho do campo nome tem que ser entre 1 e 20")
	private String nome;
	@OneToMany(mappedBy="setor")
	private List<Usuario> usuarios;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="responsavel")
	private Usuario responsavel;
	
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
	
	public Usuario getResponsavel()
	{
		return this.responsavel;
	}
	
	public void setResponsavel(Usuario responsavel)
	{
		this.responsavel = responsavel;
	}
	
	public List<Usuario> getUsuarios()
	{
		return this.usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios)
	{
		this.usuarios = usuarios;
	}
	
	
	
	
}
