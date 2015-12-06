package controlador;

import spark.*;
import java.util.*;

public class AdicionaResenhaControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		HashMap mapa = new HashMap();
		mapa.put("ISBN", req.params("ISBN"));
		return new ModelAndView(mapa, "adiciona_resenha.html");
	}
}