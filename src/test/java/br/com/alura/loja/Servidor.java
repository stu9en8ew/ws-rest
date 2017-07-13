package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

// Classe de Servidor
public class Servidor {

	public static void main(String[] args) throws IOException{
		
		HttpServer server = inicializaServidor();
		
		System.out.println("Servidor rodando...");
		System.in.read();
		
		// Parando o servidor
		server.stop();
	}

	public static HttpServer inicializaServidor() {
		// Definindo a URI
		URI uri = URI.create("http://localhost:8180/");
		
		// Definindo as configurações de pacotes do projeto para o servidor
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		
		// Criando um servidor http compatível com servlet api, JAX-RS e Jersey com o Grizzly
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		
		return server;
	}
}
