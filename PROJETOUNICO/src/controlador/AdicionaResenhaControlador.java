package controlador;

import java.util.*;
import spark.*;

public class AdicionaResenhaControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		// ArrayList<Resenha> resenhas = new ArrayList<Resenha>();
		HashMap mapa = new HashMap();
		mapa.put("ISBN", req.params("ISBN"));
		return new ModelAndView(mapa, "adiciona_resenha.html");
	}
}