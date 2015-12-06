package controlador;

import spark.*;
import java.util.*;

/**
 * 
 * 
 *
 */
public class NovoControlador_usuario implements TemplateViewRoute{

	public ModelAndView handle(Request req, Response resp) {
		HashMap mapa = new HashMap();
		if (req.queryParams("erro") != null) mapa.put("erro", req.queryParams("erro"));
		return new ModelAndView(mapa, "usuario_cadastrar.html");
	}	
}