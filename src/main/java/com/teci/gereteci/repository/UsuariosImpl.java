package com.teci.gereteci.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.teci.gereteci.model.Usuario.Usuario;

public class UsuariosImpl implements UsuariosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Optional<Usuario> porMatricula(String matricula) {
		return manager.createQuery("from Usuario where lower(matricula) = lower(:matricula)", Usuario.class).setParameter("matricula", matricula).getResultList().stream().findFirst();
	}
	
	@Override
	public List<String> permissoes (Usuario usuario) {
		return manager.createQuery(
				"select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
				.setParameter("usuario", usuario)
				.getResultList();
}

}
