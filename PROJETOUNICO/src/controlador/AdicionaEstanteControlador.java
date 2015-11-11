package controlador;

import java.util.ArrayList;
import java.util.HashMap;

import persistencia.UsuarioDAO;
import modelo.Livro;
import modelo.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class AdicionaEstanteControlador implements TemplateViewRoute {

	@Override
	public ModelAndView handle(Request req, Response resp) {
			Livro livro = new Livro();
			livro.setISBN(req.queryMap("ISBN").integerValue());
			livro.setNome(req.queryMap("nome").value());
			livro.setEscritor(req.queryMap("escritor").value());
			livro.setEditora(req.queryMap("editora").value());
			
			Usuario usuario = new Usuario();
			usuario.setLogin(req.queryMap("login").value());
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			for(int i = 0; i < usuario.getMeusLivros().size(); i++){
				Livro livroteste = new Livro();
				livroteste = usuario.getMeusLivros().get(i);
				if(livro.equals(livroteste)){
					HashMap mapa = new HashMap();
					usuario.setMeusLivros(livro);
					mapa.put("usuario", usuario);
					return new ModelAndView(mapa, "perfil.html");
				}
			}
			String erro = "Esse livro já está na sua estante";
			HashMap mapa = new HashMap();
			mapa.put("erro", erro);
			return new ModelAndView(mapa,"perfil.html");

		}
}
				
	
