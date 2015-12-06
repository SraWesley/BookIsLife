package controlador;

import spark.*;
import modelo.*;
import java.util.*;
import persistencia.*;

public class MeuMuralControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		ArrayList<Resenha> resenha_lista = new ArrayList<Resenha>();
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = req.session().attribute("usuario_logado");
		resenha_lista = dao.resenhasDoUsuario(usuario.getNumero());
		
		HashMap mapa = new HashMap();
		mapa.put("resenha_lista", resenha_lista);
		return new ModelAndView(mapa, "meuMural.html");		
	}
}