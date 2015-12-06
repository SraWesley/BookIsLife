package controlador;

import spark.*;

/** Direciona para a página inicial */
public class PaginaInicialControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {		
		return new ModelAndView(null, "index.html");
	}
}