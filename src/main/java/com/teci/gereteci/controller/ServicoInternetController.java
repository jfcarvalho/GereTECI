package com.teci.gereteci.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teci.gereteci.model.Computador.Memoria;
import com.teci.gereteci.model.Computador.StatusComputador;
import com.teci.gereteci.model.Servico.DescricaoManutencao;
import com.teci.gereteci.model.Servico.DescricaoProntaInternet;
import com.teci.gereteci.model.Servico.ServicoInternet;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.ServicosInternet;
import com.teci.gereteci.repository.ServicosManutencao;
import com.teci.gereteci.repository.Usuarios;


@Controller
@RequestMapping("/servicosinternet")
public class ServicoInternetController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroServicoInternet"; 
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private ServicosInternet servicos;
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("servico", new ServicoInternet());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated ServicoInternet servicoInternet, @RequestParam Integer usuario_id_usuario, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroServicoInternet";
		}
		Usuario user = usuarios.findOne(usuario_id_usuario);
		servicoInternet.setSolicitado(user);
		servicos.save(servicoInternet);
		attributes.addFlashAttribute("mensagem", "Serviço salvo com sucesso!");	
		return "redirect:/servicosinternet/novo";
	
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<ServicoInternet> todosServicosInternet = servicos.findAll();
		//List<Computador> todosComputadores = computadores.findAll();
		List<Usuario> todosUsuarios = usuarios.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosInternet");
	    mv.addObject("servicos", todosServicosInternet);
		mv.addObject("usuarios", todosUsuarios);
	    return mv;
	}

	@RequestMapping("{id_servico}")
	public ModelAndView edicao(@PathVariable("id_servico") ServicoInternet servicoInternet)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		Usuario solicitante = servicoInternet.getSolicitado();
		Usuario usuario_atendente = servicoInternet.getAtendente();
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("servico", servicoInternet);
		mv.addObject(servicoInternet);
		return mv;
	}
	
	@RequestMapping(value="{id_servico}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_servico, RedirectAttributes attributes)
	{
		servicos.delete(id_servico);
		attributes.addFlashAttribute("mensagem", "Serivço excluído com sucesso com sucesso!");	
		return "redirect:/servicosinternet";
	}
	@ModelAttribute("todosStatusServico")
	public List<StatusServico> todosStatusServico() {
		return Arrays.asList(StatusServico.values());
	}
	
	@ModelAttribute("todasDescricoesServicos")
	public List<DescricaoProntaInternet> todasDescricoesServico() {
		return Arrays.asList(DescricaoProntaInternet.values());
	}
	@ModelAttribute("todosUsuarios")
	public List<Usuario> todosAtendentes() {
		List<Usuario> users = usuarios.findAll();
		return users;
	}
	
	
}
