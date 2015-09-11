package smartpill.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name="listarRemedios", query="SELECT r FROM Remedio r"),
	@NamedQuery(name="listarRemediosUsuario", query="SELECT r FROM Remedio r WHERE r.usuario = :usuario")
})

@Entity
public class Remedio implements Serializable {
	private int id;
	private String nome;
	private String fabricante;
	private int quantidade;
	private boolean vencido;
	private Date vencimento;
	private String usuario;
	private static final long serialVersionUID = 1L;
	
	
	public Remedio() {
		super();
	}
	
	public Remedio(String nome, String fabricante, int quantidade, boolean vencido, Date vencimento,
			String usuario) {
		super();
		this.nome = nome;
		this.fabricante = fabricante;
		this.quantidade = quantidade;
		this.vencido = vencido;
		this.vencimento = vencimento;
		this.usuario = usuario;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getFabricante() {
		return fabricante;
	}
	
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Temporal(TemporalType.TIME)
	public Date getVencimento() {
		return vencimento;
	}
	
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public boolean isVencido() {
		return vencido;
	}

	public void setVencido(boolean vencido) {
		this.vencido = vencido;
	}
}
