package com.teci.gereteci.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Servico.ServicoEmail;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;

public interface Servicos extends JpaRepository<Servico, Integer>{

	
}
