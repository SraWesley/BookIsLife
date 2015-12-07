package controlador;

import spark.*;
import modelo.*;
import persistencia.*;

/** � criada uma vari�vel que recebe o ISBN do livro que se deseja excluir. 
 * Carrega-se o livro por esse n�mero atrav�s do load. Se o livro for diferente de null ele � 
 * exclu�do e o usu�rio � direcionado para a lista de livros.  */
public class ExcluiControlador implements TemplateViewRoute {
	
	private LivroDAO dao = new LivroDAO();

	public ModelAndView handle(Request req, Response resp) {		
		String numero = req.params("ISBN");
		Livro livro = dao.load(numero);
		if (livro != null) dao.delete(livro);
		resp.redirect("/lista");
		return null;
	}
}