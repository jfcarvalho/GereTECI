package com.teci.gereteci.model;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity


public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id_usuario;
	
	private String matricula;
	
	private String nome;
	
	private String cargo; 
	
	private Integer setor_id_setor; 
	@Enumerated(EnumType.STRING)
	
	private Nivel nivel_acesso; 
	
	
	
	//@Column
	//private String setor;
	
	/*
	public Usuario(){}
	public Usuario(String matricula, String nome, String setor, String cargo, Integer setor_id_setor) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.cargo = cargo;
		//this.setor_id_setor = setor_id_setor;
		//this.setor = setor;
	}
	*/
	public Integer getId_usuario()
	{
		return this.id_usuario;
	}
	
	public void setId_usuario(Integer id_usuario)
	{
		this.id_usuario = id_usuario;
	}
	
	public String getCargo()
	{
		return this.cargo;
	}
	
	public void setCargo(String cargo)
	{
		this.cargo = cargo;
	}
	public String getMatricula() {
		return this.matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer Setor_id_setor() {
		return this.setor_id_setor;
	}
	public void setSetor_id_setor(Integer setor_id_setor) {
		this.setor_id_setor= setor_id_setor;
	}
	
/*	public String getSetor() {
		return this.setor;
	}
	public void setSetor(String setor) {
		this.setor= setor;
	}
	
	*/
}