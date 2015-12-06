package controlador;

import spark.*;
import java.util.*;

/** Pega o ISBN do livro desejado para se add a resenha e envia para a pág. adiciona_resenha.html */
public class AdicionaResenhaControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		HashMap mapa = new HashMap();
		mapa.put("ISBN", req.params("ISBN"));
		return new ModelAndView(mapa, "adiciona_resenha.html");
	}
}