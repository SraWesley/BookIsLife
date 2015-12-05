package controlador;

import java.util.*;
import modelo.Usuario;
import persistencia.UsuarioDAO;
import spark.*;

public class AdicionaEstanteControlador implements TemplateViewRoute {
	/** Controlador que envia o livro desejado para a estante do usúario logado.
	 * O ISBN vem do livro desejado, o usuário é o que está na sessão.
	 * Caso o usuário não esteja logado é enviado para a página de login.
	 * É criado uma variável booleana para teste, onde irá ser lido o arquivo dos livros do usuário 
	 * e em seguida escrever o novo livro. Enviando para o perfil do usuário, com o novo livro na 'estante'.
	 * Caso o livo já esteja na 'estante' a variável retornará false e será enviado um aviso que o livro já pertence a 'estante'.   */
	public ModelAndView handle(Request req, Response resp) {
		
		String ISBN = req.params("ISBN");

		Usuario usuario = req.session().attribute("usuario_logado");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuario == null){
			HashMap mapa = new HashMap();
			return new ModelAndView(mapa, "login.html");
		}
		boolean foiAdicionado;
		foiAdicionado = usuarioDAO.addMeusLivros(ISBN, usuario.getNumero());
		System.out.println(foiAdicionado);
		System.out.println(usuario.getMeusLivros());

		
		if (foiAdicionado) {
			HashMap mapa = new HashMap();
			mapa.put("usuario", usuario);
			mapa.put("meusLivros", usuario.getMeusLivros());
			return new ModelAndView(mapa, "perfil.html");
		} else {
			String erro = "Esse livro já está na sua estante";
			HashMap mapa = new HashMap();
			mapa.put("erro", erro);
			mapa.put("usuario", usuario);
			mapa.put("meusLivros", usuario.getMeusLivros());
			return new ModelAndView(mapa, "perfil.html");
		}
	}
}
