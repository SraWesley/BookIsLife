package projeto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;

public class Main {

	public static void main(String[] args) throws Exception {
		File biblioteca = new File("Biblioteca.csv");
		FileWriter writer = new FileWriter(biblioteca, true);
						
		DAO<Livro> dao= new LivroDAO();
		Livro l1 = new Livro();
		l1.setISBN(0);
		l1.setNome("Questões do Coração");
		l1.setEditora("Novo Conceito");
		l1.setEscritor("Emily Giffin");
		l1.setAnopublicado(new Date(3, 1, 115));
		
		dao.save(l1);		
		
		Livro a = dao.load(0);
		System.out.println(a != null);
		System.out.println(a.getISBN() == 3);
		System.out.println(a.getNome().equals("Questões do Coração"));
		System.out.println(a.getEditora().equals("Novo Conceito"));
		System.out.println(a.getEscritor().equals("Emily Giffin"));
		System.out.println(new Date(3, 1 , 115).equals(a.getAnopublicado()));
		
}}	