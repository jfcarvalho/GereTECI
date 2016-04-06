package com.teci.gereteci.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Servico.ServicoEmail;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.ServicoRede;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;

public interface PesquisasSetor extends Repository<ServicoEmail, Integer> {
	List<ServicoEmail> findByAtendente(Usuario Atendente);
	List<ServicoEmail> findBySolicitado(Usuario solicitado);
	List<ServicoEmail> findByStatus(StatusServico status);
	
	
}
