package controlador;

import java.util.*;
import modelo.Usuario;
import persistencia.UsuarioDAO;
import spark.*;

public class AdicionaEstanteControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {

		String ISBN = req.params("ISBN");

		Usuario usuario = req.session().attribute("usuario_logado");
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		boolean foiAdicionado;
		foiAdicionado = usuarioDAO.addMeusLivros(ISBN, usuario.getNumero());
		//System.out.println(foiAdicionado);
		//System.out.println(usuario.getMeusLivros());

		if (foiAdicionado) {
			HashMap mapa = new HashMap();
			mapa.put("usuario", usuario);
			mapa.put("meusLivros", usuario.getMeusLivros());
			return new ModelAndView(mapa, "perfil.html");
		} else {
			String erro = "Esse livro já está na sua estante";
			HashMap mapa = new HashMap();
			mapa.put("erro", erro);
			mapa.put("usuario", usuario);
			mapa.put("meusLivros", usuario.getMeusLivros());
			return new ModelAndView(mapa, "perfil.html");
		}
	}
}
///
/*
 * File file = new File("Usuarios/Matriculas/" + usuario.getNumero() + "/" +
 * "meusLivros.csv"); Scanner scan; try { scan = new Scanner(file); String linha
 * = scan.nextLine(); scan.close(); String[] colunas = linha.split(";"); for(int
 * i = 0; i < colunas.length; i++){ Livro livro = new Livro();
 * livro.setISBN(ISBN); int teste = livro.getISBN(); }} catch
 * (FileNotFoundException e) { e.printStackTrace(); }
 * 
 * //String isbnteste = .toString(); // if(isbnteste.equals(colunas[i])){ // }
 * 
 * //HashMap mapa = new HashMap(); //mapa.put("usuario", usuario); //return new
 * ModelAndView(mapa, "perfil.html"); /* for(int i = 0; i <
 * usuario.getMeusLivros().size(); i++){ Livro livroteste = new Livro();
 * livroteste = usuario.getMeusLivros().get(i); if(livro.equals(livroteste)){
 * HashMap mapa = new HashMap(); usuario.setMeusLivros(livro);
 * mapa.put("usuario", usuario); return new ModelAndView(mapa, "perfil.html"); }
 * }
 */