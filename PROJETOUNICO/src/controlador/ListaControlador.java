package controlador;

import java.util.*;
import spark.*;
import modelo.Livro;
import persistencia.LivroDAO;

public class ListaControlador implements TemplateViewRoute {

	private LivroDAO dao = new LivroDAO();
	
	public ModelAndView handle(Request req, Response resp) {
		ArrayList<Livro> livros = dao.findAll();
		HashMap mapa = new HashMap();
		mapa.put("livros", livros);		
		return new ModelAndView(mapa, "livro_lista.html");
	}
}