package com.teci.gereteci.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;



public interface ServicosManutencao extends JpaRepository<ServicoManutencao, Integer>{
	List<ServicoManutencao> findByAtendente(Usuario Atendente);
	List<ServicoManutencao> findBySolicitado(Usuario solicitado);
	List<ServicoManutencao> findByStatus(StatusServico status); 
	ServicoManutencao findByProtocolo(String protocolo);
	
}
