package com.teci.gereteci.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Usuario.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Integer>{
	List<Usuario> findByNomeContaining(String nome);
}
