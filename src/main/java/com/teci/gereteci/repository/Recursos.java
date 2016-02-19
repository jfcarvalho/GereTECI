package com.teci.gereteci.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Computador.Recurso;

public interface Recursos extends JpaRepository<Recurso, Integer>{

}
