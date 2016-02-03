package com.teci.gereteci.model;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

@Entity


public class Impressora {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_impressora;
	
	@Size(min=1, max=100, message="O tamanho do campo nome tem que ser entre 1 e 20")
	private String patrimonio;
	@Size(min=3, max=100, message="O tamanho do campo cargo tem que ser entre 3 e 20")
	private String marca;
	@Size(min=3, max=100, message="O tamanho do campo cargo tem que ser entre 3 e 20")
	private String ip;
	@Enumerated(EnumType.STRING)
	private Gateway gateway;
	@Enumerated(EnumType.STRING)
	private Dns_preferencial dns_preferencial;
	@Enumerated(EnumType.STRING)
	private Dns_alternativo dns_alternativo;
	@Enumerated(EnumType.STRING)
	private Mascara mascara;
	
	private StatusComputador status;
	@ManyToMany(mappedBy="impressoras")
	private List<Computador> computadores;
	
	public Integer getId_impressora()
	{
		return this.id_impressora;
	}
	
	public void setId_impressora(Integer id_impressora)
	{
		this.id_impressora= id_impressora;
	}
	
	public String getPatrimonio()
	{
		return this.patrimonio;
	}
	
	public void setPatrimonio(String patrimonio)
	{
		this.patrimonio = patrimonio;
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
	public List<Computador> getComputadores()
	{
		return this.computadores;
	}
	public void setComputadores(List<Computador> computadores)
	{
		this.computadores = computadores;
	}
}
