package persistencia;

import java.util.ArrayList;

public interface DAO <T>{
	
	public void save(T obj) throws Exception;
	
	public void delete(T obj);
	
	public T load(int chave);
	
	public void update(T obj);
	
	public ArrayList<T> findAll();	
}