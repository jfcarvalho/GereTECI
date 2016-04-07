package com.teci.gereteci.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.ServicoTelefone;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;

public interface PesquisasTelefone extends Repository<ServicoTelefone, Integer> {
	List<ServicoTelefone> findByAtendente(Usuario Atendente);
	List<ServicoTelefone> findBySolicitado(Usuario solicitado);
	List<ServicoTelefone> findByStatus(StatusServico status);
	
	
}
