package mvc.model.beans;

import java.util.Arrays;


public class Autogenerador {
	
	private static Autogenerador instancia = new Autogenerador();
	boolean posible = true;
	private static int[] lista;
	private static int indiceLista;
	private static long cantidadNumerosGenerados;
	
	static{
		cantidadNumerosGenerados=0;
		lista = new int[10]; //El ultimo valor es el 0 siempre para poder eliminar
		indiceLista = 9;
	}
	
	private Autogenerador(){
		
	}
	
	public static Autogenerador getInstance(){
		
		return instancia;
		
	}
	
	
	
	
	public int autogenerar(int[] array){
		
		
		int numero = 0;
		if(array[8]!=0)return -1;
		agregarArray(array);
		//do{
		numero = autogenerarDe1a9();
		cantidadNumerosGenerados++;
			
		//}while(!validarNumeroConArray(numero, array)&&posible);

		return numero;
	}
	
	public int autogenerarDe1a9(){
		return lista[((int)(Math.random()*(indiceLista)))];
	}

	public void eliminarNumero(int num){
		for(int i = 0; i<indiceLista; i++){
			if(lista[i]==num){
				for(int j = i ; j<indiceLista; j++){
					lista[j]=lista[j+1];
				}
				indiceLista--;
			}
		}
	}
	
	public static void newArray(){
		limpiarArray();
		for(int i = 0; i<9; i++){
			
			lista[i] = (i+1);
			
		}
	}
	
	public void agregarArray(int[] array){
		int i = 0, contador = 0;
		do{
			for(int iArray = 0; iArray<array.length; iArray++){
				if(lista[i]!=0){
					if(lista[i]==array[iArray]){
						eliminarNumero(lista[i]);
						if(i!=0)i--;
						else iArray=0;
					}
				}
			}
			contador=0;
			i++;
		}while(i<indiceLista);
			
		System.out.println("contenido posibles numeros");
		for(int idx =0; idx<lista.length;idx++){
			if(indiceLista==idx)
				System.out.println(lista[idx]+"<--");
			else
				System.out.println(lista[idx]);

		}
		
	}
	
	private static void limpiarArray(){
		for(int i = 0; i<lista.length;i++){
			lista[i]=0;
		}
		indiceLista=9;
	}
	
	public static long getCantidadNumerosGenerados() {
		return cantidadNumerosGenerados;
	}
	
	/*public boolean validarNumeroConArray(int num, int[] array){
		
		if(array[0]==0){
			posible = false;
			return false;
		}
		
		for(int i = 0; i<array.length; i++){
			if(array[i]==num){
				eliminarNumero(num);
				return true;
			}
		}
		
		return false;
	}*/
	
	
	
	

	
	
}
