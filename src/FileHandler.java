import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class FileHandler {

	ArrayList<String> datei = new ArrayList<String>();
	
	/**
	 * Lässt eine ArrayList erstellen und gibt diese zurück
	 * @param pfad
	 * @return
	 */
	public ArrayList<String> getDatei(String pfad) {
		// TODO Auto-generated method stub
		try {
			readDatei(pfad);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datei;
	}
	
	/**
	 * Liest die Zeilen aus und Speichert sie in einer ArrayList
	 * @param pfad
	 * @throws IOException
	 */
	private void readDatei(String pfad) throws IOException {
		FileReader fr;
		fr = new FileReader(pfad);

	    BufferedReader br = new BufferedReader(fr);
	    String zeile;
	    while ((zeile = br.readLine()) != null){
	    	datei.add(zeile);
	    	System.out.println(zeile);
	    }

	    br.close();
	}

}
