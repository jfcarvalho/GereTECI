package com.teci.gereteci.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
import com.teci.gereteci.model.Recurso.Recurso;
import com.teci.gereteci.model.Servico.DescricaoManutencao;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.ServicosManutencao;
import com.teci.gereteci.repository.Usuarios;


@Controller
@RequestMapping("/servicosmanutencao")
public class ServicoManutencaoController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroServicoManutencao"; 
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private ServicosManutencao servicos;
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("servico", new ServicoManutencao());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated ServicoManutencao servicoManutencao, @RequestParam Integer usuario_id_usuario, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroServicoManutencao";
		}
		Usuario user = usuarios.findOne(usuario_id_usuario);
		servicoManutencao.setSolicitado(user);
		servicos.save(servicoManutencao);
		attributes.addFlashAttribute("mensagem", "Serviço salvo com sucesso!");	
		return "redirect:/servicosmanutencao/novo";
	
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<ServicoManutencao> todosServicosManutencao = servicos.findAll();
		//List<Computador> todosComputadores = computadores.findAll();
		List<Usuario> todosUsuarios = usuarios.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaServicosManutencao");
	    mv.addObject("servicos", todosServicosManutencao);
		mv.addObject("usuarios", todosUsuarios);
	    return mv;
	}

	@RequestMapping("{id_servico}")
	public ModelAndView edicao(@PathVariable("id_servico") ServicoManutencao servicoManutencao)
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
	
	@RequestMapping(value="{id_servico}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_servico, RedirectAttributes attributes)
	{
		servicos.delete(id_servico);
		attributes.addFlashAttribute("mensagem", "Serivço excluído com sucesso com sucesso!");	
		return "redirect:/servicosmanutencao";
	}
	@ModelAttribute("todosStatusServico")
	public List<StatusServico> todosStatusServico() {
		return Arrays.asList(StatusServico.values());
	}
	
	@ModelAttribute("todasDescricoesServicos")
	public List<DescricaoManutencao> todasDescricoesServico() {
		return Arrays.asList(DescricaoManutencao.values());
	}
	@ModelAttribute("todosUsuarios")
	public List<Usuario> todosAtendentes() {
		List<Usuario> users = usuarios.findAll();
		return users;
	}
	@ModelAttribute("todosUsuariosTECI")
	public List<Usuario> todosAtendentesTECI() {
		List<Usuario> users = usuarios.findAll();
		List<Usuario> todosUsuariosTECI = new ArrayList<Usuario>();
		Iterator it = users.iterator();
		while(it.hasNext())
		{
			Usuario obj = (Usuario) it.next();
			if(obj.getNivel_acesso().getNivel().equals("Administrador") || obj.getNivel_acesso().getNivel().equals("Usuário TECI"))
				todosUsuariosTECI.add(obj);
			
		}
		return todosUsuariosTECI;
	}
	
}
