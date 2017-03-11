package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import generator.SudokuModel;

/** @author Jarmando Corlaez */
public class SudoViewer {
	
	private SudokuModel sudoku;

	private JFrame ventana;
	private JPanel contentPane;
	private JPanel central, left, right, header, south;
	
	private JLabel lblSudoku;
	private JPanel panelGrilla;//they vary
	private JButton btnRendirse, btnPista, btnNuevo;
	
	private static int x=100;
	private static int y=100;
	private static int w=404;
	private static int h=404;
	
	public SudoViewer() {
		//FRAME
		ventana = new JFrame("Sudoku");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setBounds(100, 100, 450, 300);

		//FRAME'S PANEL CONTENT PANE
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		ventana.setContentPane(contentPane);
		
		//MAIN CONTENT CONTENEDOR CENTRAL PARA QUE EL BOX LAYOUT HAGA RESPETAR EL MAXIMO Y EL MINIMO DEL INTERNO
		fillContent();
				
		//header, left, right
		header = new JPanel();
		header.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblSudoku = new JLabel("Sudoku");
		header.add(lblSudoku);
		left = new JPanel();
		right = new JPanel();
		
		//south
		south = new JPanel();
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				central.setVisible(false);
				fillContent();
				btnPista.setText("Pista");
				btnPista.setEnabled(true);
				btnRendirse.setEnabled(true);
				ventana.validate();
				ventana.repaint();
			}
		});
		south.add(btnNuevo);
		
		btnPista = new JButton("Pista");
		btnPista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPista.getText().equals("Pista")){
					System.out.println(btnPista.getText());
					sudoku.pista();
					btnPista.setText("Volver");
					return;
				}
				sudoku.volver();
				btnPista.setText("Pista");
			}
		});
		south.add(btnPista);

		btnRendirse = new JButton("Rendirse");
		btnRendirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sudoku.mostrarRespuestaYBloquear();
				btnPista.setEnabled(false);
				btnRendirse.setEnabled(false);
			}
		});
		south.add(btnRendirse);
		
		//assign to content pane
		contentPane.add(left, BorderLayout.LINE_START);
		contentPane.add(right, BorderLayout.LINE_END);
		contentPane.add(header, BorderLayout.NORTH);
		contentPane.add(south, BorderLayout.SOUTH);

	}
	
	public void fillContent(){
//		int[][] arrt = { http://www.tech2money.com/java-thread-tutorial/java-thread-life-cycle/
//		http://docs.oracle.com/javase/tutorial/deployment/jar/index.html
//			http://www.jtech.ua.es/j2ee/publico/lja-2012-13/wholesite.pdf
//				http://desarrollandoapp.blogspot.pe/2016/04/curso-de-javaserver-faces.html#comment-form
//				{ 8, 2, 7, 1, 5, 4, 3, 9, 6, }, { 9, 6, 5, 3, 2, 7, 1, 4, 8, }, { 3, 4, 1, 6, 8, 9, 7, 5, 2, },
//				{ 5, 9, 3, 4, 6, 8, 2, 7, 1, }, { 4, 7, 2, 5, 1, 3, 6, 8, 9, }, { 6, 1, 8, 9, 7, 2, 4, 3, 5, },
//				{ 7, 8, 6, 2, 3, 5, 9, 1, 4, }, { 1, 5, 4, 7, 9, 6, 8, 2, 3, }, { 2, 3, 9, 8, 4, 1, 5, 6, 7 } };
		central = new JPanel();
		central.setLayout(new BoxLayout(central, BoxLayout.Y_AXIS));
		
		panelGrilla = new JPanel(){
			private static final long serialVersionUID = 785375;

			private void drawHorizontalLines(Graphics g, int ... ys){
				int z = 0;
				for (int i = 0; i < ys.length; i++) {
//					g.drawLine(z, ys[i]-2, getWidth()-z-1, ys[i]-2);
					g.drawLine(z, ys[i]-1, getWidth()-z-1, ys[i]-1);
					g.drawLine(z, ys[i], getWidth()-z-1, ys[i]);
					g.drawLine(z, ys[i]+1, getWidth()-z-1, ys[i]+1);
//					g.drawLine(z, ys[i]+2, getWidth()-z-1, ys[i]+2);
				}
			}

			private void drawVerticalLines(Graphics g, int ... xs){
				int z = 0;
				for (int i = 0; i < xs.length; i++) {
//					g.drawLine(xs[i]-2, z, xs[i]-2, getHeight()-z-1);
					g.drawLine(xs[i]-1, z, xs[i]-1, getHeight()-z-1);
					g.drawLine(xs[i], z, xs[i], getHeight()-z-1);
					g.drawLine(xs[i]+1, z, xs[i]+1, getHeight()-z-1);
//					g.drawLine(xs[i]+2, z, xs[i]+2, getHeight()-z-1);
				}
			}
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				int height = getHeight()-1;
				int y1 = height/3;
				g.setColor(Color.BLACK);
				drawHorizontalLines(g, y1, y1*2, height-1, 0);
				drawVerticalLines(g, y1, y1*2, height-1, 0);
			}
		};
		panelGrilla.setLayout(new GridLayout(9, 9, 1, 1));
		panelGrilla.setPreferredSize(new Dimension(400,400));
		panelGrilla.setMinimumSize(new Dimension(200,200));
		panelGrilla.setMaximumSize(new Dimension(600,600));
		
		sudoku = new SudokuModel(panelGrilla);
		central.add(panelGrilla);
		contentPane.add(central, BorderLayout.CENTER);
	};
	
	
	public static void openGame(){
		long init = System.currentTimeMillis();
		JFrame.setDefaultLookAndFeelDecorated(true);
		System.setProperty("sun.awt.noerasebackground", "true");
		JFrame frame = new SudoViewer().ventana;
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int w = e.getComponent().getWidth();
				int h = e.getComponent().getHeight();
				if(w+65!=h){
					w = w>h? h:w;
					h = w>h? h+65:w+65;
					e.getComponent().setSize(new Dimension(w,h));
					e.getComponent().repaint();
				}
			}
		});
		frame.setSize(new Dimension(w,h));
		frame.setLocation(x, y);
		frame.pack();
		frame.setVisible(true);
		long end = System.currentTimeMillis();
		System.out.println("Programa abierto en "+(end-init)/1000f+" segundos (incluye generación de Sudoku)");
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				openGame();
			}
		});
	}
	
}