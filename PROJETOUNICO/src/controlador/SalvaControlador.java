package controlador;

import spark.*;
import modelo.*;
import java.util.*;
import persistencia.*;

/** O usu�rio dever� estar na p�g. livro_cadastrar. Estando nesta p�g. ele dever� preencher os campos com as informa��es do livro, 
 * uma vez preenchidos e de acordo com as "regras" do programa, essas informa��es ser�o "setadas" e o livro ser� salvo.
 * Se essas informa��es n�o estiverem de acordo com as "regras" do programa ser�o enviadas mensagens de erro, como ISBN (tamanho
 * incorreto), nome (< 3) ou ainda se o livro j� estiver na lista de livros j� cadastrados no programa.
 * Ap�s qualquer uma dessas a��es o usu�rio ser� direcionado para a lista de livros j� cadastrados (Biblioteca) */
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
		
		if (livro.getISBN().length() == 10 || livro.getISBN().length() == 13) {
			if (livro.getNome().length() < 3) {
			String erro = "";
			erro = "T�tulo deve ter pelo menos 3 caracteres";
			HashMap mapa = new HashMap();
			mapa.put("erro", erro);
			return new ModelAndView(mapa,"livro_cadastrar.html");
		} else {
			boolean existeLivro = dao.testandoSeLivroExiste(livro.getNome());
			if (existeLivro) {
				erro2 = "Livro j� existente!";
				HashMap mapa1 = new HashMap();
				mapa1.put("erro2", erro2);
				return new ModelAndView(mapa1,"livro_cadastrar.html");
			}
		}		
			dao.save(livro);
			resp.redirect("/lista");
		} else {
			String erro3 = "";
			erro3 = "ISBN inv�lido";
			HashMap mapa = new HashMap();
			mapa.put("erro3", erro3);
			return new ModelAndView(mapa,"livro_cadastrar.html");
		}
		
		
		
		return null;
	}
}