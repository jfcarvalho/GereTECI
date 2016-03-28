package com.teci.gereteci.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Licenca.Licenca;

public interface Licencas extends JpaRepository<Licenca, Integer>{

}
