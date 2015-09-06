package projeto;

import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class LivroDAO implements DAO<Livro>{

	@Override
	public void save(Livro obj)  {
		try{
			File dir = new File("livros");
			if(!dir.exists()) dir.mkdir();
			File arq = new File("livros/" + obj.getISBN() + ".csv");
			if(arq.exists()) return;
			FileWriter writer = new FileWriter(arq);
			writer.write(obj.getISBN() + ";");
			writer.write(obj.getNome() + ";");
			writer.write(obj.getEditora() + ";");
			writer.write(obj.getEscritor() + ";");
			writer.write(obj.getAnopublicado() + ";");
			writer.flush();
			writer.close();
			System.out.println("OK");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Livro obj) {
		try{
			File arq =  new File("Lilmes/" + obj.getISBN() + ".csv");
			if(! arq.exists()) return; 
			arq.delete();
		} catch(Exception e){
			e.printStackTrace();
		}		
	}

	@Override
	public Livro load(int chave) {
		Livro l = null;
		try{
			File arq =  new File("livros/" + chave + ".csv");
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
			System.out.println("LSLSLS");
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
