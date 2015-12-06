package controlador;

import spark.*;
import modelo.*;
import java.io.*;
import java.util.*;
import persistencia.*;

public class VerResenhaControlador implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		Usuario usuario = req.session().attribute("usuario_logado");
		String ISBN = req.params("ISBN");
		ResenhaDAO dao = new ResenhaDAO();
		File  file = new File("Usuarios/Matriculas/" + usuario.getNumero() + "/" + "MinhasResenhas/" + ISBN + ".csv");
		Resenha resenha = new Resenha();
		String texto = dao.pegandoTexto(ISBN, usuario.getNumero());
		resenha.setTexto(texto);
		
		HashMap mapa = new HashMap();
		mapa.put("resenha", resenha.getTexto());
		mapa.put("ISBN", ISBN);
		return new ModelAndView(mapa, "ver_resenha.html");
	}
}