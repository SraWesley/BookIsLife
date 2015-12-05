package controlador;

import java.util.*;
import modelo.Usuario;
import persistencia.UsuarioDAO;
import spark.*;

public class AdicionaEstanteControlador implements TemplateViewRoute {
	/** Controlador que envia o livro desejado para a estante do us�ario logado.
	 * O ISBN vem do livro desejado, o usu�rio � o que est� na sess�o.
	 * Caso o usu�rio n�o esteja logado � enviado para a p�gina de login.
	 * � criado uma vari�vel booleana para teste, onde ir� ser lido o arquivo dos livros do usu�rio 
	 * e em seguida escrever o novo livro. Enviando para o perfil do usu�rio, com o novo livro na 'estante'.
	 * Caso o livo j� esteja na 'estante' a vari�vel retornar� false e ser� enviado um aviso que o livro j� pertence a 'estante'.   */
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
			String erro = "Esse livro j� est� na sua estante";
			HashMap mapa = new HashMap();
			mapa.put("erro", erro);
			mapa.put("usuario", usuario);
			mapa.put("meusLivros", usuario.getMeusLivros());
			return new ModelAndView(mapa, "perfil.html");
		}
	}
}
