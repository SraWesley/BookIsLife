package controlador;

import modelo.Cadastro;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class PaginaInicialControlador implements TemplateViewRoute {
	
	public ModelAndView handle(Request req, Response res) {
		return new ModelAndView(null, "index.html");
	}	
}