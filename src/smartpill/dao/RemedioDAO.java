package smartpill.dao;

import java.util.List;

import javax.ejb.Local;

import smartpill.entidades.Remedio;


@Local
public interface RemedioDAO {
	
	public List<Remedio> listarRemedios();
	public List<Remedio> listarRemediosUsuario(String email);
	public void salvarRemedio(Remedio remedio)  throws Exception;
	public void removerRemedio(int id)  throws Exception;
}
