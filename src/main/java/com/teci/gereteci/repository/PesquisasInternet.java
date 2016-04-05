package com.teci.gereteci.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;

public interface PesquisasInternet extends Repository<ServicoInternet, Integer> {
	List<ServicoInternet> findByAtendente(Usuario Atendente);
	List<ServicoInternet> findBySolicitado(Usuario solicitado);
	List<ServicoInternet> findByStatus(StatusServico status);
	
	
}
