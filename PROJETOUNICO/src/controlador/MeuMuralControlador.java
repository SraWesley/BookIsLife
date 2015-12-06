package controlador;

import spark.*;
import modelo.*;
import java.util.*;
import persistencia.*;

/** Ser� criado um Array de resenhas, onde o m�todo resenhasDoUsuario carregar� as resenhas daquele usu�rio que tiver o "atributo" usuario_logado, atrav�s do n�mero do 
 * usuario. As resenhas retornadas do m�todo preencher�o o Array e este ser� enviado para meuMural.html */
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