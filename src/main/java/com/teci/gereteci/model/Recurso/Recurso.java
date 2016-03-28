package com.teci.gereteci.model.Recurso;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.teci.gereteci.model.Computador.CategoriaRecurso;
import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Computador.StatusComputador;

@Entity
@Inheritance
@DiscriminatorColumn(name="TIPO_RECURSO")
@Table(name="recurso")

public abstract class Recurso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id_recurso;
	
	@Size(min=1, max=100, message="O tamanho do campo nome tem que ser entre 1 e 20")
	private String patrimonio;
	@Size(min=1, max=100, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String descricao;
	@Size(min=1, max=100, message="O tamanho do campo cargo tem que ser entre 3 e 20")
	private String marca;
	@Enumerated(EnumType.STRING)
	private StatusComputador status;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@JoinColumn(name="computador_id_computador")
	private Computador computador;
	private CategoriaRecurso categoria;
	@Column(name = "TIPO_RECURSO", insertable=false, updatable=false)
	private String tipo_recurso;
	
	
	public Integer getId_recurso()
	{
		return this.id_recurso;
	}
	
	public void setId_recurso(Integer Id_recurso)
	{
		this.id_recurso = Id_recurso;
	}
	
	public String getPatrimonio()
	{
		return this.patrimonio;
	}
	
	public void setPatrimonio(String patrimonio)
	{
		this.patrimonio = patrimonio;
	}
	public String getDescricao() {
		return this.descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getMarca() {
		return this.marca;
	}
	public void setMarca(String marca) {
		this.marca= marca;
	}
	
	public StatusComputador getStatus() {
		return this.status;
	}
	public void setStatus(StatusComputador status) {
		this.status= status;
	}
	public Computador getComputador()
	{
		return this.computador;
	}
	public void setComputador(Computador computador)
	{
		this.computador = computador;
	}
	public CategoriaRecurso getCategoria()
	{
		return this.categoria;
	}
	public void setCategoria(CategoriaRecurso categoria)
	{
		this.categoria = categoria;
	}
	public String getTipo_recurso() {
		return this.tipo_recurso;
	}
	public void setTipo_recurso(String tipo_recurso) {
		this.tipo_recurso= tipo_recurso;
	}
	
}
