package mvc.model.beans;

public class Cuadradito {

	private int valor;
	private boolean mostrado;
	
	public Cuadradito(){}
	
	public Cuadradito(int valor){
		this.valor = valor;
		mostrado = true;
	}
	
	public Cuadradito(int valor, boolean mostrado){
		this.valor = valor; this.mostrado = mostrado;
	}
	
	public int getValor() {
		return valor;
	}
	public String getValorMostrado() {
		if(mostrado)
			return String.valueOf(valor);
		else 
			return "";
	}
	public boolean isMostrado() {
		return mostrado;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public void setMostrado(boolean mostrado) {
		this.mostrado = mostrado;
	}
	
	
}
