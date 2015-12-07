package controlador;

import spark.*;
import modelo.*;
import persistencia.*;

/** Se usuario tiver o "atributo" usuario_logado, o número do usuário juntamente com o ISBN do livro que se quer excluir a resenha,
 *  serão carregados através do load e 
 * a resenha será procurada nos arquivos do usuário com o número recebido e após será excluída pelo delete.
 *  O usuário será direcionado para o perfil.  */
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