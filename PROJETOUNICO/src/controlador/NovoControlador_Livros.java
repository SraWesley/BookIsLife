package controlador;

import spark.*;
import java.util.*;

/** O usu�rio � direcionado para a p�g. livro_cadastrar. Se os dados fornecidos para o cadastro do livro, n�o obedecerem as regras
 * do programa. Ser� enviada uma mensagem de erro. */
public class NovoControlador_Livros implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		HashMap mapa = new HashMap();
		if (req.queryParams("erro") != null) mapa.put("erro", req.queryParams("erro"));
		return new ModelAndView(mapa, "livro_cadastrar.html");
	}
}