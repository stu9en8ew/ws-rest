package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ProjetoTest2 {

	// Criação da variável do server
	private HttpServer server;
	
	// Criação do método que inicializa o servidor Grizzly antes do teste
	@Before
	public void beginServidor(){
		this.server = Servidor.inicializaServidor();
	}
	
	// Criação do método que para o servidor Grizzly após o teste
	@After
	public void stopServidor(){
		server.stop();
	}
	
	/*
	// Método de teste
	@Test
	public void testaQueBuscaTrazOProjetoEsperado(){
		
		//Criação do cliente
		Client client = ClientBuilder.newClient();
		
		// Definição da URI alvo
		WebTarget target = client.target("http://localhost:8180");
		
		// Definição da URI específica baseada no alvo 
		String conteudo = target.path("/projetos/1").request().get(String.class);
		
		// Deserialização do conteúdo XML para um objeto projeto		 
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		
		// Teste unitário de igualdade
		Assert.assertEquals("Minha Loja", projeto.getNome());
	}
	*/
	
	
		// Método de teste
		@Test
		public void testaQueBuscaTrazOProjetoEsperado(){
			
			//Criação do cliente
			Client client = ClientBuilder.newClient();
			
			// Definição da URI alvo
			WebTarget target = client.target("http://localhost:8180");
			
			// Definição da URI específica baseada no alvo 
			Projeto projeto = target.path("/projetos/1").request().get(Projeto.class);
			
			// Teste unitário de igualdade
			Assert.assertEquals("Minha Loja", projeto.getNome());
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
