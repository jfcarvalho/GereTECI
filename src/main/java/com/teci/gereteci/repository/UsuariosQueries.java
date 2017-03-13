package com.teci.gereteci.repository;

import java.util.List;
import java.util.Optional;

import com.teci.gereteci.model.Usuario.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> porEmail(String email);
	public List<String> permissoes(Usuario usuario);

	
}
