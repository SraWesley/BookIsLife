package controlador;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Livro;
import modelo.Resenha;
import modelo.Usuario;
import persistencia.LivroDAO;
import persistencia.ResenhaDAO;
import persistencia.UsuarioDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class MeuMuralControlador implements TemplateViewRoute {

	@Override
	public ModelAndView handle(Request req, Response resp) {
		ArrayList<Resenha> resenha_lista = new ArrayList<Resenha>();
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = req.session().attribute("usuario_logado");
		resenha_lista = dao.resenhasDoUsuario(usuario.getNumero());
		System.out.println(resenha_lista);
		HashMap mapa = new HashMap();
		mapa.put("resenha_lista", resenha_lista);
		return new ModelAndView(mapa, "meuMural.html");	
		
	}

}
