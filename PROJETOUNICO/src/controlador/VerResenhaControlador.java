package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import modelo.Resenha;
import modelo.Usuario;
import persistencia.ResenhaDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class VerResenhaControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		Usuario usuario = req.session().attribute("usuario_logado");
		String ISBN = req.params("ISBN");
		ResenhaDAO dao = new ResenhaDAO();
		ArrayList<Resenha> resenhas = dao.resenhasDoLivro(ISBN); 
		
		
		File  file = new File("Usuarios/Matriculas/" + usuario.getNumero() + "/" + "minhasResenhas/" + ISBN + ".csv");
		Resenha resenha = new Resenha();
		if(file.exists()){
			try {
				Scanner scan = new Scanner(file);
				String textoResenha = new String();
				String linha = scan.nextLine();
				while(scan.hasNextLine()){
					linha+= scan.nextLine();
				}
				resenha.setTexto(linha);
				scan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		System.out.println(resenha.getTexto());
		HashMap mapa = new HashMap();
		mapa.put("resenha", resenha.getTexto());		
		return new ModelAndView(mapa, "ver_resenha.html");
	}
}