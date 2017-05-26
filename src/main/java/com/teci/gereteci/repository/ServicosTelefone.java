package com.teci.gereteci.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.ServicoRede;
import com.teci.gereteci.model.Servico.ServicoTelefone;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;



public interface ServicosTelefone extends JpaRepository<ServicoTelefone, Integer>{
	List<ServicoTelefone> findByAtendente(Usuario Atendente);
	List<ServicoTelefone> findBySolicitado(Usuario solicitado);
	List<ServicoTelefone> findByStatus(StatusServico status);
	ServicoRede findByProtocolo(String protocolo);
}
