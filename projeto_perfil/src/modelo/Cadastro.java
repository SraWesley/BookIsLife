package modelo;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Cadastro {

	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); 
	
	public void addUsuario(int numero, String login, String senha, String local, int idade, String nascimento, String escola) {
		Usuario usuario = new Usuario();
		if(usuarios.indexOf(usuario) == -1)	{
			
			try {
				File dir = new File("Usuarios");
				if( ! dir.exists()) dir.mkdir();
				File file = new File("Usuarios/" + usuario.getNumero() + ".csv");
				if (file.exists()) return;
				else {
					if(file.exists()) return;
					FileWriter writer = new FileWriter(file);
					writer.write(usuario.getNumero() + ";");
					writer.write(usuario.getLogin() + ";");
					writer.write(usuario.getSenha() + ";");
					writer.write(usuario.getLocal() + ";");
					writer.write(usuario.getIdade() + ";");
					writer.write(usuario.getNascimento() + ";");
					writer.write(usuario.getEscola() + ".");
					writer.flush();
					writer.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			usuarios.add(usuario);
		}
	}
		
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
		
	public String toString() {
		return usuarios.toString();
	}

	public int indexOf(Usuario m) {
		return usuarios.indexOf(m);
	}
}