package com.teci.gereteci.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity


public class Computador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id_computador;
	
	@Size(min=1, max=20, message="O tamanho do campo nome tem que ser entre 1 e 20")
	private String patrimonio;
	@Size(min=3, max=20, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String sistema;
	@Size(min=3, max=20, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String arquitetura;
	@Size(min=3, max=20, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String versao;
	@Size(min=3, max=20, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String ip;
	@Size(min=3, max=20, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String mascara;
	@Size(min=3, max=20, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String gateway;
	@Size(min=3, max=20, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String dns_alternativo;
	@Size(min=3, max=20, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String dns_preferencial;
	@Size(min=3, max=20, message="O tamanho do campo nome tem que ser entre 3 e 20")
	private String versao_java;
	@Temporal(TemporalType.DATE)
	private Date data_compra;
	@Temporal(TemporalType.DATE)
	private Date data_backup;
	@Temporal(TemporalType.DATE)
	private Date data_formatacao;
	
	
	
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
	
	public String getArquitetura()
	{
		return this.arquitetura;
	}
	
	public void setArquitetura(String arquitetura)
	{
		this.arquitetura = arquitetura;
	}
	
	public String getSistema() {
		return this.sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getVersao() {
		return versao;
	}
	public void setVersao(String versao) {
		this.versao = versao;
	}
	public String getIp() {
		return this.ip;
	}
	public void setIp(String ip) {
		this.ip= ip;
	}
	public String getGateway() {
		return this.gateway;
	}
	public void setGateway(String gateway) {
		this.gateway= gateway;
	}
	public String getMascara() {
		return this.mascara;
	}
	public void setMascara(String mascara) {
		this.mascara = mascara;
	}
	public String getDns_alternativo() {
		return this.dns_alternativo;
	}
	public void setDns_alternativo(String dns_alternativo) {
		this.dns_alternativo = dns_alternativo;
	}
	public String getDns_preferencial() {
		return this.dns_preferencial;
	}
	public void setDns_preferencial(String dns_preferencial) {
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
	
	
	
	
}
