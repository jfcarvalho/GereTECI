package com.teci.gereteci.model.Servico;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Usuario.Usuario;

@Entity
@DiscriminatorValue("Apoio")

public class ServicoApoio extends Servico {

	private boolean deslocamento_usuario;
	private String setor;
	private String horario;
	private boolean deslocamento_recurso;
	private String recurso;
		
		
	public void setDeslocamento_usuario(boolean deslocamento_usuario)
	{
		this.deslocamento_usuario = deslocamento_usuario;
	}
	public boolean getDeslocamento_usuario()
	{
		return this.deslocamento_usuario;
	}
	
	public String getSetor()
	{
		return this.setor;
	}
	
	public void setSetor(String setor)
	{
		this.setor = setor;
	}
	
	public String getHorario()
	{
		return this.horario;
	}
	
	public void setHorario(String horario)
	{
		this.horario = horario;
	}
	
	public String getRecurso()
	{
		return this.recurso;
	}
	
	public void setRecurso(String recurso)
	{
		this.recurso = recurso;
	}
	
	public void setDeslocamento_recurso(boolean deslocamento_recurso)
	{
		this.deslocamento_recurso= deslocamento_recurso;
	}
	public boolean getDeslocamento_recurso()
	{
		return this.deslocamento_recurso;
	}
	
	
}
