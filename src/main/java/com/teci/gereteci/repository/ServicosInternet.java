package com.teci.gereteci.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;



public interface ServicosInternet extends JpaRepository<ServicoInternet, Integer>{
	List<ServicoInternet> findByAtendente(Usuario Atendente);
	List<ServicoInternet> findBySolicitado(Usuario solicitado);
	List<ServicoInternet> findByStatus(StatusServico status); 
}
