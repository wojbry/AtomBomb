package pl.edu.pw.fizyka.pojava.atombomb;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;


/*
 * Author: Joanna
 */
public class Projekt {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	JButton btnPauza;
	Chart wykres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Projekt window = new Projekt();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Projekt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		wykres=new Chart(new Simulation(1, 0.4, 0.0000001, 4, 2.736)); 
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Zapisz");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmDrukuj = new JMenuItem("Drukuj");
		mnNewMenu.add(mntmDrukuj);
		
		JMenuItem mntmWybrJzyka = new JMenuItem("Wybor jezyka");
		mnNewMenu.add(mntmWybrJzyka);
		
		JPanel panelWykres = new JPanel();
		panelWykres.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JTextArea txtrIleWglaTrzeba = new JTextArea();
		txtrIleWglaTrzeba.setBackground(SystemColor.control);
		txtrIleWglaTrzeba.setEditable(false);
		txtrIleWglaTrzeba.setFont(new Font("Calibri", Font.BOLD, 13));
		txtrIleWglaTrzeba.setText("Ile w�gla trzeba spali�, by uzyska� tyle samo energii?");
		
		textField = new JTextField();
		textField.setText("");
		textField.setColumns(10);
		
		JTextArea txtrKg = new JTextArea();
		txtrKg.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtrKg.setText("kg");
		
		JButton btnSprawd = new JButton("Sprawd�!");
		
		ActionListener sprawdzListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				double masa = wykres.sym.totalEnergy*10E6*1.602*10E-19/20000000;
				textField.setText(Double.toString(masa));
			}
		};
		
		btnSprawd.addActionListener(sprawdzListener);
		
		JButton btnStart = new JButton("Start");
		
		ActionListener startListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				wykres.stop();
				try{
					wykres.clear();
					wykres.sym.refresh(Double.parseDouble(textField_1.getText()), Double.parseDouble(textField_2.getText()), Double.parseDouble(textField_3.getText()), Double.parseDouble(textField_4.getText()), Double.parseDouble(textField_5.getText()));
					wykres.start();
				}   catch(NumberFormatException a){
					JOptionPane.showMessageDialog(frame, "Z�y format parametru!", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
		
		btnStart.addActionListener(startListener);
		
		JButton btnStop = new JButton("Stop");
		
		ActionListener stopListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				wykres.stop();
			}
		};
		
		btnStop.addActionListener(stopListener);
		
		btnPauza = new JButton("||");
		
		ActionListener PauzaListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (btnPauza.getText().equals("||")){
					wykres.stop();
					btnPauza.setText("|>");
				}
				else{
					btnPauza.setText("||");
					wykres.start();
				}
			}
		};
		
		btnPauza.addActionListener(PauzaListener);
		
		JButton btnCofnij = new JButton("Ustaw dane dla urawnu 235");
		
		ActionListener UstawDaneListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textField_1.setText("172");
				textField_2.setText("0.4");
				textField_3.setText("0.0000001");
				textField_4.setText("4");
				textField_5.setText("2.7363");
				JOptionPane.showMessageDialog(frame, "Ze wzgl�du na ograniczenie masy (masa krytyczna urznu 235 to oko�o 52 kg) znacz�co zosta� zwi�kszony przekr�j czynny atomu.", null, JOptionPane.INFORMATION_MESSAGE);
			}
		};
		
		btnCofnij.addActionListener(UstawDaneListener);
		
		
		
		
		JTextArea txtrEnergiaPojedynczegoRozpadu = new JTextArea();
		txtrEnergiaPojedynczegoRozpadu.setBackground(SystemColor.menu);
		txtrEnergiaPojedynczegoRozpadu.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrEnergiaPojedynczegoRozpadu.setEditable(false);
		txtrEnergiaPojedynczegoRozpadu.setText("energia pojedynczego rozpadu [MeV]:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText("172");
		
		JTextArea txtrPrzekrojCzynny = new JTextArea();
		txtrPrzekrojCzynny.setBackground(SystemColor.menu);
		txtrPrzekrojCzynny.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrPrzekrojCzynny.setEditable(false);
		txtrPrzekrojCzynny.setText("promie� przekroju czynnego [Angstrem]:");
		
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setText("0.4"); //Angstrem
		
		JTextArea txtrPrawdopodobienstwoNaturalnegoRozpadu = new JTextArea();
		txtrPrawdopodobienstwoNaturalnegoRozpadu.setBackground(SystemColor.menu);
		txtrPrawdopodobienstwoNaturalnegoRozpadu.setEditable(false);
		txtrPrawdopodobienstwoNaturalnegoRozpadu.setText("prawdop. naturalnego rozpadu:");
		txtrPrawdopodobienstwoNaturalnegoRozpadu.setFont(new Font("Calibri", Font.PLAIN, 12));
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setText("0.0000001");
		
		JTextArea txtrMasaProbki = new JTextArea();
		txtrMasaProbki.setBackground(SystemColor.menu);
		txtrMasaProbki.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrMasaProbki.setEditable(false);
		txtrMasaProbki.setText("masa pr�bki [gram*10^-16] (max. 10)]:");
		
		JTextArea txtrOdlegloscMiedzyAtomami = new JTextArea();
		txtrOdlegloscMiedzyAtomami.setBackground(SystemColor.menu);
		txtrOdlegloscMiedzyAtomami.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrOdlegloscMiedzyAtomami.setEditable(false);
		txtrOdlegloscMiedzyAtomami.setText("odleg\u0142o\u015B\u0107 mi\u0119dzy atomami [Angstrem]:");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setText("4");  //gram*10^-16
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setText("2.7363");  //Angstrem
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnStart)
										.addGap(18)
										.addComponent(btnStop)
										.addGap(18)
										.addComponent(btnPauza)
										.addGap(18)
										.addComponent(btnCofnij))
									.addComponent(panelWykres, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
								.addGap(49))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(txtrIleWglaTrzeba, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
								.addGap(79)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSprawd)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtrKg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(191)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtrEnergiaPojedynczegoRozpadu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtrPrzekrojCzynny, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtrPrawdopodobienstwoNaturalnegoRozpadu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtrMasaProbki, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
							.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtrOdlegloscMiedzyAtomami, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							//.addComponent(rdbtnNewRadioButton)
							.addGap(22)
							)
						)
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelWykres, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtrEnergiaPojedynczegoRozpadu, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtrPrzekrojCzynny, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtrPrawdopodobienstwoNaturalnegoRozpadu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtrMasaProbki, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtrOdlegloscMiedzyAtomami, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStart)
						.addComponent(btnStop)
						.addComponent(btnPauza)
						.addComponent(btnCofnij))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtrIleWglaTrzeba, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						//.addComponent(txtWybrJednostkiE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						)
					//.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSprawd)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtrKg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						//.addComponent(rdbtnNewRadioButton_1)
						//.addComponent(rdbtnNewRadioButton))
						)
					.addGap(17))
		);
		
		JTextArea txtrNastawy = new JTextArea();
		txtrNastawy.setFont(new Font("Calibri", Font.BOLD, 14));
		txtrNastawy.setEditable(false);
		txtrNastawy.setText("Nastawy");
		panel.add(txtrNastawy);
		
		panelWykres.add(wykres, BorderLayout.CENTER);
		
		
		frame.getContentPane().setLayout(groupLayout);
	}
}
