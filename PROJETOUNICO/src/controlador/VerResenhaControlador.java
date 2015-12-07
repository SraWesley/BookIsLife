package controlador;

import spark.*;
import modelo.*;
import java.io.*;
import java.util.*;
import persistencia.*;

/** Se o usu�rio estiver logado, pega o ISBN do livro desejado e manda para o m�todo pegandoTexto juntamente com o n�mero do usu�rio
 *  que est� solicitando essa fun��o.
 * Cria-se uma vari�vel do tipo Resenha e "seta" o texto da resenha nela. ISBN e texto s�o enviados para ver_resenha.html   */
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