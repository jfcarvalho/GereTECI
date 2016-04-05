package com.teci.gereteci.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;

public interface PesquisasManutencao extends Repository<ServicoManutencao, Integer> {
	List<ServicoManutencao> findByAtendente(Usuario Atendente);
	List<ServicoManutencao> findBySolicitado(Usuario solicitado);
	List<ServicoManutencao> findByStatus(StatusServico status);
	
	
}
