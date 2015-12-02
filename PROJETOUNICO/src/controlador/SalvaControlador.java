package controlador;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Scanner;

import modelo.Livro;
import persistencia.LivroDAO;
import spark.*;

public class SalvaControlador implements TemplateViewRoute {

	private LivroDAO dao = new LivroDAO();

	public ModelAndView handle(Request req, Response resp) {

		Livro livro = new Livro();
		livro.setISBN(req.queryMap("ISBN").integerValue());
		livro.setNome(req.queryMap("nome").value());
		livro.setEscritor(req.queryMap("escritor").value());
		livro.setEditora(req.queryMap("editora").value());
		String erro2="";
		if (livro.getNome().length() < 3) {
			String erro = "";
			erro = "Título deve ter pelo menos 3 caracteres";
			HashMap mapa = new HashMap();
			mapa.put("erro", erro);
			return new ModelAndView(mapa,"livro_cadastrar.html");
		}else {
			File file = new File("livros/" + "nomes.csv");
			String nome = livro.getNome();
			System.out.println("Estou no else");
			if (file.exists()) {
				try {System.out.println(nome);
					Scanner scan = new Scanner(file);
					String linha = scan.nextLine();
					String[] colunas = linha.split(";");
					scan.close();
					for(int i = 0; i < colunas.length; i++){
						if(nome.equals(colunas[i])){
							System.out.println(colunas[i]);
							System.out.println("é igual");
							erro2 = "Livro já existente!";
						}
					}
				} catch (FileNotFoundException e) {
				}
			HashMap mapa1 = new HashMap();
			mapa1.put("erro2", erro2);
			return new ModelAndView(mapa1,"livro_cadastrar.html");
			}
		}
			dao.save(livro);
			resp.redirect("/lista");
			return null;
		}
		
}