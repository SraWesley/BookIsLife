package modelo;

import java.io.File;
import java.util.ArrayList;
import persistencia.LivroDAO;

public class Usuario {
	
	private int numero;
	private String login;
	private String senha;
	private String local;
	private int idade;
	private String nascimento;
	private String escola;
	private ArrayList<Livro> meusLivros;
	
	public Usuario() {}

	public ArrayList<Livro> getMeusLivros() {
		//File file = new File("Usuarios/Matriculas/" + this.getNumero() + "/" + "meusLivros.csv");
		if (meusLivros == null) { 
			LivroDAO livros =  new LivroDAO();
			meusLivros = livros.findAll(numero);
		}
		
		return meusLivros;
	}

	public void setMeusLivros(Livro livro) {
		meusLivros.add(livro);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getLocal() {
		return local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getNascimento() {
		return nascimento;
	}
	
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	
	public String getEscola() {
		return escola;
	}
	
	public void setEscola(String escola) {
		this.escola = escola;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null) return false;
		} 
		else if (!login.equals(other.login))
			return false;
		return true;
	}

	public String toString() {
		return "Usuario [numero=" + numero + ", login=" + login + ", senha=" + senha + ", local=" + local + ", idade="
				+ idade + ", nascimento=" + nascimento + ", escola=" + escola + ", meusLivros=" + meusLivros + "]";
	}	
}