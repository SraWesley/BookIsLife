package controlador;

import spark.*;
import modelo.*;
import java.util.*;
import persistencia.*;

/** S�o criadas duas Strings que recebem o login e a senha enviados pelo usu�rio que est� na p�g. de login. Estas s�o testadas 
 * e procuradas no Dir de Usu�rios, 
 * se o login e a senha forem iguais aos cadastrados, ao usu�rio � atribu�do "usu�rio logado". 
 * E suas informa��es pessoais, assim como sua lista de livros s�o enviados para
 * o perfil do usu�rio. Se login e senha n�o forem as mesmas cadastradas para o usu�rio,
 *  � enviada uma mensagem de erro para o usu�rio e este � enviado novamente para a 
 * p�gina de login para efetu�-lo corretamente */
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
		
		String erro = "Voc� digitou digitou errado ou n�o est� cadastrado!";
		HashMap mapa1 = new HashMap();
		mapa1.put("erro", erro);
		return new ModelAndView(mapa1,"login.html");
	}
}