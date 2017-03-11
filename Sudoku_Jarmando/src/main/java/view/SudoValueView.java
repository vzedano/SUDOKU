package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import generator.Cuadrado;

/** @author Jarmando Corlaez */
public class SudoValueView {
	private Color previousColor = Color.BLACK;
	private JTextField txtField;//getter
	private JPanel panel;//getter
	private Cuadrado cuadrado;//getter
	private boolean pista = false;//setter
	
	private String previous;//interno
	private boolean editShownValue = true;//interno

	public SudoValueView(Cuadrado cuadrado) {
		this.cuadrado = cuadrado;
		txtField = new JTextField();
		txtField.setHorizontalAlignment(JTextField.CENTER);
		txtField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SudoValueView.this.txtField.getForeground().equals(Color.GREEN)){
					SudoValueView.this.txtField.setForeground(previousColor);
				}
				else {
					SudoValueView.this.txtField.setForeground(Color.GREEN);
				}
			}
		});
		//Cuando tecleas ese textField es Negro.
		txtField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				paintBlack();
			}
		});
		//Al salir de la caja o dar enter se producen las validaciones para que el valor se quede.
		//Si editShownValue es true entonces el valorMostrado del cuadrado se altera (y el previous)
		//Si pista es true se colorea azul o rojo el texto
		txtField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent fe) {
				verificarPersistirORollback();
			}
		});
		txtField.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				verificarPersistirORollback();
			}
		});
		//seteamos el texto
		txtField.setText(cuadrado.getValorMostrado());
		//Inicializamos previous
		previous = txtField.getText();
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,1,0,0));
		panel.add(txtField);
	}

	private void verificarPersistirORollback() {
		try {
			txtField.setText(txtField.getText().trim());//borrar whitespace
			if(editShownValue){//si queremos actualizar el valorMostrado (casi siempre salvo mostrarPista)
				SudoValueView.this.cuadrado.setValorMostrado(txtField.getText());
				previous = SudoValueView.this.cuadrado.getValorMostrado();
			}
			if(pista){
				colorHint();
			}
		} catch (Exception e) {//si hubiese error, rollback
			txtField.setText(previous);
		}
	}

	private void paintRed(){
		txtField.setForeground(Color.RED);
		previousColor = Color.RED;
	}
	
	private void paintBlack(){
		txtField.setForeground(Color.BLACK);
		previousColor = Color.BLACK;
	}
	
	private void paintBlue(){
		txtField.setForeground(Color.BLUE);
		previousColor = Color.BLUE;
	}
	
	public void showAnswer(){
		pista = false;
		paintBlack();
		editShownValue = false;
		txtField.setText(cuadrado.getValor());//pone el valor en la caja sin cambiarlo en el valorMostrado
		editShownValue = true;
	}
	
	public void showSolved(){
		pista = false;
		paintBlack();
		txtField.setText(cuadrado.getValorMostrado());
	}
	
	public void colorHint(){
		showSolved();
		pista = true;
		if(cuadrado.esCorrecto()) 
			paintBlue();
		else 
			paintRed();
	}
	
	public Cuadrado getCuadrado(){
		return cuadrado;
	}

	public JTextField getTxtField() {
		return txtField;
	}
	
	public JPanel getTxtPanel() {
		return panel;
	}
	
	public void setPista(boolean pista) {
		this.pista = pista;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				System.setProperty("sun.awt.noerasebackground", "true");
				JFrame frame = new JFrame();
				JTextField txt = new SudoValueView(new Cuadrado("7",0,0)).getTxtField();
				txt.setBounds(10, 10, 50, 50);
				frame.getContentPane().setLayout(null);
				frame.getContentPane().add(txt);
				frame.setSize(new Dimension(200, 200));
				frame.setLocation(400,200);
				frame.setVisible(true);
			}
		});
	}
	
}