import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.TableView.TableCell;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.Vector;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.File;
import java.io.IOException;


public class Oberflaeche extends JFrame {

	public Steuerung strg;
	private JTable tableSpeicher;
	private JPanel contentPane;
	private JTable tableDatei;
	private DefaultTableModel Modell;
	private String[] TitelSpeicher;
	private String[] TitelPortsRC;
	private String[] TitelStatusregister;
	private String[] TitelInterrupt;
	private int aktuelleZeile = 0;
	private DefaultTableModel tabModel;
	private JScrollPane scrollpaneSpeicher;
	private JScrollPane scrollpaneText;
	private JTable tablePortsRA;
	private JTable tablePortsRB;
	private JTable tablePortsRC;
	private JTable tableStatusregister;
	private JTable tableInterrupt;
	private String inhaltZelle;
	private int reihe;
	private int spalte;
	private JLabel lblWertWregister;
	private  JLabel lblWertPC;
	private JLabel lblWertFSR;

	/**
	 * Create the frame.
	 */
	public Oberflaeche(Steuerung strg) {
		this.strg = strg;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1300, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		####################################################################################################################		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnHilfe = new JMenu("Hilfe");
		menuBar.add(mnHilfe);
		
		JMenuItem mnItemDoku = new JMenuItem("Dokumentation anzeigen");
		mnItemDoku.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	try {
					File pdfFile = new File("Dokumentation/Dokumentation.pdf");
					if (pdfFile.exists()) {
						if (Desktop.isDesktopSupported()) {
							Desktop.getDesktop().open(pdfFile);
						} else {
							System.out.println("Awt Desktop is not supported!");
						}
					} else {
						System.out.println("File is not exists!");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		});
		mnHilfe.add(mnItemDoku);

		
		// ########################## Buttons ##############################################
		JPanel panelButtons = new JPanel();
		panelButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panelButtons.setBounds(591, 11, 683, 50);
		contentPane.add(panelButtons);
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		
		JButton btnEinlesen = new JButton("Datei einlesen");
		btnEinlesen.setPreferredSize(new Dimension(130, 30));
		btnEinlesen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Oberflaeche.this.strg.oeffneDatei();
			}
		});
		panelButtons.add(btnEinlesen);
		
		JButton btnAusfuehren = new JButton("Ausf\u00FChren");
		btnAusfuehren.setPreferredSize(new Dimension(130, 30));
		btnAusfuehren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Oberflaeche.this.strg.startTimer();
			}
		});
		panelButtons.add(btnAusfuehren);
		
		JButton btnNaechsterSchritt = new JButton("N\u00E4chster Schritt");
		btnNaechsterSchritt.setPreferredSize(new Dimension(130, 30));
		btnNaechsterSchritt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			Oberflaeche.this.strg.nextStep();
			Oberflaeche.this.strg.setMarkierungtxt();
			Oberflaeche.this.strg.refreshRegister();
			}
		});
		panelButtons.add(btnNaechsterSchritt);
		
		JButton btnPause = new JButton("Pause");
		btnPause.setPreferredSize(new Dimension(130, 30));
		btnPause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			Oberflaeche.this.strg.stopTimer();
			}
		});
		panelButtons.add(btnPause);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setPreferredSize(new Dimension(130, 30));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Oberflaeche.this.strg.reset();
			}
		});
		panelButtons.add(btnReset);
		
		
		// ########################## Textdatei ##############################################
		String[][] text = new String[][]{{""}};
		String [] tabellenkopf = new String[]{"Programmcode"};
		Modell = new DefaultTableModel(text, tabellenkopf);
		tableDatei = new JTable( Modell );
		tableDatei.setEnabled(false);
		scrollpaneText = new JScrollPane(tableDatei);
		scrollpaneText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scrollpaneText.setBounds(10, 11, 571, 622);
		contentPane.add(scrollpaneText);
		
		
		
		
		
		
		
		// ########################## Ports ##############################################
		
	      
	      String[][] DatenPortsRA = {
				    { "Tris", "0", "0", "0", "1", "1", "1", "1", "1" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
	      };
	      String[] TitelPortsRA = {"RA", "7", "6", "5", "4", "3", "2", "1", "0"};
	      DefaultTableModel tabModelRA = new DefaultTableModel(DatenPortsRA, TitelPortsRA);
	      tablePortsRA = new JTable (tabModelRA){
	    	  public boolean isCellEditable(int x, int y) {
	                return false;
	            }
	      };
	      tablePortsRA.setBackground(new Color(240, 240, 240));
	      tablePortsRA.getTableHeader().setReorderingAllowed(false);
	      tablePortsRA.addMouseListener(new MouseAdapter() {
	    	  public void mouseClicked(MouseEvent e){
	    		  reihe = Oberflaeche.this.tablePortsRA.getSelectedRow();
	    		  spalte = Oberflaeche.this.tablePortsRA.getSelectedColumn();
	    		  inhaltZelle = (String) tablePortsRA.getValueAt(reihe, spalte);
	    		  System.out.println("RA - Reihe: " + reihe + " - Spalte: " + spalte);
	    		  if (reihe == 1){
	    			  String tris = (String) tablePortsRA.getValueAt(reihe-1, spalte);
	    			  if (inhaltZelle.equals("0"))
	    			  {
	    				  if(tris.equals("1")){
	    					  tablePortsRA.setValueAt("1", reihe, spalte);
	    				  }
	    			  }
	    			  else
	    			  {
	    				  if(tris.equals("1")){
	    					  tablePortsRA.setValueAt("0", reihe, spalte);
	    				  }
	    			  }
	    			  Oberflaeche.this.strg.portATMR0(Integer.valueOf((String) tablePortsRA.getValueAt(reihe, spalte)), Integer.valueOf(inhaltZelle));
	    			  Oberflaeche.this.strg.editPort("A", spalte, (String) tablePortsRA.getValueAt(reihe, spalte));
	    		  }
	    	  }
	      });
	      TableColumnModel columnModelRA = tablePortsRA.getColumnModel();
	      columnModelRA.getColumn( 0 ).setPreferredWidth( 200 );
	      JScrollPane scrollpaneRA = new JScrollPane(tablePortsRA);
	      scrollpaneRA.setBounds(10, 10, 255, 55);
	      
	      
	      
	      
	      String[][] DatenPortsRB = {
				    { "Tris", "1", "1", "1", "1", "1", "1", "1", "1" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
	      };
	      String[] TitelPortsRB = {"RB", "7", "6", "5", "4", "3", "2", "1", "0"};
	      DefaultTableModel tabModelRB = new DefaultTableModel(DatenPortsRB, TitelPortsRB);
	      tablePortsRB = new JTable (tabModelRB){
	    	  public boolean isCellEditable(int x, int y) {
	                return false;
	            }
	      };
	      tablePortsRB.setBackground(new Color(240, 240, 240));
	      tablePortsRB.getTableHeader().setReorderingAllowed(false);
	      tablePortsRB.addMouseListener(new MouseAdapter() {
	    	  public void mouseClicked(MouseEvent e){
	    		  reihe = Oberflaeche.this.tablePortsRB.getSelectedRow();
	    		  spalte = Oberflaeche.this.tablePortsRB.getSelectedColumn();
	    		  inhaltZelle = (String) Oberflaeche.this.tablePortsRB.getValueAt(reihe, spalte);
	    		  System.out.println("RB - Reihe: " + reihe + " - Spalte: " + spalte);
	    		  //prüft ob Port geklickt wurde
	    		  if (reihe == 1){
	    			  String tris = (String) tablePortsRB.getValueAt(reihe-1, spalte);
	    			  if (inhaltZelle.equals("0"))
	    			  {
	    				  //prüft ob Tris gesetzt ist
	    				  if(tris.equals("1")){
	    					  tablePortsRB.setValueAt("1", reihe, spalte);
	    				  }
	    			  }
	    			  else
	    			  {
	    				  //prüft ob Tris gesetzt ist
	    				  if(tris.equals("0")){
	    					  tablePortsRB.setValueAt("1", reihe, spalte);
	    				  }
	    			  }
	    			  Oberflaeche.this.strg.portBInterrupt(Integer.valueOf((String) tablePortsRB.getValueAt(reihe, spalte)), Integer.valueOf(inhaltZelle));
	    			  Oberflaeche.this.strg.editPort("B", spalte,(String) tablePortsRB.getValueAt(reihe, spalte));
	    		  }
	    	  }
	      });
	      TableColumnModel columnModelRB = tablePortsRB.getColumnModel();
	      columnModelRB.getColumn( 0 ).setPreferredWidth( 200 );
	      JScrollPane scrollpaneRB = new JScrollPane(tablePortsRB);
	      scrollpaneRB.setBounds(10, 75, 255, 55);
	      
	      
	      
	      String[][] DatenPortsRC = {
				    { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
	      };
	      TitelPortsRC = new String[]{"RC", "7", "6", "5", "4", "3", "2", "1", "0"};
	      DefaultTableModel tabModelRC = new DefaultTableModel(DatenPortsRC, TitelPortsRC);
	      tablePortsRC = new JTable (tabModelRC){
	    	  public boolean isCellEditable(int x, int y) {
	                return false;
	            }
	      };
	      tablePortsRC.setBackground(new Color(240, 240, 240));
	      tablePortsRC.getTableHeader().setReorderingAllowed(false);
	      tablePortsRC.addMouseListener(new MouseAdapter() {
	    	  public void mouseClicked(MouseEvent e){
	    		  reihe = Oberflaeche.this.tablePortsRC.getSelectedRow();
	    		  spalte = Oberflaeche.this.tablePortsRC.getSelectedColumn();
	    		  inhaltZelle = (String) Oberflaeche.this.tablePortsRC.getValueAt(reihe, spalte);
	    		  System.out.println("RC - Reihe: " + reihe + " - Spalte: " + spalte);
	    		  if (reihe == 1){
	    			  if (inhaltZelle == "0")
	    			  {
	    				  tablePortsRC.setValueAt("1", reihe, spalte);
	    			  }
	    			  else
	    			  {
	    				  tablePortsRC.setValueAt("0", reihe, spalte);
	    			  }
	    			  Oberflaeche.this.strg.editPort("C", spalte,(String) tablePortsRC.getValueAt(reihe, spalte));
	    		  }
	    	  }
	      });
	      TableColumnModel columnModelRC = tablePortsRC.getColumnModel();
	      columnModelRC.getColumn( 0 ).setPreferredWidth( 200 );
	      JScrollPane scrollpaneRC = new JScrollPane(tablePortsRC);
	      scrollpaneRC.setBounds(10, 140, 255, 55);
	      
	      
	      JPanel panelPorts = new JPanel();
	      panelPorts.setBounds(601, 72, 275, 205);
	      panelPorts.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	      panelPorts.setLayout(null);
	      panelPorts.add(scrollpaneRA);
	      panelPorts.add(scrollpaneRB);
	      panelPorts.add(scrollpaneRC);
	      contentPane.add(panelPorts);
	      
	      
// ########################## Speicher ##############################################
	      TitelSpeicher = new String[]{" ", "00", "01", "02", "03", "04", "05", "06", "07"};
	      tabModel = new DefaultTableModel(strg.getRegisterArray(), TitelSpeicher);
	      tableSpeicher = new JTable (tabModel);
	      tableSpeicher.setBackground(new Color(240, 240, 240));
	      tableSpeicher.setBounds(710, 270, 525, 16);
	      tableSpeicher.setEnabled(false);
	      tableSpeicher.getTableHeader().setReorderingAllowed(false);
	      scrollpaneSpeicher = new JScrollPane(tableSpeicher);
	      scrollpaneSpeicher.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		  scrollpaneSpeicher.setBounds(972, 72, 302, 534);
		  contentPane.add(scrollpaneSpeicher);
		  
		  
// ########################## Spezialfunktionsregister ##############################################

		  
		  JLabel lblWregister = new JLabel("W-Register:");
		  lblWregister.setBounds(10, 10, 100, 14);
		  
		  lblWertWregister = new JLabel(Integer.toHexString(strg.getW()));
		  lblWertWregister.setBounds(120, 10, 50, 14);
		  

		  JLabel lblPc = new JLabel("PC:");
		  lblPc.setBounds(10, 30, 100, 14);
		  		  
		  lblWertPC = new JLabel(Integer.toHexString(strg.getProgramcounter()));
		  lblWertPC.setBounds(120, 30, 50, 14);
		  
		  JLabel lblFSR = new JLabel("FSR:");
		  lblFSR.setBounds(10, 50, 100, 14);
		  		  
		  lblWertFSR = new JLabel(Integer.toHexString(strg.getRegisterClass().getWertOhneBank(0x04)));
		  lblWertFSR.setBounds(120, 50, 50, 14);
		  
		  JLabel lblStatus = new JLabel("Status");
		  lblStatus.setBounds(10, 80, 100, 14);
		  
		  JLabel lblInterrupt = new JLabel("Interrupt");
		  lblInterrupt.setBounds(10, 155, 100, 14);
		  //Statusregister
		  String[][] DatenStatusregister = {{"0", "0", "0", "1", "1", "0", "0", "0"}};
	      TitelStatusregister = new String[]{"IRP", "RP1", "RP0", "TO", "PD", "Z", "DC", "C"};
	      DefaultTableModel tabModelStatusregister = new DefaultTableModel(DatenStatusregister, TitelStatusregister);
	      tableStatusregister = new JTable (tabModelStatusregister);
	      tableStatusregister.setBackground(new Color(240, 240, 240));
	      tableStatusregister.setEnabled(false);
	      tableStatusregister.getTableHeader().setReorderingAllowed(false);
	      tableStatusregister.addMouseListener(new MouseAdapter() {
	    	  public void mouseClicked(MouseEvent e){
	    		  System.out.println("Spalte: " + Oberflaeche.this.tableStatusregister.getSelectedColumn());
	    	  }
	      });
	      JScrollPane scrollpaneStatusregister = new JScrollPane(tableStatusregister);
	      scrollpaneStatusregister.setBounds(10, 100, 255, 39);
	      
	      
	      //Interrupts
	      String[][] DatenInterrupt = {{"0", "0", "0", "0", "0", "0", "0", "0"}};
	      TitelInterrupt = new String[]{"GIE", "PIE", "T0IE", "INTE", "RBIE", "T0IF", "INTF", "RBIF"};
	      DefaultTableModel tabModelInterrupt = new DefaultTableModel(DatenInterrupt, TitelInterrupt);
	      tableInterrupt = new JTable (tabModelInterrupt);
	      tableInterrupt.setEnabled(false);
	      tableInterrupt.setBackground(new Color(240, 240, 240));
	      tableInterrupt.getTableHeader().setReorderingAllowed(false);
	      tableInterrupt.addMouseListener(new MouseAdapter() {
	    	  public void mouseClicked(MouseEvent e){
	    		  System.out.println("Spalte: " + Oberflaeche.this.tableInterrupt.getSelectedColumn());
	    	  }
	      });
	      JScrollPane scrollpaneInterrupt = new JScrollPane(tableInterrupt);
	      scrollpaneInterrupt.setBounds(10, 175, 255, 39);
		  
	      
	      //panelSpezialfunktionsregister
		  JPanel panelSpezialfunktionsregister = new JPanel();
		  panelSpezialfunktionsregister.setBounds(601, 304, 275, 302);
		  panelSpezialfunktionsregister.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		  panelSpezialfunktionsregister.setLayout(null);
		  panelSpezialfunktionsregister.add(lblWregister);
		  panelSpezialfunktionsregister.add(lblWertWregister);
		  panelSpezialfunktionsregister.add(lblPc);
		  panelSpezialfunktionsregister.add(lblWertPC);
		  panelSpezialfunktionsregister.add(lblFSR);
		  panelSpezialfunktionsregister.add(lblWertFSR);
		  panelSpezialfunktionsregister.add(lblStatus);
		  panelSpezialfunktionsregister.add(scrollpaneStatusregister);
		  panelSpezialfunktionsregister.add(lblInterrupt);
		  panelSpezialfunktionsregister.add(scrollpaneInterrupt);
		  contentPane.add(panelSpezialfunktionsregister);
	      this.setVisible(true);
	}
	
	
	

	/**
	 * Erneuert sämtliche Speicher und Register
	 * @param speicher
	 */
	public void refreshSpeicher(String[][] speicher){
		int anzahlZeilenInTabelle = tableSpeicher.getRowCount();
		for (int i=0; i < anzahlZeilenInTabelle; i++)
		{
			tabModel.removeRow(0);
		}
		for (int i = 0; i < 32; i++)
		{
			Object[] zeile = new Object[9];
			for (int j = 0; j < 9; j++)
			{
				zeile[j] = speicher[i][j];
			}
			tabModel.addRow(zeile);
		}
		lblWertWregister.setText(Integer.toHexString(strg.getW()));
		lblWertPC.setText(Integer.toHexString(strg.getProgramcounter()));
		lblWertFSR.setText(Integer.toHexString(strg.getRegisterClass().getWertOhneBank(0x04)));
		int valPortA = strg.getRegisterClass().getWertOhneBank(0x05);
		int valTrisA = strg.getRegisterClass().getWertOhneBank(0x85);
		int valPortB = strg.getRegisterClass().getWertOhneBank(0x06);
		int valTrisB = strg.getRegisterClass().getWertOhneBank(0x86);
		int valPortC = strg.getRegisterClass().getWertOhneBank(0x07);
		int valStatus = strg.getRegisterClass().getWert(0x03);
		int valInterrupt = strg.getRegisterClass().getWert(0x0b);
		String stringStatus = Integer.toBinaryString(valStatus + 256);
		String stringInterrupt = Integer.toBinaryString(valInterrupt + 256);
		String stringPortA = Integer.toBinaryString(valPortA + 256);
		String stringTrisA = Integer.toBinaryString(valTrisA + 256);
		String stringPortB = Integer.toBinaryString(valPortB + 256);
		String stringTrisB = Integer.toBinaryString(valTrisB + 256);
		String stringPortC = Integer.toBinaryString(valPortC + 256);
		tablePortsRA.setValueAt(String.valueOf(stringPortA.charAt(8)), 1, 8);
		tablePortsRA.setValueAt(String.valueOf(stringPortA.charAt(7)), 1, 7);
		tablePortsRA.setValueAt(String.valueOf(stringPortA.charAt(6)), 1, 6);
		tablePortsRA.setValueAt(String.valueOf(stringPortA.charAt(5)), 1, 5);
		tablePortsRA.setValueAt(String.valueOf(stringPortA.charAt(4)), 1, 4);
		tablePortsRA.setValueAt(String.valueOf(stringPortA.charAt(3)), 1, 3);
		tablePortsRA.setValueAt(String.valueOf(stringPortA.charAt(2)), 1, 2);
		tablePortsRA.setValueAt(String.valueOf(stringPortA.charAt(1)), 1, 1);
		tablePortsRA.setValueAt(String.valueOf(stringTrisA.charAt(8)), 0, 8);
		tablePortsRA.setValueAt(String.valueOf(stringTrisA.charAt(7)), 0, 7);
		tablePortsRA.setValueAt(String.valueOf(stringTrisA.charAt(6)), 0, 6);
		tablePortsRA.setValueAt(String.valueOf(stringTrisA.charAt(5)), 0, 5);
		tablePortsRA.setValueAt(String.valueOf(stringTrisA.charAt(4)), 0, 4);
		tablePortsRA.setValueAt(String.valueOf(stringTrisA.charAt(3)), 0, 3);
		tablePortsRA.setValueAt(String.valueOf(stringTrisA.charAt(2)), 0, 2);
		tablePortsRA.setValueAt(String.valueOf(stringTrisA.charAt(1)), 0, 1);
		tablePortsRB.setValueAt(String.valueOf(stringTrisB.charAt(8)), 0, 8);
		tablePortsRB.setValueAt(String.valueOf(stringTrisB.charAt(7)), 0, 7);
		tablePortsRB.setValueAt(String.valueOf(stringTrisB.charAt(6)), 0, 6);
		tablePortsRB.setValueAt(String.valueOf(stringTrisB.charAt(5)), 0, 5);
		tablePortsRB.setValueAt(String.valueOf(stringTrisB.charAt(4)), 0, 4);
		tablePortsRB.setValueAt(String.valueOf(stringTrisB.charAt(3)), 0, 3);
		tablePortsRB.setValueAt(String.valueOf(stringTrisB.charAt(2)), 0, 2);
		tablePortsRB.setValueAt(String.valueOf(stringTrisB.charAt(1)), 0, 1);
		tablePortsRB.setValueAt(String.valueOf(stringPortB.charAt(8)), 1, 8);
		tablePortsRB.setValueAt(String.valueOf(stringPortB.charAt(7)), 1, 7);
		tablePortsRB.setValueAt(String.valueOf(stringPortB.charAt(6)), 1, 6);
		tablePortsRB.setValueAt(String.valueOf(stringPortB.charAt(5)), 1, 5);
		tablePortsRB.setValueAt(String.valueOf(stringPortB.charAt(4)), 1, 4);
		tablePortsRB.setValueAt(String.valueOf(stringPortB.charAt(3)), 1, 3);
		tablePortsRB.setValueAt(String.valueOf(stringPortB.charAt(2)), 1, 2);
		tablePortsRB.setValueAt(String.valueOf(stringPortB.charAt(1)), 1, 1);
		tablePortsRC.setValueAt(String.valueOf(stringPortC.charAt(8)), 1, 8);
		tablePortsRC.setValueAt(String.valueOf(stringPortC.charAt(7)), 1, 7);
		tablePortsRC.setValueAt(String.valueOf(stringPortC.charAt(6)), 1, 6);
		tablePortsRC.setValueAt(String.valueOf(stringPortC.charAt(5)), 1, 5);
		tablePortsRC.setValueAt(String.valueOf(stringPortC.charAt(4)), 1, 4);
		tablePortsRC.setValueAt(String.valueOf(stringPortC.charAt(3)), 1, 3);
		tablePortsRC.setValueAt(String.valueOf(stringPortC.charAt(2)), 1, 2);
		tablePortsRC.setValueAt(String.valueOf(stringPortC.charAt(1)), 1, 1);
		tableStatusregister.setValueAt(String.valueOf(stringStatus.charAt(8)), 0, 7);
		tableStatusregister.setValueAt(String.valueOf(stringStatus.charAt(7)), 0, 6);
		tableStatusregister.setValueAt(String.valueOf(stringStatus.charAt(6)), 0, 5);
		tableStatusregister.setValueAt(String.valueOf(stringStatus.charAt(5)), 0, 4);
		tableStatusregister.setValueAt(String.valueOf(stringStatus.charAt(4)), 0, 3);
		tableStatusregister.setValueAt(String.valueOf(stringStatus.charAt(3)), 0, 2);
		tableStatusregister.setValueAt(String.valueOf(stringStatus.charAt(2)), 0, 1);
		tableStatusregister.setValueAt(String.valueOf(stringStatus.charAt(1)), 0, 0);
		tableInterrupt.setValueAt(String.valueOf(stringInterrupt.charAt(8)), 0, 7);
		tableInterrupt.setValueAt(String.valueOf(stringInterrupt.charAt(7)), 0, 6);
		tableInterrupt.setValueAt(String.valueOf(stringInterrupt.charAt(6)), 0, 5);
		tableInterrupt.setValueAt(String.valueOf(stringInterrupt.charAt(5)), 0, 4);
		tableInterrupt.setValueAt(String.valueOf(stringInterrupt.charAt(4)), 0, 3);
		tableInterrupt.setValueAt(String.valueOf(stringInterrupt.charAt(3)), 0, 2);
		tableInterrupt.setValueAt(String.valueOf(stringInterrupt.charAt(2)), 0, 1);
		tableInterrupt.setValueAt(String.valueOf(stringInterrupt.charAt(1)), 0, 0);
	}
	
	/**
	 * Markiert die aktuelle Zeile
	 * @param Zeilennummer
	 */
	public void markiereZeile (int Zeilennummer){
		Zeilennummer --;
		tableDatei.removeRowSelectionInterval(aktuelleZeile, aktuelleZeile);
		tableDatei.addRowSelectionInterval(Zeilennummer, Zeilennummer);
		aktuelleZeile=Zeilennummer;
		JScrollBar scrollbar = scrollpaneText.getVerticalScrollBar();
		int anzeige = aktuelleZeile * 16;
		scrollbar.setValue(anzeige);
		scrollbar.repaint();
	}
	
	/**
	 * Fügt Zeile zu Tabelle hinzu
	 * @param zeile
	 */
	public void setZeile(String zeile){
		String [] zeileeinf = new String[] {zeile};
		Modell.addRow(zeileeinf);
	}
	
	public void clearErsteZeile(){
		Modell.removeRow(0);
	}
	
	public int getAnzahlZeilen(){
		return tableDatei.getRowCount();
	}
	
}
