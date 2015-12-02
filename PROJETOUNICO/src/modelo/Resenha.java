package modelo;

public class Resenha {

	private String texto;
	private String ISBN;
	private int numero;
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public Resenha(){}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Resenha(String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public String toString() {
		return texto;
	}
}