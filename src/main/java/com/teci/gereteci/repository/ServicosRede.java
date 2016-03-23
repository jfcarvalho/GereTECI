package com.teci.gereteci.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;


import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.ServicoRede;



public interface ServicosRede extends JpaRepository<ServicoRede, Integer>{

}
