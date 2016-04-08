package com.teci.gereteci.model.Servico;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Usuario.Usuario;

@Entity
@DiscriminatorValue("Web")

public class ServicoWeb extends Servico {

	private boolean atualizacao_conteudo;
	private boolean licitacao;
	private String conteudo_editado;
	private String edital_licitacao;

		
	
	
}
