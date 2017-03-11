package generator;
/** @author Jarmando Corlaez */
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import generator.Cuadrante;

public class CuadranteTest {

    @Test
	public void verificarColumnasContraOtrosCuadrantes(){
		Cuadrante cuad1 = new Cuadrante(new int[]{5,2,4,7,3,1,8,6,9});
		Cuadrante cuad2 = new Cuadrante(new int[]{4,9,7,2,1,3,6,8,5});
		Cuadrante cuad3 = new Cuadrante(new int[]{3,7,8,1,4,2,9,5,6});
		boolean b1 = cuad1.verificarColumnas(cuad2, cuad3);
		boolean b2 = cuad2.verificarColumnas(cuad1, cuad3);
		boolean b3 = cuad3.verificarColumnas(cuad1, cuad2);
		assertTrue(b1 && b2 && b3);
	}
    
    @Test
	public void verificarColumnasEHorizontalesContraOtrosCuadrantes(){
		//horizontales
    	Cuadrante h1 = new Cuadrante(new int[]{
    			6,4,1,
    			5,9,7,
    			3,8,2});
		Cuadrante h2 = new Cuadrante(new int[]{
				9,5,2,
				8,3,6,
				4,7,1});
		Cuadrante [] hs = {h1, h2};
		//verticales
		Cuadrante v1 = new Cuadrante(new int[]{
				5,2,4,
				7,3,1,
				8,6,9});
		Cuadrante v2 = new Cuadrante(new int[]{
				4,9,7,
				2,1,3,
				6,8,5});
		//test
//		7    8    3    
//		4    2    1    
//		6    9    5  
		
//		3    7    8    
//		1    4    2    
//		9    5    6  
		
		Cuadrante test = new Cuadrante(new int[]{
				3,7,8,
				1,4,2,
				9,5,6});
		Cuadrante generado = Cuadrante.makeCuadranteHorizontalVerticalA(-1, hs, v1, v2);
		System.out.println(generado.toStringBidimensional());
		assertTrue(test.equals(generado));
		generado = Cuadrante.makeCuadranteHorizontalVerticalA(-1, hs, v1, v2);
		System.out.println(generado.toStringBidimensional());
//		assertTrue(test.equals(generado));
		generado = Cuadrante.makeCuadranteHorizontalVerticalA(-1, hs, v1, v2);
		System.out.println(generado.toStringBidimensional());
//		assertTrue(test.equals(generado));
		generado = Cuadrante.makeCuadranteHorizontalVerticalA(-1, hs, v1, v2);
		System.out.println(generado.toStringBidimensional());
//		assertTrue(test.equals(generado));
    }
    
}
