package controlador;

import spark.*;
import modelo.*;
import java.io.*;
import java.util.*;
import persistencia.*;

/** Se o usuário estiver logado, pega o ISBN do livro desejado e manda para o método pegandoTexto juntamente com o número do usuário
 *  que está solicitando essa função.
 * Cria-se uma variável do tipo Resenha e "seta" o texto da resenha nela. ISBN e texto são enviados para ver_resenha.html   */
public class VerResenhaControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		Usuario usuario = req.session().attribute("usuario_logado");
		String ISBN = req.params("ISBN");
		ResenhaDAO dao = new ResenhaDAO();
		Resenha resenha = new Resenha();
		String texto = dao.pegandoTexto(ISBN, usuario.getNumero());
		resenha.setTexto(texto);
		
		HashMap mapa = new HashMap();
		mapa.put("resenha", resenha.getTexto());
		mapa.put("ISBN", ISBN);
		return new ModelAndView(mapa, "ver_resenha.html");
	}
}