package controlador;

import spark.*;
import modelo.*;
import persistencia.*;

public class ExcluirResenhaControlador implements TemplateViewRoute{

	public ModelAndView handle(Request req, Response resp) {
		Usuario usuario = req.session().attribute("usuario_logado");
		String ISBN = req.params("ISBN");
		ResenhaDAO dao = new ResenhaDAO();
		Resenha resenha = dao.load(ISBN, usuario.getNumero());
		dao.delete(resenha, usuario.getNumero());
		resp.redirect("/perfil");
		return  null;
	}
}