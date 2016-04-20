package com.teci.gereteci.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.ServicoRede;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;



public interface ServicosRede extends JpaRepository<ServicoRede, Integer>{
	List<ServicoRede> findByAtendente(Usuario Atendente);
	List<ServicoRede> findBySolicitado(Usuario solicitado);
	List<ServicoRede> findByStatus(StatusServico status);
	
}
