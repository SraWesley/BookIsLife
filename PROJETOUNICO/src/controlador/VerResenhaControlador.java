package controlador;

import java.io.File;
import java.util.HashMap;

import modelo.Resenha;
import modelo.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class VerResenhaControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		Usuario usuario = req.session().attribute("usuario_logado");
		Resenha r = dao.load(?);
		HashMap mapa = new HashMap();
		mapa.put("texto", r.getTexto());		
		return new ModelAndView(mapa, "ver_resenha.html");
	}

}
