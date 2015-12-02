package controlador;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Scanner;
import modelo.Usuario;
import persistencia.UsuarioDAO;
import spark.*;

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
		String erro = "";
		String erro2 = "";
		if (usuario.getLogin().length() < 3) {
			
			erro = "Login deve ter pelo menos 3 caracteres";
			HashMap mapa = new HashMap();
			mapa.put("erro", erro);
			return new ModelAndView(mapa,"usuario_cadastrar.html");
		}else {
			File file = new File("Usuarios/" + "login.csv");
			String login = usuario.getLogin();
			System.out.println("Estou no else");
			if (file.exists()) {
				try {System.out.println(login);
					Scanner scan = new Scanner(file);
					String linha = scan.nextLine();
					String[] colunas = linha.split(";");
					scan.close();
					for(int i = 0; i < colunas.length; i++){
						if(login.equals(colunas[i])){
							
							System.out.println(colunas[i]);
							System.out.println("é igual");
							erro2 = "Login já existente! Escolha outro!";
						}
					}
				} catch (FileNotFoundException e) {
				}
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