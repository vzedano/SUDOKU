package mvc.model.interfaces;

import mvc.model.beans.CuadraditosMap;

public interface INodo{
	
	
	public void inicializarBox();
	
	public CuadraditosMap getBox();
	
	public void imprimirConsolaNodo();
	
	public boolean validarBox(String nombre, int numero);
	
	public boolean validarFila(String nombre, int numero);
	
	public boolean validarColumna(String nombre, int numero);
	
	public int get(String nombre);
	
	public String getNombreNodo();
		
	public void set(String nombre, Integer num);
	
	
}
