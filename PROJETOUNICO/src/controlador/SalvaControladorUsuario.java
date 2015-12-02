package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
		
		if (usuario.getLogin().length() < 3) {
			String erro = "";
			try {
				erro = URLEncoder.encode("Login deve ter pelo menos 3 caracteres", "UTF-8");
			} 
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			resp.redirect("/?erro=" + erro);
		}else {
			File file = new File("Usuarios/" + "login.csv");
			String login = usuario.getLogin();
			String erro2 = "";
			if (file.exists()) {
				try {
					Scanner scan = new Scanner(file);
					String linha = scan.nextLine();
					String[] colunas = linha.split(";");
					scan.close();
					for(int i = 0; i < colunas.length; i++){
						if(login.equals(colunas[i])){
							erro2 = "Login já existente! Escolha outro!";
						}
					}
				} catch (FileNotFoundException e) {
				}
			}
			resp.redirect("/?erro=" + erro2);
		}
			dao.save(usuario);	
			resp.redirect("/");
			return null;
		}
}