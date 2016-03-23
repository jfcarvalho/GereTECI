package com.teci.gereteci.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Licenca.LicencaOffice;

public interface LicencasOffice extends JpaRepository<LicencaOffice, Integer>{

}
