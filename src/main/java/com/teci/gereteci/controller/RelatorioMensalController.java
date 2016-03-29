package com.teci.gereteci.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Usuario.Usuario;

@Controller

public class RelatorioMensalController {
	private static final String RELATORIO_PATH = "/relatorios/RelatorioMensal";
	
	@RequestMapping("/relatoriomensal")
	public ModelAndView relatorio()
	{
		ModelAndView mv = new ModelAndView(RELATORIO_PATH);
		return mv;
	}
	/*public  edicao(@PathVariable("id_servico") ServicoManutencao servicoManutencao)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		Usuario solicitante = servicoManutencao.getSolicitado();
		Usuario usuario_atendente = servicoManutencao.getAtendente();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("servico", servicoManutencao);
		mv.addObject(servicoManutencao);
		return mv;
	}
	*/
}
