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
		Resenha resenha = new Resenha();
		resenha = req.session().attribute("resenha_logada");
		System.out.println(resenha);
		HashMap mapa = new HashMap();
		mapa.put("resenha", resenha.getTexto());		
		return new ModelAndView(mapa, "ver_resenha.html");
	}

}
