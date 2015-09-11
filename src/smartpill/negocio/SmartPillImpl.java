package smartpill.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import smartpill.dao.*;
import smartpill.entidades.*;


@Stateless
public class SmartPillImpl implements SmartPill {

	@EJB
	RemedioDAO remedioDAO;
	
	@EJB
	HistoricoDAO historicoDAO;
	
	@EJB
	UsuarioDAO usuarioDAO;
	
	private String mensagem;
	
	
	/*
	 * Metodos da entidade REMEDIO
	 * */
	
	// Lista os remedios do Usuario passado
	@Override
	public List<Remedio> listarRemediosUsuario(String email) {
		return remedioDAO.listarRemediosUsuario(email);
	}

	// Salva um novo registro de Remedio
	@Override
	public boolean salvarRemedio(Remedio remedio) {
		try {
			remedioDAO.salvarRemedio(remedio);
			return true;
		}
		catch (Exception e) {
			mensagem = e.getMessage();
			return false;
		}
	}

	// Remove um registro de Remedio
	@Override
	public boolean removerRemedio(int id) {
		try {
			remedioDAO.removerRemedio(id);
			return true;
		}
		catch (Exception e) {
			mensagem = e.getMessage();
			return false;
		}
	}
	
	
	
	/*
	 * Metodos da entidade HISTORICO
	 * */

	@Override
	public List<Historico> historicoUsuario(String email) {
		return historicoDAO.historicoUsuario(email);
	}

	@Override
	public boolean salvarHistorico(Historico historico) {
		try {
			historicoDAO.salvarHistorico(historico);
			return true;
		} 
		catch (Exception e) {
			mensagem = e.getMessage();
			return false;
		}
	}
	
	
	
	/*
	 * Metodos da entidade USUARIO
	 * */

	@Override
	public boolean salvarUsuario(Usuario usuario) {
		try {
			usuarioDAO.salvarUsuario(usuario);
			return true;
		} 
		catch (Exception e) {
			mensagem = e.getMessage();
			return false;
		}
	}

	@Override
	public Usuario buscarUsuario(Usuario usuario) {
		return usuarioDAO.buscarUsuario(usuario);
	}

	@Override
	public boolean removerUsuario(Usuario usuario) {
		try {
			usuarioDAO.removerUsuario(usuario);
			return true;
		} 
		catch (Exception e) {
			mensagem = e.getMessage();
			return false;
		}
	}

	
	
	/*
	 * Atributo auxiliar
	 * */
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
	/*
	 * Metodo Agendado
	 * */
	
	// Atualiza status dos remedios cadastrados
	@Schedule(hour="0")
	public void atualizarInformacoes() {
		// Recupera remedios salvos
		List<Remedio> remedios = remedioDAO.listarRemedios(); 
		
		for (Remedio remedio : remedios) {
			// Data atual
			Date dataAtual = new Date();
			
			// Compara a data atual com a data de vencimento do remedio
			if (dataAtual.before(remedio.getVencimento())) {
				remedio.setVencido(false);
			}
			else {
				remedio.setVencido(true);
			}
		}
	}
}
