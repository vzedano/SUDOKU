package generator;

import java.util.ArrayList;
import java.util.List;

/** @author Jarmando Corlaez */
class SudokuGenerator {
	Cuadrante[][] cuadrantes = new Cuadrante[3][3];
	
	public SudokuGenerator(){
		System.out.print(".");
		long init = System.currentTimeMillis();
		List<Cuadrante> invalidos = new ArrayList<>();
		//aleatorio
		cuadrantes[0][0] = Cuadrante.makeCuadranteRandom(0);//llenado aleatorio del superior izquierdo
		//algoritmos tipo1
		cuadrantes[0][1] = Cuadrante.makeCuadranteHorizontalA(1, cuadrantes[0][0]);//1ra fila
		
		cuadrantes[0][2] = Cuadrante.makeCuadranteHorizontalA(2, cuadrantes[0][0],cuadrantes[0][1]);//1ra fila
		
		cuadrantes[1][0] = Cuadrante.makeCuadranteVerticalA(3, cuadrantes[0][0]);//1ra columna
		
		cuadrantes[2][0] = Cuadrante.makeCuadranteVerticalA(6, cuadrantes[0][0],cuadrantes[1][0]);//1ra columna
		//algoritmos tipo2
		Cuadrante [] cuadranteParamHorizontal;
		int countCuadrantesCreados = 9;
		int vecesSeguidasQueSeGeneroUnCentralInvalido = 0;
		
		while(true){
			cuadranteParamHorizontal = new Cuadrante [] {cuadrantes[1][0]};
			cuadrantes[1][1] = Cuadrante.makeCuadranteHorizontalVerticalA(4, cuadranteParamHorizontal, cuadrantes[0][1]);
			
			if(cuadrantes[1][1] == null){
//				System.out.println("Central null (1), crear 4 cuadrantes de nuevo");
				countCuadrantesCreados++;
				continue;
			}if(invalidos.contains(cuadrantes[1][1])){
//				System.out.println("Central invalido, crear 4 cuadrantes de nuevo");
				vecesSeguidasQueSeGeneroUnCentralInvalido++;
				countCuadrantesCreados++;
				if(vecesSeguidasQueSeGeneroUnCentralInvalido>5){
					System.out.println(invalidos.size()+" INVALIDOS. LIMPIANDO=========================================");
					invalidos.clear();
				}
				continue;
			}
			vecesSeguidasQueSeGeneroUnCentralInvalido = 0;
			cuadranteParamHorizontal = new Cuadrante [] {cuadrantes[2][0]};
			cuadrantes[2][1] = Cuadrante.makeCuadranteHorizontalVerticalA(7, cuadranteParamHorizontal, cuadrantes[0][1], cuadrantes[1][1]);
			if(cuadrantes[2][1]==null){
				countCuadrantesCreados++;
//				System.out.println("Inferior null (2), crear 4 cuadrantes de nuevo");
//				System.out.println("INVALIDADO:");
//				System.out.println(cuadrantes[1][1].toStringBidimensional());
				if(!invalidos.contains(cuadrantes[1][1])){
					invalidos.add(cuadrantes[1][1]);
				}
				continue;
			}
			
			cuadranteParamHorizontal = new Cuadrante [] {cuadrantes[1][0],cuadrantes[1][1]};
			cuadrantes[1][2] = Cuadrante.makeCuadranteHorizontalVerticalA(5, cuadranteParamHorizontal, cuadrantes[0][2]);
			if(cuadrantes[1][2]==null){
				countCuadrantesCreados++;
//				System.out.println("Central-derecho null (3), crear 4 cuadrantes de nuevo");
//				System.out.println("INVALIDADO:");
//				System.out.println(cuadrantes[1][1].toStringBidimensional());
				if(!invalidos.contains(cuadrantes[1][1])){
					invalidos.add(cuadrantes[1][1]);
				}
				continue;
			}

			cuadranteParamHorizontal = new Cuadrante [] {cuadrantes[2][0],cuadrantes[2][1]};
			cuadrantes[2][2] = Cuadrante.makeCuadranteHorizontalVerticalA(8, cuadranteParamHorizontal, cuadrantes[0][2], cuadrantes[1][2]);
			if(cuadrantes[2][2]==null){
				countCuadrantesCreados++;
//				System.out.println("Inferior-derecho null (4), crear 4 cuadrantes de nuevo");
				continue;
			}
			break;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cuadrantes[0][1].setCuadranteNumero(i*3+j);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(invalidos.size()+" invalidos y "+countCuadrantesCreados+" cuadrantes Creados en "+(end-init)/1000f);

		System.out.print(".");
	}
	//Legacy
	public SudokuGenerator(int [][] valores){//cada fila es una fila, 9 filas en total. (ejes x y)
		String [][] valoresS = Helper.parseArray(valores);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Cuadrante cuad = Cuadrante.makeCuadranteVacio(i*3+j);
				for (int i2 = 0; i2 < 3; i2++) {
					for (int j2 = 0; j2 < 3; j2++) {
						cuad.setValue(i2*3+j2, valoresS[i*3+i2][j*3+j2]);
					}//esto es transformado a 9 cuadrantes con ejes coordenados x y
				}
				cuadrantes[i][j] = cuad;
			}
		}
//		if(!this.esValido())
//			throw new InvalidParameterException("Valores del Sudoku inválidos detectados.");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < 9; i++) {
			sb.append("Cuadrante "+(i/3)+","+(i%3)+"\n");
			sb.append(getStringCuadrante(i)+"\n");
		}
		return sb.toString();
	}
	
	public boolean esValido(){
		for (int i = 0; i < 3; i++) {//comprobar validez dentro de los cuadrados
			for (int j = 0; j < 3; j++) {
				if(!cuadrantes[i][j].esValido()) return false;
			}
		}
		for (int n = 0; n < 9; n++) {//comprobar validez de las columnas y filas
			if(!comprobarFila(n)) return false;	
			if(!comprobarColumna(n)) return false;	
		}
		return true;
	}

	private boolean comprobarFila(int i){
		int indexFilaDeSudoku = i/3;	//De 0-9 lo traduce a 0-2
		int indexFilaDeCuadrante = i%3;	//De 0-9 lo traduce a 0-2
		//Ejemplo: fila 8: indexFilaDeSudoku:2 indexFilaDeCuadrante: 2 (3*2+2 = 8)
		
		Cuadrante[] filaDeSudoku = cuadrantes[indexFilaDeSudoku];//Cuadrantes Involucrados
		List<String> list = Helper.newList1to9inclusive();
		for (int j = 0; j < 3; j++) {
			boolean boolParcial = filaDeSudoku[j].verificarFila(indexFilaDeCuadrante, list);
			if (!boolParcial) return false;	
		}
		return true;
	}

	private boolean comprobarColumna(int j){
		int indexColumnaDeSudoku = j/3;	//De 0-9 lo traduce a 0-2
		int indexColumnaDeCuadrante = j%3;	//De 0-9 lo traduce a 0-2
		//Ejemplo: Columna 8: indexColumnaDeSudoku:2 indexColumnaDeCuadrante: 2 (3*2+2 = 8)
		
		Cuadrante[] columnaDeSudoku = new Cuadrante[3];//Cuadrantes Involucrados
		columnaDeSudoku[0] = cuadrantes[0][indexColumnaDeSudoku];
		columnaDeSudoku[1] = cuadrantes[1][indexColumnaDeSudoku];
		columnaDeSudoku[2] = cuadrantes[2][indexColumnaDeSudoku];

		List<String> list = Helper.newList1to9inclusive();
		for (int i = 0; i < 3; i++) {
			boolean boolParcial = columnaDeSudoku[i].verificarColumna(indexColumnaDeCuadrante, list);
			if (!boolParcial) return false;	
		}
		return true;
	}
	
	public String getStringCuadrante(int n){
		int i = n/3;	//De 0-9 lo traduce a 0-2
		int j = n%3;	
		return cuadrantes[i][j].toStringBidimensional();
	}

	public Cuadrado getCuadrado(int x, int y){
		int sudx = x/3;
		int cuax = x%3;
		int sudy = y/3;
		int cuay = y%3;
		return  (cuadrantes[sudx][sudy].cuadrados[cuax][cuay] == null)? null : cuadrantes[sudx][sudy].cuadrados[cuax][cuay];
	}
	
//	public void set(int x, int y, String valorMostrado){
//		int sudx = x/3;
//		int cuax = x%3;
//		int sudy = y/3;
//		int cuay = y%3;
//		cuadrantes[sudx][sudy].cuadrados[cuax][cuay].setValorMostrado(valorMostrado);
//	}//Future? Here or new class?
	
	public static void main(String[] args) {
		System.out.println(new SudokuGenerator().esValido());
	}
}
