package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ProjetoTest3 {

	private HttpServer server;
	private Client client;
	
	@Before
	public void startServer(){
		server = Servidor.inicializaServidor();
	}
	
	@After
	public void stopServer(){
		server.stop();
	}
	
	
	@Test
	public void testaInclusaoProjeto(){
		
		// Criação de um client
		this.client = ClientBuilder.newClient();
		
		//Criação de um alvo com a URI base
		WebTarget target = client.target("http://localhost:8180");
		
		// Crio a instância do novo projeto
		Projeto projeto = new Projeto(314L,"WebService",2016);
		
		// Efetuo a conversão para XML
		String xml = projeto.toXML();
		
		// Indica a montagem da representatividade para o servidor associando o XML e o MediaType usando o JAX-RS	
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		// Indicação de um caminho específico com o alvo
		Response response = target.path("/projetos").request().post(entity);
		
		// Efetua a validação do teste
		Assert.assertEquals(201, response.getStatus());
		
		// Verificação se a url está correta
		String location = response.getHeaderString("Location");
		
		// Efetuo um acesso para eu testar a URL do location
		String conteudo = client.target(location).request().get(String.class);
		
		// Efetuo a validação de conteúdo do meu objeto
		Assert.assertTrue(conteudo.contains("WebService"));
		
		// Efetue a validação do teste
		//Assert.assertEquals("<status>sucesso</sucesso>", response.readEntity(String.class));
		
	}
	
}
