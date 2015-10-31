package controlador;

import java.util.ArrayList;
import java.util.HashMap;
import modelo.Livro;
import persistencia.LivroDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class ListaControlador implements TemplateViewRoute {

	private LivroDAO dao = new LivroDAO();
	
	public ModelAndView handle(Request req, Response resp) {
		ArrayList<Livro> livros = dao.findAll();
		HashMap mapa = new HashMap();
		mapa.put("livros", livros);		
		return new ModelAndView(mapa, "livro_lista.html");
	}
}