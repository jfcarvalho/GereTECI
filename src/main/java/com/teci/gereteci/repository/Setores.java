package com.teci.gereteci.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Setor.Setor;
import com.teci.gereteci.model.Usuario.Usuario;

public interface Setores extends JpaRepository<Setor, Integer>{
	List<Setor> findByNome(String nome);
	Setor findBySigla(String nome);
}
