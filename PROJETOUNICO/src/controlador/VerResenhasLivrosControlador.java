package controlador;

import spark.*;
import modelo.*;
import java.util.*;
import persistencia.*;

/** Cria um ArrayList<Resenha>, pega o ISBN do livro desejado, add para o Array todas as resenhas de todos os usuários que tiverem
 *  resenhas neste livro com este ISBN.
 * Carrega a sinopse do livro que tiver esse ISBN e manda essas informações para verTodasResenhas.html  */
public class VerResenhasLivrosControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		ArrayList<Resenha> resenha_lista = new ArrayList<Resenha>();
		ResenhaDAO dao = new ResenhaDAO();
		String ISBN = req.params("ISBN");
		resenha_lista = dao.resenhasDoLivro(ISBN);
		LivroDAO daoLivro = new LivroDAO();
		Livro livro = daoLivro.load(ISBN);
	
		HashMap mapa = new HashMap();
		mapa.put("livro", livro.getSinopse());
		mapa.put("resenha_lista", resenha_lista);
		return new ModelAndView(mapa, "verTodasResenhas.html");	
	}
}