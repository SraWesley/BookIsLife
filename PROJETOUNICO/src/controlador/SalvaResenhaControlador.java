package controlador;

import spark.*;
import java.util.*;
import modelo.*;
import persistencia.ResenhaDAO;

public class SalvaResenhaControlador implements TemplateViewRoute {

	ResenhaDAO dao = new ResenhaDAO();

	public ModelAndView handle(Request req, Response resp) {
		Resenha resenha = new Resenha();
		Usuario usuario = req.session().attribute("usuario_logado");
		resenha.setTexto(req.queryMap("texto").value());
		System.out.println(usuario.getNumero());
		resenha.setNumero(usuario.getNumero());
		
		resenha.setISBN(req.params("ISBN"));
		
		boolean resenhaJaExiste = dao.resenhaExiste(resenha);
		if (!resenhaJaExiste) {
			dao.save(resenha);
		} else {
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