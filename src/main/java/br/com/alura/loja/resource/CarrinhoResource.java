package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

// Anotação para criação de Recurso
@Path("carrinhos")
public class CarrinhoResource {

	/*
	// Recebendo parâmetro pela URI
	@Path("{id}")
	// Indica o método de acesso
	@GET 
	
	// Indica o tipo de retorno produzido pelo método em XML
	//@Produces(MediaType.APPLICATION_JSON)
	
	// Indica o tipo de retorno produzido pelo método em XML
	@Produces(MediaType.APPLICATION_XML) 
	
	//public String busca(@QueryParam("id") long id){
	public String busca(@PathParam("id") long id){
		
		// Busca um carrinho no banco simulado
		Carrinho carrinho = new CarrinhoDAO().busca(id);

		// Usa a biblioteca XStream para retornar um JSON
		//return carrinho.toJSON();
				
		// Usa a biblioteca XStream para retornar um XML
		return carrinho.toXML();
		
	}
	
	*/
	
	
		// Recebendo parâmetro pela URI
		@Path("{id}")
		// Indica o método de acesso
		@GET 
		
		// Indica o tipo de retorno produzido pelo método em XML
		//@Produces(MediaType.APPLICATION_JSON)
		
		// Indica o tipo de retorno produzido pelo método em XML
		@Produces(MediaType.APPLICATION_XML) 
		
		// Usando o JAX-B para serializar o retorno
		public Carrinho busca(@PathParam("id") long id){
			
			// Busca um carrinho no banco simulado
			Carrinho carrinho = new CarrinhoDAO().busca(id);

			// Usa a biblioteca XStream para retornar um XML
			return carrinho;
			
		}
	

	/*
	
	@POST
	//@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	//public String adiciona(String conteudo){
	public Response adiciona(String conteudo){
		
		// Deserializar o meu conteúdo em um Carrinho para persistir no banco de dados
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		
		// Crio a instância do DAO para persistir no banco de dados
		new CarrinhoDAO().adiciona(carrinho);
		
		// Cria uma url que indica o suceso
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		
		// Seto o meu retorno para um Status Code de Created // 201 - Created
		return Response.created(uri).build();
		
		// Encaminho uma string de resposta
		//return "<status>sucesso</status>";
	}
	
	*/
		
	
	@POST
	//@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	// Usando o JAX-B para serializar o retorno
	public Response adiciona(Carrinho carrinho){
		
		// Crio a instância do DAO para persistir no banco de dados
		new CarrinhoDAO().adiciona(carrinho);
		
		// Cria uma url que indica o suceso
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		
		// Seto o meu retorno para um Status Code de Created // 201 - Created
		return Response.created(uri).build();
	}		
		
		
	
	
	
	
	//Uso de subrecursos para definição de path
	@Path("{id}/produtos/{produtoId}")
	// Uso da anotação @DELETE
	@DELETE
	// Criação do método para remover um produto do carrinho
	public Response removeProduto(@PathParam("id")long id, @PathParam("produtoId") long produtoId){
		
		// Busco um carrinho baseado no seu id
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		// Removo um produto do carrinho baseado em seu produtoId
		carrinho.remove(produtoId);
		// Retorno um Status Code de resposta // 200 - OK
		return Response.ok().build();
	}
	
	// Uso de subrecursos para definição do path de alteração de um produto
	//@Path("{id}/produtos/{produtoId}")
	
	// Uso de subrecursos para definição do path de alteração da quantidade de um produto
	@Path("{id}/produtos/{produtoId}/quantidade")
	@Consumes(MediaType.APPLICATION_XML)
	@PUT
	public Response alteraProduto(String conteudo, @PathParam("id")long id, @PathParam("produtoId")long produtoId){
		
		// Busca o carrinho por Id
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		
		//Com o envio da representação pelo cliente, eu devo deserializar o meu conteúdo para um Produto
		Produto produto = (Produto) new XStream().fromXML(conteudo);
		
		// Efetuo a troca do produto
		//carrinho.troca(produto);
		
		//Efetuo a troca da quantidade do meu produto
		carrinho.trocaQuantidade(produto);
		
		// Retorna um Status Code // 200 - OK
		return Response.ok().build();
	}
	
}
