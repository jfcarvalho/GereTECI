package com.teci.gereteci.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.teci.gereteci.model.*;
import com.teci.gereteci.model.Computador.Arquitetura;
import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Computador.Memoria;
import com.teci.gereteci.model.Computador.Recurso;
import com.teci.gereteci.model.Computador.Sistema;
import com.teci.gereteci.model.Computador.StatusComputador;
import com.teci.gereteci.model.Impressora.Impressora;
import com.teci.gereteci.model.Internet.Dns_alternativo;
import com.teci.gereteci.model.Internet.Dns_preferencial;
import com.teci.gereteci.model.Internet.Gateway;
import com.teci.gereteci.model.Internet.Mascara;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.*;



@Controller
@RequestMapping("/computadores")
public class ComputadorController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroComputador"; 
	@Autowired
	private Computadores computadores;
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private Impressoras impressoras;
	@Autowired
	private Recursos recursos;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Computador());
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Computador computador, @RequestParam Integer usuario_id_usuario, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(errors.hasErrors())
		{
			return "cadastroComputador";
		}
		Usuario user = usuarios.findOne(usuario_id_usuario);
		computador.setUsuario(user);
		computadores.save(computador);
		attributes.addFlashAttribute("mensagem", "Computador salvo com sucesso!");	
		return "redirect:/computadores/novo";
	
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
		List<Computador> todosComputadores = computadores.findAll();
		List<Usuario> todosUsuarios = usuarios.findAll();
		ModelAndView mv = new ModelAndView("/pesquisa/PesquisaComputadores");
	    mv.addObject("computadores", todosComputadores);
		mv.addObject("usuarios", todosUsuarios);
	    return mv;
	}

	@RequestMapping("{id_computador}")
	public ModelAndView edicao(@PathVariable("id_computador") Computador computador)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("pc", computador);
		mv.addObject(computador);
		
		return mv;
	}
	
	@RequestMapping(value="{id_computador}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_computador, RedirectAttributes attributes)
	{
		computadores.delete(id_computador);
		attributes.addFlashAttribute("mensagem", "Computador excluido com sucesso com sucesso!");	
		return "redirect:/computadores";
	}
	@ModelAttribute("todosSistemasComputador")
	public List<Sistema> todosSistemasComputador() {
		return Arrays.asList(Sistema.values());
	}
	@ModelAttribute("todasArquiteturasComputador")
	public List<Arquitetura> todasArquiteturasComputador() {
		return Arrays.asList(Arquitetura.values());
	}
	@ModelAttribute("todasMascarasComputador")
	public List<Mascara> todasMascarasComputador() {
		return Arrays.asList(Mascara.values());
	}
	@ModelAttribute("todosGatewaysComputador")
	public List<Gateway> todosGatewaysComputador() {
		return Arrays.asList(Gateway.values());
	}
	@ModelAttribute("todosDNSPComputador")
	public List<Dns_preferencial> todosDNSPComputador() {
		return Arrays.asList(Dns_preferencial.values());
	}
	
	@ModelAttribute("todosDNSAComputador")
	public List<Dns_alternativo> todosDNSAComputador() {
		return Arrays.asList(Dns_alternativo.values());
	}
	
	@ModelAttribute("todosStatusComputador")
	public List<StatusComputador> todosStatusComputador() {
		return Arrays.asList(StatusComputador.values());
	}
	
	@ModelAttribute("todasMemoriasComputador")
	public List<Memoria> todosMemoriasComputador() {
		return Arrays.asList(Memoria.values());
	}
	
	@ModelAttribute("todosUsuariosComputador")
	public List<Usuario> todosUsuariosComputador()
	{
		List<Usuario> todosUsuarios = usuarios.findAll();
		return todosUsuarios;
	}
	
	@ModelAttribute("todasImpressorasComputador")
	public List<Impressora> todasImpressorasComputador()
	{
		List<Impressora> todasImpressoras= impressoras.findAll();
		return todasImpressoras;
	}
	@ModelAttribute("todosRecursosComputador")
	public List<Recurso> todasRecursosComputador()
	{
		List<Recurso> todosRecursos= recursos.findAll();
		return todosRecursos;
	}
	@ModelAttribute("todosRecursosMonitor")
	public List<Recurso> todasRecursosMonitor()
	{
		
		
		List<Recurso> todosRecursos= recursos.findAll();
		List<Recurso> todosMonitores = new ArrayList<Recurso>();
		Iterator it = todosRecursos.iterator();
		while(it.hasNext())
		{
			Recurso obj = (Recurso) it.next();
			if(obj.getCategoria().getCategoria().equals("Monitor"))
			{
				todosMonitores.add(obj);
			}
		}
		return todosMonitores;
	}
	
	@ModelAttribute("todosRecursosMouse")
	public List<Recurso> todasRecursosMouse()
	{
		
		
		List<Recurso> todosRecursos= recursos.findAll();
		List<Recurso> todosMouses = new ArrayList<Recurso>();
		Iterator it = todosRecursos.iterator();
		while(it.hasNext())
		{
			Recurso obj = (Recurso) it.next();
			if(obj.getCategoria().getCategoria().equals("Moouse"))
			{
				todosMouses.add(obj);
			}
		}
		return todosMouses;
	}
	
	@ModelAttribute("todosRecursosTeclado")
	public List<Recurso> todasRecursosTeclado()
	{
		
		
		List<Recurso> todosRecursos= recursos.findAll();
		List<Recurso> todosTeclados = new ArrayList<Recurso>();
		Iterator it = todosRecursos.iterator();
		while(it.hasNext())
		{
			Recurso obj = (Recurso) it.next();
			if(obj.getCategoria().getCategoria().equals("Teclado"))
			{
				todosTeclados.add(obj);
			}
		}
		return todosTeclados;
	}
	
	@ModelAttribute("todosRecursosMR")
	public List<Recurso> todasRecursosMR()
	{
		
		
		List<Recurso> todosRecursos= recursos.findAll();
		List<Recurso> todosMR = new ArrayList<Recurso>();
		Iterator it = todosRecursos.iterator();
		while(it.hasNext())
		{
			Recurso obj = (Recurso) it.next();
			if(obj.getCategoria().getCategoria().equals("Mídia Removível"))
			{
				todosMR.add(obj);
			}
		}
		return todosMR;
	}
	@ModelAttribute("todosRecursosCS")
	public List<Recurso> todasRecursosCS()
	{
		
		
		List<Recurso> todosRecursos= recursos.findAll();
		List<Recurso> todosCS = new ArrayList<Recurso>();
		Iterator it = todosRecursos.iterator();
		while(it.hasNext())
		{
			Recurso obj = (Recurso) it.next();
			if(obj.getCategoria().getCategoria().equals("Caixa de Som"))
			{
				todosCS.add(obj);
			}
		}
		return todosCS;
	}
	
	@ModelAttribute("todosRecursosFone")
	public List<Recurso> todasRecursosFone()
	{
		
		
		List<Recurso> todosRecursos= recursos.findAll();
		List<Recurso> todosFones= new ArrayList<Recurso>();
		Iterator it = todosRecursos.iterator();
		while(it.hasNext())
		{
			Recurso obj = (Recurso) it.next();
			if(obj.getCategoria().getCategoria().equals("Monitor"))
			{
				todosFones.add(obj);
			}
		}
		return todosFones;
	}
}
