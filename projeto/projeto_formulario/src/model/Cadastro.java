package model;

import java.util.ArrayList;

public class Cadastro {
		
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); 
	
	public void addUsuario (String login, String senha) {
		Usuario usuario = new Usuario(login, senha);
		if(usuarios.indexOf(usuario) == -1)	usuarios.add(usuario);
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