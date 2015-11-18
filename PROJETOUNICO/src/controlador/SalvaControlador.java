package controlador;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import modelo.Livro;
import persistencia.LivroDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class SalvaControlador implements TemplateViewRoute {
	
	private LivroDAO dao = new LivroDAO();

	public ModelAndView handle(Request req, Response resp) {
		
		Livro livro = new Livro();
		livro.setISBN(req.queryMap("ISBN").integerValue());
		livro.setNome(req.queryMap("nome").value());
		livro.setEscritor(req.queryMap("escritor").value());
		livro.setEditora(req.queryMap("editora").value());
		
		///ver se tem necessidade disso
		if (livro.getNome().length() < 3) {
			String erro = "";
			
			try {
				erro = URLEncoder.encode("T�tulo deve ter pelo menos 3 caracteres", "UTF-8");
			}
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			resp.redirect("/novo?erro=" + erro);
		} 
		
		else {
			dao.save(livro);	
			resp.redirect("/lista");
		}
//		if (filme.getTitulo().length() < 3) { // inválido
//			Spark.halt(400, "Título deve ter pelo menos 3 caracteres");
//		} else { // válido
//			dao.save(filme);	
//			resp.redirect("/lista");
//		}
		
		return null;
	}
}