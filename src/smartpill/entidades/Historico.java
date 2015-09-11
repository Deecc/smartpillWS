package smartpill.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name="historicoUsuario", query="SELECT h FROM Historico h WHERE h.usuario = :usuario")
})

@Entity
public class Historico implements Serializable {
	private int id;
	private int remedio;
	private String usuario;
	private Date horario;
	private static final long serialVersionUID = 1L;

	
	public Historico() {
		super();
	}

	public Historico(int remedio, String usuario, Date horario) {
		super();
		this.remedio = remedio;
		this.usuario = usuario;
		this.horario = horario;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRemedio() {
		return remedio;
	}

	public void setRemedio(int remedio) {
		this.remedio = remedio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.DATE)
	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}
}
