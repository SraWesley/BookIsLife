package controlador;

import java.util.*;
import modelo.Livro;
import persistencia.LivroDAO;
import spark.*;

public class SalvaControlador implements TemplateViewRoute {

	private LivroDAO dao = new LivroDAO();

	public ModelAndView handle(Request req, Response resp) {

		Livro livro = new Livro();
		livro.setISBN(req.queryMap("ISBN").value());
		livro.setNome(req.queryMap("nome").value());
		livro.setEscritor(req.queryMap("escritor").value());
		livro.setEditora(req.queryMap("editora").value());
		livro.setSinopse(req.queryMap("sinopse").value());
		String erro2="";
		LivroDAO dao = new LivroDAO();
		boolean ISBNvalido = true;
		ISBNvalido = dao.validandoISBN(livro.getISBN());
		if (!ISBNvalido) {
			String erro3 = "";
			erro3 = "ISBN inválido";
			HashMap mapa = new HashMap();
			mapa.put("erro3", erro3);
			return new ModelAndView(mapa,"livro_cadastrar.html");
		}
		
		if (livro.getNome().length() < 3) {
			String erro = "";
			erro = "Título deve ter pelo menos 3 caracteres";
			HashMap mapa = new HashMap();
			mapa.put("erro", erro);
			return new ModelAndView(mapa,"livro_cadastrar.html");
		} else {
				boolean existeLivro = dao.testandoSeLivroExiste(livro.getNome());
				if(existeLivro){
					erro2 = "Livro já existente!";
					HashMap mapa1 = new HashMap();
					mapa1.put("erro2", erro2);
					return new ModelAndView(mapa1,"livro_cadastrar.html");
				}
			}
		
			dao.save(livro);
			resp.redirect("/lista");
			return null;
		}
		
}