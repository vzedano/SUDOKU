package mvc.model.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.sun.org.apache.xml.internal.utils.StringComparable;

import mvc.model.interfaces.Nodo;
import mvc.model.interfaces.NodoContainer;

public class SudokuNodoContainer implements NodoContainer{

	public Map<String, Nodo> nodeBox;
	Autogenerador auto;

	public SudokuNodoContainer() {
		nodeBox = new HashMap<String, Nodo>();
		auto = Autogenerador.getInstance();
	}
	
	
public boolean procesar(){
		
		long inicio = Calendar.getInstance().getTimeInMillis();
		long fin;
		boolean listo = false;
		System.out.println("Procesando...");
		do{
			inicializarNodeBox();
			listo = generarTodo();
		}while(!listo);
		
		fin = Calendar.getInstance().getTimeInMillis();
		//imprimirConsolaNodos();
		System.out.println("terminé en "+((fin-inicio)*1.0)/1000+" segundos.");
		
		return listo;
	}
	
	public boolean generarTodo(){
		ArrayList<String> keySet = new ArrayList<String>(nodeBox.keySet());
		Collections.sort(keySet);
		
		Set<String> nodeKeySet;
	
		for(String nodeName : keySet){
			nodeKeySet = nodeBox.get(nodeName).getBox().keySet();
			for(String nodeKey : nodeKeySet){
				if(!generarNumero(nodeName, nodeKey))
					return false;
			}
		}
			
		
		return true;
	}
	
	private boolean generarNumero(String nombreNodo, String nombre){
		int[] array = new int[9];
		int iArray = 0;
		int numero = 0;
		boolean valido = false;
		
		do{
			
			if(!valido){
				
				
				
				numero = auto.autogenerar(array);
				valido = validarTodo(nombreNodo, nombre, numero);
				
				if(numero == -1)
					return false;
				
				if(!valido){
					//Ignorar el primer numero
					if(numero!=0){
						if(!Arrays.asList(array).contains(numero)){
							array[iArray]=numero;
							iArray++;
						}
					}
				}
				
				StringBuffer arrayS = new StringBuffer();
				arrayS.append(array[0]);
				for(int i = 1;i<array.length;i++){
					arrayS.append(", "+array[i]);
				}
				
				//System.out.println(nombreNodo+" >> "+nombre+" - "+ numero + " ["+arrayS.toString()+"] ");	
				
			}
				
			
			
		}while(!valido);
		
		nodeBox.get(nombreNodo).set(nombre, numero);
		
		return true;
	}
	
	@Override
	public boolean validarFila(String nombreNodo, String nombre, int numero) {
		Set<String> keySet = nodeBox.keySet();
		return keySet.stream()				
				.filter(x -> nodeBox.get(x).getNombreNodo().startsWith(nombreNodo.substring(0,1)))
				.allMatch(x -> nodeBox.get(x).validarFila(nombre, numero));
	}

	@Override
	public boolean validarColumna(String nombreNodo, String nombre, int numero) {
		Set<String> keySet = nodeBox.keySet();
		return keySet.stream()
				.filter(x -> nodeBox.get(x).getNombreNodo().endsWith(nombreNodo.substring(1)))
				.allMatch(x -> nodeBox.get(x).validarColumna(nombre, numero));
	}
	
	@Override
	public boolean validarBox(String nombreNodo, String nombre, int numero) {		
		return nodeBox.get(nombreNodo).validarBox(nombre, numero);
	}
	
	@Override
	public boolean validarTodo(String nombreNodo, String nombre, int numero) {

		if(validarBox(nombreNodo, nombre, numero))
			if(validarColumna(nombreNodo, nombre, numero))
				if(validarFila(nombreNodo, nombre, numero))
					return true;
		return false;
		
	}
	
	@Override
	public void imprimirConsolaNodos() {
		Set<String> keySet = nodeBox.keySet();
		
		keySet.stream().sorted().forEach(x -> nodeBox.get(x).imprimirConsolaNodo());
		
	}

	@Override
	public void inicializarNodeBox(){
		nodeBox.put("AA", new SudokuNodo("AA"));
		nodeBox.put("AB", new SudokuNodo("AB"));
		nodeBox.put("AC", new SudokuNodo("AC"));
		nodeBox.put("BA", new SudokuNodo("BA"));
		nodeBox.put("BB", new SudokuNodo("BB"));
		nodeBox.put("BC", new SudokuNodo("BC"));
		nodeBox.put("CA", new SudokuNodo("CA"));
		nodeBox.put("CB", new SudokuNodo("CB"));
		nodeBox.put("CC", new SudokuNodo("CC"));
	}
	@Override
	public Map<String, Nodo> getNodeBox(){
		return nodeBox;
	}
	@Override
	public Nodo getNodo(String nombre){
		return nodeBox.get(nombre);
	}
	
	
	
}
