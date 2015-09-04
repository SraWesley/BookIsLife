package projeto;

import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class LivroDAO implements DAO<Livro>{

	@Override
	public void save(Livro obj) throws Exception {
		try{
			File dir = new File("Livros");
			if(!dir.exists()) dir.mkdir();
			File arq = new File("livros/" + obj.getISBN() + ".csv");
			if(arq.exists()) return;
			FileWriter writer = new FileWriter(arq);
			writer.write(obj.getNome() + ";");
			writer.write(obj.getEditora() + ";");
			writer.write(obj.getEscritor() + ";");
			writer.write(obj.getAnopublicado() + ";");
			writer.write(obj.getPaginas() + ";");
			writer.flush();
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Livro obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Livro load(int chave) {
		Livro l = null;
		try{
			File arq =  new File("filmes/" + chave + ".csv");
			if(!arq.exists()) return null;
			l = new Livro();
			Scanner scan = new Scanner(arq);
			String linha = scan.nextLine();
			String[] colunas = linha.split(";");
			l.setISBN(chave);
			l.setNome(colunas[1]);
			l.setEditora(colunas[2]);
			l.setEscritor(colunas[3]);
		//	l.setAnopublicado(Date a = new Date(colunas[4]));
			return l;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;

	
	}

	@Override
	public void update(Livro obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Livro> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
}	
