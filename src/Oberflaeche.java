import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.Vector;


public class Oberflaeche extends JFrame {

	public Steuerung strg;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable tableDatei;
	private DefaultTableModel Modell;


	/**
	 * Create the frame.
	 */
	public Oberflaeche(Steuerung strg) {
		this.strg = strg;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panelButtons = new Panel();
		panelButtons.setBounds(25, 20, 150, 204);
		contentPane.add(panelButtons);
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnEinlesen = new JButton("Datei einlesen");
		btnEinlesen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Oberflaeche.this.strg.oeffneDatei();
				
			}
		});
		panelButtons.add(btnEinlesen);
		
		JButton btnAusfuehren = new JButton("Ausf\u00FChren");
		panelButtons.add(btnAusfuehren);
		
		JButton btnNaechsterSchritt = new JButton("n\u00E4chster Schritt");
		panelButtons.add(btnNaechsterSchritt);
		
		JButton btnVorherigerSchritt = new JButton("vorheriger Schritt");
		panelButtons.add(btnVorherigerSchritt);
		
		JButton btnPause = new JButton("Pause");
		panelButtons.add(btnPause);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelButtons.add(btnReset);
		
		
		Panel panelTextdatei = new Panel();
		panelTextdatei.setBounds(201, 26, 700, 300);
		contentPane.add(panelTextdatei);
		panelTextdatei.setLayout(null);
		
		
		
		
		
		
	
		String[][] text = new String[][]{{}};
		String [] tabellenkopf = new String[]{ "inhalt"};
		tableDatei = new JTable( text, tabellenkopf );
		tableDatei.setBounds(37, 67, 700, 200);
		
		tableDatei.setEnabled(false);
		panelTextdatei.add(tableDatei);
		
		
		Modell = new DefaultTableModel(text, tabellenkopf);
		tableDatei.setModel(Modell);

		
		//nächste Zeile markiert immer eine/mehrere bestimmten Zeilen
//		tableDatei.addRowSelectionInterval(1, 1);
		tableDatei.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		/*
		new AbstractTableModel() {
		    public String getColumnName(int col) {
		        return columnNames[col].toString();
		    }
		    public int getRowCount() { return rowData.length; }
		    public int getColumnCount() { return columnNames.length; }
		    public Object getValueAt(int row, int col) {
		        return rowData[row][col];
		    }
		    public boolean isCellEditable(int row, int col)
		        { return true; }
		    public void setValueAt(Object value, int row, int col) {
		        rowData[row][col] = value;
		        fireTableCellUpdated(row, col);
		    }
		}
		*/
		
		
		
		/*
		JTextArea text = new JTextArea();
		text.setBounds(86, 83, 150, 112);
		text.setText("Test\ncode");
		//text.setEnabled(false);
		panelTextdatei.add(text);
		text.append("\ngeil");
		panelTextdatei.setVisible(true);
		*/
		
		/*
		JTextPane text = new JTextPane();
		text.setText("Test\ncode");
		text.setBounds(90, 104, 143, 91);
		panelTextdatei.add(text);
		StyledDocument doc = text.getStyledDocument ();
		Style style = text.addStyle("Red", null);  
		StyleConstants.setBackground(style, Color.PINK);
		doc.setCharacterAttributes(2, 5, text.getStyle("Red"), true); 
		setVisible (true);
		*/
		
		
		
		
		
		
		
		
		
		Panel panelPorts = new Panel();
		panelPorts.setBounds(201, 350, 700, 300);
		contentPane.add(panelPorts);
		String[][] Daten = {
			    { "RA", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
			    { "RB", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
			    { "RC", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
			    { "RD", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
			    { "RE", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
		};
		String[] Titel = {
				"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9"
		};
		JTable tablePorts = new JTable (Daten, Titel );
		tablePorts.setEnabled(false);
		
	      
	
	    TableColumnModel columnModel = tablePorts.getColumnModel();
	            
	      // Die einzelnen Columns ansprechen und die Grösse setzen
	      columnModel.getColumn( 0 ).setPreferredWidth( 80 );
	      columnModel.getColumn( 1 ).setPreferredWidth( 5 );
	      columnModel.getColumn( 2 ).setPreferredWidth( 5 );
	      columnModel.getColumn( 3 ).setPreferredWidth( 5 );
	      columnModel.getColumn( 4 ).setPreferredWidth( 5 );
	      columnModel.getColumn( 5 ).setPreferredWidth( 5 );
	      columnModel.getColumn( 6 ).setPreferredWidth( 5 );
	      columnModel.getColumn( 7 ).setPreferredWidth( 5 );
		
	      panelPorts.add(tablePorts);

	      
	      
	      
	      
	      
	      
	      this.setVisible(true);
	}
	
	public void setZeile(String zeile){
		String [] zeileeinf = new String[] {zeile};
		Modell.addRow(zeileeinf);
		
	}
}
