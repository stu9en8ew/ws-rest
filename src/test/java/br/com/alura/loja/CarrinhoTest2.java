package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class CarrinhoTest2 {
	
	private HttpServer server;
	private Client client;
	
	@Before
	public void before(){
		this.server = Servidor.inicializaServidor();
	}
	
	@After
	public void after(){
		this.server.stop();
	}
	
	/*
	
	
	@Test // Indico um m�todo de teste unit�ro
	public void testaInclusaoCarrinho(){
		
		// Criando a configura��o de um cliente
		ClientConfig config = new ClientConfig();
		
		// Registro da configura��o do cliente usando o filtro de logging do Jersey
		config.register(new LoggingFilter());
		
		// Cria��o de um client com a configura��o de logging
		this.client = ClientBuilder.newClient(config);
		
		//Cria��o de um alvo com a URI base
		WebTarget target = client.target("http://localhost:8180");
		
		// Criando um novo carrinho
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("S�o Paulo");
		String xml = carrinho.toXML();
		
		// Montagem da representatividade associando o XML e o MediaType
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
			
		// Indica��o de uma URI espec�fica com o alvo
		Response response = target.path("/carrinhos").request().post(entity);
		
		// Defini��o do teste unit�rio
		//Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
		Assert.assertEquals(201, response.getStatus());
		
		// Verificando se a URL chamada est� correta
		String location = response.getHeaderString("Location");
		
		//Efetuo um acesso por get para testar a URL do location.
		String conteudo = client.target(location).request().get(String.class);
		
		// Efetuo o teste para localizar o nome produto incluido
		Assert.assertTrue(conteudo.contains("Tablet"));
		
	}
	*/
	
	
	
	@Test // Indico um m�todo de teste unit�ro
	// Usando o JAX-B para serializar o retorno
	public void testaInclusaoCarrinho(){
		
		// Criando a configura��o de um cliente
		ClientConfig config = new ClientConfig();
		
		// Registro da configura��o do cliente usando o filtro de logging do Jersey
		config.register(new LoggingFilter());
		
		// Cria��o de um client com a configura��o de logging
		this.client = ClientBuilder.newClient(config);
		
		//Cria��o de um alvo com a URI base
		WebTarget target = client.target("http://localhost:8180");
		
		// Criando um novo carrinho
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("S�o Paulo");
		//String xml = carrinho.toXML();
		
		// Montagem da representatividade associando o XML e o MediaType
		Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);
			
		// Indica��o de uma URI espec�fica com o alvo
		Response response = target.path("/carrinhos").request().post(entity);
		
		// Defini��o do teste unit�rio
		//Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
		Assert.assertEquals(201, response.getStatus());
		
		// Verificando se a URL chamada est� correta
		String location = response.getHeaderString("Location");
		
		//Efetuo um acesso por get para testar a URL do location.
		Carrinho carrinhoCarregado = client.target(location).request().get(Carrinho.class);
		
		// Efetuo o teste para localizar o nome produto incluido
		Assert.assertEquals("Tablet",carrinhoCarregado.getProdutos().get(0).getNome());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
