package controlador;

import modelo.Livro;
import persistencia.LivroDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class ExcluiControlador implements TemplateViewRoute {
	
	private LivroDAO dao = new LivroDAO();

	@Override
	public ModelAndView handle(Request req, Response resp) {		
		int numero = Integer.parseInt(req.params("ISBN")); // /excluir/:numero
		Livro livro = dao.load(numero);
		if (livro != null) {
			dao.delete(livro);
		}		
		resp.redirect("/lista");
		return null;
	}

}
