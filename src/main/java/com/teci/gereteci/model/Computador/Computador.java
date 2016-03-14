package com.teci.gereteci.model.Computador;



import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;



import org.springframework.format.annotation.DateTimeFormat;

import com.teci.gereteci.model.Impressora.Impressora;
import com.teci.gereteci.model.Internet.Dns_alternativo;
import com.teci.gereteci.model.Internet.Dns_preferencial;
import com.teci.gereteci.model.Internet.Gateway;
import com.teci.gereteci.model.Internet.Mascara;
import com.teci.gereteci.model.Licenca.LicencaOffice;
import com.teci.gereteci.model.Recurso.CaixaDeSom;
import com.teci.gereteci.model.Recurso.Monitor;
import com.teci.gereteci.model.Recurso.Mouse;
import com.teci.gereteci.model.Recurso.Teclado;
import com.teci.gereteci.model.Usuario.Usuario;


@Entity


public class Computador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id_computador;
	
	@Size(min=1, max=100, message="O tamanho do campo nome tem que ser entre 1 e 20")
	private String patrimonio;
	@Enumerated(EnumType.STRING)
	private Sistema sistema;
	@Enumerated(EnumType.STRING)
	private Arquitetura arquitetura;
	@Size(min=3, max=100, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String ip;
	@Enumerated(EnumType.STRING)
	private Mascara mascara;
	@Enumerated(EnumType.STRING)
	private Gateway gateway;
	@Enumerated(EnumType.STRING)
	private Dns_alternativo dns_alternativo;
	@Enumerated(EnumType.STRING)
	private Dns_preferencial dns_preferencial;
	@Size(min=1, max=100, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String versao_java;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data_compra;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data_backup;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data_formatacao;
	@Size(min=3, max=100, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String id_impressao;
	@Enumerated(EnumType.STRING)
	private StatusComputador status;
	//private Integer usuario_id_usuario;
	@Size(min=1, max=100, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String processador;
	@Enumerated(EnumType.STRING)
	private Memoria memoria;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "computador_has_impressora", joinColumns = { @JoinColumn(name="computador_id_computador")}, inverseJoinColumns={ @JoinColumn (name = "impressora_id_impressora") })
	private List<Impressora> impressoras;
	@OneToOne
	@JoinColumn(name="id_recurso_monitor")
	private Monitor recurso_monitor1;
	@OneToOne
	@JoinColumn(name="id_recurso_teclado")
	private Teclado recurso_teclado;
	@OneToOne
	@JoinColumn(name="id_recurso_mouse")
	private Mouse recurso_mouse;
	@OneToOne
	@JoinColumn(name="id_recurso_caixa")
	private CaixaDeSom recurso_caixa;
	/*@OneToOne
	@JoinColumn(name="id_recurso")
	private Monitor recurso_monitor2;
	@OneToOne
	@JoinColumn(name="id_recurso")
	private Teclado recurso_teclado;
	@OneToOne
	@JoinColumn(name="id_recurso")
	private Mouse recurso_mouse;
	@OneToOne
	@JoinColumn(name="id_recurso")
	private CaixaDeSom recurso_caixa;
	*/
	/*@ManyToMany(mappedBy="computadores")
	
	private List<LicencaOffice> licencasOffice;
	*/
	/*@OneToMany(mappedBy="computador")
	private List<Recurso> recursos;
	*/
	@OneToOne
	@JoinColumn(name="usuario_id_usuario")
	private Usuario usuario;
	

	
	public Integer getId_computador()
	{
		return this.id_computador;
	}
	
	public void setId_computador(Integer id_computador)
	{
		this.id_computador = id_computador;
	}
	
	public String getPatrimonio()
	{
		return this.patrimonio;
	}
	
	public void setPatrimonio(String patrimonio)
	{
		this.patrimonio = patrimonio;
	}
	
	public Arquitetura getArquitetura()
	{
		return this.arquitetura;
	}
	
	public void setArquitetura(Arquitetura arquitetura)
	{
		this.arquitetura = arquitetura;
	}
	
	public Sistema getSistema() {
		return this.sistema;
	}
	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
	
	public String getIp() {
		return this.ip;
	}
	public void setIp(String ip) {
		this.ip= ip;
	}
	public Gateway getGateway() {
		return this.gateway;
	}
	public void setGateway(Gateway gateway) {
		this.gateway= gateway;
	}
	public Mascara getMascara() {
		return this.mascara;
	}
	public void setMascara(Mascara mascara) {
		this.mascara = mascara;
	}
	public Dns_alternativo getDns_alternativo() {
		return this.dns_alternativo;
	}
	public void setDns_alternativo(Dns_alternativo dns_alternativo) {
		this.dns_alternativo = dns_alternativo;
	}
	public Dns_preferencial getDns_preferencial() {
		return this.dns_preferencial;
	}
	public void setDns_preferencial(Dns_preferencial dns_preferencial) {
		this.dns_preferencial = dns_preferencial;
	}
	public String getVersao_java() {
		return this.versao_java;
	}
	public void setVersao_java(String versao_java) {
		this.versao_java = versao_java;
	}
	
	public Date getData_compra() {
		return this.data_compra;
	}
	public void setData_compra(Date data_compra) {
		this.data_compra = data_compra;
	}
	public Date getData_backup() {
		return this.data_backup;
	}
	public void setData_backup(Date data_backup) {
		this.data_backup = data_backup;
	}
	public Date getData_formatacao() {
		return this.data_formatacao;
	}
	public void setData_formatacao(Date data_formatacao) {
		this.data_formatacao = data_formatacao;
	}
	
	public String getId_impressao() {
		return this.id_impressao;
	}
	
	public void setId_impressao(String id_impressao)
	{
		this.id_impressao = id_impressao;
	}
	
	public StatusComputador getStatus() {
		return this.status;
	}
	
	public void setStatus(StatusComputador status)
	{
		this.status = status;
	}
	/*
	public Integer getUsuario_id_usuario()
	{
		return this.usuario_id_usuario;
	}
	
	public void setUsuario_id_usuario(Integer usuario_id_usuario)
	{
		this.usuario_id_usuario = usuario_id_usuario;
	}
	*/
	public Memoria getMemoria() {
		return this.memoria;
	}
	
	public void setMemoria(Memoria memoria)
	{
		this.memoria = memoria;
	}
	public String getProcessador() {
		return this.processador;
	}
	
	public void setProcessador(String processador)
	{
		this.processador = processador;
	}
	public List<Impressora> getImpressoras()
	{
		return this.impressoras;
	}
	public void setImpressoras(List<Impressora> impressoras)
	{
		this.impressoras = impressoras;
	}
	/*
	public List<LicencaOffice> getLicencasOffice()
	{
		return this.licencasOffice;
	}
	
	public void setLicencasOffice(List<LicencaOffice> licencasOffice)
	{
		this.licencasOffice = licencasOffice;
	}
	
	*/
	/*
	public List<Recurso> getRecursos()
	{
		return this.recursos;
	}
	
	public void setRecursos(List<Recurso> recursos)
	{
		this.recursos = recursos;
	}
	*/
	public Usuario getUsuario()
	{
		return this.usuario;
	}
	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}
	public Monitor getRecurso_monitor1()
	{
		return this.recurso_monitor1;
	}
	public void setRecurso_monitor1(Monitor recurso_monitor)
	{
		this.recurso_monitor1= recurso_monitor;
	}
	
	public Teclado getRecurso_teclado()
	{
		return this.recurso_teclado;
	}
	public void setRecurso_teclado(Teclado recurso_teclado)
	{
		this.recurso_teclado= recurso_teclado;
	}
	public Teclado getRecurso_mouse()
	{
		return this.recurso_teclado;
	}
	public void setRecurso_mouse(Mouse recurso_mouse)
	{
		this.recurso_mouse= recurso_mouse;
	}
	public Teclado getRecurso_caixa()
	{
		return this.recurso_teclado;
	}
	public void setRecurso_caixa(CaixaDeSom recurso_caixa)
	{
		this.recurso_caixa= recurso_caixa;
	}
	
}
