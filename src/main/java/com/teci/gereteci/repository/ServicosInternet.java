package com.teci.gereteci.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;



public interface ServicosInternet extends JpaRepository<ServicoInternet, Integer>{

}
