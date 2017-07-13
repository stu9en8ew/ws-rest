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
	@Test // Indico um método de teste unitáro
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado(){
		
		// Criação de um client
		Client client = ClientBuilder.newClient();
		
		//Criação de um alvo com a URI base
		WebTarget target = client.target("http://localhost:8180");
		
		// Indicação de um caminho específico com o alvo
		String conteudo = target.path("/carrinhos/1").request().get(String.class);
		
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
		
	}
	*/
	
	
	@Test // Indico um método de teste unitáro
	// Usando o JAX-B para serializar o retorno
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado(){
		
		// Criação de um client
		Client client = ClientBuilder.newClient();
		
		//Criação de um alvo com a URI base
		WebTarget target = client.target("http://localhost:8180");
		
		// Indicação de um caminho específico com o alvo
		Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);
		
		//Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
		
	}
	
	
	
	
	
	
	
}
