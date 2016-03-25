package com.teci.gereteci.model.Usuario;

public enum Nivel {
	ADMINISTRADOR("Administrador"),
	USUARIOTECI("Usuário TECI"),
	USUARIOPADRAO("Usuário Padrão");
	
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