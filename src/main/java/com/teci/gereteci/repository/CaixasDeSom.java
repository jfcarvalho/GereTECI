package com.teci.gereteci.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Recurso.CaixaDeSom;


public interface CaixasDeSom extends JpaRepository<CaixaDeSom, Integer>{

}
