package com.teci.gereteci.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Servico.ServicoManutencao;
import com.teci.gereteci.model.Servico.StatusServico;
import com.teci.gereteci.model.Setor.Setor;
import com.teci.gereteci.model.Usuario.Grupo;
import com.teci.gereteci.model.Usuario.Nivel;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.*;
import com.teci.gereteci.security.AppUserDetailsService;

@Controller
@RequestMapping("/gereteci/usuarios")
public class UsuarioController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroUsuario"; 
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private Setores setores;
	@Autowired
	private Computadores computadores;
	@Autowired
	private ServicosInternet sinternet;
	@Autowired
	private ServicosManutencao smanutencao;
	@Autowired
	private ServicosRede srede;
	@Autowired
	private Grupos grupos;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Usuario());
		
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar (Usuario usuario, @RequestParam Integer setor_id_setor, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		System.out.println(">>>>> ID DO SETOR: "+ setor_id_setor +"");
		if(errors.hasErrors())
		{
			return "cadastroUsuario";
		}
		//WTF?
		if(setor_id_setor != null) {
			Setor sector = setores.findOne(setor_id_setor);
			usuario.setSetor(sector);
			List<Usuario> users = sector.getUsuarios();
			users.add(usuario);
			sector.setUsuarios(users);
		}
		
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuarios.save(usuario);
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");	
		return "redirect:/gereteci/usuarios/novo";
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView pesquisar(String busca, String nome, String setor) throws ParseException
	{
		//List<ServicoManutencao> todosServicosManutencao = servicos.findAll();
		//Usuario user = usuarios.findOne(14);
		if(nome != null) {
			if(busca != null && nome.equals("on")) {
				List<Usuario> todosUsuarios = usuarios.findByNomeContaining(busca);
				ModelAndView mv = new ModelAndView("/pesquisa/PesquisaUsuarios");
				mv.addObject("usuarios", todosUsuarios);
				return mv;
			}
		}
			else
						if(setor != null)
						{
							if(busca != null && setor.equals("on")) 
							{	
								Setor s = setores.findBySigla(busca);
								List<Usuario> users = usuarios.findBySetor(s);
								ModelAndView mv = new ModelAndView("/pesquisa/PesquisaUsuarios");
							    mv.addObject("usuarios", users);
								return mv;
							}
						}
		   
		   List<Usuario> todosUsuarios = usuarios.findAll();
			ModelAndView mv = new ModelAndView("/pesquisa/PesquisaUsuarios");
			mv.addObject("usuarios", todosUsuarios);
	    
		return mv;
	}
	
	@RequestMapping("{id_usuario}")
	public ModelAndView edicao(@PathVariable("id_usuario") Usuario usuario)
	{
		//System.out.println(">>>>>>> codigo recebido: " + id_usuario);
		//Usuario usuario = usuarios.findOne(id_usuario);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		
		
		mv.addObject("su", usuario);
		mv.addObject(usuario);
		return mv;
	}
	
	@RequestMapping(value="{id_usuario}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Integer id_usuario, RedirectAttributes attributes)
	{
		
		Usuario u = usuarios.findOne(id_usuario);
			System.out.println(">>>>>>>>>>>>>>>>>> +" + u.getId_usuario());
			System.out.println(">>>>>>>>>>>>>>>>>> +" + u.getComputador().getId_computador());
			System.out.println(">>>>>>>>>>>>>>>>>> +" + u.getComputador().getIp());
			System.out.println(">>>>>>>>>>>>>>>>>> +" + u.getComputador().getUsuario());
			if (u.getComputador() != null) {
				Computador computador = computadores.findOne(u.getComputador().getId_computador());
				System.out.println(">>>>>> " + computador.getIp());
				System.out.println(">>>>>> " + computador.getUsuario().getNome());
				computador.setUsuario(null);
				computadores.save(computador);
		}
		if(u.getSetor() != null) {
			System.out.println(">>>>>>>>>>>>>>>>>> +" + u.getSetor());
			System.out.println(">>>>>>>>>>>>>>>>>> +" + u.getSetor().getResponsavel());
			Setor s = setores.findOne(u.getSetor().getId_setor());
			List<Usuario> users = s.getUsuarios();
			users.remove(s.getId_setor());
			s.setUsuarios(users);
			if(s.getResponsavel() != null) {
				if(s.getResponsavel().getId_usuario() == id_usuario)
				{
					s.setResponsavel(null);
				}
			}
			setores.save(s);
		}
		attributes.addFlashAttribute("mensagem", "Usuário excluido com sucesso com sucesso!");	
		usuarios.delete(id_usuario);
		return "redirect:/gereteci/usuarios";
	}
	@ModelAttribute("todosNiveisUsuario")
	public List<Nivel> todosNiveisUsuario() {
		return Arrays.asList(Nivel.values());
	}

	@ModelAttribute("grupos")
	public List<Grupo> todosGrupos() {
		return grupos.findAll();
	}

	
	@ModelAttribute("todosSetoresUsuario")
	public List<Setor> todosSetoresUsuario()
	{
		List<Setor> todosSetores= setores.findAll();
		Comparator<Setor> comparator = new Comparator<Setor>() {
		    public int compare(Setor u1, Setor u2) {
		    	if(u2.getNome().compareTo(u1.getNome()) < 0) {
					return 0;
				}
		    	if(u2.getNome().compareTo(u1.getNome()) > 0) {
					return -1;
				}
				
				return 0;
		    }
		};
		Collections.sort(todosSetores, comparator);
		return todosSetores;
	}
	@ModelAttribute("home_teci")
	public boolean homeTECI() {
		return AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_HOME_TECI") || AppUserDetailsService.cusuario.getAuthorities().toString().contains("ROLE_CADASTRAR_SERVICO");
	}
	
	
}
