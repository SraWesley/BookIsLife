package controlador;

import java.util.ArrayList;
import modelo.Usuario;
import persistencia.UsuarioDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class EntraControlador implements TemplateViewRoute{
		
	public ModelAndView handle(Request req, Response resp) {
		
		Usuario usuario = new Usuario();
		usuario.setLogin(req.queryMap("Login").value());
		usuario.setSenha(req.queryMap("Senha").value());
		
		UsuarioDAO dao = new UsuarioDAO();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = dao.findAll();
		
		for (int i = 0; i < usuarios.size(); i++) {
			String s;
			s = usuarios.get(i).getLogin();
			if (s == usuario.getLogin()) return new ModelAndView(null, "perfil.html");
		}
		return null;
	}
}