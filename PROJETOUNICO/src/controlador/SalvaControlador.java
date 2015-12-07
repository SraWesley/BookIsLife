package controlador;

import spark.*;
import modelo.*;
import java.util.*;
import persistencia.*;

/** O usuário deverá estar na pág. livro_cadastrar. Estando nesta pág. ele deverá preencher os campos com as informações do livro, 
 * uma vez preenchidos e de acordo com as "regras" do programa, essas informações serão "setadas" e o livro será salvo.
 * Se essas informações não estiverem de acordo com as "regras" do programa serão enviadas mensagens de erro, como ISBN (tamanho
 * incorreto), nome (< 3) ou ainda se o livro já estiver na lista de livros já cadastrados no programa.
 * Após qualquer uma dessas ações o usuário será direcionado para a lista de livros já cadastrados (Biblioteca) */
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
			erro = "Título deve ter pelo menos 3 caracteres";
			HashMap mapa = new HashMap();
			mapa.put("erro", erro);
			return new ModelAndView(mapa,"livro_cadastrar.html");
		} else {
			boolean existeLivro = dao.testandoSeLivroExiste(livro.getNome());
			if (existeLivro) {
				erro2 = "Livro já existente!";
				HashMap mapa1 = new HashMap();
				mapa1.put("erro2", erro2);
				return new ModelAndView(mapa1,"livro_cadastrar.html");
			}
		}		
			dao.save(livro);
			resp.redirect("/lista");
		} else {
			String erro3 = "";
			erro3 = "ISBN inválido";
			HashMap mapa = new HashMap();
			mapa.put("erro3", erro3);
			return new ModelAndView(mapa,"livro_cadastrar.html");
		}
		
		
		
		return null;
	}
}