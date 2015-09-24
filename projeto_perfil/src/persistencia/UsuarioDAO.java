package persistencia;

import java.io.*;
import java.util.*;
import modelo.Usuario;

public class UsuarioDAO implements DAO<Usuario> {

	public void save(Usuario obj) {
		try {
			File dir = new File("Usuarios");
			if( ! dir.exists()) dir.mkdir();
			
			File file = new File("Usuarios/" + obj.getLogin() + ".csv");
			if (file.exists()) return;
			else {
				if(file.exists()) return;
				FileWriter writer = new FileWriter(file);
				writer.write(obj.getNumero() + ";");
				writer.write(obj.getLogin() + ";");
				writer.write(obj.getSenha() + ";");
				writer.write(obj.getLocal() + ";");
				writer.write(obj.getIdade() + ";");
				writer.write(obj.getNascimento() + ";");
				writer.write(obj.getEscola() + ".");
				writer.flush();
				writer.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Usuario obj) {
		try {
			File arq = new File("Usuarios/" + obj.getLogin() +".csv");
			if (! arq.exists()) return;
			arq.delete();
			System.out.println("Excluído com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Usuario load(int chave) {
		try {
			File arq = new File("Usuarios/" + chave + ".csv");
			
			if (! arq.exists()) return null;
			
			Scanner scan = new Scanner(arq);
			String linha = scan.nextLine();
			scan.close();
			String[] colunas = linha.split(";");
			
			Usuario u = new Usuario();
			u.setNumero(chave);
			u.setLogin(colunas[1]);
			u.setSenha(colunas[2]);
			u.setLocal(colunas[3]);
			u.setIdade(Integer.parseInt(colunas[4]));
			u.setNascimento(colunas[5]);
			u.setEscola(colunas[6]);
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return null;
	}

	public void update(Usuario obj) {
		Usuario u = null;
		try{
			File arq =  new File("Usuarios/" + u.getLogin() + ".csv");
			if(arq.exists()) {
			FileWriter writer = new FileWriter(arq);
			writer.write(obj.getNumero() + ";");
			writer.write(obj.getLogin() + ";");
			writer.write(obj.getSenha() + ";");
			writer.write(obj.getLocal() + ";");
			writer.write(obj.getIdade() + ";");
			writer.write(obj.getNascimento() + ";");
			writer.write(obj.getEscola() + ".");
			writer.flush();
			writer.close();
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<Usuario> findAll() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			File dir = new File("Usuarios");
			File[] arqs = dir.listFiles();
			
			for(File arq : arqs) {
				Scanner scan = new Scanner(arq);
				String linha = scan.nextLine();
				scan.close();
				String[] colunas = linha.split(";");
				
				Usuario u = new Usuario();
				u.setLogin(colunas[1]);
				u.setSenha(colunas[2]);
				u.setLocal(colunas[3]);
				u.setIdade(Integer.parseInt(colunas[4]));
				u.setNascimento(colunas[5]);
				u.setEscola(colunas[6]);
				lista.add(u);
			}
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
}