package controlador;

import java.util.ArrayList;
import java.util.HashMap;
import modelo.Usuario;
import persistencia.UsuarioDAO;
import spark.*;

public class EntraControlador implements TemplateViewRoute{
		
	public ModelAndView handle(Request req, Response resp) {
		HashMap mapa = new HashMap();
		if (req.queryParams("erro") != null) { 
			mapa.put("erro", req.queryParams("erro"));
		}
		
		Usuario usuario = new Usuario();
		usuario.setLogin(req.queryMap("username").value());
		usuario.setSenha(req.queryMap("psw").value());
		
		UsuarioDAO dao = new UsuarioDAO();
		ArrayList<Usuario> usuarios = dao.findAll();
	
		for (int i = 0; i < usuarios.size(); i++) {
			String s, x;
			
			s = usuarios.get(i).getLogin();
			x = usuarios.get(i).getSenha();
	
			if (s.equals(usuario.getLogin()) && x.equals(usuario.getSenha())){
				req.session().attribute("usuario_logado", usuarios.get(i));
				HashMap mapa2 = new HashMap();
				mapa2.put("usuario", usuarios.get(i));
				
				return new ModelAndView(mapa2, "perfil.html");
			}
		}
		
		String erro = "Você digitou digitou errado ou não está cadastrado!";
		HashMap mapa1 = new HashMap();
		mapa1.put("erro", erro);
		return new ModelAndView(mapa1,"login.html");
	}
}