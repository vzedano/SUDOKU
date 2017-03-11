package mvc.model.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mvc.model.interfaces.INodoContainer;

public class NodoContainer implements INodoContainer{

	public Map<String, Nodo> nodeBox;
	Autogenerador auto;

	public NodoContainer() {
		nodeBox = new HashMap<String, Nodo>();
		auto = Autogenerador.getInstance();
	}
	
	
public boolean procesar(){

	
		boolean listo = false;
		System.out.println("Procesando...");
		Nodo.iniciar();
		
		do{
			System.out.println("Volviendo a intentar...");
			Nodo.contarVez();
			inicializarNodeBox();
			listo = generarTodo();
		}while(!listo);
		
		imprimirConsolaNodos();
		
		Nodo.terminar();
		System.out.println("Cantidad de intentos de crear un Sudoku "+Nodo.getIntentos());
		System.out.println("Cantidad de numeros generados "+Autogenerador.getCantidadNumerosGenerados());
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
	
	private boolean generarNumero(String nombreNodo, String nombreCuadradito){
		int[] array = getArrayTodo(nombreNodo, nombreCuadradito);
		int iArray = 0;
		int numero = 0;
		boolean valido = false;
		
		Autogenerador.newArray();
			
		numero = auto.autogenerar(array);
		valido = validarTodo(nombreNodo, nombreCuadradito, numero);
		
		if(numero == -1)
			return false;
		
		StringBuffer arrayS = new StringBuffer();
		arrayS.append(array[0]);
		for(int i = 1;i<array.length;i++){
			arrayS.append(", "+array[i]);
		}
		
		System.out.println(nombreNodo+" >> "+nombreCuadradito+" - "+ numero + " de no posibles ["+arrayS.toString()+"] ");	
			
		
		nodeBox.get(nombreNodo).set(nombreCuadradito, numero);
		
		return true;
	}
	
	public int[] getArrayBox(String nombreNodo, String nombreCuadradito){
		return get(nombreNodo).obtenerValoresBox(nombreCuadradito);
	}
	
	public int[] getArrayFila(String nombreNodo, String nombreCuadradito){
		String letraFila = nombreNodo.substring(0,1);
		int[] array = new int[3];

		nodeBox.keySet().stream()
						.filter(x -> x.startsWith(letraFila))
						.forEach(x -> integrarArrays(array, get(x).obtenerValoresFila(nombreCuadradito)));
		
		return array;
	}
	public int[] getArrayColumna(String nombreNodo, String nombreCuadradito){
		String letraColumna = nombreNodo.substring(1);
		int[] array = new int[3];
		
		nodeBox.keySet().stream()
						.filter(x -> x.endsWith(letraColumna))
						.forEach(x -> integrarArrays(array, get(x).obtenerValoresColumna(nombreCuadradito)));
		
		return array;
	}
	public int[] getArrayTodo(String nombreNodo, String nombreCuadradito){
		int[] array = new int[9];
		
		integrarArrays(array, getArrayBox(nombreNodo, nombreCuadradito));
		integrarArrays(array, getArrayFila(nombreNodo, nombreCuadradito));
		integrarArrays(array, getArrayColumna(nombreNodo, nombreCuadradito));
		
		System.out.println("contenido arraydevalidacion");
		for(int i =0; i<array.length;i++){
			System.out.println(array[i]);

		}
		
		return array;
	}
	@Override
	public boolean validarFila(String nombreNodo, String nombreCuadradito, int numero) {
		
		Set<String> keySet = nodeBox.keySet();
		
		return keySet.stream()				
				.filter(x -> nodeBox.get(x).getNombreNodo().startsWith(nombreNodo.substring(0,1)))
				.allMatch(x -> nodeBox.get(x).validarFila(nombreCuadradito, numero));
	}

	@Override
	public boolean validarColumna(String nombreNodo, String nombreCuadradito, int numero) {
		
		Set<String> keySet = nodeBox.keySet();
		
		return keySet.stream()
				.filter(x -> nodeBox.get(x).getNombreNodo().endsWith(nombreNodo.substring(1)))
				.allMatch(x -> nodeBox.get(x).validarColumna(nombreCuadradito, numero));
	}
	
	@Override
	public boolean validarBox(String nombreNodo, String nombreCuadradito, int numero) {		
		return nodeBox.get(nombreNodo).validarBox(nombreCuadradito, numero);
	}
	
	@Override
	public boolean validarTodo(String nombreNodo, String nombreCuadradito, int numero) {

		if(validarBox(nombreNodo, nombreCuadradito, numero))
			if(validarColumna(nombreNodo, nombreCuadradito, numero))
				if(validarFila(nombreNodo, nombreCuadradito, numero))
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
		nodeBox.put("AA", new Nodo("AA"));
		nodeBox.put("AB", new Nodo("AB"));
		nodeBox.put("AC", new Nodo("AC"));
		nodeBox.put("BA", new Nodo("BA"));
		nodeBox.put("BB", new Nodo("BB"));
		nodeBox.put("BC", new Nodo("BC"));
		nodeBox.put("CA", new Nodo("CA"));
		nodeBox.put("CB", new Nodo("CB"));
		nodeBox.put("CC", new Nodo("CC"));
	}
	@Override
	public Map<String, Nodo> getNodeBox(){
		return nodeBox;
	}
	@Override
	public Nodo getNodo(String nombreCuadradito){
		return nodeBox.get(nombreCuadradito);
	}
	
	public Nodo get(String nombreNodo){
		return nodeBox.get(nombreNodo);
	}
	
	public void integrarArrays(int[] arrayIntegrado, int[] arrayIntegrante){
		
		for(int i = 0; i<arrayIntegrante.length;i++){
			if(arrayIntegrante[i]!=0){
				if(!Arrays.asList(arrayIntegrado).contains(arrayIntegrante[i])){
					agregarNumeroAlArray(arrayIntegrado, arrayIntegrante[i]);
				}
			}
		}
		
	}
	
	public void agregarNumeroAlArray(int[]array,int num){
		boolean sepuede = true;
		for(int i=0; i<array.length;i++){
			if(array[i]==0 && sepuede){
				array[i] = num;
				sepuede=false;
			}
		}
	}
	
}
