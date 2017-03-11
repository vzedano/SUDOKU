package mvc.model.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import mvc.model.interfaces.Nodo;

public class SudokuNodo implements Nodo{
	
	Autogenerador autogenerador;
	private String nombreNodo;
	public Map<String, Integer> box;

	public SudokuNodo(String nombre) {
		this.nombreNodo = nombre;
		inicializarBox();
		autogenerador = Autogenerador.getInstance();
	}
	
	@Override
	public boolean validarBox(String nombre, int numero) {
		

		Set<String> keySet = box.keySet();

		return keySet.stream().noneMatch(x -> box.get(x)==numero);
	}

	@Override
	public boolean validarFila(String nombre, int numero) {
		Set<String> keySet = box.keySet();

		return keySet.stream()
				.filter(x -> x.startsWith(nombre.substring(0,1)))
				.noneMatch(x -> box.get(x)==numero);
	}

	@Override
	public boolean validarColumna(String nombre, int numero) {
		Set<String> keySet = box.keySet();

		return keySet.stream()
				.filter(x -> x.endsWith(nombre.substring(1)))
				.noneMatch(x -> box.get(x)==numero);
	}

	@Override
	public Map<String, Integer> getBox() {
		return box;
	}

	@Override
	public int get(String nombre) {
		return box.get(nombre);
	}
	

	@Override
	public String getNombreNodo() {
		return nombreNodo;
	}

	public void setNombreNodo(String nombreNodo) {
		this.nombreNodo = nombreNodo;
	}

	@Override
	public void imprimirConsolaNodo() {
		Set<String> keySet = box.keySet();
		
		keySet.stream().sorted().forEach(x -> System.out.println(nombreNodo+" > "+x+" - "+get(x)));
	}

	@Override
	public void set(String nombre, Integer num) {
		box.put(nombre, num);
	}
	
	@Override
	public void inicializarBox(){
		box = new HashMap<String, Integer>();
		box.put("aa", 0);
		box.put("ab", 0);
		box.put("ac", 0);
		box.put("ba", 0);
		box.put("bb", 0);
		box.put("bc", 0);
		box.put("ca", 0);
		box.put("cb", 0);
		box.put("cc", 0);
	}
	

}
