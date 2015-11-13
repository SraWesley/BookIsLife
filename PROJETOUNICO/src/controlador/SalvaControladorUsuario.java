package controlador;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import modelo.Usuario;
import persistencia.UsuarioDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class SalvaControladorUsuario implements TemplateViewRoute{

	private UsuarioDAO dao = new UsuarioDAO();

	public ModelAndView handle(Request req, Response resp) {		
		
		Usuario usuario = new Usuario();
		usuario.setLogin(req.queryMap("Login").value());
		usuario.setSenha(req.queryMap("Senha").value());
		usuario.setLocal(req.queryMap("Local").value());
		usuario.setIdade(req.queryMap("Idade").integerValue());
		usuario.setNascimento(req.queryMap("Nascimento").value());
		usuario.setEscola(req.queryMap("Escola").value());
		
		
		if (usuario.getLogin().length() < 3) {
			String erro = "";
			
			try {
				erro = URLEncoder.encode("Login deve ter pelo menos 3 caracteres", "UTF-8");
			} 
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			resp.redirect("/novo?erro=" + erro);
		}
		else {
			dao.save(usuario);	
			resp.redirect("/");
		}
		
		return null;
	}
}