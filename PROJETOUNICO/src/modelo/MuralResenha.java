package modelo;

import java.util.*;

/** add Resenha para um ArrayList de resenhas, get do ArrayList de resenhas e toString das resenhas */
public class MuralResenha {

	private ArrayList<Resenha> resenhas = new  ArrayList<Resenha>();
	
	public void addResenha(String texto) {
		Resenha t = new Resenha(texto);
		resenhas.add(t);
	}
	
	public ArrayList<Resenha> getMensagens() {
		return resenhas;
	}
	
	public String toString() {
		return resenhas.toString();
	}
}