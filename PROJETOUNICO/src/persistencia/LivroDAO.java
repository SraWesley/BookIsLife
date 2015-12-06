package persistencia;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import modelo.Livro;

public class LivroDAO implements DAO<Livro> {

	public void save(Livro obj) {
		try {
			File dir = new File("livros");
			if (!dir.exists()) dir.mkdir();
			File arq = new File("livros/" + obj.getISBN() + ".csv");
			if (arq.exists()) return;
			FileWriter writer = new FileWriter(arq);
			writer.write(obj.getISBN() + ";");
			writer.write(obj.getNome() + ";");
			writer.write(obj.getEscritor() + ";");
			writer.write(obj.getEditora() + ";");
			writer.write(obj.getSinopse() + ";");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		adicionaTitulos(obj.getNome());
	}

	public void adicionaTitulos(String titulo) {
		File dir = new File("titulos/");
		if (!dir.exists()) dir.mkdir();
		File file = new File("titulos/" + "titulos.csv");
		String nome = "";
		if (file.exists()) {
			try {
				Scanner scan = new Scanner(file);
				while (scan.hasNextLine()) {
					nome += scan.nextLine();
				}
				scan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				FileWriter writer = new FileWriter(file);
				writer.write(nome);
				writer.write(titulo);
				writer.write(";");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				FileWriter writer = new FileWriter(file);
				writer.write(titulo + ";");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(Livro obj) {
		try {
			File arq = new File("livros/" + obj.getISBN() + ".csv");
			//System.out.println(arq.exists());
			if (!arq.exists()) return;
			Files.delete(arq.toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Livro obj) {
		Livro l = null;
		String chave = obj.getISBN();
		try {
			File arq = new File("livros/" + chave + ".csv");
			if (arq.exists()) {
				FileWriter writer = new FileWriter(arq);
				writer.write(obj.getISBN() + ";");
				writer.write(obj.getNome() + ";");
				writer.write(obj.getEscritor() + ";");
				writer.write(obj.getEditora() + ";");
				writer.write(obj.getEditora() + ";");
				writer.flush();
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Livro> findAll() {
		ArrayList<Livro> lista = new ArrayList<Livro>();
		try {
			File dir = new File("livros");
			File[] arqs = dir.listFiles();
			for (File arq : arqs) {
				Scanner scan = new Scanner(arq);
				String linha = scan.nextLine();
				scan.close();
				String[] colunas = linha.split(";");
				Livro l = new Livro();
				l.setISBN(colunas[0]);
				l.setNome(colunas[1]);
				l.setEscritor(colunas[2]);
				l.setEditora(colunas[3]);
				l.setSinopse(colunas[4]);
				lista.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	public Livro load(Object chave) {
		Livro l = null;
		
		try {
			File arq = new File("livros/" + chave + ".csv");
			if (!arq.exists()) return null;
			l = new Livro();
			Scanner scan = new Scanner(arq);
			String linha = scan.nextLine();
			scan.close();
			String[] colunas = linha.split(";");
			l.setISBN(colunas[0]);
			l.setNome(colunas[1]);
			l.setEscritor(colunas[2]);
			l.setEditora(colunas[3]);
			l.setSinopse(colunas[4]);
			return l;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Livro> findAll(int numeroUsuario) {
		ArrayList<Livro> lista = new ArrayList<Livro>();
		Scanner scan = null;
		try {
			File file = new File("Usuarios/Matriculas/" + numeroUsuario + "/" + "meusLivros.csv");
			if (file.exists()) {
				scan = new Scanner(file);
				String linha = scan.nextLine();
				String[] colunas = linha.split(";");
				for (int i = 0; i < colunas.length; i++) {
					Livro livro = load(colunas[i]);
					lista.add(livro);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (scan != null) scan.close();
		return lista;
	}

	public boolean testandoSeLivroExiste(String nome) {
		File file = new File("titulos/titulos.csv");
		//System.out.println("Estou no else");
		if (file.exists()) {
			try {
				//System.out.println(nome);
				Scanner scan = new Scanner(file);
				String linha = scan.nextLine();
				String[] colunas = linha.split(";");
				scan.close();
				for(int i = 0; i < colunas.length; i++){
					if(nome.equals(colunas[i])){
						//System.out.println(colunas[i]);
						//System.out.println("� igual");
						return true;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}