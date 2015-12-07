package controlador;

import spark.*;
import modelo.*;
import java.util.*;
import persistencia.*;

/** São criadas duas Strings que recebem o login e a senha enviados pelo usuário que está na pág. de login. Estas são testadas 
 * e procuradas no Dir de Usuários, 
 * se o login e a senha forem iguais aos cadastrados, ao usuário é atribuído "usuário logado". 
 * E suas informações pessoais, assim como sua lista de livros são enviados para
 * o perfil do usuário. Se login e senha não forem as mesmas cadastradas para o usuário,
 *  é enviada uma mensagem de erro para o usuário e este é enviado novamente para a 
 * página de login para efetuá-lo corretamente */
public class EntraControlador implements TemplateViewRoute {
		
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