package com.teci.gereteci.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Impressora.Impressora;

public interface Impressoras extends JpaRepository<Impressora, Integer>{

}
