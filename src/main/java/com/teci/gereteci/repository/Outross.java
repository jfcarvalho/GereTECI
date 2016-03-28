package com.teci.gereteci.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Recurso.Midia;
import com.teci.gereteci.model.Recurso.Outros;
import com.teci.gereteci.model.Recurso.Recurso;

public interface Outross extends JpaRepository<Outros, Integer>{

}
