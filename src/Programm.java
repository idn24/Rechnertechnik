import java.util.ArrayList;
import java.util.HashMap;


public class Programm {

	ArrayList<String> datei;
	HashMap<Integer, Integer> opcode = new HashMap<Integer,Integer>();
	HashMap<Integer, Integer> zeile = new HashMap<Integer,Integer>();
	
	/**
	 * Gibt den Opcode an der Stelle des Programmcounter zurück
	 * @param progZaehler
	 * @return
	 */
	public int getOpcode(int progZaehler){
		return opcode.get(progZaehler);
	}
	
	/**
	 * Gibt die aktuelle Zeilennummer zurück
	 * @param progZaehler
	 * @return
	 */
	public int getZeile(int progZaehler){
		return zeile.get(progZaehler);
	}
	
	/**
	 * Verarbeitet den übergebenen Programmcode
	 * @param datei
	 */
	public Programm(ArrayList<String> datei) {
		this.datei = datei;
		selectOpcode();
	}
	
	/**
	 * gibt den Programmcode zurück
	 * @return
	 */
	public ArrayList<String> getDatei() {
		return datei;
	}
	
	/**
	 * Liest aus dem Programmcode alle opcodes aus und speichert sie in verbindung mit den PC-Werten in einer HashMap
	 */
	private void selectOpcode(){
		for(int i = 0; i<datei.size();i++){
			String vgl = datei.get(i);
			if(vgl.startsWith("0")){
				opcode.put((int)Integer.parseInt(vgl.substring(0, 4),16), (int)Integer.parseInt(vgl.substring(5, 9), 16));
				zeile.put((int)Integer.parseInt(vgl.substring(0, 4),16), (int)Integer.parseInt(vgl.substring(20,25)));
			}
		}
	}

}
