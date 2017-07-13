package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

// Anota��o para a cria��o do recurso
@Path("projetos")
public class ProjetoResource {

	/*
	//Recebendo par�metro pela URI
	@Path("{id}")
	// Indica o m�todo de acesso
	@GET
	
	// Indica o tipo de retorno produzido pelo m�todo em XML
	//@Produces(MediaType.APPLICATION_JSON)
	
	// Indica o tipo de retorno produzido pelo m�todo em XML
	@Produces(MediaType.APPLICATION_XML)
	
	public String busca(@PathParam("id") long id){
		
		// Busca o projeto no banco simulado
		Projeto projeto = new ProjetoDAO().busca(id);
		
		// Usa a biblioteca XStream para retornar um JSON
		//return projeto.toJSON();
		
		// Usa a biblioteca XStream para retornar um XML
		return projeto.toXML();
		
	}
	*/
	
	
		//Recebendo par�metro pela URI
		@Path("{id}")
		// Indica o m�todo de acesso
		@GET
		
		// Indica o tipo de retorno produzido pelo m�todo em XML
		//@Produces(MediaType.APPLICATION_JSON)
		
		// Indica o tipo de retorno produzido pelo m�todo em XML
		@Produces(MediaType.APPLICATION_XML)
		
		// Usando o JAX-B para serializar o retorno
		public Projeto busca(@PathParam("id") long id){
			
			// Busca o projeto no banco simulado
			Projeto projeto = new ProjetoDAO().busca(id);
			
			// Usa a biblioteca XStream para retornar um XML
			return projeto;
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo){
		
		// Deserializar o meu conte�do em um Projeto para persistir no banco de dados
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		
		// Crio a inst�ncia do DAO para persistir no banco de dados
		new ProjetoDAO().adiciona(projeto);
		
		// Cria uma url para uso no retorno como Response
		URI location = URI.create("/projetos/" + projeto.getId());
		
		// Retorna um Status Code // 201 - Created
		return Response.created(location).build();
		
		// Encaminho uma string de resposta
		//return "<status>sucesso</sucesso>";
	}
	
	// Cria��o do path com subrecurso
	@Path("{id}/projetos/{id}")
	// Uso da anota��o @DELETE
	@DELETE
	public Response removeProjeto(@PathParam("id")long id){
		
		new ProjetoDAO().remove(id);		
		return Response.ok().build();
	}
	
	
}
