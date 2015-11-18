package controlador;

import java.util.ArrayList;
import java.util.HashMap;
import modelo.Resenha;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class AdicionaResenhaControlador implements TemplateViewRoute {
 
	public ModelAndView handle(Request req, Response resp) {
		ArrayList<Resenha> resenhas = new ArrayList<Resenha>();
		HashMap mapa = new HashMap();
		mapa.put("resenhas", resenhas);		
		return new ModelAndView(mapa, "adiciona_resenha.html");
	}
}