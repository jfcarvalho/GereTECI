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
import com.teci.gereteci.model.Recurso.TipoES;
import com.teci.gereteci.repository.Computadores;
import com.teci.gereteci.repository.Monitores;
import com.teci.gereteci.repository.Mouses;
import com.teci.gereteci.security.AppUserDetailsService;

@Controller
@RequestMapping("/gereteci/mouses")

public class MouseController {
	private static final String CADASTRO_VIEW_MOUSE = "/cadastro/CadastroMouse"; 
	private static final String EDICAO1_VIEW = "/edicoes/EditarMouse";
	private static final String EDICAO2_VIEW = "/edicoes/EditarMouseComputador";
	private static final String EDICAO_MENU = "/edicoes/PopUPMouse";
	
	@Autowired
	private Mouses mouses;
	@Autowired
	private Computadores computadores;
	
	@RequestMapping("/novo")
	public ModelAndView novoMouse()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MOUSE);
		mv.addObject(new Mouse());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Mouse mouse, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MOUSE);
		if(errors.hasErrors())
		{
			return "cadastroMouse";
		}
		if(computador_id_computador != null)
		{
			Computador computer= computadores.findOne(computador_id_computador);
			mouse.setComputador(computer);
			computer.setRecurso_mouse(mouse);
			computadores.save(computer);
			//List<Recurso> resources = computer.getRecursos();
			//resources.add(recurso);
			//computer.setRecursos(resources);
		}
		mouses.save(mouse);
		attributes.addFlashAttribute("mensagem", "Mouse salvo com sucesso!");	
		return "redirect:/gereteci/mouses/novo";
	}
	@RequestMapping(value="/{id_recurso}/salvar1",method = RequestMethod.POST)
	public String salvar1(@Validated Mouse mouse, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(EDICAO1_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
	//	System.out.println(">>>>>> " + usuario_id_usuario);
		Mouse m = mouses.findOne(mouse.getId_recurso());
		m.setPatrimonio(mouse.getPatrimonio());
		m.setDescricao(mouse.getDescricao());
		m.setMarca(mouse.getMarca());
		m.setCor(mouse.getCor());
		m.setStatus(mouse.getStatus());
		m.setRolagem(mouse.getRolagem());
		m.setTipoes(mouse.getTipoes());
		
		mouses.save(m);
		attributes.addFlashAttribute("mensagem", "Mouse salvo com sucesso!");	
		return "redirect:/gereteci/mouses/novo";
		
	}
@RequestMapping("/{id_recurso}/editar1")
	
	public ModelAndView editar1(@PathVariable("id_recurso") Mouse mouse)
	{
		ModelAndView mv = new ModelAndView(EDICAO1_VIEW);
		
		mv.addObject("rec", mouse);
		mv.addObject(mouse);
		
		return mv;
	}
@RequestMapping("/{id_recurso}/editar2")

public ModelAndView editar2(@PathVariable("id_recurso") Mouse mouse)
{
	ModelAndView mv = new ModelAndView(EDICAO2_VIEW);
	
	mv.addObject("rec", mouse);
	mv.addObject(mouse);
	
	return mv;
}

@RequestMapping(value="/{id_recurso}/salvar2",method = RequestMethod.POST)
public String salvar2(@Validated Mouse mouse, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
{
	ModelAndView mv = new ModelAndView(EDICAO2_VIEW);
	if(errors.hasErrors())
	{
		return "cadastroComputador";
	}
//	System.out.println(">>>>>> " + usuario_id_usuario);
	Mouse m = mouses.findOne(mouse.getId_recurso());



	
	if(computador_id_computador != null)
	{
		if(m.getComputador() != null) {
			Computador pc = computadores.findOne(m.getComputador().getId_computador()); //computador antigo
			System.out.println(">>>>> computador antigo" + pc.getId_computador());	
			pc.setRecurso_mouse(null); //OK
		}
		Computador pcnovo = computadores.findOne(computador_id_computador);
		pcnovo.setRecurso_mouse(m);
		m.setComputador(pcnovo);
		mouses.save(m);
		computadores.save(pcnovo);
	}
	mouses.save(m);
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



	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Mouse> todosMouses = mouses.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaMouses");
	    mv.addObject("mouses", todosMouses);
		return mv;
	}
	
	@RequestMapping("{id_recurso}")
	public ModelAndView edicao(@PathVariable("id_recurso") Mouse mouse)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MOUSE);
		
		mv.addObject("rec", mouse);
		mv.addObject(mouse);
		return mv;
	}
	
	@RequestMapping(value="{id_recurso}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_recurso, RedirectAttributes attributes)
	{
		Mouse mouse = mouses.findOne(id_recurso);
		if(mouse.getComputador() != null)
		{
			Computador pc = computadores.findOne(mouse.getComputador().getId_computador());
			pc.setRecurso_mouse(null);
			computadores.save(pc);
		}
		mouses.delete(id_recurso);
		attributes.addFlashAttribute("mensagem", "Mouse excluido com sucesso com sucesso!");	
		return "redirect:/gereteci/mouses";
	}
	
	@ModelAttribute("todosComputadoresDisponiveis")
	public List<Computador> todosComputadoresDisponiveis()
	{
		List<Computador> todosComputadores = computadores.findAll();
		List<Computador> todosComputadoresDisponiveis = new ArrayList<Computador>();
		Iterator it = todosComputadores.iterator();
		while(it.hasNext())
		{
			Computador obj = (Computador) it.next();
			if(obj.getRecurso_mouse() == null) {
				System.out.println(obj.getId_impressao());
				todosComputadoresDisponiveis.add(obj);
			}
		}
		
		return todosComputadoresDisponiveis;
	}
	
	@ModelAttribute("todosStatusMouse")
	public List<Status> todosStatusMouses() {
		return Arrays.asList(Status.values());
	}
	
	@ModelAttribute("todosTiposMouse")
	public List<TipoES> todosTiposTeclado() {
		return Arrays.asList(TipoES.values());
	}
	
	@RequestMapping(value="/{id_recurso}/manutencao", method=RequestMethod.PUT)
	public @ResponseBody String manutencao(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Mouse mouse = mouses.findOne(id_recurso);
		mouse.setStatus(Status.manutencao);
		mouses.save(mouse);
		return Status.manutencao.getStatus();
	}
	
	@RequestMapping(value="/{id_recurso}/baixa", method=RequestMethod.PUT)
	public @ResponseBody String baixa(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Mouse mouse = mouses.findOne(id_recurso);
		mouse.setStatus(Status.com_defeito_para);
		mouses.save(mouse);
		return Status.com_defeito_para.getStatus();
		
	}
	
	@RequestMapping(value="/{id_recurso}/consertado", method=RequestMethod.PUT)
	public @ResponseBody String consertado(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Mouse mouse = mouses.findOne(id_recurso);
		mouse.setStatus(Status.funcionando);
		mouses.save(mouse);
		return Status.funcionando.getStatus();
	}
	
	@ModelAttribute("home_teci")
	public boolean homeTECI() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_TECI") || AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_CADASTRAR_SERVICO");
	}
	
}
