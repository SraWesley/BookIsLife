package persistencia;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import modelo.Resenha;
import modelo.Usuario;

public class ResenhaDAO implements DAO<Resenha>{

	private static final String DIR = "Usuarios/Matriculas/";
	
	public void save(Resenha r) {
		try {
			File dir = new File(DIR);
			if (! dir.exists()) dir.mkdir();
				
			 dir = new File(DIR + "/" + r.getNumero() + "/" + "MinhasResenhas");
			if (! dir.exists()) dir.mkdir();
			File file = new File(DIR + "/" + r.getNumero() + "/" + "MinhasResenhas" + "/" + r.getISBN()+ ".csv");

			FileWriter writer = new FileWriter(file);
			writer.write(r.getTexto() + ";");
			writer.flush();
			writer.close();
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