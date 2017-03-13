package com.teci.gereteci.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Setor.Setor;
import com.teci.gereteci.model.Usuario.Nivel;
import com.teci.gereteci.model.Usuario.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Integer>, UsuariosQueries{
	List<Usuario> findByNomeContaining(String nome);
	List<Usuario> findBySetor(Setor setor);
	Usuario findByEmail(String email);
	
}
