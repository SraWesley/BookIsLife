package controlador;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import modelo.Resenha;
import modelo.Usuario;
import persistencia.ResenhaDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class SalvaResenhaControlador implements TemplateViewRoute {

	ResenhaDAO dao = new ResenhaDAO();
	
	public ModelAndView handle(Request req, Response resp) {
		Resenha resenha = new Resenha();
		Usuario usuario = req.session().attribute("usuario_logado");
		resenha.setTexto(req.queryMap("texto").value());
		resenha.setNumero(usuario.getNumero());
		System.out.println(usuario.getNumero());
		resenha.setISBN(Integer.parseInt(req.params("ISBN")));
		if ((resenha.getTexto().length()) < 10) {
			String erro = "";
			
			try {
				erro = URLEncoder.encode("Resenha deve ter pelo menos 140 caracteres", "UTF-8");
			}
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			HashMap mapa = new HashMap();
			mapa.put("erro", erro);
			return new ModelAndView(mapa,"adiciona_resenha.html");
		}
		else {
			boolean resenhaJaExiste = dao.resenhaExiste(resenha);
			if(!resenhaJaExiste){
					dao.save(resenha);
			}else{
				String erro2 ="Você já tem resenha desse livro!";
				HashMap mapa2 = new HashMap();
				mapa2.put("erro2", erro2);
				return new ModelAndView(mapa2,"adiciona_resenha.html");
			}
			req.session().attribute("resenha_logada", resenha);
			resp.redirect("/ver_resenha/" + resenha.getISBN());
		}
		return null;
	}
}