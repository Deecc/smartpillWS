package smartpill.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import smartpill.entidades.Remedio;
import smartpill.entidades.Usuario;


@Stateless
public class RemedioDAOImpl implements RemedioDAO {

	@PersistenceContext(unitName="smartPill")
	private EntityManager em;
	
	@EJB
	UsuarioDAO usuarioDAO;
	

	/*
	 * Retorna uma lista com todos os remedios cadastrados
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Remedio> listarRemedios() {
		Query consulta = em.createNamedQuery("listarRemedios");
		return consulta.getResultList();
	}
	
	/*
	 * Retorna uma lista com todos os remedios de um usuario
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Remedio> listarRemediosUsuario(String email) {
		Query consulta = em.createNamedQuery("listarRemediosUsuario");
		consulta.setParameter("usuario", email);
		return consulta.getResultList();
	}

	/*
	 * Salva um novo registro de Remedio
	 * */
	@Override
	public void salvarRemedio(Remedio remedio) throws Exception {
		// Verifica se o email é valido e se o Remedio já foi cadastrado
		Usuario usuario = usuarioDAO.buscarUsuarioEmail(remedio.getUsuario());
		
		if (usuario != null) {
			em.persist(remedio);
		}
		else {
			throw new Exception("O usuário não é cadastrado");
		}
	}

	/*
	 * Remove um registro de Remedio
	 * */
	@Override
	public void removerRemedio(int id) throws Exception {
		// Verifica se o remedio existe
		Remedio remedio = em.find(Remedio.class, id);
		
		if (remedio != null) {
			em.remove(remedio);
		}
		else {
			throw new Exception("O remédio não foi cadastrado");
		}
	}
}
