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
		ResenhaDAO dao = new ResenhaDAO();
		Resenha resenha = new Resenha();
		resenha = dao.load(ISBN);
		System.out.println(resenha.getTexto());
		dao.delete(resenha);
		HashMap mapa = new HashMap();
		return new ModelAndView(mapa, "perfil.html");
	}

}
