package controlador;

import java.io.*;
import java.util.*;
import modelo.*;
import persistencia.ResenhaDAO;
import spark.*;

public class VerResenhaControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		Usuario usuario = req.session().attribute("usuario_logado");
		String ISBN = req.params("ISBN");
		ResenhaDAO dao = new ResenhaDAO();
		ArrayList<Resenha> resenhas = dao.resenhasDoLivro(ISBN); 
		
		File  file = new File("Usuarios/Matriculas/" + usuario.getNumero() + "/" + "MinhasResenhas/" + ISBN + ".csv");
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
		mapa.put("ISBN", ISBN);
		return new ModelAndView(mapa, "ver_resenha.html");
	}
}