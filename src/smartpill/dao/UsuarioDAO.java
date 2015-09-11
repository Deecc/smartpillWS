package smartpill.dao;

import javax.ejb.Local;

import smartpill.entidades.Usuario;


@Local
public interface UsuarioDAO {
	
	public void salvarUsuario(Usuario usuario) throws Exception;
	public Usuario buscarUsuario(Usuario usuario);
	public Usuario buscarUsuarioEmail(String email);
	public void removerUsuario(Usuario usuario) throws Exception;
}
