package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;

public class CarrinhoTest {
	
	private HttpServer server;
	
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
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado(){
		
		// Cria��o de um client
		Client client = ClientBuilder.newClient();
		
		//Cria��o de um alvo com a URI base
		WebTarget target = client.target("http://localhost:8180");
		
		// Indica��o de um caminho espec�fico com o alvo
		String conteudo = target.path("/carrinhos/1").request().get(String.class);
		
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
		
	}
	*/
	
	
	@Test // Indico um m�todo de teste unit�ro
	// Usando o JAX-B para serializar o retorno
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado(){
		
		// Cria��o de um client
		Client client = ClientBuilder.newClient();
		
		//Cria��o de um alvo com a URI base
		WebTarget target = client.target("http://localhost:8180");
		
		// Indica��o de um caminho espec�fico com o alvo
		Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);
		
		//Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
		
	}
	
	
	
	
	
	
	
}
