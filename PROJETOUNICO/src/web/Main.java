package web;

import controlador.EntraControlador;
import controlador.ExcluiControlador;
import controlador.ListaControlador;
import controlador.LoginControlador;
import controlador.NovoControlador_Livros;
import controlador.NovoControlador_usuario;
import controlador.PaginaInicialControlador;
import controlador.SalvaControlador;
import controlador.SalvaControladorUsuario;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {
	
	public static void main(String[] args) {
		
		Spark.staticFileLocation("/pub");
		
		MustacheTemplateEngine engine = new MustacheTemplateEngine("apresentacao");
		
		// HOME
		PaginaInicialControlador paginaInicial = new PaginaInicialControlador();
		Spark.get("/", paginaInicial, engine);
		
		
		// USUARIO
		NovoControlador_usuario novoControladorUsuario = new NovoControlador_usuario();
		Spark.get("/usuario_cadastrar", novoControladorUsuario, engine);
		
		SalvaControladorUsuario salvaUsuario = new SalvaControladorUsuario();
		Spark.post("/salvausuario", salvaUsuario, engine);
		
		
		
		LoginControlador loginControlador = new LoginControlador();
		Spark.get("/login", loginControlador, engine);
		
		EntraControlador entraControlador = new EntraControlador();
		Spark.post("/login", entraControlador, engine);
		
		
		// LIVROS
		NovoControlador_Livros novoControlador = new NovoControlador_Livros();
		Spark.get("/novo", novoControlador, engine);
				
		SalvaControlador salvaControlador = new SalvaControlador();
		Spark.post("/salva", salvaControlador, engine);
		
		ListaControlador listaControlador = new ListaControlador();
		Spark.get("/lista", listaControlador, engine);
		
		ExcluiControlador excluiControlador = new ExcluiControlador();
		Spark.get("/exclui/:ISBN", excluiControlador, engine);
	}
}