package com.teci.gereteci.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.Servico.ServicoEmail;
import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.ServicoOutro;
import com.teci.gereteci.model.Servico.ServicoRede;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;



public interface ServicosOutros extends JpaRepository<ServicoOutro, Integer>{
	List<ServicoOutro> findByAtendente(Usuario Atendente);
	List<ServicoOutro> findBySolicitado(Usuario solicitado);
	List<ServicoOutro> findByStatus(StatusServico status); 
	ServicoOutro findByProtocolo(String protocolo);
}
