package com.teci.gereteci.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Servico.ServicoEmail;
import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.ServicoRede;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;



public interface ServicosEmail extends JpaRepository<ServicoEmail, Integer>{
	List<ServicoEmail> findByAtendente(Usuario Atendente);
	List<ServicoEmail> findBySolicitado(Usuario solicitado);
	List<ServicoEmail> findByStatus(StatusServico status);
	ServicoManutencao findByProtocolo(String protocolo);
}
