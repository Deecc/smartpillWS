package smartpill.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import smartpill.entidades.Remedio;
import smartpill.entidades.Usuario;


@Stateless
public class UsuarioDAOImpl implements UsuarioDAO {

	@PersistenceContext(unitName="smartPill")
	private EntityManager em;
	
	
	/*
	 * Salva um novo registro de Usuario
	 * */
	@Override
	public void salvarUsuario(Usuario usuario) throws Exception {
		// Verifica se o Usuario ja esta cadastrado
		try {
			if (buscarUsuarioEmail(usuario.getEmail()) == null) {
				em.persist(usuario);
			}
			else {
				throw new Exception("Login existente");
			}
		}
		catch (ConstraintViolationException exception) {
			throw new Exception("Login existente");
		}
		catch (PersistenceException exception) {
			throw new Exception("Login existente");
		}
	}

	/*
	 * Busca um registro de Usuario
	 * */
	@Override
	public Usuario buscarUsuario(Usuario usuario) {
		Usuario usuarioSalvo = buscarUsuarioEmail(usuario.getEmail());
		if (usuarioSalvo != null &&
			usuarioSalvo.getSenha().equals(usuario.getSenha())) {
			return usuarioSalvo;
		}
		else {
			return null;
		}
	}
	
	/*
	 * Busca um registro de Usuario por email
	 * */
	@Override
	public Usuario buscarUsuarioEmail(String email) {
		return em.find(Usuario.class, email);
	}

	/*
	 * Remove um registro de Remedio
	 * */
	@Override
	public void removerUsuario(Usuario usuario) throws Exception {
		// Verifica se o Usuario existe		
		Usuario usuarioSalvo = buscarUsuarioEmail(usuario.getEmail());
		if (usuarioSalvo.getSenha().equals(usuario.getSenha())) {
			em.remove(usuarioSalvo);
		}
		else {
			throw new Exception("Usuário não encontrado");
		}
	}
}
