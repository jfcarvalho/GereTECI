package com.teci.gereteci.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.ServicoRede;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;

public interface PesquisasRede extends Repository<ServicoRede, Integer> {
	List<ServicoRede> findByAtendente(Usuario Atendente);
	List<ServicoRede> findBySolicitado(Usuario solicitado);
	List<ServicoRede> findByStatus(StatusServico status);
	
	
}
