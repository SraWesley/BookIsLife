package controlador;

import java.util.*;
import modelo.Usuario;
import persistencia.UsuarioDAO;
import spark.*;

public class EntraControlador implements TemplateViewRoute{
		
	public ModelAndView handle(Request req, Response resp) {
		HashMap mapa = new HashMap();
		if (req.queryParams("erro") != null) { 
			mapa.put("erro", req.queryParams("erro"));
		}
		
		String username = req.queryMap("username").value();
		String psw = req.queryMap("psw").value();
		
		UsuarioDAO dao = new UsuarioDAO();
		ArrayList<Usuario> usuarios = dao.findAll();
	
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);
	
			if (username.equals(usuario.getLogin()) && psw.equals(usuario.getSenha())){
				req.session().attribute("usuario_logado", usuario);
				HashMap mapa2 = new HashMap();
				mapa2.put("usuario", usuario);
				System.out.println("estamos aquii");
				mapa2.put("meusLivros", usuario.getMeusLivros());
				return new ModelAndView(mapa2, "perfil.html");
			}
		}
		
		String erro = "Você digitou digitou errado ou não está cadastrado!";
		HashMap mapa1 = new HashMap();
		mapa1.put("erro", erro);
		return new ModelAndView(mapa1,"login.html");
	}
}