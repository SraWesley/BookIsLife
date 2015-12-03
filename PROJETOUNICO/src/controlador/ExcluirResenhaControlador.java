package controlador;

import java.util.HashMap;

import modelo.Resenha;
import modelo.Usuario;
import persistencia.ResenhaDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class ExcluirResenhaControlador implements TemplateViewRoute{

	@Override
	public ModelAndView handle(Request req, Response resp) {
		Usuario usuario = req.session().attribute("usuario_logado");
		String ISBN = req.params("ISBN");
		System.out.println(ISBN);
		ResenhaDAO dao = new ResenhaDAO();
		Resenha resenha = dao.load(ISBN, usuario.getNumero());
		System.out.println(resenha.getTexto());
		dao.delete(resenha, usuario.getNumero());
		resp.redirect("/perfil");
		return  null;
	}

}
