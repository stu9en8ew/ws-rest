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

	// Cria��o da vari�vel do server
	private HttpServer server;
	
	// Cria��o do m�todo que inicializa o servidor Grizzly antes do teste
	@Before
	public void beginServidor(){
		this.server = Servidor.inicializaServidor();
	}
	
	// Cria��o do m�todo que para o servidor Grizzly ap�s o teste
	@After
	public void stopServidor(){
		server.stop();
	}
	
	/*
	// M�todo de teste
	@Test
	public void testaQueBuscaTrazOProjetoEsperado(){
		
		//Cria��o do cliente
		Client client = ClientBuilder.newClient();
		
		// Defini��o da URI alvo
		WebTarget target = client.target("http://localhost:8180");
		
		// Defini��o da URI espec�fica baseada no alvo 
		String conteudo = target.path("/projetos/1").request().get(String.class);
		
		// Deserializa��o do conte�do XML para um objeto projeto		 
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		
		// Teste unit�rio de igualdade
		Assert.assertEquals("Minha Loja", projeto.getNome());
	}
	*/
	
	
		// M�todo de teste
		@Test
		public void testaQueBuscaTrazOProjetoEsperado(){
			
			//Cria��o do cliente
			Client client = ClientBuilder.newClient();
			
			// Defini��o da URI alvo
			WebTarget target = client.target("http://localhost:8180");
			
			// Defini��o da URI espec�fica baseada no alvo 
			Projeto projeto = target.path("/projetos/1").request().get(Projeto.class);
			
			// Teste unit�rio de igualdade
			Assert.assertEquals("Minha Loja", projeto.getNome());
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
