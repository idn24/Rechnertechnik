import java.io.File;


public class Steuerung {

	/**
	 * @param args
	 */
	Oberflaeche gui;
	DateiEinlesen read;
	Programm programm;
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
		programm = new Programm(read.getDatei(pfad));
		for (int i=0; i < programm.getDatei().size(); i++){
			gui.setZeile(programm.getDatei().get(i));
		}
	}

}
