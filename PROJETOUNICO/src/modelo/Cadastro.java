package modelo;

import java.io.*;
import java.util.*;

/** Cria um ArrayList de Usuarios e apenas o add se todos os campos forem preenchidos. Se o usuário passar por todas as "regras" de 
 * cadastro e for o primeiro a ser cadastrado. Uma pasta deverá ser criada a fim de armazenar seus dados. Com isso será criado um
 * arquivo .csv para guardar as suas informações pessoais. Se não for o primeiro a ser cadastrado, apenas add.
 * Get do ArrayList dos Usuarios. ToString dos Usuarios.
 * E o indexOf onde pesquisa o usuario no Array através de seu login */
public class Cadastro {

	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); 
	
	public void addUsuario(int numero, String login, String senha, String local, int idade, String nascimento, String escola) {
		Usuario usuario = new Usuario();
		
		if (usuarios.indexOf(usuario) == -1)	{
			try {
				File dir = new File("Usuarios");
				if( ! dir.exists()) dir.mkdir();
		
				File file = new File("Usuarios/" + usuario.getNumero() + ".csv");
				if (file.exists()) return;
				else {
					if (file.exists()) return;
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
			} catch (Exception e) {
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

	public int indexOf(Usuario u) {
		return usuarios.indexOf(u);
	}
}