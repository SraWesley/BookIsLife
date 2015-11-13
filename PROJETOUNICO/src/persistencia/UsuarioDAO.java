package persistencia;

import java.io.*;
import java.util.*;

import modelo.Livro;
import modelo.Usuario;

public class UsuarioDAO implements DAO<Usuario> {
	
	private static final String DIR = "Usuarios/Matriculas/";
	
	public void save(Usuario obj) {
		try {
			File dir = new File("Usuarios/");
			if( ! dir.exists()) dir.mkdir();
			File subdir = new File(DIR);
			if(! subdir.exists()) subdir.mkdir();
			
			int n = subdir.list().length + 1;
			/*System.out.println(n);
			int num = 0;
			do {
			    num++;
			}
			while (num < n);
			if(num == 0){
				obj.setNumero(num);
			}else {*/
			obj.setNumero(n);
		    dir = new File(DIR + obj.getNumero() + "/");
			if( ! dir.exists()) dir.mkdir();
			File file = new File(DIR + obj.getNumero() + "/" + "dados.csv");
			if (file.exists()) return;
			
			FileWriter writer = new FileWriter(file);
			writer.write(obj.getNumero() + ";");
			writer.write(obj.getLogin() + ";");
			writer.write(obj.getSenha() + ";");
			writer.write(obj.getLocal() + ";");
			writer.write(obj.getIdade() + ";");
			writer.write(obj.getNascimento() + ";");
			writer.write(obj.getEscola() + ";");
			writer.flush();
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		adicionaNomes(obj.getLogin());
		adicionaSenhas(obj.getSenha());
	}

	public boolean addMeusLivros(int ISBN, int chave) {
		File file = new File(DIR +chave+ "/" + "meusLivros.csv");
		String isbn = "";
		
		if (file.exists()) {
			try {
				Scanner scan1 = new Scanner(file);
				String linha =  scan1.nextLine();
				String[] colunas = linha.split(";");
				for(int i = 0; i < colunas.length; i++) {
					int isbnteste = Integer.parseInt(colunas[i]);
					if(isbnteste == ISBN) return false;
				}
			} 
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			try {
				Scanner scan = new Scanner(file);
				while(scan.hasNextLine()){
					isbn += scan.nextLine();
					System.out.println(isbn);
				}
				scan.close();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				FileWriter  writer = new FileWriter(file);
				writer.write(isbn);
				writer.write(ISBN);
				writer.write(";");
				writer.flush();
				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else {
			try {
				FileWriter  writer = new FileWriter(file);
				writer.write(ISBN + ";");
				writer.flush();
				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		try {
			Scanner scan3 =  new Scanner(file);
			String linha3 =  scan3.nextLine();
			String[] colunas3 = linha3.split(";");
			for(int i = 0; i < colunas3.length; i++) {
				LivroDAO daoLIVRO = new LivroDAO();
				Livro livro = new Livro();
				livro = daoLIVRO.load(Integer.parseInt(colunas3[i]));
				UsuarioDAO dao = new UsuarioDAO();
				Usuario usuario = new Usuario();
				usuario = dao.load(chave);
				usuario.setMeusLivros(livro);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void adicionaSenhas(String senha) {
		File file = new File("Usuarios/" + "senha.csv");
		String senha1 = "";

		if(file.exists()) {
			try {
				Scanner scan = new Scanner(file);
				while(scan.hasNextLine()){
					senha1 += scan.nextLine();
				}
				scan.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
			try {
				FileWriter  writer = new FileWriter(file);
				writer.write(senha1);
				writer.write(senha);
				writer.write(";");
				writer.flush();
				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
				else {
			try {
				FileWriter  writer = new FileWriter(file);
				writer.write(senha + ";");
				writer.flush();
				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void adicionaNomes(String login) {
		File file = new File("Usuarios/" + "login.csv");
		String nome= "";
		if(file.exists()) {
			try {
				Scanner scan = new Scanner(file);
				while(scan.hasNextLine()){
					nome += scan.nextLine();
				}
			scan.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			try {
				FileWriter  writer = new FileWriter(file);
				writer.write(nome);
				writer.write(login);
				writer.write(";");
				writer.flush();
				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else {
			try {
				FileWriter  writer = new FileWriter(file);
				writer.write(login + ";");
				writer.flush();
				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		
	public void delete(Usuario obj) {
		try {
			File arq = new File(DIR + obj.getNumero() +".csv");
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
			File arq = new File(DIR + chave+ "dados.csv");
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
		try {
			File arq =  new File(DIR + obj.getNumero() + ".csv");
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
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Usuario> findAll() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {
			File dir = new File("Usuarios/" + "login.csv");
			File dir2 = new File("Usuarios/" + "senha.csv");
			
			Scanner scan1 = new Scanner(dir);
			String linhas = scan1.nextLine();
			int aux = 0;
			String[] colunas = linhas.split(";");
			aux = colunas.length;
			Scanner scan2 = new Scanner(dir2);
			String linhas2 = scan2.nextLine();
			String[] colunas2 = linhas2.split(";");
			int aux2 = 1;
			for(int i = 0; i < aux; i++) {
				File dir3 =  new File(DIR + aux2  + "/" + "dados.csv");
				Scanner scan3 = new Scanner(dir3);
				String linhas3 = scan3.nextLine();
				String[] colunas3 = linhas3.split(";");
				Usuario u = new Usuario();
				u.setLogin(colunas[i]);
				u.setSenha(colunas2[i]);
				u.setNumero(Integer.parseInt(colunas3[0]));
				u.setLocal(colunas3[3]);
				u.setIdade(Integer.parseInt(colunas3[4]));
				u.setNascimento(colunas3[5]);
				u.setEscola(colunas3[6]);
				aux2++;
				lista.add(u);
			}
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
}