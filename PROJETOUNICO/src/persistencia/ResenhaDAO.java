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
	/** O seguinte m�todo auxilia para verificar se um determinado usu�rio j� possui ou n�o uma resenha em determinado livro,
	 * recebendo como par�metro a resenha, pois essa possui os dados de ISBN do livro ao qual est� vinculada e chave do usu�rio
	 * autor. Logo o arquivo .csv da resenha em quest�o � lido caso exista e verificado se o texto � igual ao da resenha que o usu�rio
	 * deseja adicionar. Retornando valor true caso a resenha j� exista e false caso n�o. 
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
	/** O m�todo a seguir auxilia em VerResenhasLivrosControlador, recebendo o ISBN do livro que deseja-se ver todas as resenhas.
	 *  Ent�o, � criado um Array com todos os usu�rios cadastraos, ap�s percorrer todas as listas de resenha e cada um, verificando 
	 *  se trata-se de uma resenha do livro em quest�o, quando encontrado o arquivo � lido, e salvo em uma nova resenha, esta � 
	 *  salva em um ArrayList de resenhas que ser� retornado no fim do m�todo.
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
	
	/** O seguinte m�todo auxilia em VerResenhaControlador, ele recebe o ISBN do livro e o n�mero do usu�rio que est� solicitando a
	 * fun��o, assim pode ler o arquivo .csv da resenha em quest�o, cado a resenha exista ele une o texto do arquivo em apenas uma 
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