package controlador;

import spark.*;
import modelo.*;
import java.io.*;
import java.util.*;
import persistencia.*;

/** O usu�rio dever� estar na p�g. login. Estando nesta p�g. ele dever� preencher com as suas informa��es pessoais, uma vez
 * preenchidos e de acordo com as "regras" do programa, essas informa��es ser�o "setadas" e o usu�rio ser� salvo. 
 * Se essas informa��es n�o estiverem de acordo com as "regras" do programa ser�o enviadas mensagens de erro, como login (< 3) ou 
 * ainda se o login j� for existente.
 * Se alguma das mensagens de erro for exibida o usu�rio retornar� para a p�g. login. 
 * Se j� cadastrado ser� direcionado para o index*/
public class SalvaControladorUsuario implements TemplateViewRoute {

	private UsuarioDAO dao = new UsuarioDAO();

	public ModelAndView handle(Request req, Response resp) {		
		
		Usuario usuario = new Usuario();
		usuario.setLogin(req.queryMap("Login").value());
		usuario.setSenha(req.queryMap("Senha").value());
		usuario.setLocal(req.queryMap("Local").value());
		usuario.setIdade(req.queryMap("Idade").integerValue());
		usuario.setNascimento(req.queryMap("Nascimento").value());
		usuario.setEscola(req.queryMap("Escola").value());
		String erro = "";
		String erro2 = "";
		if (usuario.getLogin().length() < 3) {
			erro = "Login deve ter pelo menos 3 caracteres";
			HashMap mapa = new HashMap();
			mapa.put("erro", erro);
			return new ModelAndView(mapa,"usuario_cadastrar.html");
		} else {
			File file = new File("Usuarios/" + "login.csv");
			String login = usuario.getLogin();
			if (file.exists()) {
				try {
					Scanner scan = new Scanner(file);
					String linha = scan.nextLine();
					String[] colunas = linha.split(";");
					scan.close();
					for (int i = 0; i < colunas.length; i++) {
						if (login.equals(colunas[i])) {
							erro2 = "Login j� existente! Escolha outro!";
							break;
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			if (!erro2.equals("")) {
				HashMap mapa1 = new HashMap();
				mapa1.put("erro2", erro2);
				return new ModelAndView(mapa1,"usuario_cadastrar.html");
			}
		}
		
		dao.save(usuario);	
		resp.redirect("/");
		return null;
	}
}