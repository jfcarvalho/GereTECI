package com.teci.gereteci.model.Usuario;



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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Setor.Setor;
import java.lang.Comparable;
@Entity


public class Usuario implements Comparable<Usuario>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_usuario;
	
	@Size(min=1, max=100, message="O tamanho do campo matricula tem que ser entre 1 e 20")
	private String matricula;
	@Size(min=3, max=100, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String nome;
	@Size(min=3, max=100, message="O tamanho do campo cargo tem que ser entre 3 e 20")
	private String cargo; 


	
	//private Integer setor_id_setor;
	
	@Enumerated(EnumType.STRING)
	private Nivel nivel_acesso; 
	@Size(min=3, max=100, message="O tamanho do campo telefone tem que ser entre 3 e 20")
	private String telefone;
	@Size(min=3, max=100, message="O tamanho do campo email tem que ser entre 3 e 20")
	private String email;
	private String password;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="setor_id_setor")
	private Setor setor;
	@OneToMany(mappedBy="solicitado", fetch = FetchType.LAZY,  cascade = {CascadeType.ALL})
	private List<Servico> servicos_manutencao;	
	@Size(min=3, max=100, message="O tamanho do campo telefone tem que ser entre 3 e 20")
	private String usuario_rede;
	@OneToOne
	@JoinColumn(name="computador_id_computador")
	private Computador computador;
	
	@ManyToMany
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "codigo_usuario")
				, inverseJoinColumns = @JoinColumn(name = "codigo_grupo"))	
	private List<Grupo> grupos;
	
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
	

	public Nivel getNivel_acesso() {
		return this.nivel_acesso;
	}
	
	public void setNivel_acesso(Nivel nivel)
	{
		this.nivel_acesso = nivel;
	}
	
	public String getTelefone()
	{
		return this.telefone;
	}
	
	public void setTelefone(String telefone)
	{
		this.telefone= telefone;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(String email)
	{
		this.email= email;
	}
	
	public Setor getSetor()
	{
		return this.setor;
	}
	public void setSetor(Setor setor)
	{
		this.setor = setor;
	}
	
	public String getUsuario_rede()
	{
		return this.usuario_rede;
	}
	
	public void setUsuario_rede(String usuario_rede)
	{
		this.usuario_rede= usuario_rede;
	}
	
	public Computador getComputador()
	{
		return this.computador;
	}
	public void setComputador(Computador computador)
	{
		this.computador = computador;
	}
	public List<Servico> getServicos_manutencao()
	{
		return this.servicos_manutencao;
	}
	public void setServicos_manutencao(List<Servico> servicos)
	{
		this.servicos_manutencao = servicos;
	}

	@Override
	public int compareTo(Usuario user) {
		if(user.getNome().compareTo(this.nome) == 0) {
			return -1;
		}
		
			return 0;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
}
