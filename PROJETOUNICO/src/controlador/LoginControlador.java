package controlador;

import spark.*;
import java.util.*;

/** Direciona o usuário para a página de login. Se as informações dos usuário forem digitadas de forma incorreta,
 *  será enviada uma mensagem o avisando disto. */
public class LoginControlador implements TemplateViewRoute{

	public ModelAndView handle(Request req, Response resp) {	
		HashMap mapa = new HashMap();
		if (req.queryParams("erro") != null) { 
			mapa.put("erro", req.queryParams("erro"));
		}
		return new ModelAndView(mapa, "login.html");
	}
}