package controlador;

import modelo.*;
import persistencia.ResenhaDAO;
import spark.*;

public class ExcluirResenhaControlador implements TemplateViewRoute{

	public ModelAndView handle(Request req, Response resp) {
		Usuario usuario = req.session().attribute("usuario_logado");
		String ISBN = req.params("ISBN");
		//System.out.println(ISBN);
		ResenhaDAO dao = new ResenhaDAO();
		Resenha resenha = dao.load(ISBN, usuario.getNumero());
		//System.out.println(resenha.getTexto());
		dao.delete(resenha, usuario.getNumero());
		resp.redirect("/perfil");
		return  null;
	}
}