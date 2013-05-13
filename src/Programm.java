import java.util.ArrayList;
import java.util.HashMap;


public class Programm {

	ArrayList<String> datei;
	HashMap<Integer, Integer> opcode = new HashMap<Integer,Integer>();
	
	public int getOpcode(int progZaehler){
		return opcode.get(progZaehler);
	}
	
	
	public Programm(ArrayList<String> datei) {
		this.datei = datei;
		selectOpcode();
	}
	public ArrayList<String> getDatei() {
		return datei;
	}
	
	private void selectOpcode(){
		for(int i = 0; i<datei.size();i++){
			String vgl = datei.get(i);
			if(vgl.startsWith("0")){
				opcode.put((int)Integer.parseInt(vgl.substring(0, 4),16), (int)Integer.parseInt(vgl.substring(5, 9), 16));
			}
		}
	}
	
	public String getString(){
		String test = "";
		for(int i = 0; i<datei.size();i++){
			test = test+datei.get(i)+"\n";
		}
		return test;
	}

}
