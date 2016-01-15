package com.teci.gereteci.model;

public enum Nivel {
	ADMINISTRADOR("Administrador"),
	USUARIOTECI("UsuarioTECI"),
	USUARIOPADRAO("UsuarioPadrao");
	
	private String nivel;


 Nivel(String nivel)
{
	this.nivel = nivel;
}

public String getNivel()
{
	return nivel;
}

public void setNivel(String nivel)
{
	this.nivel = nivel;
}

}