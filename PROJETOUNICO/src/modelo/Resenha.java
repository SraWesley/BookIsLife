package modelo;

/** Get e set de Resenha, ISBN do livro para add resenha e número do usuário que quer add resenha */
public class Resenha {

	private String texto;
	private String ISBN;
	private int numeroUsuario;
	
	public Resenha(){}
	
	public Resenha(String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getNumero() {
		return numeroUsuario;
	}

	public void setNumero(int numero) {
		this.numeroUsuario = numero;
	}
	
	public String toString() {
		return texto;
	}
}