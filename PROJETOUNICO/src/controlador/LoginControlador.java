package controlador;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class LoginControlador implements TemplateViewRoute{

	public ModelAndView handle(Request req, Response resp) {		
		return new ModelAndView(null, "login.html");
	}
}