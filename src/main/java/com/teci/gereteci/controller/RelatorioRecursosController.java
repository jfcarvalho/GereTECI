package com.teci.gereteci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teci.gereteci.repository.CaixasDeSom;
import com.teci.gereteci.repository.Computadores;
import com.teci.gereteci.repository.Midias;
import com.teci.gereteci.repository.Monitores;
import com.teci.gereteci.repository.Mouses;
import com.teci.gereteci.repository.Outross;
import com.teci.gereteci.repository.Servicos;
import com.teci.gereteci.repository.ServicosEmail;
import com.teci.gereteci.repository.ServicosInternet;
import com.teci.gereteci.repository.ServicosManutencao;
import com.teci.gereteci.repository.ServicosRede;
import com.teci.gereteci.repository.ServicosTelefone;
import com.teci.gereteci.repository.Teclados;

public class RelatorioRecursosController {
	
	@Autowired
	private Computadores computadores;
	@Autowired
	private Monitores monitores;
	@Autowired
	private Mouses mouses;
	@Autowired
	private Teclados teclados;
	@Autowired
	private CaixasDeSom caixas;
	@Autowired
	private Outross outros;
	


}
