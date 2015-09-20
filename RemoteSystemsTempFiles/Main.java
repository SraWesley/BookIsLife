package projeto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;

public class Main {

	public static void main(String[] args) throws Exception {
		File livros = new File("livros.csv");
		FileWriter writer = new FileWriter(livros, true);
						
		DAO<Livro> dao= new LivroDAO();
		
		Livro l1 = new Livro();
		l1.setISBN(1);
		l1.setNome("Questões do Coração");
		l1.setEditora("Novo Conceito");
		l1.setEscritor("Emily Giffin");
		l1.setAnopublicado(new Date(3, 1, 115));
		
		dao.save(l1);		
		
		Livro a = dao.load(1);
		System.out.println(a != null);
		System.out.println(a.getISBN() == 1);
		System.out.println(a.getNome().equals("Questões do Coração"));
		System.out.println(a.getEditora().equals("Novo Conceito"));
		System.out.println(a.getEscritor().equals("Emily Giffin"));
		//System.out.println(new Date(3, 1 , 115).equals(a.getAnopublicado()));
		
		Livro l2 = new Livro();
		l2.setISBN(2);
		l2.setNome("Maldosas");
		l2.setEditora("Rocco");
		l2.setEscritor("Sarah Shepard");
		l2.setAnopublicado(new Date(4, 2, 114));
		
		dao.save(l2);
		
		Livro b = dao.load(11); //número não encontrado: NULL
		System.out.println(b == null);
		
		Livro c = dao.load(2);
		System.out.println(c.equals(l2));
		
		dao.delete(c); // ou f1
		
		Livro d = dao.load(1);
		System.out.println(d == null);
		
		
		
}}	