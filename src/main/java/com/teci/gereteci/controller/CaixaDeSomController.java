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

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Computador.StatusComputador;
import com.teci.gereteci.model.Recurso.CaixaDeSom;
import com.teci.gereteci.model.Recurso.Midia;
import com.teci.gereteci.repository.CaixasDeSom;
import com.teci.gereteci.repository.Computadores;
import com.teci.gereteci.repository.Midias;

@Controller
@RequestMapping("/cs")

public class CaixaDeSomController {
	private static final String CADASTRO_VIEW_CS = "/cadastro/CadastroCS"; 
	@Autowired
	private CaixasDeSom caixas;
	@Autowired
	private Computadores computadores;
	
	@RequestMapping("/novo")
	public ModelAndView novaCS()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_CS);
		mv.addObject(new CaixaDeSom());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated CaixaDeSom cs, @RequestParam Integer computador_id_computador, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_CS);
		if(errors.hasErrors())
		{
			return "cadastroCS";
		}
		if(computador_id_computador != null)
		{
			Computador computer= computadores.findOne(computador_id_computador);
			cs.setComputador(computer);
			//List<Recurso> resources = computer.getRecursos();
			//resources.add(recurso);
			//computer.setRecursos(resources);
		}
		caixas.save(cs);
		attributes.addFlashAttribute("mensagem", "Caixas de som salva com sucesso!");	
		return "redirect:/cs/novo";
	}
	
	public ModelAndView pesquisar()
	{
		List<CaixaDeSom> todasCaixas = caixas.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaCaixas");
	    mv.addObject("caixas", todasCaixas);
		return mv;
	}
	
	@RequestMapping("{id_recurso}")
	public ModelAndView edicao(@PathVariable("id_recurso") CaixaDeSom cs)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW_CS);
		
		mv.addObject("cs", cs);
		mv.addObject(cs);
		return mv;
	}
	
	@RequestMapping(value="{id_recurso}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_recurso, RedirectAttributes attributes)
	{
		caixas.delete(id_recurso);
		attributes.addFlashAttribute("mensagem", "Caixa de Som excluída com sucesso com sucesso!");	
		return "redirect:/caixas";
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
			if(obj.getRecurso_cs() == null) {
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
	

}
