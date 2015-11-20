package controlador;

import spark.*;

public class PaginaInicialControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {		
		return new ModelAndView(null, "index.html");
	}
}