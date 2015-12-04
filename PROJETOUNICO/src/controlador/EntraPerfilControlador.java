package controlador;

import java.util.HashMap;

import modelo.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class EntraPerfilControlador implements TemplateViewRoute{

	public ModelAndView handle(Request req, Response resp) {
		Usuario u = req.session().attribute("usuario_logado");
		HashMap mapa = new HashMap();
		mapa.put("usuario", u);
		System.out.println("estamos aquii");
		mapa.put("meusLivros", u.getMeusLivros());
		return new ModelAndView(mapa, "perfil.html");
	}

}
