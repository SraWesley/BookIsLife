package modelo;

import java.util.ArrayList;

public class MuralResenha {

	private ArrayList<Resenha> resenhas = new  ArrayList<Resenha>();
	
	public void addResenha(String texto) {
		Resenha m = new Resenha(texto);
		resenhas.add(m);
	}
	
	public ArrayList<Resenha> getMensagens() {
		return resenhas;
	}
	
	public String toString() {
		return resenhas.toString();
	}
}