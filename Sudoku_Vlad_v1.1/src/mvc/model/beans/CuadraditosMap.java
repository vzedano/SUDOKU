package mvc.model.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CuadraditosMap{
	
	private Map<String,Cuadradito> mapa = new HashMap<String,Cuadradito>();
	
	public void put(String nombre, Cuadradito cuadradito){
		mapa.put(nombre, cuadradito);
	}
	
	public void put(String nombre, int numero){
		mapa.put(nombre, new Cuadradito(numero));
	}
	
	public int get(String nombre){
		return mapa.get(nombre).getValor();
	}
	
	public Cuadradito getCuadradito(String nombre){
		return mapa.get(nombre);
	}
	
	public Set<String> keySet(){
		return mapa.keySet();
	}
	
	public Map<String, Cuadradito> getMap(){
		return mapa;
	}	
	
}
