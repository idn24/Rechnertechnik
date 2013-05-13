
public class Befehle {
	
	private Steuerung strg;
	
	public Befehle (Steuerung strg){
		this.strg = strg;
	}
	
	public void setBit(int opcode){
		
	}
	
	public void clr(int opcode){
		if(0x17f >= opcode && opcode >= 0x100){
			strg.setW(0);
			strg.getRegisterClass().setBit(0x3, 2);
		}
		else if(0x1ff >= opcode && opcode >= 0x180){
			
		}
		else if(opcode == 0x0064){
			
		}
		strg.incPCounter();
		
	}
	
	public void andwf(int opcode){
		
	}
	
	public void or(int opcode){
		
	}
	
	public void addwf(int opcode){
		
	}
	
	public void comf(int opcode){
	
	}
	
	//noch nicht fertig
	public void decf(int opcode){
		if ( (opcode & 128) == 1){
			short wert = strg.getRegisterClass().getWert(opcode & 127);
			
		}else{
			
		}
			
	}
	public void decfsz(int opcode){
	
	}
	
	public void incf(int opcode){
		
	}
	
	public void incfsz(int opcode){
		
	}
	
	public void iorwf(int opcode){
		
	}
	
	public void movf(int opcode){
		
	}
	
	public void movwf(int opcode){
		int f = opcode & 127;
		strg.getRegisterClass().setWert(f, (short)strg.getW());
		strg.incPCounter();
	}
	
	public void nop(int opcode){
		
	}
	
	public void rlf(int opcode){
		
	}
	
	public void rrf(int opcode){
		
	}
	
	public void subwf(int opcode){
		
	}
	
	public void swapf(int opcode){
		
	}
	
	public void xorwf(int opcode){
		
	}

	//NEU MACHEN!!!!!!!!!!!!!!!!!!!!!!!!!
	public void gooto(int opcode) {
		int wert = opcode & 2047;
		strg.setProgramcounter(wert);
	}

	public void movlw(int opcode) {
		int wert = opcode & 255;
		strg.setW(wert);
		strg.incPCounter();
	}

	public void bsf(int opcode) {
		int b = opcode & 896;
		b = b >> 7;
		strg.getRegisterClass().setBit(opcode & 7, b);
		strg.incPCounter();
	}

	public void bcf(int opcode) {
		int b = opcode & 896;
		b = b >> 7;
		strg.getRegisterClass().clearBit(opcode & 7, b);
		strg.incPCounter();
	}

	public void call(int opcode) {
		strg.pushCall();
		gooto(opcode);
	}
}
