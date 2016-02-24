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
import org.springframework.web.bind.annotation.ResponseBody;
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
	List<Usuario> todosUsuariosSemComputador = new ArrayList<Usuario>();
	Integer usuarioTemporario;
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
	//	System.out.println(">>>>>> " + usuario_id_usuario);
	
		if(usuario_id_usuario != null)
		{
			if(usuarioTemporario != null)
			{
				if(usuarioTemporario != usuario_id_usuario)
				{
					Usuario user = usuarios.findOne(usuarioTemporario);
					todosUsuariosSemComputador.add(user);
				}
			}
			Usuario user = usuarios.findOne(usuario_id_usuario);
			computador.setUsuario(user);
			user.setComputador(computador);
			
		}		
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
		System.out.println(">>>>>>> codigo recebido: " + computador.getId_computador());
		System.out.println(">>>>>>> Codigo de usuario recebido: " + computador.getUsuario().getId_usuario());
		//Usuario usuario = usuarios.findOne(id_usuario);
		usuarioTemporario = computador.getUsuario().getId_usuario();
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
	
	@ModelAttribute("todosUsuariosComputador") //Lista todos os usuários que ainda nao tem computador
	public List<Usuario> todosUsuariosComputador()
	{
		List<Usuario> todosUsuarios = usuarios.findAll();
		
		Iterator it = todosUsuarios.iterator();
		while(it.hasNext())
		{
			Usuario user = (Usuario) it.next();
			if(user.getComputador() == null && !(todosUsuariosSemComputador.contains(user)))
			{
					todosUsuariosSemComputador.add(user);
					System.out.println(todosUsuariosSemComputador.size());
			}
		}
		return todosUsuariosSemComputador; 
		//return todosUsuarios;
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
			if(obj.getCategoria().getCategoria().equals("Mouse"))
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
			if(obj.getCategoria().getCategoria().equals("Mídia removível"))
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
			if(obj.getCategoria().getCategoria().equals("Fone"))
			{
				todosFones.add(obj);
			}
		}
		return todosFones;
	}
	@ModelAttribute("todosRecursosOutros")
	public List<Recurso> todasRecursosOutros()
	{
		
		
		List<Recurso> todosRecursos= recursos.findAll();
		List<Recurso> todosOutros= new ArrayList<Recurso>();
		Iterator it = todosRecursos.iterator();
		while(it.hasNext())
		{
			Recurso obj = (Recurso) it.next();
			if(obj.getCategoria().getCategoria().equals("Outros"))
			{
				todosOutros.add(obj);
			}
		}
		return todosOutros;
	}
	@RequestMapping(value="/{id_computador}/manutencao", method=RequestMethod.PUT)
	public @ResponseBody String manutencao(@PathVariable Integer id_computador)
	{
		//Isso aqui vai para camada de serviço
		Computador computador = computadores.findOne(id_computador);
		computador.setStatus(StatusComputador.manutencao);
		computadores.save(computador);
		return StatusComputador.manutencao.getStatus();
	}
	
	@RequestMapping(value="/{id_computador}/baixa", method=RequestMethod.PUT)
	public @ResponseBody String baixa(@PathVariable Integer id_computador)
	{
		//Isso aqui vai para camada de serviço
		Computador computador = computadores.findOne(id_computador);
		computador.setStatus(StatusComputador.com_defeito_para);
		computadores.save(computador);
		return StatusComputador.com_defeito_para.getStatus();
	}
	
	@RequestMapping(value="/{id_computador}/consertado", method=RequestMethod.PUT)
	public @ResponseBody String consertado(@PathVariable Integer id_computador)
	{
		//Isso aqui vai para camada de serviço
		Computador computador = computadores.findOne(id_computador);
		computador.setStatus(StatusComputador.funcionando);
		computadores.save(computador);
		return StatusComputador.funcionando.getStatus();
	}
}
