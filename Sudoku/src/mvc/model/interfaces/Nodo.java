package mvc.model.interfaces;

import java.util.HashMap;
import java.util.Map;

public interface Nodo{
	
	
	public void inicializarBox();
	
	public Map<String, Integer> getBox();
	
	public void imprimirConsolaNodo();
	
	public boolean validarBox(String nombre, int numero);
	
	public boolean validarFila(String nombre, int numero);
	
	public boolean validarColumna(String nombre, int numero);
	
	public int get(String nombre);
	
	public String getNombreNodo();
		
	public void set(String nombre, Integer num);
	
	
}
