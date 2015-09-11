package smartpill.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import smartpill.entidades.Historico;
import smartpill.entidades.Remedio;
import smartpill.entidades.Usuario;


@Stateless
public class HistoricoDAOImpl implements HistoricoDAO {

	@PersistenceContext(unitName="smartPill")
	private EntityManager em;
	
	@EJB
	UsuarioDAO usuarioDAO;
	
	@EJB
	RemedioDAO remedioDAO;
	
	
	/*
	 * Retorna o historico do usuario passado
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> historicoUsuario(String usuario) {
		Query consulta = em.createNamedQuery("historicoUsuario");
		consulta.setParameter("usuario", usuario);
		return consulta.getResultList();
	}

	/*
	 * Salva um novo registro de Historico (Mensagem Assincrona)
	 * */
	@Override
	public void salvarHistorico(Historico historico) throws Exception {
		// Verifica se o Usuario e o Remedio associados existem
		Usuario usuario = em.find(Usuario.class, historico.getUsuario());
		Remedio remedio = em.find(Remedio.class, historico.getRemedio());
		
		if (remedio.getQuantidade() == 0) {
			throw new Exception("O remédio acabou");
		}
		else if (usuario != null && remedio != null) {
			// Atualiza a quantidade do remedio
			remedio.setQuantidade(remedio.getQuantidade() - 1);
			em.merge(remedio);
			
			// Registra o consumo no histórico
			em.persist(historico);
		}
		else if (usuario == null) {
			throw new Exception("O usuario não foi cadastrado");
		}
		else {
			throw new Exception("O remédio não foi cadastrado");
		}
	}
}
