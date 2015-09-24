package controlador;

import java.util.HashMap;

import modelo.Cadastro;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class NovoControlador implements TemplateViewRoute {
	
	private Cadastro cadastro = new Cadastro();
	public final TemplateViewRoute listandoUsuarios = new ListandoUsuarios();

	public ModelAndView handle(Request req, Response res) {
		
		return new ModelAndView(null, "usuario_cadastrar.html");
	}
	
	public class ListandoUsuarios implements TemplateViewRoute {
		public ModelAndView handle(Request req, Response res) {
			HashMap mapa = new HashMap();
			mapa.put("usuarios", cadastro.getUsuarios());
			return new ModelAndView(mapa, "usuarios.html");
		}	
	}
}