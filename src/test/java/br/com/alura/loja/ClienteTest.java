package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

// Classe de Teste
public class ClienteTest {

	@Test // Indico um método de teste unitáro
	public void testaQueAConexaoComOServidorFunciona(){
		
		// Criação de um client
		Client client = ClientBuilder.newClient();
		
		//Criação de um alvo com a URI base
		WebTarget target = client.target("http://www.mocky.io");
		
		// Indicação de um caminho específico com o alvo
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		
		// Imprimindo a representação do carrinho
		System.out.println(conteudo);
		
		// Validação do teste unitário
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
	}
}
