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
import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
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
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable tableDatei;
	private DefaultTableModel Modell;
	private JTextField txtPorts;


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
		
		panelButtons.setBounds(25, 20, 150, 220);
		contentPane.add(panelButtons);
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnEinlesen = new JButton("Datei einlesen");
		btnEinlesen.setPreferredSize(new Dimension(140, 30));
		btnEinlesen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Oberflaeche.this.strg.oeffneDatei();
				
			}
		});
		panelButtons.add(btnEinlesen);
		
		JButton btnAusfuehren = new JButton("Ausf\u00FChren");
		btnAusfuehren.setPreferredSize(new Dimension(140, 30));
		panelButtons.add(btnAusfuehren);
		
		JButton btnNaechsterSchritt = new JButton("N\u00E4chster Schritt");
		btnNaechsterSchritt.setPreferredSize(new Dimension(140, 30));
		btnNaechsterSchritt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			Oberflaeche.this.strg.nextStep();

			}
			});
		panelButtons.add(btnNaechsterSchritt);
		
		JButton btnVorherigerSchritt = new JButton("Vorheriger Schritt");
		btnVorherigerSchritt.setPreferredSize(new Dimension(140, 30));
		panelButtons.add(btnVorherigerSchritt);
		
		JButton btnPause = new JButton("Pause");
		btnPause.setPreferredSize(new Dimension(140, 30));
		panelButtons.add(btnPause);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setPreferredSize(new Dimension(140, 30));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelButtons.add(btnReset);
		
		
		// ########################## Textdatei ##############################################
		String[][] text = new String[][]{{""}};
		String [] tabellenkopf = new String[]{"Textdatei"};
		tableDatei = new JTable( text, tabellenkopf );
		
		tableDatei.setEnabled(false);
		
		Modell = new DefaultTableModel(text, tabellenkopf);
		tableDatei.setModel(Modell);
		
		
		
		//nächste Zeile markiert immer eine/mehrere bestimmten Zeilen
		//tableDatei.addRowSelectionInterval(1, 1);
		
		
		
		JScrollPane scrollpaneText = new JScrollPane(tableDatei);
		scrollpaneText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		scrollpaneText.setBounds(293, 20, 700, 200);
		contentPane.add(scrollpaneText);
		
		
		
		
		
		
		
		// ########################## Ports ##############################################
		JPanel panelPorts = new JPanel();
		panelPorts.setBounds(25, 260, 516, 300);
		panelPorts.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelPorts.setLayout(new BorderLayout(5, 5));
		contentPane.add(panelPorts);
		
	      
	      
	      /*JTextPane textPorts = new JTextPane();
	      textPorts.setBackground(new Color(240, 240, 240));
	      textPorts.setEnabled(false);
	      textPorts.setEditable(false);
	      textPorts.setText("Ports");
	      panelPorts.add(textPorts, BorderLayout.PAGE_START);
	      
	      
	      
	      txtPorts = new JTextField();
	      txtPorts.setEditable(false);
	      txtPorts.setForeground(Color.BLACK);
	      //txtPorts.setBackground();
	      txtPorts.setEnabled(false);
	      txtPorts.setText("Ports");
	      txtPorts.setColumns(30);
	      panelPorts.add(txtPorts, BorderLayout.PAGE_START);
	      */
		
		Label label = new Label("Ports");
	      panelPorts.add(label, BorderLayout.NORTH);
	      
	      String[][] DatenPorts = {
				    { "RA", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
				    { "RB", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
				    { "RC", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
				    
	      };
	      String[] TitelPorts = {
	    		  "ports0", "ports1", "ports2", "ports3", "ports4", "ports5", "ports6", "ports7", "ports8"
	      };
	      JTable tablePorts = new JTable (DatenPorts, TitelPorts );
	      tablePorts.setBackground(new Color(240, 240, 240));
	      tablePorts.setEnabled(false);
	      TableColumnModel columnModel = tablePorts.getColumnModel();
	      // Die einzelnen Columns ansprechen und die Grösse setzen
	      columnModel.getColumn( 0 ).setPreferredWidth( 80 );
	      columnModel.getColumn( 1 ).setPreferredWidth( 20 );
	      columnModel.getColumn( 2 ).setPreferredWidth( 20 );
	      columnModel.getColumn( 3 ).setPreferredWidth( 20 );
	      columnModel.getColumn( 4 ).setPreferredWidth( 20 );
	      columnModel.getColumn( 5 ).setPreferredWidth( 20 );
	      columnModel.getColumn( 6 ).setPreferredWidth( 20 );
	      columnModel.getColumn( 7 ).setPreferredWidth( 20 );
	      panelPorts.add(tablePorts);
	      
	      
	      
	      
	      

	      
	      
// ########################## Speicher ##############################################

	      
	      
	      

	      

	      String[] TitelSpeicher = new String[]{" ", "00", "01", "02", "03", "04", "05", "06", "07"};
	      JTable tableSpeicher = new JTable (strg.getRegisterArray(), TitelSpeicher );
	      tableSpeicher.setBackground(new Color(240, 240, 240));
	      tableSpeicher.setBounds(710, 270, 525, 16);
	      tableSpeicher.setEnabled(false);
	      
	      
	      
	      
	      
	      TableColumnModel columnModel2 = tableSpeicher.getColumnModel();
          
	      // Die einzelnen Columns ansprechen und die Grösse setzen
	      columnModel2.getColumn( 0 ).setPreferredWidth( 40 );
	      columnModel2.getColumn( 1 ).setPreferredWidth( 40 );
	      columnModel2.getColumn( 2 ).setPreferredWidth( 40 );
	      columnModel2.getColumn( 3 ).setPreferredWidth( 40 );
	      columnModel2.getColumn( 4 ).setPreferredWidth( 40 );
	      columnModel2.getColumn( 5 ).setPreferredWidth( 40 );
	      columnModel2.getColumn( 6 ).setPreferredWidth( 40 );
	      columnModel2.getColumn( 7 ).setPreferredWidth( 40 );
	      columnModel2.getColumn( 8 ).setPreferredWidth( 40 );
	      
	      
	      
	      
	      
	      
	      
	      JScrollPane scrollpaneSpeicher = new JScrollPane(tableSpeicher);
			scrollpaneSpeicher.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			scrollpaneSpeicher.setBounds(700, 260, 535, 373);
			contentPane.add(scrollpaneSpeicher);
	      
	      
	      
	      
	      
	      
	      
	      this.setVisible(true);
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
