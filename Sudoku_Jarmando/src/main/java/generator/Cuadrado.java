package generator;

import java.security.InvalidParameterException;

/** @author Jarmando Corlaez */
public class Cuadrado {
	private final String valor;
	private String valorMostrado;
	private final int x;
	private final int y;
	int numeroDeCuadrante = -1;

	public Cuadrado(int x, int y){
		setValorMostrado("");
		this.valor = "";
		this.x = x;
		this.y =y;
	}

	public Cuadrado(String valor, int x, int y){
		setValorMostrado(valor);
		this.valor = valor;
		this.x = x;
		this.y =y;
	}
	
	@Override
	public String toString() {
		return valorMostrado+" ("+valor+")";
	}

	public String getValor(){
		return String.valueOf(valor);
	}

	public String getValorMostrado(){
		return valorMostrado==null? "":valorMostrado;
	}
	
	public void setValorMostrado(String valorMostrado) throws InvalidParameterException{
		if(!isValidValue(valorMostrado)) throw new InvalidParameterException("Not an integer between 1 to 9 or empty");
		this.valorMostrado = valorMostrado.trim();
	}
	
	public boolean esCorrecto(){
		if(valorMostrado == null) return false;
		return valorMostrado.equals(valor);
	}
	
	private Integer myparse(String s){
		if (s.trim().equals("") || s==null) return null;
		else return Integer.parseInt(s.trim());
	}

	private boolean isValidValue(String s){
		Integer i = myparse(s);
		return i==null || (i<10 && i>0);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof Cuadrado)) return false;
		Cuadrado cuad = (Cuadrado) obj;
		return valor.equals(cuad.valor) && x == cuad.x && y == cuad.y && numeroDeCuadrante == cuad.numeroDeCuadrante;
	}
	
	public static void main(String[] args) {
		Cuadrado c1 = new Cuadrado("1",-1,-1);
		Cuadrado c2 = new Cuadrado("1",-1,-1);
		System.out.println(c1.equals(c2));
	}
}