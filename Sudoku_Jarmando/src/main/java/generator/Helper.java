package generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/** @author Jarmando Corlaez */
class Helper {
	private static final Random rand = new Random();
	private static final List<String> listaInmutable = Arrays.asList(new String[]{"1","2","3","4","5","6","7","8","9"});

	static List<String> newList1to9inclusive(){
		return  new ArrayList<String>(listaInmutable);
	}
	

	public static String getRandomAndRemove(List<String> list) {
		int index = Helper.aleatoryInt(list.size());
		String x = list.get(index);
		list.remove(index);
		return x;
	}

	static int aleatoryInt(int possibleValues){
		return (int) (rand.nextFloat()*possibleValues);
	}
	
	static String [] parseArray(int ... intArr){
		String [] stringArr = new String[intArr.length];
		for (int i = 0; i < intArr.length; i++)
			stringArr[i] = String.valueOf(intArr[i]);
		return stringArr;
	}

	static String [][] parseArray(int [][] intArr){
		String [][] stringArr = new String[intArr.length][intArr[0].length];
		for (int i = 0; i < intArr.length; i++)
			for (int j = 0; j < intArr[0].length; j++) 
				stringArr[i][j] = String.valueOf(intArr[i][j]);
		return stringArr;
	}
	
	
}
