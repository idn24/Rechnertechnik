
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
		int f = opcode & 127;
		int d = opcode >> 7;
		d = d & 1;
		int val = strg.getRegisterClass().getWert(f) & strg.getW();
		if(val == 0){
			strg.getRegisterClass().setBit(0x03, 2);
		}
		else{
			strg.getRegisterClass().clearBit(0x03, 2);
		}
		if (d==0){
			strg.setW(val);
		}else{
			strg.getRegisterClass().setWert(f, (short)val);
		}
		strg.incPCounter();
	}
	
	public void or(int opcode){
		
	}
	
	public void addlw(int opcode){
		int k = opcode & 255;
		int w = strg.getRegisterClass().getW() + k;
		
		if (w > 255){
			w = w % 256;
			strg.getRegisterClass().setBit(0x3, 0);
		}else{
			strg.getRegisterClass().clearBit(0x3, 0);
		}
		
		if(w == 0){
			strg.getRegisterClass().setBit(0x3, 2);
		}else{
			strg.getRegisterClass().clearBit(0x3, 2);
		}
		strg.getRegisterClass().setW(w);
		strg.incPCounter();
	}
	
	public void addwf(int opcode){
		int f = opcode & 127;
		int d = opcode >> 7;
		d = d &1;
		int val = strg.getRegisterClass().getWert(f) + strg.getW();
		if ( val > 255){
			val = val % 256;
			strg.getRegisterClass().setBit(0x3, 0);
		}else{
			strg.getRegisterClass().clearBit(0x3, 0);
		}
		if ( val == 0){
			strg.getRegisterClass().setBit(0x3, 2);
		}else{
			strg.getRegisterClass().clearBit(0x3, 2);
		}
		if(d == 0){
			strg.setW(val);
		}else{
			strg.getRegisterClass().setWert(f,(short) val);
		}
		strg.incPCounter();
	}
	
	public void comf(int opcode){
		int f = opcode & 127;
		int d = opcode >> 7;
		d = d & 1;
		int val = (~strg.getRegisterClass().getWert(f))&255;
		if (val == 0){
			strg.getRegisterClass().setBit(0x3, 2);
		}else{
			strg.getRegisterClass().clearBit(0x3, 2);
		}
		if(d == 0){
			strg.setW(val);
		}else{
			strg.getRegisterClass().setWert(f, (short) val);
		}
		strg.incPCounter();
	}
	
	//Nachfragen
	public void decf(int opcode){
		int d = opcode >> 7;
		d = d & 1;
		int f = opcode & 127;
		int val = strg.getRegisterClass().getWert(f) - 1;
		
		while (val <= -1){
			val = val + 256;
		}
		if (val == 0){
			strg.getRegisterClass().setBit(0x3, 2);
		}
		else{
			strg.getRegisterClass().clearBit(0x3, 2);
		}
		if (d == 0){
			strg.getRegisterClass().setW(val);
		}else{
			strg.getRegisterClass().setWert(f,(short) val);
		}
		strg.incPCounter();
			
	}
	public void decfsz(int opcode){
		int f = opcode & 127;
		int d = opcode >> 7;
		d = d & 1;
		int val = strg.getRegisterClass().getWert(f) -1 ;
		
		while (val <= -1){
			val = val + 256;
		}
		if (val == 0){
			strg.getRegisterClass().setBit(0x3, 2);
		}
		else{
			strg.getRegisterClass().clearBit(0x3, 2);
		}
		if (d == 0){
			strg.getRegisterClass().setW(val);
		}else{
			strg.getRegisterClass().setWert(f,(short) val);
		}
		
		if(strg.getRegisterClass().getWert(f) != 0){
			strg.incPCounter();
			return;
		}else{
			strg.incPCounter();
			strg.incPCounter();
		}
		
	}
	
	public void incf(int opcode){
		int f = opcode & 127;
		int d = opcode >> 7;
		d = d & 1;
		int val = strg.getRegisterClass().getWert(f) + 1;
		
		if(val > 255){
			val = val % 256;
		}
		
		if (val == 0){
			strg.getRegisterClass().setBit(0x3, 2);
		}else{
			strg.getRegisterClass().clearBit(0x3, 2);
		}
		if (d == 0){
			strg.getRegisterClass().setW(val);
		}else{
			strg.getRegisterClass().setWert(f, (short)val);
		}
		strg.incPCounter();
	}
	
	public void incfsz(int opcode){
		
	}
	
	public void iorwf(int opcode){
		
	}
	
	public void movf(int opcode){
		int f = opcode & 127;
		int d = opcode >> 7;
		d = d & 1;
		int val = strg.getRegisterClass().getWert(f);
		if (val == 0){
			strg.getRegisterClass().setBit(0x3, 2);
		}else{
			strg.getRegisterClass().clearBit(0x3, 2);
		}
		if(d == 0){
			strg.setW(val);
		}else{
			strg.getRegisterClass().setWert(f,(short) val);
		}
		strg.incPCounter();
	}
	
	public void movwf(int opcode){
		int f = opcode & 127;
		strg.getRegisterClass().setWert(f, (short)strg.getW());
		strg.incPCounter();
	}
	
	public void nop(int opcode){
		
	}
	
	public void rlf(int opcode){
		int f = opcode & 127;
		int d = opcode >> 7;
		d = d & 1;
		int val = strg.getRegisterClass().getWert(f);
		val = val << 1;
		if ((strg.getRegisterClass().getWert(0x03) & 1) == 1){
			val = val + 1;
			strg.getRegisterClass().clearBit(0x03, 0);
		}
		if( val > 255){
			val = val % 256;
			strg.getRegisterClass().setBit(0x03, 0);
		}
		if ( d == 0){
			strg.setW(val);
		}
		else{
			strg.getRegisterClass().setWert(f, (short) val);
		}
		strg.incPCounter();
	}
	
	public void rrf(int opcode){
		int f = opcode & 127;
		int d = opcode >> 7;
		d = d & 1;
		int val = strg.getRegisterClass().getWert(f);
		val = val >> 1;
		if ((strg.getRegisterClass().getWert(0x03) & 1) == 1){
			val = val + 128;
			strg.getRegisterClass().clearBit(0x03, 0);
		}
		if((strg.getRegisterClass().getWert(f) % 2) == 0){
			strg.getRegisterClass().clearBit(0x03, 0);
		}
		else{
			strg.getRegisterClass().setBit(0x03, 0);
		}
		if ( d == 0){
			strg.setW(val);
		}
		else{
			strg.getRegisterClass().setWert(f, (short) val);
		}
		strg.incPCounter();
	}
	
	public void subwf(int opcode){
		int f = opcode & 127;
		int d = opcode >> 7;
		d = d & 1;
		int val = strg.getRegisterClass().getWert(f) - strg.getW();
		
		if (val < 0){
			val = val + 256;
			strg.getRegisterClass().clearBit(0x3, 0);
		} else{
			strg.getRegisterClass().setBit(0x3, 0);
		}
		if (val == 0){
			strg.getRegisterClass().setBit(0x3, 2);
		}
		else{
			strg.getRegisterClass().clearBit(0x3, 2);
		}
		if(d == 0){
			strg.setW(val);
		}
		else{
			strg.getRegisterClass().setWert(f, (short)val);
		}
		
		strg.incPCounter();
	}
	
	public void swapf(int opcode){
		int f = opcode & 127;
		int d = opcode >> 7;
		d = d & 1;
		int nibbel1 = strg.getRegisterClass().getWert(f) & 15;
		nibbel1 = nibbel1 << 4;
		int nibbel2 = strg.getRegisterClass().getWert(f) & 240;
		nibbel2 = nibbel2 >> 4;
		int val = 0;
		val = nibbel1;
		val = val + nibbel2;
		if (d == 0){
			strg.setW(val);
		}else{
			strg.getRegisterClass().setWert(f,(short) val);
		}
		strg.incPCounter();
		
	}
	
	public void xorwf(int opcode){
		int f = opcode & 127;
		int d = opcode >> 7;
		d = d & 1;
		int val = strg.getW() ^ strg.getRegisterClass().getWert(f);
		if (val == 0){
			strg.getRegisterClass().setBit(0x03, 2);
		}else{
			strg.getRegisterClass().clearBit(0x03, 2);
		}
		if(d==0){
			strg.setW(val);
		}else{
			strg.getRegisterClass().setWert(f, (short)val);
		}
		strg.incPCounter();
	}
	
	public void xorlw(int opcode){
		int k = opcode & 255;
		strg.setW(k ^ strg.getW());
		if(strg.getW() == 0	){
			strg.getRegisterClass().setBit(0x03, 2);
		}else{
			strg.getRegisterClass().clearBit(0x03, 2);
		}
		strg.incPCounter();
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
		strg.getRegisterClass().setBit(opcode & 127, b);
		strg.incPCounter();
	}

	public void bcf(int opcode) {
		int b = opcode & 896;
		b = b >> 7;
		strg.getRegisterClass().clearBit(opcode & 127, b);
		strg.incPCounter();
	}

	public void call(int opcode) {
		strg.pushCall();
		gooto(opcode);
	}
	
	public void retlw(int opcode){
		int k = opcode & 255;
		strg.getRegisterClass().setW(k);
		strg.setProgramcounter(strg.getRegisterClass().getStackHead());
	}
	
	public void btfsc(int opcode){
		int f = opcode & 127;
		int b = opcode >> 7;
		b = b & 7;
		int reg = strg.getRegisterClass().getWert(f);
		int help = 1;
		help = help << b;
		reg = reg & help;
		reg = reg >> b;
		if (reg == 1){
			strg.incPCounter();
		}else{
			strg.incPCounter();
			strg.incPCounter();
		}
	}
	
	public void retfie(){
		strg.setProgramcounter(strg.getRegisterClass().getStackHead());
		strg.getRegisterClass().setBit(0x0b, 7);
	}

	public void btfss(int opcode) {
		int f = opcode & 127;
		int b = opcode & 896;
		b = b >> 7;
		int reg = strg.getRegisterClass().getWert(f);
		int help = 1;
		help = help << b;
		reg = reg & help;
		if (reg == 0){
			strg.incPCounter();
		}else{
			strg.incPCounter();
			strg.incPCounter();
		}		
	}
	
	public void ret(){
		strg.setProgramcounter(strg.getRegisterClass().getStackHead());
	}
	
	public void sublw(int opcode){
		int val = opcode & 255;
		val = val % 256;
		val = val - strg.getW();
		if (val < 0) {
			val = val + 256;
			strg.getRegisterClass().clearBit(0x3, 0);
		} else {
			strg.getRegisterClass().setBit(0x3, 0);
		}
		if (val == 0) {
			strg.getRegisterClass().setBit(0x3, 2);
		} else {
			strg.getRegisterClass().clearBit(0x3, 2);
		}
		strg.setW(val);
		strg.incPCounter();
	}

	public void iorlw(int opcode) {
		int k = opcode & 255;
		strg.setW(strg.getW() | k);
		if(strg.getW() == 0){
			strg.getRegisterClass().setBit(0x03, 2);
		}else{
			strg.getRegisterClass().clearBit(0x03, 2);
		}
		
		strg.incPCounter();
	}

	public void andlw(int opcode) {
		int k = opcode & 255;
		strg.setW(strg.getW() & k);
		if(strg.getW() == 0){
			strg.getRegisterClass().setBit(0x03, 2);
		}else{
			strg.getRegisterClass().clearBit(0x03, 2);
		}
		strg.incPCounter();
	}
}
