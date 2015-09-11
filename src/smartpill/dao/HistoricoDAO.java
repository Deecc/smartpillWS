package smartpill.dao;

import java.util.List;

import javax.ejb.Local;

import smartpill.entidades.Historico;


@Local
public interface HistoricoDAO {
	
	public List<Historico> historicoUsuario(String usuario);
	public void salvarHistorico(Historico historico) throws Exception;
}
