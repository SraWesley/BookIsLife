package persistencia;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import modelo.*;

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
		/*
		 * if (file.exists()) { Scanner scan = new Scanner(file); String linha =
		 * scan.nextLine(); scan.close(); String[] colunas = linha.split(";");
		 * FileWriter writer = new FileWriter(file); writer.write();
		 * writer.write(r.getISBN() + ";"); writer.write(r.getTexto() + ";"); //
		 * writer.write(); // writer.flush(); //writer.close(); //} FileWriter
		 * writer = new FileWriter(file); writer.write(r.getISBN() + ";");
		 * writer.write(r.getTexto() + ";"); writer.flush(); writer.close(); }
		 * 
		 * catch (Exception e) { e.printStackTrace(); }
		 */
	}

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
		//String textodaResenha = "";
		File arq = new File("Usuarios/Matriculas/" + numeroUsuario + "/" + "MinhasResenhas/" + isbn + ".csv");
		r = new Resenha();
		if (!arq.exists()) return null;
		try {
			scan = new Scanner(arq);
			String linha = "";
			
			while(scan.hasNextLine()) {
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

	public ArrayList<Resenha> resenhasDoLivro(String iSBN) {
		ArrayList<Resenha> listaResenha = new ArrayList<Resenha>();
		File dirUsuarios = new File("Usuarios/Matriculas/");
		File[] usuarios = dirUsuarios.listFiles();
		for (int i = 0; i < usuarios.length; i++) {
			//System.out.println(usuarios[i].getPath());
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
						//System.out.println(textoDaResenha);
						r.setTexto(textoDaResenha);
						listaResenha.add(r);
						//System.out.println(listaResenha);
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

	public String pegandoTexto(String ISBN, int numero) {
		File  file = new File("Usuarios/Matriculas/" + numero + "/" + "MinhasResenhas/" + ISBN + ".csv");
		String linha = "";
		if(file.exists()){
			try {
				Scanner scan = new Scanner(file);
				//String textoResenha = new String();
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