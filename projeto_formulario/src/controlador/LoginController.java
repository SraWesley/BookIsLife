package controlador;

import spark.*;
import model.Cadastro;
import model.Perfil;
import model.Usuario;

public class LoginController {

	private Cadastro cadastro = new Cadastro();

	public final Route novoCadastro = new NovoCadastro();
		
	public final TemplateViewRoute usandoCadastro = new UsandoCadastro();
	
	public final Route logando = new Logando();
		
	public final PerfilRota perfil = new PerfilRota();
	
	public final PerfilPost perfilp = new PerfilPost();
		
	private Perfil perfil2 = new Perfil();

	public class NovoCadastro implements Route {
		public Object handle(Request req, Response res) throws Exception {
				String login = req.queryParams("login");
				String senha = req.queryParams("senha");
				cadastro.addUsuario(login, senha);
				res.redirect("/home");
				return null;
			}
		}
		
		public class UsandoCadastro implements TemplateViewRoute {

			public ModelAndView handle(Request req, Response res) {
				return new ModelAndView(cadastro, "home.html");
			}	
		}
	
		public class Logando implements Route {
			public Object handle(Request req, Response res) throws Exception {
				String login = req.queryParams("login");
				String senha = req.queryParams("senha");
				Usuario m = new Usuario(login, senha);
				if(cadastro.indexOf(m) != -1)
					res.redirect("/perfil");
				else
					return "Login/Senha errada";
				return null;
			}
		}
		
		public class PerfilRota implements TemplateViewRoute {

			public ModelAndView handle(Request req, Response res) {
				return new ModelAndView(perfil2, "perfil.html");
			}
		}
		
		public class PerfilPost implements Route {

			public Object handle(Request req, Response res) {
				perfil2.setLocal(req.queryParams("local"));
				perfil2.setIdade(req.queryParams("idade"));
				perfil2.setNascimento(req.queryParams("nascimento"));
				res.redirect("/perfil");
				return null;
			}
		}
	}