package mvc.model.beans;



import java.util.Arrays;
import java.util.Set;



import mvc.model.interfaces.INodo;

public class Nodo implements INodo{
	
	Autogenerador autogenerador;
	private String nombreNodo;
	private CuadraditosMap box = new CuadraditosMap();
	boolean generado;
	
	private static long inicio;
	private static long fin;
	private static long contador;
	
	static{
		inicio = 0;
		fin = 0;
		contador = 0;
	}

	public Nodo(String nombreCuadradito) {
		this.nombreNodo = nombreCuadradito;
		inicializarBox();
		autogenerador = Autogenerador.getInstance();
	}
	
	@Override
	public boolean validarBox(String nombreCuadradito, int numero) {
		
		Set<String> keySet = box.keySet();

		return keySet.stream().noneMatch(x -> box.get(x)==numero);
	}
	

	@Override
	public boolean validarFila(String nombreCuadradito, int numero) {
		Set<String> keySet = box.keySet();
		
		return keySet.stream()
				.filter(x -> x.startsWith(nombreCuadradito.substring(0,1)))
				.noneMatch(x -> box.get(x)==numero);
	}

	@Override
	public boolean validarColumna(String nombreCuadradito, int numero) {
		Set<String> keySet = box.keySet();

		return keySet.stream()
				.filter(x -> x.endsWith(nombreCuadradito.substring(1)))
				.noneMatch(x -> box.get(x)==numero);
	}

	@Override
	public CuadraditosMap getBox() {
		return box;
	}

	@Override
	public int get(String nombreCuadradito) {
		return box.get(nombreCuadradito);
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
	public void set(String nombreCuadradito, Integer num) {
		box.put(nombreCuadradito, num);
	}
	
	@Override
	public void inicializarBox(){
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
	
	public int[] obtenerValoresBox(String nombreCuadradito){
		
		String letraFila = nombreCuadradito.substring(0,1);
		String letraColumna = nombreCuadradito.substring(1);
		
		int[] valores;
		String[] keyArray = box.keySet().stream()
										.toArray(size -> new String[size]);
		
		int size = ((size = keyArray.length)<0)?0:size;
		
		valores = new int[size];
		
		for(int i = 0; i<size; i++){
			valores[i] = box.get(keyArray[i]);
		}
		
		System.out.println("Valores de un box");		
		for(int i = 0; i<valores.length;i++){
			System.out.println(valores[i]);
		}
		System.out.println("");
		
		return valores;
	}
	
	public int[] obtenerValoresFila(String nombreCuadradito){
		
		String letraFila = nombreCuadradito.substring(0,1);
		
		int[] valores;
		
		String[] keyArray = box.keySet().stream()
										.filter(x -> box.get(x)!=0)
										.filter(x -> x.startsWith(letraFila))
										.toArray(size -> new String[size]);
		
		int size = ((size = keyArray.length-1)<0)?0:size;

		valores = new int[size];
		
		for(int i = 0; i<size; i++){
			valores[i] = box.get(keyArray[i]);
		}
		
		System.out.println("Valores de una fila");
		for(int i = 0; i<valores.length;i++){
			System.out.println(valores[i]);
		}
		System.out.println("");

		
		return valores;
	}
	
	public int[] obtenerValoresColumna(String nombreCuadradito){
		
		String letraColumna = nombreCuadradito.substring(1);
		
		int[] valores;
		String[] keyArray = box.keySet().stream()
										.filter(x -> box.get(x)!=0)
										.filter(x -> x.endsWith(letraColumna))
										.toArray(size -> new String[size]);

		int size = ((size = keyArray.length-1)<0)?0:size;

		valores = new int[size];
		
		for(int i = 0; i<size; i++){
			valores[i] = box.get(keyArray[i]);
		}
		
		System.out.println("Valores de una columna");		
		for(int i = 0; i<valores.length;i++){
			System.out.println(valores[i]);
		}
		System.out.println("");

		
		return valores;
	}
	public static void iniciar(){
		inicio = System.nanoTime();
	}
	
	public static void terminar(){
		fin = System.nanoTime();
		tiempoTotal();
	}
	
	public static void contarVez(){
		contador++;
	}
	
	public static void tiempoTotal(){
		System.out.println("Terminé en "+tiempoMillis()+" milisegundos.");
	}
	
	public static float tiempoNanos(){
		return (float)((fin-inicio)*1.0);
	}
	
	public static float tiempoMillis(){
		return tiempoNanos()/1000000;
	}
	
	public static void promedioTiempoGeneracion(){
		float millis = tiempoMillis();
		
		System.out.println("El promedio para generar un sudoku es de "+ millis/contador+" milisegundos.");
		
		
	}
	
	public static long getIntentos(){
		return contador;
	}

	
	public boolean isGenerado() {
		return generado;
	}

	
	public void setGenerado(boolean generado) {
		this.generado = generado;
	}

	
}
