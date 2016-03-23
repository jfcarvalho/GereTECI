package com.teci.gereteci.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;


import com.teci.gereteci.model.Servico.ServicoManutencao;



public interface ServicosManutencao extends JpaRepository<ServicoManutencao, Integer>{

}
