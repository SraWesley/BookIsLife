package controlador;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class NovoControlador_Livros implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		
		HashMap mapa = new HashMap();
		if (req.queryParams("erro") != null) mapa.put("erro", req.queryParams("erro"));
		
		return new ModelAndView(mapa, "livro_cadastrar.html");
	}
}