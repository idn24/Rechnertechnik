import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.ArrayList;
import javax.swing.JTextArea;


public class Oberflaeche extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTable table_1;
    private Steuerung strg;
    private JTextPane txtpaneTextdatei;
    /**
     * Create the frame.
     */
    public Oberflaeche(Steuerung strg) {
    	this.strg = strg;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 319);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
       
        JLabel label = new JLabel("");
        label.setBounds(66, 105, 46, 14);
        contentPane.add(label);
       
        Panel panelButtons = new Panel();
        panelButtons.setBounds(25, 20, 127, 204);
        contentPane.add(panelButtons);
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
       
        JButton btnEinlesen = new JButton("Datei einlesen");
        btnEinlesen.addActionListener(new ActionListener() {
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
        
        JTextArea textArea = new JTextArea();
        panelButtons.add(textArea);
       
       
        Panel panelTextdatei = new Panel();
        panelTextdatei.setBounds(201, 26, 207, 106);
        contentPane.add(panelTextdatei);
        panelTextdatei.setLayout(null);
       
        txtpaneTextdatei = new JTextPane();
        txtpaneTextdatei.setBackground(Color.WHITE);
        txtpaneTextdatei.setBounds(10, 5, 187, 90);
        txtpaneTextdatei.setText("\t0001\t;Test\r\n\t0002\t;bla");
        panelTextdatei.add(txtpaneTextdatei);
       
        Panel panelPorts = new Panel();
        panelPorts.setBounds(201, 148, 207, 106);
        contentPane.add(panelPorts);
       
       
       
       
        String[][] rowData = {
                { "RA", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
                { "RB", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
                { "RC", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
                { "RD", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
                { "RE", "7", "6", "5", "4", "3", "2", "1", "0" }, { "Tris", "0", "0", "0", "0", "0", "0", "0", "0" }, { "Pin", "0", "0", "0", "0", "0", "0", "0", "0" },
        };
        String[] columnNames = {
                "Land", "Durchschnittliche Sehdauer pro Tag in Minuten"
        };
        JTable table = new JTable( rowData, columnNames );
        panelPorts.add(table);
        
        this.setVisible(true);
    }
    
    public void setTextfield(ArrayList<String> datei){
    	
    }
}