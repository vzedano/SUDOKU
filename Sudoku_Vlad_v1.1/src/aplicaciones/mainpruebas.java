package aplicaciones;

import mvc.model.beans.NodoContainer;


public class mainpruebas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		mainpruebas main;
		main = new mainpruebas();
	}

	public mainpruebas() {
		
		NodoContainer contenedor = new NodoContainer();
		
		System.out.println("Sudoku generado : "+contenedor.procesar());
			

	}
	

}
