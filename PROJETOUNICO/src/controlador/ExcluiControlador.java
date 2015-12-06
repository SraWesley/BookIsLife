package controlador;

import spark.*;
import modelo.*;
import persistencia.*;

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