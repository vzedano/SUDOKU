package mvc.model.beans;


public class Autogenerador {
	
	private static Autogenerador instancia = new Autogenerador();
	boolean posible = true;
	
	private Autogenerador(){
		
	}
	
	public static Autogenerador getInstance(){
		
		return instancia;
		
	}
	
	public int autogenerar(int[] array){
		
		int numero = 0;
		
		do{
			if(!posible)
				return -1;
			numero = autogenerarDe0a9();
		}while(!validarNumeroConArray(numero, array)&&posible);
		
		if(!posible){
			posible = true;
			return -1;
		}
			
		
		return numero;
	}
	
	public boolean validarNumeroConArray(int num, int[] array){
		for(int i = 0; i<array.length; i++){
			if(array[8]!=0){
				posible = false;
				return false;
			}
			if(array[i]==num) return false;
		}
		

		
		return true;
	}
	
	public int autogenerarDe0a9(){
		return (int)(Math.random()*9)+1;
	}
	
}
