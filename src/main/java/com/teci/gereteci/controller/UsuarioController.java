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

import com.teci.gereteci.model.*;
import com.teci.gereteci.model.Setor.Setor;
import com.teci.gereteci.model.Usuario.Nivel;
import com.teci.gereteci.model.Usuario.Usuario;
import com.teci.gereteci.repository.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	private static final String CADASTRO_VIEW = "/cadastro/CadastroUsuario"; 
	@Autowired
	private Usuarios usuarios;
	@Autowired
	private Setores setores;
	
	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Usuario());
		
		//mv.addObject("todosNiveisUsuario", Nivel.values());
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Usuario usuario, @RequestParam Integer setor_id_setor, Errors errors, RedirectAttributes attributes)
	{
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		System.out.println(">>>>> ID DO SETOR: "+ setor_id_setor +"");
		if(errors.hasErrors())
		{
			return "cadastroUsuario";
		}
		//WTF?
		Setor sector = setores.findOne(setor_id_setor);
		usuario.setSetor(sector);
		List<Usuario> users = sector.getUsuarios();
		users.add(usuario);
		sector.setUsuarios(users);
		
		usuarios.save(usuario);
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");	
		return "redirect:/usuarios/novo";
	}
	@RequestMapping
	public ModelAndView pesquisar()
	{
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
		usuarios.delete(id_usuario);
		attributes.addFlashAttribute("mensagem", "Usuário excluido com sucesso com sucesso!");	
		return "redirect:/usuarios";
	}
	@ModelAttribute("todosNiveisUsuario")
	public List<Nivel> todosNiveisUsuario() {
		return Arrays.asList(Nivel.values());
	}

	@ModelAttribute("todosSetoresUsuario")
	public List<Setor> todosSetoresUsuario()
	{
		List<Setor> todosSetores= setores.findAll();
		return todosSetores;
	}
	
	
}
