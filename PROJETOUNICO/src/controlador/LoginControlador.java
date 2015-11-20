package controlador;

import java.util.*;
import spark.*;

public class LoginControlador implements TemplateViewRoute{

	public ModelAndView handle(Request req, Response resp) {	
		HashMap mapa = new HashMap();
		if (req.queryParams("erro") != null) { 
			mapa.put("erro", req.queryParams("erro"));
		}
		return new ModelAndView(mapa, "login.html");
	}
}