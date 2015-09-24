package web;

import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import controlador.NovoControlador;
import controlador.PaginaInicialControlador;

public class Main {

	public static void main(String[] args) {
		
		Spark.staticFileLocation("/publico");
	
		MustacheTemplateEngine engine = new MustacheTemplateEngine("apresentacao");
		
		PaginaInicialControlador paginaInicial = new PaginaInicialControlador();
		NovoControlador novoControlador = new NovoControlador();
		
		Spark.get("/", paginaInicial, engine);
		Spark.get("/novo", novoControlador, engine);
	}
}