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
import com.teci.gereteci.model.Recurso.Midia;
import com.teci.gereteci.model.Recurso.Mouse;
import com.teci.gereteci.model.Recurso.TipoES;
import com.teci.gereteci.repository.CaixasDeSom;
import com.teci.gereteci.repository.Computadores;
import com.teci.gereteci.repository.Midias;

@Controller
@RequestMapping("/caixas")

public class CaixaDeSomController {
	private static final String CADASTRO_VIEW_CS = "/cadastro/CadastroCaixaDeSom"; 
	private static final String EDICAO1_VIEW = "/edicoes/EditarCaixa";
	private static final String EDICAO2_VIEW = "/edicoes/EditarCaixaComputador";
	@Autowired
	private CaixasDeSom caixas;
	@Autowired
	private Computadores computadores;
	
	@RequestMapping("/novo")
	public ModelAndView novaCS()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_CS);
		mv.addObject("caixa", new CaixaDeSom());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated CaixaDeSom cs, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_CS);
		if(errors.hasErrors())
		{
			return "CadastroCaixaDeSom";
		}
		if(computador_id_computador != null)
		{
			Computador computer= computadores.findOne(computador_id_computador);
			cs.setComputador(computer);
			computer.setRecurso_caixa(cs);
			computadores.save(computer);
			//List<Recurso> resources = computer.getRecursos();
			//resources.add(recurso);
			//computer.setRecursos(resources);
		}
		caixas.save(cs);
		attributes.addFlashAttribute("mensagem", "Caixas de som salva com sucesso!");	
		return "redirect:/caixas/novo";
	}
	
	@RequestMapping(value="/{id_recurso}/salvar1",method = RequestMethod.POST)
	public String salvar1(@Validated CaixaDeSom caixa, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(EDICAO1_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
	//	System.out.println(">>>>>> " + usuario_id_usuario);
		CaixaDeSom c = caixas.findOne(caixa.getId_recurso());
		c.setPatrimonio(caixa.getPatrimonio());
		c.setDescricao(caixa.getDescricao());
		c.setMarca(caixa.getMarca());
		c.setCor(caixa.getCor());
		c.setStatus(caixa.getStatus());
		c.setTipoes(caixa.getTipoes());
		
		caixas.save(c);
		attributes.addFlashAttribute("mensagem", "Caixa de som salva com sucesso!");	
		return "redirect:/gereteci/caixas/novo";
		
	}
	
	@RequestMapping(value="/{id_recurso}/salvar2",method = RequestMethod.POST)
	public String salvar2(@Validated CaixaDeSom caixa, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(EDICAO2_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
//		System.out.println(">>>>>> " + usuario_id_usuario);
		CaixaDeSom c = caixas.findOne(caixa.getId_recurso());
		System.out.println(">>>>>> ID do monitor: " + caixa.getId_recurso());
		System.out.println(">>>>>> Tipo de Recurso " + caixa.getTipo_recurso());
		System.out.println(">>>>>> ID do computador" + computador_id_computador);
		
		if(computador_id_computador != null)
		{
			if(c.getComputador() != null) {
				Computador pc = computadores.findOne(c.getComputador().getId_computador()); //computador antigo
				System.out.println(">>>>> computador antigo" + pc.getId_computador());	
				pc.setRecurso_caixa(null); //OK
			}
			Computador pcnovo = computadores.findOne(computador_id_computador);
			pcnovo.setRecurso_caixa(c);
			c.setComputador(pcnovo);
			caixas.save(c);
			computadores.save(pcnovo);
		}
		caixas.save(c);
		attributes.addFlashAttribute("mensagem", "Mouse salvo com sucesso!");	
		return "redirect:/caixas/";
		
	}
	
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<CaixaDeSom> todasCaixas = caixas.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaCaixaDeSom");
	    mv.addObject("caixas", todasCaixas);
		return mv;
	}
	
	@RequestMapping("{id_recurso}")
	public ModelAndView edicao(@PathVariable("id_recurso") CaixaDeSom cs)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_CS);
		
		mv.addObject("caixa", cs);
		mv.addObject(cs);
		return mv;
	}
@RequestMapping("/{id_recurso}/editar1")
	
	public ModelAndView editar1(@PathVariable("id_recurso") CaixaDeSom caixa)
	{
		ModelAndView mv = new ModelAndView(EDICAO1_VIEW);
		
		mv.addObject("caixa", caixa);
		mv.addObject(caixa);
		
		return mv;
	}

@RequestMapping("/{id_recurso}/editar2")

public ModelAndView editar2(@PathVariable("id_recurso") CaixaDeSom caixa)
{
	ModelAndView mv = new ModelAndView(EDICAO2_VIEW);
	
	mv.addObject("caixa", caixa);
	mv.addObject(caixa);
	
	return mv;
}

	@RequestMapping(value="{id_recurso}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_recurso, RedirectAttributes attributes)
	{
		CaixaDeSom caixa = caixas.findOne(id_recurso);
		if(caixa.getComputador() != null)
		{
			Computador pc = computadores.findOne(caixa.getComputador().getId_computador());
			pc.setRecurso_caixa(null);
			computadores.save(pc);
		}
		caixa.setComputador(null);
		caixas.delete(id_recurso);
		attributes.addFlashAttribute("mensagem", "Caixa de Som excluída com sucesso com sucesso!");	
		return "redirect:/gereteci/caixas";
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
			if(obj.getRecurso_caixa() == null) {
				System.out.println(obj.getId_impressao());
				todosComputadoresDisponiveis.add(obj);
			}
		}
		
		return todosComputadoresDisponiveis;
	}
	
	@ModelAttribute("todosStatusCS")
	public List<StatusComputador> todosStatusCS() {
		return Arrays.asList(StatusComputador.values());
	}
	
	@ModelAttribute("todosTiposCS")
	public List<TipoES> todosTiposCS() {
		return Arrays.asList(TipoES.values());
	}

	
	@RequestMapping(value="/{id_recurso}/manutencao", method=RequestMethod.PUT)
	public @ResponseBody String manutencao(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		CaixaDeSom caixa = caixas.findOne(id_recurso);
		caixa.setStatus(StatusComputador.manutencao);
		caixas.save(caixa);
		return StatusComputador.manutencao.getStatus();
	}
	
	@RequestMapping(value="/{id_recurso}/baixa", method=RequestMethod.PUT)
	public @ResponseBody String baixa(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		CaixaDeSom caixa = caixas.findOne(id_recurso);
		caixa.setStatus(StatusComputador.com_defeito_para);
		caixas.save(caixa);
		return StatusComputador.com_defeito_para.getStatus();
		
	}
	
	@RequestMapping(value="/{id_recurso}/consertado", method=RequestMethod.PUT)
	public @ResponseBody String consertado(@PathVariable Integer id_recurso)
	{
		//Isso aqui vai para camada de serviço
		CaixaDeSom caixa= caixas.findOne(id_recurso);
		caixa.setStatus(StatusComputador.funcionando);
		caixas.save(caixa);
		return StatusComputador.funcionando.getStatus();
	}
}
