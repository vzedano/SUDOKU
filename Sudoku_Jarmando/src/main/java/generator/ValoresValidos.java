package generator;

import java.util.List;

class ValoresValidos {
	/** Aumenta al sacar valor con removeAndGetRandom()*/
	public static int contadorDeNumerosGeneradosConValoresValidos;
	 
	private List<Integer> listaValoresValidos;
 
 
	{ 
		for (int i = 1; i < 10; i++) {
			listaValoresValidos.add(i);
		}
	}

	public boolean contains(int i) {
		return listaValoresValidos.contains(i);
	}
 
	public Integer removeAndGetRandom() {
		 int index = Helper.aleatoryInt(listaValoresValidos.size());
		 Integer valorRemovido = listaValoresValidos.get(index);
		 listaValoresValidos.remove(index);
		 contadorDeNumerosGeneradosConValoresValidos++;
		 return valorRemovido;
	}

}
