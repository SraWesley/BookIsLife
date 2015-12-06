package persistencia;

import modelo.*;
import java.io.*;
import java.util.*;
import java.nio.file.Files;

public class LivroDAO implements DAO<Livro> {

	/** Se livro o que estiver sendo cadastrado passar pelas "regras" de cadastro e o diretório que armazena os livros ainda não 
	 * existir este deve ser criado. Após será criado um arquivo .csv que "setará" as informações do livro. Quando salvo add em
	 * outro arquivo .csv apenas o título da obra */
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
		} else {
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
	
	/** Se o arquivo que deseja ser deletado existir no diretorio de livros o arquivo .csv será excluído */
	public void delete(Livro obj) {
		try {
			File arq = new File("livros/" + obj.getISBN() + ".csv");
			if (!arq.exists()) return;
			Files.delete(arq.toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Será informada uma chava (ISBN) e através dessa chave, se o arquivo existir as informações salvas do livro serão enviadas
	 * para o usuario */
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

	/** Cria um ArrayList de livros, onde o método "roda" até a lista de arquivos terminar. Toda vez que este "rodar" ele atribui
	 * as informações de cada um dos arquuivos a um new Livro e os add no Array. Retornando o Array de livros */
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

	/** Se o ISBN recebido existir na lista de livros, o método "seta" as informações do livro e retorna para o usuário. */
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

	/** Cria um ArrayList de livros. Através do número do Usuário que é recebido é "criado" um diretório, se este existir serão
	 * scanneados os arquivos, enquanto tiver uma "nova linha", Livro carrega as proximas linhas e os add no Array. Se não tiver 
	 * mais arquivos Scanner é fechado e a lista o Array é retornado. */
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
	
	/** O método recebe um nome se este nome existir no diretório indicado retornará true, se não retornará false */
	public boolean testandoSeLivroExiste(String nome) {
		File file = new File("titulos/titulos.csv");
		if (file.exists()) {
			try {
				Scanner scan = new Scanner(file);
				String linha = scan.nextLine();
				String[] colunas = linha.split(";");
				scan.close();
				for (int i = 0; i < colunas.length; i++) {
					if (nome.equals(colunas[i])) return true;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}