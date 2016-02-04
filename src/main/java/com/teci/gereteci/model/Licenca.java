package com.teci.gereteci.model;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity


public class Licenca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_licenca;
	
	@Size(min=1, max=100, message="O tamanho do campo nome tem que ser entre 1 e 20")
	private String nome;
	@Size(min=3, max=100, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String chave;
	@Temporal(TemporalType.DATE)
	private Date data_compra;
	@Temporal(TemporalType.DATE)
	private Date data_expira;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="computador_id_computador")
	private Computador computador;
	//private Integer computador_id_computador;
	
	public Integer getId_licenca()
	{
		return this.id_licenca;
	}
	
	public void setId_licenca(Integer id_licenca)
	{
		this.id_licenca= id_licenca;
	}
	
	public String getChave()
	{
		return this.chave;
	}
	
	public void setChave(String chave)
	{
		this.chave = chave;
	}
	public Date getData_compra() {
		return this.data_compra;
	}
	public void setData_compra(Date data_compra) {
		this.data_compra = data_compra;
	}
	public Date getData_expira() {
		return this.data_expira;
	}
	public void setData_expira(Date data_expira) {
		this.data_expira= data_expira;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Computador getComputador() {
		return this.computador;
	}
	public void setComputador(Computador computador) {
		
		this.computador = computador;
	}
	
}
