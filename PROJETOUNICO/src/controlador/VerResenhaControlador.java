package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import modelo.Resenha;
import modelo.Usuario;
import spark.*;

public class VerResenhaControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		Usuario usuario = req.session().attribute("usuario_logado");
		int ISBN = Integer.parseInt(req.params("ISBN"));
		File  file = new File("Usuarios/Matriculas/" + usuario.getNumero() + "/" + "minhasResenhas/" + ISBN + ".csv");
		Resenha resenha = new Resenha();
		if(file.exists()){
			try {
				Scanner scan = new Scanner(file);
				String textoResenha = new String();
				String linha = scan.nextLine();
			
				resenha.setTexto(linha);
				scan.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(resenha.getTexto());
		HashMap mapa = new HashMap();
		mapa.put("resenha", resenha.getTexto());		
		return new ModelAndView(mapa, "ver_resenha.html");
	}
}