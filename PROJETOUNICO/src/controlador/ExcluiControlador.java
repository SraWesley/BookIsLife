package controlador;

import modelo.Livro;
import persistencia.LivroDAO;
import spark.*;

public class ExcluiControlador implements TemplateViewRoute {
	
	private LivroDAO dao = new LivroDAO();

	public ModelAndView handle(Request req, Response resp) {		
		int numero = Integer.parseInt(req.params("ISBN"));
		Livro livro = dao.load(numero);
		if (livro != null) dao.delete(livro);
		resp.redirect("/lista");
		return null;
	}
}