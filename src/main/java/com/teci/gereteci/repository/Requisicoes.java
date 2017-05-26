package com.teci.gereteci.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Recurso.Monitor;
import com.teci.gereteci.model.Recurso.Recurso;
import com.teci.gereteci.model.Requisicao.Requisicao;

public interface Requisicoes extends JpaRepository<Requisicao, Integer>{

}
