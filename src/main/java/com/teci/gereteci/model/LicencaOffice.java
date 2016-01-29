package com.teci.gereteci.model;



import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name="licenca_office")

public class LicencaOffice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_licencaoffice;
	
	@Size(min=1, max=20, message="O tamanho do campo nome tem que ser entre 1 e 20")
	private String nome;
	@Size(min=3, max=20, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String chave;
	@Temporal(TemporalType.DATE)
	private Date data_compra;
	@Temporal(TemporalType.DATE)
	private Date data_expira;
	@Enumerated(EnumType.STRING)
	private PlanoOffice plano;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "computador_has_licenca_office", joinColumns = { @JoinColumn(name="licenca_office_id_licencaoffice")}, inverseJoinColumns={ @JoinColumn (name = "computador_id_computador") })
	private List<Computador> computadores;
	
	
	public Integer getId_licencaoffice()
	{
		return this.id_licencaoffice;
	}
	
	public void setId_licencaoffice(Integer id_licencaoffice)
	{
		this.id_licencaoffice= id_licencaoffice;
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
	public PlanoOffice getPlano()
	{
		return this.plano;
	}
	public void setPlano(PlanoOffice plano)
	{
		this.plano = plano;
	}
	public  List<Computador> getComputadores()
	{
		return this.computadores;
	}
	public void setComputadores( List<Computador> computadores)
	{
		this.computadores = computadores;
	}
}
