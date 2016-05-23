package com.teci.gereteci.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.*;
import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Computador.Status;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Usuario.Usuario;

public interface Computadores extends JpaRepository<Computador, Integer>
{
	List<Computador> findByIpContaining(String ip);
	List<Computador> findByUsuarioContaining(Usuario u);
	List<Computador> findByStatus(Status s);
}
