package web;

import spark.*;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {

	public static void main(String[] args) {

		MustacheTemplateEngine engine = new MustacheTemplateEngine("views");
		
		Spark.staticFileLocation("/pub");
		
		LoginController loginController = new LoginController();
		
		Route cadastrando = loginController.novoCadastro;
		
		TemplateViewRoute logando = loginController.usandoCadastro;
		
		Route entrando = loginController.logando;
		
		TemplateViewRoute perfil = loginController.perfil;
		
		Route perfilp = loginController.perfilp;

		Spark.post("/reg", cadastrando);
		
		Spark.get("/home", logando, engine);
			
		Spark.post("/login", entrando);
			
		Spark.post("/perfil", perfilp);
			
		Spark.get("/perfil", perfil, engine);	
	}
}