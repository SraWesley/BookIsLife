package projeto;

import java.sql.Date;

public class Livro {
	private String nome;
	private String editora;
	private String escritor;
	private int ISBN;
	private Date anopublicado;
	private int paginas;

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

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public Date getAnopublicado() {
		return anopublicado;
	}

	public void setAnopublicado(java.util.Date date) {
		this.anopublicado = (Date) date;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}


	@Override
	public String toString() {
		return "Livro [nome=" + nome + ", editora=" + editora + ", escritor="
				+ escritor + ", ISBN=" + ISBN + ", anopublicado="
				+ anopublicado + ", paginas=" + paginas + "]";
	}
	
	
	
}
