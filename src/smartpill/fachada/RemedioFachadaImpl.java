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

import smartpill.entidades.Remedio;
import smartpill.entidades.ResponseRequest;
import smartpill.negocio.SmartPill;


@Stateless
@Path("/api/remedio")
public class RemedioFachadaImpl {

	@EJB
	private SmartPill negocio;
	
	
	/*
	 * - Listagem de remedios por Usuario
	 * URL: http://localhost:8080/smartpill/api/remedio/listar?email=asd@asd.com (GET)
	 * */
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Remedio> listar(@QueryParam("email") String nome) {
		return negocio.listarRemediosUsuario(nome);
	}

	/*
	 * - Cadastro de novo registro de Remedio
	 * URL: http://localhost:8080/smartpill/api/remedio/cadastrar (POST)
	 * */
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(Remedio remedio) {
		if (negocio.salvarRemedio(remedio)) { // Sucesso
			return Response.status(201).entity(new ResponseRequest()).build();
		}
		else { // Erro
			return Response.status(400).entity(new ResponseRequest(negocio.getMensagem())).build();
		}
	}

	/*
	 * - Exclui registro de Remedio pelo ID
	 * URL: http://localhost:8080/smartpill/api/remedio/remover?id=1 (POST)
	 * */
	@POST
	@Path("/remover")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response remover(@QueryParam("id") int id) {
		if (negocio.removerRemedio(id)) { // Sucesso
			return Response.status(201).entity(new ResponseRequest()).build();
		}
		else { // Erro
			return Response.status(400).entity(new ResponseRequest(negocio.getMensagem())).build();
		}
	}

}
