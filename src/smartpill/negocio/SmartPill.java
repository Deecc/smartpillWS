package smartpill.negocio;

import java.util.List;

import javax.ejb.Local;

import smartpill.entidades.Historico;
import smartpill.entidades.Remedio;
import smartpill.entidades.Usuario;


@Local
public interface SmartPill {
	
	// Remedio
	public List<Remedio> listarRemediosUsuario(String nome);
	public boolean salvarRemedio(Remedio remedio);
	public boolean removerRemedio(int id);
	
	// Historico
	public List<Historico> historicoUsuario(String email);
	public boolean salvarHistorico(Historico historico);
	
	// Usuario
	public boolean salvarUsuario(Usuario usuario);
	public Usuario buscarUsuario(Usuario usuario);
	public boolean removerUsuario(Usuario usuario);
	
	// Auxiliar
	public String getMensagem();
	public void setMensagem(String mensagem);
}
