import java.io.File;


public class Steuerung {

	/**
	 * @param args
	 */
	Oberflaeche gui;
	DateiEinlesen read;
	Datei datei;
	FileChooser chooser;
	
	public Steuerung (){
		gui = new Oberflaeche(this);
	}
	
	public void oeffneDatei(){
		chooser = new FileChooser();
		String pfad = chooser.oeffnen();
		leseDatei(pfad);
	}
	
	private void leseDatei(String pfad){
		read = new DateiEinlesen();
		datei = new Datei(read.getDatei(pfad));
	}

}
