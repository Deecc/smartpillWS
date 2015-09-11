package smartpill.entidades;

import java.io.Serializable;
import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name="buscarUsuario", query="SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha"),
	@NamedQuery(name="buscarUsuarioEmail", query="SELECT u FROM Usuario u WHERE u.email = :email")
})

@Entity
public class Usuario implements Serializable {
	private String email;
	private String nome;
	private String senha;
	private static final long serialVersionUID = 1L;
	
	
	public Usuario() {
		super();
	}
	
	public Usuario(String email, String nome, String senha) {
		super();
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}
	
	@Id
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
