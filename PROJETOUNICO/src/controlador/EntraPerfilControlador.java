package controlador;

import spark.*;
import modelo.*;
import java.util.*;

/** O usu�rio que possuir o "atributo" usuario_logado, ser� direcionado para o seu perfil juntamente com suas informa��es
 *  e sua lista de livros */
public class EntraPerfilControlador implements TemplateViewRoute{

	public ModelAndView handle(Request req, Response resp) {
		Usuario u = req.session().attribute("usuario_logado");
		HashMap mapa = new HashMap();
		mapa.put("usuario", u);
		mapa.put("meusLivros", u.getMeusLivros());
		return new ModelAndView(mapa, "perfil.html");
	}
}