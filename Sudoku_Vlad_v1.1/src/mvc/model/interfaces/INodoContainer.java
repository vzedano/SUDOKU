package mvc.model.interfaces;

import java.util.HashMap;
import java.util.Map;

import mvc.model.beans.Nodo;

public interface INodoContainer {

	
	public void inicializarNodeBox();
	
	public Map<String, Nodo> getNodeBox();
	
	public Nodo getNodo(String nombre);
	
	public boolean validarFila(String nombreNodo,String nombre, int numero);
	
	public boolean validarColumna(String nombreNodo,String nombre, int numero);
	
	public boolean validarBox(String nombreNodo, String nombre, int numero);
	
	public boolean validarTodo(String nombreNodo, String nombre, int numero);
	
	public void imprimirConsolaNodos();
	
}
