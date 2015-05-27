package pl.edu.pw.fizyka.pojava.atombomb;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
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



public class Projekt {

	JFrame frame;
	private JTextField textField;
	private JTextField txtWybrJednostkiE;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	Chart wykres;
	Simulation1 sym;

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
		
		sym = new Simulation1(1, 0.2, 0.0000005, 1, 0.75);
		wykres=new Chart(sym);
		
		
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JTextArea txtrIleWglaTrzeba = new JTextArea();
		txtrIleWglaTrzeba.setBackground(SystemColor.control);
		txtrIleWglaTrzeba.setEditable(false);
		txtrIleWglaTrzeba.setFont(new Font("Calibri", Font.BOLD, 13));
		txtrIleWglaTrzeba.setText("Ile wêgla trzeba spaliæ, by uzyskaæ tyle samo energii?");
		
		textField = new JTextField();
		textField.setText("");
		textField.setColumns(10);
		
		JTextArea txtrKg = new JTextArea();
		txtrKg.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtrKg.setText("kg");
		
		JButton btnSprawd = new JButton("SprawdŸ!");
		
		JButton btnStart = new JButton("Start");
		
		ActionListener startListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				wykres.clear();
				sym.refresh(Double.parseDouble(textField_1.getText()), Double.parseDouble(textField_2.getText()), Double.parseDouble(textField_3.getText()), Integer.parseInt(textField_4.getText()), Double.parseDouble(textField_5.getText()));
				wykres.start();
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
		
		JButton btnPauza = new JButton("Pauza");
		
		JButton btnCofnij = new JButton("Cofnij");
		
		txtWybrJednostkiE = new JTextField();
		txtWybrJednostkiE.setBackground(SystemColor.control);
		txtWybrJednostkiE.setFont(new Font("Calibri", Font.BOLD, 13));
		txtWybrJednostkiE.setEditable(false);
		txtWybrJednostkiE.setText("Wybór jednostki E:");
		txtWybrJednostkiE.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("MeV");
		rdbtnNewRadioButton.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("J");
		rdbtnNewRadioButton_1.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		JTextArea txtrEnergiaPojedynczegoRozpadu = new JTextArea();
		txtrEnergiaPojedynczegoRozpadu.setBackground(SystemColor.menu);
		txtrEnergiaPojedynczegoRozpadu.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrEnergiaPojedynczegoRozpadu.setEditable(false);
		txtrEnergiaPojedynczegoRozpadu.setText("energia pojedynczego rozpadu:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText("1");
		
		JTextArea txtrPrzekrojCzynny = new JTextArea();
		txtrPrzekrojCzynny.setBackground(SystemColor.menu);
		txtrPrzekrojCzynny.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrPrzekrojCzynny.setEditable(false);
		txtrPrzekrojCzynny.setText("przekrój czynny");
		
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setText("0.2");
		
		JTextArea txtrPrawdopodobienstwoNaturalnegoRozpadu = new JTextArea();
		txtrPrawdopodobienstwoNaturalnegoRozpadu.setBackground(SystemColor.menu);
		txtrPrawdopodobienstwoNaturalnegoRozpadu.setEditable(false);
		txtrPrawdopodobienstwoNaturalnegoRozpadu.setText("prawdop. naturalnego rozpadu");
		txtrPrawdopodobienstwoNaturalnegoRozpadu.setFont(new Font("Calibri", Font.PLAIN, 12));
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setText("0.0000005");
		
		JTextArea txtrMasaProbki = new JTextArea();
		txtrMasaProbki.setBackground(SystemColor.menu);
		txtrMasaProbki.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrMasaProbki.setEditable(false);
		txtrMasaProbki.setText("masa próbki:");
		
		JTextArea txtrOdlegloscMiedzyAtomami = new JTextArea();
		txtrOdlegloscMiedzyAtomami.setBackground(SystemColor.menu);
		txtrOdlegloscMiedzyAtomami.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrOdlegloscMiedzyAtomami.setEditable(false);
		txtrOdlegloscMiedzyAtomami.setText("odleg\u0142o\u015B\u0107 mi\u0119dzy atomami:");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setText("500000");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setText("1");
		
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
									.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
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
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtrPrawdopodobienstwoNaturalnegoRozpadu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtrMasaProbki, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
							.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtrOdlegloscMiedzyAtomami, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnNewRadioButton)
							.addGap(22)
							.addComponent(rdbtnNewRadioButton_1))
						.addComponent(txtWybrJednostkiE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
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
						.addComponent(txtWybrJednostkiE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSprawd)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtrKg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnNewRadioButton_1)
						.addComponent(rdbtnNewRadioButton))
					.addGap(17))
		);
		
		JTextArea txtrNastawy = new JTextArea();
		txtrNastawy.setFont(new Font("Calibri", Font.BOLD, 14));
		txtrNastawy.setEditable(false);
		txtrNastawy.setText("Nastawy");
		panel.add(txtrNastawy);
		
		tabbedPane.addTab("E(t)", null, wykres, null);
		tabbedPane.setEnabledAt(0, true);
		
		//JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		//tabbedPane.addTab("E(m)", null, tabbedPane_2, null);
		frame.getContentPane().setLayout(groupLayout);
	}
}
