package controlador;

import spark.*;
import modelo.*;
import java.util.*;
import persistencia.*;

/** Cria um Array de livros que s�o "lidos" pelo findAll e s�o "impressos" no livro_lista.html */
public class ListaControlador implements TemplateViewRoute {

	private LivroDAO dao = new LivroDAO();
	
	public ModelAndView handle(Request req, Response resp) {
		ArrayList<Livro> livros = dao.findAll();
		HashMap mapa = new HashMap();
		mapa.put("livros", livros);		
		return new ModelAndView(mapa, "livro_lista.html");
	}
}