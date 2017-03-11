package generator;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import view.SudoValueView;

/** @author Jarmando Corlaez */
public class SudokuModel {
	
	private SudoValueView[] arr;
	
	public SudokuModel() {
		SudokuGenerator sud = new SudokuGenerator();//Sudoku.generatePlayableSudoku(); getValueViewArray();
		arr = new SudoValueView[81];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new SudoValueView(sud.getCuadrado(i / 9, i % 9));
		}
		randomDeleteShownValues(20);
	}
	
	public SudokuModel(JPanel panel) {
		this();
		addModelToPanel(panel);
	}
	
	public void addModelToPanel(JPanel panel){
		for (int i = 0; i < arr.length; i++) {
			panel.add(arr[i].getTxtPanel(), null);
		}
		volver();
	}
	
	public void pista(){
		for (int i = 0; i < arr.length; i++)
			arr[i].colorHint();
	}
	
	public void volver(){
		for (int i = 0; i < arr.length; i++)
			arr[i].showSolved();
	}
	
	public void mostrarRespuestaYBloquear(){
		for (int i = 0; i < arr.length; i++) {
			arr[i].showAnswer();
			arr[i].getTxtField().setEditable(false);
		}
	}
	
	public void randomDeleteShownValues(int n){
		List<Cuadrado> cuads = new ArrayList<>();
		Cuadrado c;
		while (cuads.size() < n) {
			c = arr[Helper.aleatoryInt(81)].getCuadrado();
			if(!cuads.contains(c)){
				c.setValorMostrado("");
				cuads.add(c);
			}
			System.out.println("valor mostrado: "+c.getValorMostrado());
		}
	}
}
