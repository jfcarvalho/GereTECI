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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Computador.Status;
import com.teci.gereteci.model.Recurso.Monitor;
import com.teci.gereteci.model.Recurso.Mouse;
import com.teci.gereteci.model.Recurso.Recurso;
import com.teci.gereteci.model.Recurso.Teclado;
import com.teci.gereteci.model.Recurso.TipoES;
import com.teci.gereteci.repository.Computadores;
import com.teci.gereteci.repository.Monitores;
import com.teci.gereteci.repository.Teclados;
import com.teci.gereteci.security.AppUserDetailsService;

@Controller
@RequestMapping("/gereteci/teclados")

public class TecladoController {
	private static final String CADASTRO_VIEW_TECLADO = "/cadastro/CadastroTeclado"; 
	private static final String EDICAO1_VIEW = "/edicoes/EditarTeclado";
	private static final String EDICAO2_VIEW = "/edicoes/EditarTecladoComputador";
	private static final String EDICAO_MENU = "/edicoes/PopUPTeclado";
	@Autowired
	private Teclados teclados;
	@Autowired
	private Computadores computadores;
	
	@RequestMapping("/novo")
	public ModelAndView novoTeclado()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_TECLADO);
		mv.addObject(new Teclado());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
@RequestMapping("/{id_recurso}/editar1")
	
	public ModelAndView editar1(@PathVariable("id_recurso") Teclado teclado)
	{
		ModelAndView mv = new ModelAndView(EDICAO1_VIEW);
		
		mv.addObject("rec", teclado);
		mv.addObject(teclado);
		
		return mv;
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Teclado teclado, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_TECLADO);
		if(errors.hasErrors())
		{
			return "cadastroTeclado";
		}
		if(computador_id_computador != null)
		{
			Computador computer= computadores.findOne(computador_id_computador);
			teclado.setComputador(computer);
			computer.setRecurso_teclado(teclado);
			computadores.save(computer);
			//List<Recurso> resources = computer.getRecursos();
			//resources.add(recurso);
			//computer.setRecursos(resources);
		}
		teclados.save(teclado);
		attributes.addFlashAttribute("mensagem", "Teclado salvo com sucesso!");	
		return "redirect:/gereteci/teclados/novo";
	}
	@RequestMapping(value="/{id_recurso}/salvar1",method = RequestMethod.POST)
	public String salvar1(@Validated Teclado teclado, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(EDICAO1_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
	//	System.out.println(">>>>>> " + usuario_id_usuario);
		Teclado t = teclados.findOne(teclado.getId_recurso());
		t.setPatrimonio(teclado.getPatrimonio());
		t.setDescricao(teclado.getDescricao());
		t.setMarca(teclado.getMarca());
		t.setCor(teclado.getCor());
		t.setStatus(teclado.getStatus());
		t.setFuncoes(teclado.getFuncoes());
		t.setTipoes(teclado.getTipoes());
		
		teclados.save(t);
		attributes.addFlashAttribute("mensagem", "Mouse salvo com sucesso!");	
		return "redirect:/teclados/novo";
		
	}
	@RequestMapping(value="/{id_recurso}/salvar2",method = RequestMethod.POST)
	public String salvar2(@Validated Teclado teclado, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(EDICAO2_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
//		System.out.println(">>>>>> " + usuario_id_usuario);
		Teclado t = teclados.findOne(teclado.getId_recurso());
		System.out.println(">>>>>> ID do monitor: " + teclado.getId_recurso());
		System.out.println(">>>>>> Tipo de Recurso " + teclado.getTipo_recurso());
		System.out.println(">>>>>> ID do computador" + computador_id_computador);
		
		if(computador_id_computador != null)
		{
			if(t.getComputador() != null) {
				Computador pc = computadores.findOne(t.getComputador().getId_computador()); //computador antigo
				System.out.println(">>>>> computador antigo" + pc.getId_computador());	
				pc.setRecurso_teclado(null); //OK
			}
			Computador pcnovo = computadores.findOne(computador_id_computador);
			pcnovo.setRecurso_teclado(t);
			t.setComputador(pcnovo);
			teclados.save(t);
			computadores.save(pcnovo);
		}
		teclados.save(t);
		attributes.addFlashAttribute("mensagem", "Mouse salvo com sucesso!");	
		return "redirect:/gereteci/computadores/novo";
		
	}
	
	@RequestMapping("/{id_recurso}/edicaomenu")
	public ModelAndView edicaomenu(@PathVariable("id_recurso") Recurso recurso)
	{
		//ObjectMapper mapper = new ObjectMapper();
		
		//System.out.println(">>>>>>> codigo recebido: " + computador.getId_computador());
		//System.out.println(">>>>>>> Codigo de usuario recebido: " + recursos.getDescricao());
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(EDICAO_MENU);	
		mv.addObject("recurso", recurso);
		mv.addObject(recurso);
		
		return mv;
	}



	@RequestMapping("/{id_recurso}/editar2")

	public ModelAndView editar2(@PathVariable("id_recurso") Teclado teclado)
	{
		ModelAndView mv = new ModelAndView(EDICAO2_VIEW);
		
		mv.addObject("rec", teclado);
		mv.addObject(teclado);
		
		return mv;
	}

	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Teclado> todosTeclados= teclados.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaTeclados");
	    mv.addObject("teclados", todosTeclados);
		return mv;
	}
	
	@RequestMapping("{id_recurso}")
	public ModelAndView edicao(@PathVariable("id_recurso") Teclado teclado)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_TECLADO);
		
		mv.addObject("rec", teclado);
		mv.addObject(teclado);
		return mv;
	}
	
	@RequestMapping(value="{id_recurso}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_recurso, RedirectAttributes attributes)
	{
		Teclado teclado = teclados.findOne(id_recurso);
		if(teclado.getComputador() != null)
		{
			Computador pc = computadores.findOne(teclado.getComputador().getId_computador());
			pc.setRecurso_teclado(null);
			computadores.save(pc);
		}
		teclados.delete(id_recurso);
		attributes.addFlashAttribute("mensagem", "Teclado excluido com sucesso com sucesso!");	
		return "redirect:/gereteci/teclados";
	}
	
	@ModelAttribute("todosComputadoresDisponiveis")
	public List<Computador> todosComputadoresDisponiveis()
	{
		List<Computador> todosComputadores= computadores.findAll();
		List<Computador> todosComputadoresDisponiveis = new ArrayList<Computador>();
		Iterator it = todosComputadores.iterator();
		while(it.hasNext())
		{
			Computador obj = (Computador) it.next();
			if(obj.getRecurso_teclado() == null) {
				//System.out.println(obj.getId_impressao());
				todosComputadoresDisponiveis.add(obj);
			}
			
		}
		
		return todosComputadoresDisponiveis;
	}
	
	@ModelAttribute("todosStatusTeclado")
	public List<Status> todosStatusMonitor() {
		return Arrays.asList(Status.values());
	}
	
	@ModelAttribute("todosTiposTeclado")
	public List<TipoES> todosTiposTeclado() {
		return Arrays.asList(TipoES.values());
	}
	
	@RequestMapping(value="/{id_recurso}/manutencao", method=RequestMethod.PUT)
	public @ResponseBody String manutencao(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Teclado teclado= teclados.findOne(id_recurso);
		teclado.setStatus(Status.manutencao);
		teclados.save(teclado);
		return Status.manutencao.getStatus();
	}
	
	@RequestMapping(value="/{id_recurso}/baixa", method=RequestMethod.PUT)
	public @ResponseBody String baixa(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Teclado teclado = teclados.findOne(id_recurso);
		teclado.setStatus(Status.com_defeito_para);
		teclados.save(teclado);
		return Status.com_defeito_para.getStatus();
		
	}
	
	@RequestMapping(value="/{id_recurso}/consertado", method=RequestMethod.PUT)
	public @ResponseBody String consertado(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Teclado teclado = teclados.findOne(id_recurso);
		teclado.setStatus(Status.funcionando);
		teclados.save(teclado);
		return Status.funcionando.getStatus();
	}
	
	@ModelAttribute("home_teci")
	public boolean homeTECI() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_TECI") || AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_CADASTRAR_SERVICO");
	}
}
