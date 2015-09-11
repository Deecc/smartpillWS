package smartpill.fachada;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import smartpill.entidades.Historico;
import smartpill.entidades.ResponseRequest;
import smartpill.negocio.SmartPill;


@Stateless
@Path("/api")
public class HistoricoFachadaImpl {

	@EJB
	private SmartPill negocio;
	
	
	/*
	 * - Histórico de usuario
	 * URL: http://localhost:8080/smartpill/api/historico?email=asf@asd.com (GET)
	 * */
	@GET
	@Path("/historico")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Historico> historico(@QueryParam("email") String usuario) {
		return negocio.historicoUsuario(usuario);
	}

	/*
	 * - Registro de consumo do remedio
	 * URL: http://localhost:8080/smartpill/api/historico/registrar (POST)
	 * */
	@POST
	@Path("/historico/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrar(Historico historico) {
		if (negocio.salvarHistorico(historico)) { // Sucesso
			return Response.status(201).entity(new ResponseRequest()).build();
		}
		else { // Erro
			return Response.status(400).entity(new ResponseRequest(negocio.getMensagem())).build();
		}
	}
}
