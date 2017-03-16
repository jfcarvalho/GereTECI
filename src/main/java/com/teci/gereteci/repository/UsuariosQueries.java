package com.teci.gereteci.repository;

import java.util.List;
import java.util.Optional;

import com.teci.gereteci.model.Usuario.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> porMatricula(String matricula);
	public List<String> permissoes(Usuario usuario);

	
}
