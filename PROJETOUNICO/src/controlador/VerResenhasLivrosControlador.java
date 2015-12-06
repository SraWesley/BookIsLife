package controlador;

import java.util.*;
import modelo.*;
import persistencia.*;
import spark.*;

public class VerResenhasLivrosControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		ArrayList<Resenha> resenha_lista = new ArrayList<Resenha>();
		ResenhaDAO dao = new ResenhaDAO();
		String ISBN = req.params("ISBN");
		resenha_lista = dao.resenhasDoLivro(ISBN);
		// System.out.println(resenha_lista);
		LivroDAO daoLivro = new LivroDAO();
		Livro livro = daoLivro.load(ISBN);
		
		HashMap mapa = new HashMap();
		mapa.put("livro", livro.getSinopse());
		mapa.put("resenha_lista", resenha_lista);
		return new ModelAndView(mapa, "verTodasResenhas.html");	
	}
}