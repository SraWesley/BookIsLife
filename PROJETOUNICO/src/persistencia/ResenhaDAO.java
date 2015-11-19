package persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Resenha;
import modelo.Usuario;

public class ResenhaDAO implements DAO<Resenha>{

	private static final String DIR = "Usuarios/Matriculas/";
	
	public void save(Resenha r) {
			File dir = new File(DIR + "/" + r.getNumero() + "/" + "minhasResenhas");
			if (! dir.exists()) dir.mkdir();
			String resenhasExistente = "";
			File file = new File(DIR + "/" + r.getNumero() + "/" + "minhasResenhas"+ "/" + r.getISBN()+".csv");
			Scanner scan = null;
			boolean resenhaExiste = false;
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
						if(!resenhasExistente.isEmpty()){
							writer.write(resenhasExistente);
						} 
						writer.write(r.getTexto());
						writer.write(";");
						writer.flush();
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else { // Arquivo não existe
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
			
				/*if (file.exists()) {
				Scanner scan = new Scanner(file);
				String linha = scan.nextLine();
				scan.close();
				String[] colunas = linha.split(";");
				FileWriter writer = new FileWriter(file);
				writer.write();
				writer.write(r.getISBN() + ";");
				writer.write(r.getTexto() + ";");
		//		writer.write();
			//	writer.flush();
				//writer.close();
			//}

			FileWriter writer = new FileWriter(file);
			writer.write(r.getISBN() + ";");
			writer.write(r.getTexto() + ";");
			writer.flush();
			writer.close();
		}
		
		 catch (Exception e) {
				e.printStackTrace();
			}
*/
	}
	public boolean resenhaExiste(Resenha resenha) {
		File dir = new File(DIR + "/" + resenha.getNumero() + "/" + "minhasResenhas");
		if (! dir.exists()) dir.mkdir();
		String resenhasExistente = "";
		File file = new File(DIR + "/" + resenha.getNumero() + "/" + "minhasResenhas"+ "/" + resenha.getISBN()+".csv");
		Scanner scan = null;
		if(file.exists()){
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

	public void delete(Resenha obj) {
			
	}

	public Resenha load(int chave) {
		return null;
	}

	public void update(Resenha obj) {
		
	}

	public ArrayList<Resenha> findAll() {
		
		return null;
	}


	
}