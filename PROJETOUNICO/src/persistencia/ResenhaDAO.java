package persistencia;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import modelo.Livro;
import modelo.Resenha;

public class ResenhaDAO implements DAO<Resenha>{

	
	private static final String DIR = "livros/ISBN/";
	
	public void save(Resenha obj) {
		try {
			File dir = new File("livros/");
			if (! dir.exists()) dir.mkdir();
			File subdir = new File(DIR);
			if (! subdir.exists()) subdir.mkdir();
			
			dir = new File(DIR + obj.getISBN() + "/");
			if (! dir.exists()) dir.mkdir();
			File file = new File(DIR + obj.getISBN() + "/" + "resenhas.csv");
			if (file.exists()) return;
			
			FileWriter writer = new FileWriter(file);
			
		}
		
		 catch (Exception e) {
				e.printStackTrace();
			}

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