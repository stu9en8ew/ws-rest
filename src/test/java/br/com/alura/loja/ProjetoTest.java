package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

import junit.framework.Assert;

// Classe de Teste
public class ProjetoTest {

	// Anotação de teste unitário
	@Test
	public void executaTesteDoProjeto(){
		
		// Criando o cliente
		Client client = ClientBuilder.newClient();
		
		//Definir uma URI base como alvo
		WebTarget target = client.target("http://localhost:8180");
		
		// Definindo uma URI específica com base no alvo
		String conteudo = target.path("/projetos").request().get(String.class);
		
		// Imprimo o conteúdo
		System.out.println(conteudo);
		
		// Validação do contéudo com o Assert
		Assert.assertTrue(conteudo.contains("Minha Loja"));
		
	}
}
