package persistencia;

import modelo.*;
import java.io.*;
import java.util.*;
import java.nio.file.Files;

public class ResenhaDAO implements DAO<Resenha> {

	private static final String DIR = "Usuarios/Matriculas/";
	Usuario usuario = new Usuario();
	
	public void save(Resenha r) {
		File dir = new File(DIR + "/" + r.getNumero() + "/" + "MinhasResenhas");
		if (!dir.exists()) dir.mkdir();
		String resenhasExistente = "";
		File file = new File(DIR + "/" + r.getNumero() + "/" + "MinhasResenhas" + "/" + r.getISBN() + ".csv");
		Scanner scan = null;
		if (file.exists()) {
			try {
				scan = new Scanner(file);
				if (scan.hasNextLine()) resenhasExistente += scan.nextLine();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				scan.close();
			}
			
			try {
				FileWriter writer = new FileWriter(file);
				if (!resenhasExistente.isEmpty()) writer.write(resenhasExistente);
				writer.write(r.getTexto());
				writer.write(";");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				FileWriter writer = new FileWriter(file);
				writer.write(r.getTexto());
				writer.write(";");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/** O seguinte método auxilia para verificar se um determinado usuário já possui ou não uma resenha em determinado livro,
	 * recebendo como parêmetro a resenha, pois essa possui os dados de ISBN do livro ao qual está vinculada e chave do usuário
	 * autor. Logo o arquivo .csv da resenha em questão é lido caso exista e verificado se o texto é igual ao da resenha que o usuário
	 * deseja adicionar. Retornando valor true caso a resenha já exista e false caso não. 
	 * */
	public boolean resenhaExiste(Resenha resenha) {
		File dir = new File(DIR + "/" + resenha.getNumero() + "/" + "MinhasResenhas");
		if (!dir.exists()) dir.mkdir();
		File file = new File(DIR + "/" + resenha.getNumero() + "/" + "MinhasResenhas" + "/" + resenha.getISBN() + ".csv");
		Scanner scan = null;
		if (file.exists()) {
			try {
				scan = new Scanner(file);
				if (scan.hasNextLine()) {
					String linha = scan.nextLine();
					String[] colunas = linha.split(";");
					for (int i = 0; i < colunas.length; i++) {
						String resenhaTeste = colunas[i];
						if (resenhaTeste.equals(resenha.getTexto())) return true;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				scan.close();
			}
		}
		
		return false;
	}

	public void delete(Resenha obj, int numeroUsuario) {
		try {
			File arq = new File("Usuarios/Matriculas/" + numeroUsuario + "/" + "MinhasResenhas/" + obj.getISBN() + ".csv");
			if (!arq.exists()) return;
			Files.delete(arq.toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Resenha load(String isbn, int numeroUsuario) {
		Resenha r = new Resenha();
		Scanner scan = null;
		File arq = new File("Usuarios/Matriculas/" + numeroUsuario + "/" + "MinhasResenhas/" + isbn + ".csv");
		r = new Resenha();
		if (!arq.exists()) return null;
		try {
			scan = new Scanner(arq);
			String linha = "";
			while (scan.hasNextLine()) {
				linha += scan.nextLine();
			}
			r.setTexto(linha);
			r.setNumero(numeroUsuario);
			r.setISBN(isbn);
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

	public void update(Resenha obj) {
		String isbn = obj.getISBN();
		try {
			File arq = new File("Usuarios/Matriculas/" + usuario.getNumero() + "/" + "MinhasResenhas/" + isbn + ".csv");
			if (arq.exists()) {
				FileWriter writer = new FileWriter(arq);
				writer.write(obj.getTexto() + ";");
				writer.flush();
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Resenha> findAll() {
		ArrayList<Resenha> lista = new ArrayList<Resenha>();
		Resenha resenha = new Resenha();
		String isbn = resenha.getISBN();
		Scanner scan = null;
		try {
			File file = new File("Usuarios/Matriculas/" + usuario.getNumero() + "/" + "MinhasResenhas/" + isbn + ".csv");
			if (file.exists()) {
				scan = new Scanner(file);
				String linha = scan.nextLine();
				String[] colunas = linha.split(";");
				for (int i = 0; i < colunas.length; i++) {
					Resenha r = load(Integer.parseInt(colunas[i]));
					lista.add(r);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (scan != null) scan.close();
		return lista;
	}
	/** O método a seguir auxilia em VerResenhasLivrosControlador, recebendo o ISBN do livro que deseja-se ver todas as resenhas.
	 *  Então, é criado um Array com todos os usuários cadastraos, após percorrer todas as listas de resenha e cada um, verificando 
	 *  se trata-se de uma resenha do livro em questão, quando encontrado o arquivo é lido, e salvo em uma nova resenha, esta é 
	 *  salva em um ArrayList de resenhas que será retornado no fim do método.
	 *  */
	public ArrayList<Resenha> resenhasDoLivro(String iSBN) {
		ArrayList<Resenha> listaResenha = new ArrayList<Resenha>();
		File dirUsuarios = new File("Usuarios/Matriculas/");
		File[] usuarios = dirUsuarios.listFiles();
		for (int i = 0; i < usuarios.length; i++) {
			File resenha = new File(usuarios[i].getPath() + "/" + "MinhasResenhas/" + iSBN + ".csv");
			if (resenha.exists()) {
				String textoDaResenha = "";
				Resenha r = new Resenha();
				Scanner scan;
				try {
					scan = new Scanner(resenha);
					while (scan.hasNextLine()) {
						String linha = scan.nextLine();
						textoDaResenha += linha;
						r.setTexto(textoDaResenha);
						listaResenha.add(r);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
		return listaResenha;
	}

	public void delete(Resenha obj) { }

	public Resenha load(Object chave) {
		return null;
	}
	
	/** O seguinte método auxilia em VerResenhaControlador, ele recebe o ISBN do livro e o número do usuário que está solicitando a
	 * função, assim pode ler o arquivo .csv da resenha em questão, cado a resenha exista ele une o texto do arquivo em apenas uma 
	 * String retornando-a. 
	 * */
	public String pegandoTexto(String ISBN, int numero) {
		File  file = new File("Usuarios/Matriculas/" + numero + "/" + "MinhasResenhas/" + ISBN + ".csv");
		String linha = "";
		if (file.exists()) {
			try {
				Scanner scan = new Scanner(file);
				linha = scan.nextLine();
				while(scan.hasNextLine()){
					linha+= scan.nextLine();
				}
				scan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return linha;
	}
}