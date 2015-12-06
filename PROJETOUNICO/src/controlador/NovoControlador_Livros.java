package controlador;

import spark.*;
import java.util.*;

/** O usuário é direcionado para a pág. livro_cadastrar. Se os dados fornecidos para o cadastro do livro, não obedecerem as regras
 * do programa. Será enviada uma mensagem de erro. */
public class NovoControlador_Livros implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response resp) {
		HashMap mapa = new HashMap();
		if (req.queryParams("erro") != null) mapa.put("erro", req.queryParams("erro"));
		return new ModelAndView(mapa, "livro_cadastrar.html");
	}
}