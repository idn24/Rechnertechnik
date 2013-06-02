import java.awt.BorderLayout;
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


public class Oberflaeche extends JFrame {

	public Steuerung strg;
	private JTable tableSpeicher;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable tableDatei;
	private DefaultTableModel Modell;
	private JTextField txtPorts;
	private String[] TitelSpeicher;
	private String[] TitelPortsRC;
	private int aktuelleZeile = 0;
	private DefaultTableModel tabModel;
	private JScrollPane scrollpaneSpeicher;
	private JScrollPane scrollpaneText;
	private JTable tablePortsRA;


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
				    { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
	      };
	      String[] TitelPortsRA = {"RA", "7", "6", "5", "4", "3", "2", "1", "0"};
	      tablePortsRA = new JTable (DatenPortsRA, TitelPortsRA );
	      tablePortsRA.setBackground(new Color(240, 240, 240));
	      tablePortsRA.addMouseListener(new MouseAdapter() {
	    	  public void mouseclicked(MouseEvent e){
	    		  System.out.println("Reihe" + Oberflaeche.this.tablePortsRA.columnAtPoint(e.getPoint()));
	    		  //System.out.println("Spalte" + Oberflaeche.this.tablePortsRA.getSelectedColumn());
	    	  }
	      });
	      TableColumnModel columnModelRA = tablePortsRA.getColumnModel();
	      columnModelRA.getColumn( 0 ).setPreferredWidth( 200 );
	      JScrollPane scrollpaneRA = new JScrollPane(tablePortsRA);
	      scrollpaneRA.setBounds(10, 10, 220, 55);
	      
	      
	      
	      
	      String[][] DatenPortsRB = {
				    { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
	      };
	      String[] TitelPortsRB = {"RB", "7", "6", "5", "4", "3", "2", "1", "0"};
	      JTable tablePortsRB = new JTable (DatenPortsRB, TitelPortsRB );
	      tablePortsRB.setBackground(new Color(240, 240, 240));
	      tablePortsRB.setEnabled(false);
	      TableColumnModel columnModelRB = tablePortsRB.getColumnModel();
	      columnModelRB.getColumn( 0 ).setPreferredWidth( 200 );
	      JScrollPane scrollpaneRB = new JScrollPane(tablePortsRB);
	      scrollpaneRB.setBounds(10, 75, 220, 55);
	      
	      
	      
	      String[][] DatenPortsRC = {
				    { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
	      };
	      TitelPortsRC = new String[]{"RC", "7", "6", "5", "4", "3", "2", "1", "0"};
	      DefaultTableModel tabModelRC = new DefaultTableModel(DatenPortsRC, TitelPortsRC);
	      JTable tablePortsRC = new JTable (tabModelRC);
	      tablePortsRC.setBackground(new Color(240, 240, 240));
	      tablePortsRC.setEnabled(false);
	      TableColumnModel columnModelRC = tablePortsRC.getColumnModel();
	      columnModelRC.getColumn( 0 ).setPreferredWidth( 200 );
	      JScrollPane scrollpaneRC = new JScrollPane(tablePortsRC);
	      scrollpaneRC.setBounds(10, 140, 220, 55);
	      
	      
	      JPanel panelPorts = new JPanel();
	      panelPorts.setBounds(601, 72, 240, 205);
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
	      scrollpaneSpeicher = new JScrollPane(tableSpeicher);
	      scrollpaneSpeicher.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		  scrollpaneSpeicher.setBounds(972, 72, 302, 534);
		  contentPane.add(scrollpaneSpeicher);
	      /*         
	      TableColumnModel columnModel2 = tableSpeicher.getColumnModel();
	      columnModel2.getColumn( 0 ).setPreferredWidth( 40 );
	      ...
	      */
	            
	      
	      this.setVisible(true);
	}
	
	
	
	
	
	public void refreshSpeicher(String[][] Speicher){
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
				zeile[j] = Speicher[i][j];
			}
			tabModel.addRow(zeile);
		}
	}
	
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
