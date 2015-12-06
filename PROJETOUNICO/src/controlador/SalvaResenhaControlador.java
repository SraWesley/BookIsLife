package controlador;

import spark.*;
import modelo.*;
import java.util.*;
import persistencia.ResenhaDAO;

/** Testa se o usuário está logado, se sim "seta" a resenha, o número do usuário e o ISBN do livro. Cria uma variável booleana que
 * testa se a resenha já existe, se sim envia uma mensagem de erro, se não salva e direciona para ver_resenha com o número do ISBN */
public class SalvaResenhaControlador implements TemplateViewRoute {

	ResenhaDAO dao = new ResenhaDAO();

	public ModelAndView handle(Request req, Response resp) {
		Resenha resenha = new Resenha();
		Usuario usuario = req.session().attribute("usuario_logado");
		resenha.setTexto(req.queryMap("texto").value());
		resenha.setNumero(usuario.getNumero());
		resenha.setISBN(req.params("ISBN"));
		
		boolean resenhaJaExiste = dao.resenhaExiste(resenha);
		if (!resenhaJaExiste) dao.save(resenha);
		else {
			String erro2 = "Você já tem resenha desse livro!";
			HashMap mapa2 = new HashMap();
			mapa2.put("erro2", erro2);
			return new ModelAndView(mapa2, "adiciona_resenha.html");
		}
		
		req.session().attribute("resenha_logada", resenha);
		resp.redirect("/ver_resenha/" + resenha.getISBN());
		return null;
	}
}