package controlador;

import spark.*;
import modelo.*;
import persistencia.*;

/** Se usuario tiver o "atributo" usuario_logado, o n�mero do usu�rio juntamente com o ISBN do livro que se quer excluir a resenha,
 *  ser�o carregados atrav�s do load e 
 * a resenha ser� procurada nos arquivos do usu�rio com o n�mero recebido e ap�s ser� exclu�da pelo delete.
 *  O usu�rio ser� direcionado para o perfil.  */
public class ExcluirResenhaControlador implements TemplateViewRoute{

	public ModelAndView handle(Request req, Response resp) {
		Usuario usuario = req.session().attribute("usuario_logado");
		String ISBN = req.params("ISBN");
		ResenhaDAO dao = new ResenhaDAO();
		Resenha resenha = dao.load(ISBN, usuario.getNumero());
		dao.delete(resenha, usuario.getNumero());
		resp.redirect("/perfil");
		return  null;
	}
}