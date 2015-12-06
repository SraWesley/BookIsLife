package modelo;

import java.sql.Date;

public class Livro {
	
	private String nome;
	private String editora;
	private String escritor;
	private String ISBN;
	private Date anopublicado;
	private String sinopse;
	
	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getEscritor() {
		return escritor;
	}

	public void setEscritor(String escritor) {
		this.escritor = escritor;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Date getAnopublicado() {
		return anopublicado;
	}

	public void setAnopublicado(java.util.Date date) {
		this.anopublicado = (Date) date;
	}

	public String toString() {
		return "" + nome + "";
	}
}