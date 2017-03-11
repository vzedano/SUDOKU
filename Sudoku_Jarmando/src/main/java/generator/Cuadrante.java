package generator;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author Jarmando Corlaez */
class Cuadrante {
	
	//Se genera a sí mismo (valido siempre)
	
	Cuadrado[][] cuadrados = new Cuadrado[3][3];
	private int cuadranteNumero = -1;
	
	private Cuadrante(int cuadranteNumero){
		this.cuadranteNumero = cuadranteNumero;
	}
	
	public Cuadrante(int [] valores){//1 valor a 1 cuadrado
		if(valores.length == 0) throw new InvalidParameterException();
		String [] valoresS = Helper.parseArray(valores);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int valoresIndex = i*3+j;
				if (valores.length <= valoresIndex) return;
				cuadrados[i][j] = new Cuadrado(valoresS[valoresIndex],i,j);
			}
		}
	}
	
	@Deprecated
	public Cuadrante(int [][] valores){
		if(valores.length == 0) throw new InvalidParameterException();
		String [][] valoresS = Helper.parseArray(valores);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cuadrados[i][j] = new Cuadrado(valoresS[i][j],i,j);
			}
		}
	}

	static Cuadrante makeCuadranteVacio(int cuadranteNumero){
		Cuadrante c = new Cuadrante(cuadranteNumero);
		for (int n = 0; n < 9; n++) {
			 int i = n/3, j = n%3;
			 c.cuadrados[i][j] = new Cuadrado("",i,j);
		}
		return c;
	}
	
	static Cuadrante makeCuadranteRandom(int cuadranteNumero){
		Cuadrante c = new Cuadrante(cuadranteNumero);
		List<String> list = Helper.newList1to9inclusive();
		 for (int n = 0; n < 9; n++) {
			 int i = n/3, j = n%3;
			 c.cuadrados[i][j] = new Cuadrado(Helper.getRandomAndRemove(list),i,j);
		}
		return c;
	}
	
	static Cuadrante makeCuadranteVerticalA(int cuadranteNumero, Cuadrante ... verticales){
		Cuadrante cuadrante = new Cuadrante(cuadranteNumero);
		
		List<String> l0 = Helper.newList1to9inclusive();
		List<String> l1 = Helper.newList1to9inclusive();
		List<String> l2 = Helper.newList1to9inclusive();
		
		for (int i = 0; i < verticales.length; i++) {
			Cuadrante vertical = verticales[i];
			vertical.verificarColumna(0, l0);
			vertical.verificarColumna(1, l1);
			vertical.verificarColumna(2, l2);
		}
		
		List<String> wl0, wl1, wl2;
		
		do{
			wl0 = new ArrayList<>(l0);
			wl1 = new ArrayList<>(l1);
			wl2 = new ArrayList<>(l2);
			for (int i = 0; i < 3; i++) {
				cuadrante.cuadrados[i][0] = new Cuadrado(Helper.getRandomAndRemove(wl0),i,0);
				cuadrante.cuadrados[i][1] = new Cuadrado(Helper.getRandomAndRemove(wl1),i,1);
				cuadrante.cuadrados[i][2] = new Cuadrado(Helper.getRandomAndRemove(wl2),i,2);
			}
		}while(!cuadrante.esValido());
		return cuadrante;
	}
	
	static Cuadrante makeCuadranteHorizontalA(int cuadranteNumero, Cuadrante ... horizontales){
		Cuadrante cuadrante = new Cuadrante(cuadranteNumero);
		List<String> l0 = Helper.newList1to9inclusive();
		List<String> l1 = Helper.newList1to9inclusive();
		List<String> l2 = Helper.newList1to9inclusive();
		
		
		for (int i = 0; i < horizontales.length; i++) {
			Cuadrante vertical = horizontales[i];
			vertical.verificarFila(0, l0);
			vertical.verificarFila(1, l1);
			vertical.verificarFila(2, l2);
		}
		
		
		List<String> wl0, wl1, wl2;
		
		do{
			wl0 = new ArrayList<>(l0);
			wl1 = new ArrayList<>(l1);
			wl2 = new ArrayList<>(l2);
			for (int j = 0; j < 3; j++) {
				cuadrante.cuadrados[0][j] = new Cuadrado(Helper.getRandomAndRemove(wl0),0,j);
				cuadrante.cuadrados[1][j] = new Cuadrado(Helper.getRandomAndRemove(wl1),1,j);
				cuadrante.cuadrados[2][j] = new Cuadrado(Helper.getRandomAndRemove(wl2),2,j);
			}
		} while(!cuadrante.esValido());
		return cuadrante;	
	}
	
	public static Cuadrante makeCuadranteHorizontalVerticalA(int cuadranteNumero, Cuadrante [] horizontales, Cuadrante ... verticales) {
		return jodete(cuadranteNumero, horizontales, verticales);
	}
	
	public static Cuadrante jodete(int cuadranteNumero, Cuadrante [] horizontales, Cuadrante ... verticales) {
		Cuadrante cuadrante = Cuadrante.makeCuadranteVacio(cuadranteNumero);
		int countCuadrante = 0;
		int count = 0;
		
		do{
			if(countCuadrante == 100)
				return null;
			countCuadrante++;
//			System.out.println("ext");
			for (int n = 0; n < 9; n++) {
				
				int x = n/3;
				int y = n%3;
				List<String> list;
				
				do{
//					System.out.println("int");
//					System.out.println(cuadrante.toStringBidimensional());
					list = Helper.newList1to9inclusive();
					//quitando los valores prohibidos es decir del mismo cuadrante
					
					for (int nn = 0; nn < 9; nn++) {
						int xx = nn/3;
						int yy = nn%3;
						cuadrante.verificarFila(xx,list);
						cuadrante.verificarColumna(yy,list);
					}
					
					//filas
					if(horizontales != null && horizontales.length > 0)
						horizontales[0].verificarFila(x, list);
					if(horizontales != null && horizontales.length == 2)
						horizontales[1].verificarFila(x, list);
					//columnas
					if(verticales != null && verticales.length > 0)
						verticales[0].verificarColumna(y, list);
					if(verticales != null && verticales.length==2)
						verticales[1].verificarColumna(y, list);
					
					//Si me quede sin opciones validas, reiniciar cuadrante
					if(list.size() == 0){
						cuadrante = Cuadrante.makeCuadranteVacio(cuadranteNumero);
						count++;
						if(count == 100){
							return null;
						}
					}

					//y repetir (Si me quede sin opciones validas)
				}while(list.size() == 0);
				cuadrante.cuadrados[x][y] = new Cuadrado(Helper.getRandomAndRemove(list),x,y);
			}
		}while(!cuadrante.esValido());
		return cuadrante;
	}
	
	public boolean verificarColumnas(Cuadrante ... verticales){
		List<String> list0 = Helper.newList1to9inclusive();
		List<String> list1 = Helper.newList1to9inclusive();
		List<String> list2 = Helper.newList1to9inclusive();
		//verificando este cuadrante
		if(!(verificarColumna(0, list0) && verificarColumna(1, list1) && verificarColumna(2, list2))){
			return false;
		}
		for (int n = 0; n < verticales.length; n++) {//verificando con verticales
			if(!(verticales[n].verificarColumna(0, list0) && verticales[n].verificarColumna(1, list1) && verticales[n].verificarColumna(2, list2)))//verificando este cuadrante
				return false;
		}
		return true;
	}

	public boolean verificarFilas(Cuadrante ... horizontales){
		List<String> list0 = Helper.newList1to9inclusive();
		List<String> list1 = Helper.newList1to9inclusive();
		List<String> list2 = Helper.newList1to9inclusive();
		//verificando este cuadrante
		if(!(verificarFila(0, list0) && verificarFila(1, list1) && verificarFila(2, list2))){
			return false;
		}
		for (int n = 0; n < horizontales.length; n++) {//verificando con verticales
			if(!(horizontales[n].verificarFila(0, list0) && horizontales[n].verificarFila(1, list1) && horizontales[n].verificarFila(2, list2)))//verificando este cuadrante
				return false;
		}
		return true;
	}
	
	//mutator
	public void setValue(int index, String value){
		if(index>=9) return;
		int i = index/3;
		int j = index%3;
		cuadrados[i][j] = new Cuadrado(value,i,j);
	}
	
	public boolean esValido(){
		List<String> list = Helper.newList1to9inclusive();//1-9
		for (int n = 0; n < 3; n++) {
			if (!verificarColumna(n, list))
				return false;		
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(cuadrados[i][j]);
			}
			sb.append("\n");
		}
		return Arrays.deepToString(cuadrados);//sb.toString();
	}
	
	public static void main(String[] args) {
    	Cuadrante h1 = new Cuadrante(new int[]{
    			6,4,1,
    			5,9,7,
    			3,8,2});
//		Cuadrante h2 = new Cuadrante(9,5,2,8,3,6,4,7,1);
		
		Cuadrante [] hs = {h1};
		//verticales
		Cuadrante v1 = new Cuadrante(new int[]{
				5,2,4,
				7,3,1,
				8,6,9});
//		Cuadrante v2 = new Cuadrante(4,9,7,2,1,3,6,8,5);
		//test
		Cuadrante test = new Cuadrante(new int[]{3,7,8,1,4,2,9,5,6});
		Cuadrante generado = Cuadrante.makeCuadranteHorizontalVerticalA(-1, hs, v1);
		
		System.out.println(generado.toStringBidimensional());
//		boolean b = generado.verificarColumnas(v1) && generado.verificarFilas(h1);
		System.out.println(generado.equals(test));
		
//		System.out.println(test.toStringBidimensional());
//		System.out.println(generado.toStringBidimensional());
//		System.out.println(test.equals(generado));
	}
	
	boolean verificarFila(int n, List<String> list){
		boolean b = true;
		int i = n;
		for (int j = 0; j < 3; j++) {
			if(cuadrados[i][j] == null){
				b = false;
			}
			if(!list.contains(cuadrados[i][j].getValor())) {
				b = false;
			}
			else {
				int index = list.indexOf(cuadrados[i][j].getValor());
				list.remove(index);
			}
		}
		return b;
	}
	
	boolean verificarColumna(int n, List<String> list){
		int j = n;
		boolean b = true;
		for (int i = 0; i < 3; i++) {
			if(cuadrados[i][j] == null){
				b = false;
			}
			else if(!list.contains(cuadrados[i][j].getValor())) {
				b = false;
			}
			else {
				int index = list.indexOf(cuadrados[i][j].getValor());
				list.remove(index);
			}
		}
		return b;
	}
	
	public String toStringBidimensional(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(cuadrados[i][j].getValor()+"    ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof Cuadrante)) return false;
		Cuadrante cuad = (Cuadrante) obj;
		for (int i = 0; i < cuadrados.length; i++) {
			if(!get(i/3,i%3).equals(cuad.get(i/3,i%3)))
				return false;
		}
		return cuad.cuadranteNumero == cuadranteNumero;
	}	

	private Cuadrado get(int i, int j) {
		return cuadrados[i][j];
	}
	
	public void setCuadranteNumero(int cuadranteNumero) {
		if(cuadranteNumero<0 || cuadranteNumero>8) throw new InvalidParameterException();
		if(this.cuadranteNumero != cuadranteNumero){
			this.cuadranteNumero = cuadranteNumero;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					cuadrados[i][j].numeroDeCuadrante = cuadranteNumero;
				}
			}
		}
		
	}
	
}
