package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

// Classe de Teste
public class ClienteTest {

	@Test // Indico um m�todo de teste unit�ro
	public void testaQueAConexaoComOServidorFunciona(){
		
		// Cria��o de um client
		Client client = ClientBuilder.newClient();
		
		//Cria��o de um alvo com a URI base
		WebTarget target = client.target("http://www.mocky.io");
		
		// Indica��o de um caminho espec�fico com o alvo
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		
		// Imprimindo a representa��o do carrinho
		System.out.println(conteudo);
		
		// Valida��o do teste unit�rio
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
	}
}
