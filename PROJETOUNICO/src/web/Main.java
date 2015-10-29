package web;

import controlador.ExcluiControlador;
//import controlador.FotoControlador;
import controlador.ListaControlador;
import controlador.NovoControlador_Livros;
import controlador.NovoControlador_usuario;
import controlador.PaginaInicialControlador;
import controlador.SalvaControlador;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {
	
	public static void main(String[] args) {
		
		
		
		// precisa de um package publico (pub)
		// onde ficam os css, js, imgs, conteúdo fixo
		Spark.staticFileLocation("/pub");
		
		// precisa de um package apresentacao (views)
		// onde ficam os HTML's
		MustacheTemplateEngine engine = 
				new MustacheTemplateEngine("apresentacao");
		
		PaginaInicialControlador paginaInicial =
				new PaginaInicialControlador();
		
		// get ou post (get ler informações, post submeter informações)
		Spark.get("/", paginaInicial, engine);
		
		NovoControlador_Livros novoControlador = 
				new NovoControlador_Livros();
		
		// abrir o form
		Spark.get("/novo", novoControlador, engine);
		
		NovoControlador_usuario novoControladorUsuario = new NovoControlador_usuario();
		
		Spark.get("/usuario_cadastrar", novoControladorUsuario, engine);
		
		SalvaControlador salvaControlador =
				new SalvaControlador();
		
		// submissão do form
		Spark.post("/salva", salvaControlador, engine);
		
		ListaControlador listaControlador =
				new ListaControlador();
		
		Spark.get("/lista", listaControlador, engine);
		
		ExcluiControlador excluiControlador = 
				new ExcluiControlador();
		
		Spark.get("/exclui/:numero", excluiControlador, engine);
		
	
		//FotoControlador upador = new FotoControlador();
		//Spark.post("/recebefoto", upador);
		
		
				
	}

}







