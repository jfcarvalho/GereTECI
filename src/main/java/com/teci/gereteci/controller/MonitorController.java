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
import com.teci.gereteci.model.Computador.StatusComputador;
import com.teci.gereteci.model.Recurso.CaixaDeSom;
import com.teci.gereteci.model.Recurso.CategoriaMonitor;
import com.teci.gereteci.model.Recurso.Monitor;
import com.teci.gereteci.model.Recurso.Mouse;
import com.teci.gereteci.model.Recurso.Recurso;
import com.teci.gereteci.model.Recurso.Teclado;
import com.teci.gereteci.model.Usuario.Nivel;
import com.teci.gereteci.repository.Computadores;
import com.teci.gereteci.repository.Monitores;

@Controller
@RequestMapping("/monitores")

public class MonitorController {
	private static final String CADASTRO_VIEW_MONITOR = "/cadastro/CadastroMonitor"; 
	private static final String EDICAO1_VIEW = "/edicoes/EditarMonitor";
	private static final String EDICAO2_VIEW = "/edicoes/EditarMonitorComputador";
	@Autowired
	private Monitores monitores;
	@Autowired
	private Computadores computadores;
	
	@RequestMapping("/novo")
	public ModelAndView novoMonitor()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MONITOR);
		mv.addObject(new Monitor());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Monitor monitor, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MONITOR);
		if(errors.hasErrors())
		{
			return "cadastroMonitor";
		}
		if(computador_id_computador != null)
		{
			Computador computer= computadores.findOne(computador_id_computador);
			monitor.setComputador(computer);
			if(monitor.getCategoria_monitor().getCategoria().equals("Primário"))
				computer.setRecurso_monitor1(monitor);
			else {computer.setRecurso_monitor2(monitor);}
			computadores.save(computer);
			//List<Recurso> resources = computer.getRecursos();
			//resources.add(recurso);
			//computer.setRecursos(resources);
		}
		monitores.save(monitor);
		attributes.addFlashAttribute("mensagem", "Monitor salvo com sucesso!");	
		return "redirect:/monitores/novo";
	}
	@RequestMapping(value="/{id_recurso}/salvar1",method = RequestMethod.POST)
	public String salvar1(@Validated Monitor monitor, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(EDICAO1_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
	//	System.out.println(">>>>>> " + usuario_id_usuario);
		Monitor m = monitores.findOne(monitor.getId_recurso());
		m.setPatrimonio(monitor.getPatrimonio());
		m.setDescricao(monitor.getDescricao());
		m.setMarca(monitor.getMarca());
		m.setStatus(monitor.getStatus());
		m.setPolegadas(monitor.getPolegadas());
		m.setCategoria_monitor(monitor.getCategoria_monitor());
		
		monitores.save(m);
		attributes.addFlashAttribute("mensagem", "Monitor salvo com sucesso!");	
		return "redirect:/monitores/novo";
		
	}
@RequestMapping("/{id_recurso}/editar1")
	
	public ModelAndView editar1(@PathVariable("id_recurso") Monitor monitor)
	{
		ModelAndView mv = new ModelAndView(EDICAO1_VIEW);
		
		mv.addObject("rec", monitor);
		mv.addObject(monitor);
		
		return mv;
	}

@RequestMapping("/{id_recurso}/editar2")

public ModelAndView editar2(@PathVariable("id_recurso") Monitor monitor)
{
	ModelAndView mv = new ModelAndView(EDICAO2_VIEW);
	
	mv.addObject("rec", monitor);
	mv.addObject(monitor);
	
	return mv;
}
@RequestMapping(value="/{id_recurso}/salvar2",method = RequestMethod.POST)
public String salvar2(@Validated Monitor monitor, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
{
	ModelAndView mv = new ModelAndView(EDICAO2_VIEW);
	if(errors.hasErrors())
	{
		return "cadastroComputador";
	}
//	System.out.println(">>>>>> " + usuario_id_usuario);
	Monitor m = monitores.findOne(monitor.getId_recurso());
	System.out.println(">>>>>> ID do monitor: " + monitor.getId_recurso());
	System.out.println(">>>>>> Tipo de Recurso " + monitor.getTipo_recurso());
	System.out.println(">>>>>> ID do computador" + computador_id_computador);
	
	if(computador_id_computador != null)
	{
		Computador pc = computadores.findOne(m.getComputador().getId_computador()); //computador antigo
		System.out.println(">>>>> computador antigo" + pc.getId_computador());
		if(m.getCategoria_monitor().getCategoria().equals("Primário"))
		{
			pc.setRecurso_monitor1(null); //OK	
			Computador pcnovo = computadores.findOne(computador_id_computador);
			pcnovo.setRecurso_monitor1(m);
			m.setComputador(pcnovo);
			monitores.save(m);
			computadores.save(pc);
		}
		else if(m.getCategoria_monitor().getCategoria().equals("Secundário"))
		{
			pc.setRecurso_monitor2(null);
			
			Computador pcnovo = computadores.findOne(computador_id_computador);
			pc.setRecurso_monitor1(m);
			m.setComputador(pcnovo);
			monitores.save(m);
			computadores.save(pc);
		}
		
	}
	monitores.save(m);
	attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
	return "redirect:/computadores/novo";
	
}

	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Monitor> todosRecursos = monitores.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaMonitores");
	    mv.addObject("monitores", todosRecursos);
		return mv;
	}
	
	@RequestMapping("{id_recurso}")
	public ModelAndView edicao(@PathVariable("id_recurso") Monitor monitor)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_MONITOR);
		
		mv.addObject("rec", monitor);
		mv.addObject(monitor);
		return mv;
	}
	
	@RequestMapping(value="{id_recurso}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_recurso, RedirectAttributes attributes)
	{
		monitores.delete(id_recurso);
		attributes.addFlashAttribute("mensagem", "Monitor excluido com sucesso com sucesso!");	
		return "redirect:/monitores";
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
			if(obj.getRecurso_monitor1() == null) {
				System.out.println(obj.getId_impressao());
				todosComputadoresDisponiveis.add(obj);
			}
		}
		
		return todosComputadoresDisponiveis;
	}
	@ModelAttribute("todosStatusMonitor")
	public List<StatusComputador> todosStatusMonitor() {
		return Arrays.asList(StatusComputador.values());
	}
	
	@ModelAttribute("todasCategoriasMonitor")
	public List<CategoriaMonitor> todasCategoriasMonitor() {
		return Arrays.asList(CategoriaMonitor.values());
	}
	
	@RequestMapping(value="/{id_recurso}/manutencao", method=RequestMethod.PUT)
	public @ResponseBody String manutencao(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Monitor monitor = monitores.findOne(id_recurso);
		monitor.setStatus(StatusComputador.manutencao);
		monitores.save(monitor);
		return StatusComputador.manutencao.getStatus();
	}
	
	@RequestMapping(value="/{id_recurso}/baixa", method=RequestMethod.PUT)
	public @ResponseBody String baixa(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Monitor monitor = monitores.findOne(id_recurso);
		monitor.setStatus(StatusComputador.com_defeito_para);
		monitores.save(monitor);
		return StatusComputador.com_defeito_para.getStatus();
		
	}
	
	@RequestMapping(value="/{id_recurso}/consertado", method=RequestMethod.PUT)
	public @ResponseBody String consertado(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		Monitor monitor = monitores.findOne(id_recurso);
		monitor.setStatus(StatusComputador.funcionando);
		monitores.save(monitor);
		return StatusComputador.funcionando.getStatus();
	}
	
}
