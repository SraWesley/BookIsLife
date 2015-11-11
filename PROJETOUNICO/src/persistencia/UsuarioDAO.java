package persistencia;

import java.io.*;
import java.util.*;

import modelo.Livro;
import modelo.Usuario;

public class UsuarioDAO implements DAO<Usuario> {
	
	public void save(Usuario obj) {
				      
		try {
			File dir = new File("Usuarios/");
			if( ! dir.exists()) dir.mkdir();
			
			int n = dir.list().length;
			int num = 0;
			do {
			    num++;
			}
			while (num < n);
			      obj.setNumero(num + 1);
		    dir = new File("Usuarios/" + obj.getNumero() + "/");
			if( ! dir.exists()) dir.mkdir();
			File file = new File("Usuarios/" + obj.getNumero() + "/" + "dados.csv");
			if (file.exists()) {
				System.out.println("OLA");return;
			}
			
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
			
			
		//	File file2 =  new File("Usuarios/" + obj.getNumero() + "/" + "meuslivros.csv");
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		adicionaNomes(obj.getLogin());
		adicionaSenhas(obj.getSenha());
	}

	public void adicionaSenhas(String senha){
		File file = new File("Usuarios/" + "senha.csv");
		String senha1 = "";
		if(file.exists()){
			//System.out.println("tA AQUI 1");
				try {
				Scanner scan = new Scanner(file);
				while(scan.hasNextLine()){
				senha1 += scan.nextLine();
			}
			scan.close();
		}
		 catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			try {
				FileWriter  writer = new FileWriter(file);
				writer.write(senha1);
				writer.write(senha);
				writer.write(";");
				writer.flush();
				writer.close();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				//System.out.println("TA AQUI 2");
				FileWriter  writer = new FileWriter(file);
				writer.write(senha + ";");
				writer.flush();
				writer.close();
			
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void adicionaNomes(String login){
		File file = new File("Usuarios/" + "login.csv");
		String nome= "";
		if(file.exists()){
			System.out.println("tA AQUI 1");
				try {
				Scanner scan = new Scanner(file);
				while(scan.hasNextLine()){
				nome += scan.nextLine();
			}
			scan.close();
		}
		 catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			try {
				FileWriter  writer = new FileWriter(file);
				writer.write(nome);
				writer.write(login);
				writer.write(";");
				writer.flush();
				writer.close();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				System.out.println("TA AQUI 2");
				FileWriter  writer = new FileWriter(file);
				writer.write(login + ";");
				writer.flush();
				writer.close();
			
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
		
	
	public void delete(Usuario obj) {
		try {
			File arq = new File("Usuarios/" + obj.getNumero() +".csv");
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
			File arq =  new File("Usuarios/" + obj.getNumero() + ".csv");
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

	@Override
	
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
			System.out.println(aux);
			Scanner scan2 = new Scanner(dir2);
			String linhas2 = scan1.nextLine();
			String[] colunas2 = linhas2.split(";");
			for(int i = 0; i < aux; i++){
				Usuario u = new Usuario();
				u.setLogin(colunas[i]);
				u.setSenha(colunas2[i]);
				lista.add(u);
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(lista.toString());
		return lista;
	}
}