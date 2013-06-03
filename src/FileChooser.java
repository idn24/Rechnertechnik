import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileChooser {
	
	public FileChooser(){
		
	}
	
	/**
	 * Gibt Pfad der ausgew�hlten Datei zur�ck
	 * @return
	 */
    public String oeffnen() {
    	String pfad ="";
    	// JFileChooser-Objekt erstellen
        JFileChooser chooser = new JFileChooser();
        // Dialog zum Oeffnen von Dateien anzeigen
        int rueckgabeWert = chooser.showOpenDialog(null);
        
        /* Abfrage, ob auf "�ffnen" geklickt wurde */
        if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
        {
            pfad = chooser.getSelectedFile().getPath();
        }
        return pfad;
    }
} 