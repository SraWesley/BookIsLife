package controlador;

import spark.*;
import modelo.*;
import java.util.*;

public class EntraPerfilControlador implements TemplateViewRoute{

	public ModelAndView handle(Request req, Response resp) {
		Usuario u = req.session().attribute("usuario_logado");
		HashMap mapa = new HashMap();
		mapa.put("usuario", u);
		mapa.put("meusLivros", u.getMeusLivros());
		return new ModelAndView(mapa, "perfil.html");
	}
}