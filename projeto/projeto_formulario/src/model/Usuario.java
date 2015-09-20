package model;

public class Usuario {

	String login;
	String senha;
		
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public Usuario getUsuario(){
		return this;
	}
		
	public String toString() {
		return login;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null) return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null) return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}	
}