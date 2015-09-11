package smartpill.fachada;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import smartpill.entidades.ResponseRequest;
import smartpill.entidades.Usuario;
import smartpill.negocio.SmartPill;


@Stateless
@Path("/api/usuario")
public class UsuarioFachadaImpl {

	@EJB
	private SmartPill negocio;
	
	
	/*
	 * - Cadastro de usuario
	 * URL: http://localhost:8080/smartpill/api/usuario/cadastrar (POST)
	 * */
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(Usuario usuario) {
		if (negocio.salvarUsuario(usuario)) { // Sucesso
			return Response.status(201).entity(new ResponseRequest()).build();
		}
		else { // Erro
			return Response.status(400).entity(new ResponseRequest(negocio.getMensagem())).build();
		}
	}

	/*
	 * - Login de usuario
	 * URL: http://localhost:8080/smartpill/api/usuario/login (POST)
	 * */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Usuario usuario) {
		if (negocio.buscarUsuario(usuario) != null) { // Sucesso
			return Response.status(201).entity(new ResponseRequest()).build();
		}
		else { // Erro
			return Response.status(400).entity(new ResponseRequest(negocio.getMensagem())).build();
		}
	}

	/*
	 * - Remocao de usuario
	 * URL: http://localhost:8080/smartpill/api/usuario/remover (POST)
	 * */
	@POST
	@Path("/remover")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response remover(Usuario usuario) {
		if (negocio.removerUsuario(usuario)) { // Sucesso
			return Response.status(201).entity(new ResponseRequest()).build();
		}
		else { // Erro
			return Response.status(400).entity(new ResponseRequest(negocio.getMensagem())).build();
		}
	}
	
	/*
	 * Request para verificar se foi possivel o deploy
	 * */
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_HTML)
	public String helloHtml() {
		return "<html> " + "<title>" + "SmartPill" + "</title><body><h1>" + "SmartPill" + "</h1><p>" + "Voce vai precisar de nos" + "</p></body>" + "</html> ";
	}
}
