package controlador;

import spark.*;
import java.util.*;

/** Direciona o usu�rio para a p�gina de login. Se as informa��es dos usu�rio forem digitadas de forma incorreta,
 *  ser� enviada uma mensagem o avisando disto. */
public class LoginControlador implements TemplateViewRoute{

	public ModelAndView handle(Request req, Response resp) {	
		HashMap mapa = new HashMap();
		if (req.queryParams("erro") != null) { 
			mapa.put("erro", req.queryParams("erro"));
		}
		return new ModelAndView(mapa, "login.html");
	}
}