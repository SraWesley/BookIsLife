package controlador;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Usuario;
import persistencia.UsuarioDAO;
import spark.*;

public class EntraControlador implements TemplateViewRoute{
		
	public ModelAndView handle(Request req, Response resp) {
		
		Usuario usuario = new Usuario();
		usuario.setLogin(req.queryMap("username").value());
		usuario.setSenha(req.queryMap("psw").value());
		
		System.out.println(usuario.getLogin());
		System.out.println(usuario.getSenha());

		UsuarioDAO dao = new UsuarioDAO();
		ArrayList<Usuario> usuarios = dao.findAll();
		System.out.println(usuarios.toString());
		
		for (int i = 0; i < usuarios.size(); i++) {
			String s;
			s = usuarios.get(i).getLogin();
			if (s.equals(usuario.getLogin())){
				HashMap mapa = new HashMap();
				mapa.put("usuario", usuarios.get(i));
				return new ModelAndView(mapa, "perfil.html");
			}
		}
		String erro = "Você digitou digitou errado ou não está cadastrado!";
		HashMap mapa = new HashMap();
		mapa.put("erro", erro);
		return new ModelAndView(mapa,"login.html");
	}
}