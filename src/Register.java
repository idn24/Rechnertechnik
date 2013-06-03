import java.util.Stack;


public class Register {


	private short register[];
	private int w;
	private Stack<Integer> stack;
//	private int programcounter = 0;

	public Stack<Integer> getStack() {
		return stack;
	}

	public void setStack(int push) {
		if (this.stack.size() < 13){
			this.stack.push(push);
		}
		else{
			System.out.println("ERRRORR");
		}
		//NOCH FERTIG PROGRAMMIEREN!
	}

	public Register (){
		register = new short[0x100];
		for(int i = 0; i< this.register.length; i++){
			register[i]=0;
		}
		this.register[0x3] = Short.parseShort("00011000", 2);
		this.register[0x83] = Short.parseShort("00011000", 2);
		this.register[0x81] = Short.parseShort("11111111", 2);
		this.register[0x85] = Short.parseShort("00011111", 2);
		this.register[0x86] = Short.parseShort("11111111", 2);
		stack = new Stack<Integer>();
	}
	
	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}
	
	public int getPC(){
		
		return getWert(0x02);
	}
	
	public void setPC(int val){
		setWert(0x02, (short)val);
	}
	
	public short[] getRegister(){
		return register;
	}
	
	
	
	public void setWert(int regIndex, short wert){
		
		if(regIndex == 0x00){
			regIndex = getWert(0x04);
		}
		
		switch(regIndex){
		case 0x0:	register[0x0] = wert;
					register[0x80] = wert;
					break;
		case 0x2:	register[0x2] = wert;
					register[0x82] = wert;
					break;
		case 0x3:	register[0x3] = wert;
					register[0x83] = wert;
					break;
		case 0x4:	register[0x4] = wert;
					register[0x84] = wert;
					break;
		case 0xA:	register[0xA] = wert;
					register[0x8A] = wert;
					break;
		case 0xB:	register[0xB] = wert;
					register[0x8B] = wert;
					break;
		case 0x80:	register[0x0] = wert;
					register[0x80] = wert;
					break;
		case 0x82:	register[0x2] = wert;
					register[0x82] = wert;
					break;
		case 0x83:	register[0x3] = wert;
					register[0x83] = wert;
					break;
		case 0x84:	register[0x4] = wert;
					register[0x84] = wert;
					break;
		case 0x8A:	register[0xA] = wert;
					register[0x8A] = wert;
					break;
		case 0x8B:	register[0xB] = wert;
					register[0x8B] = wert;
					break;
		default:	
					if(getBank()==1 && regIndex < 0x80){
						regIndex = regIndex + 0x80;	
					}
					register[regIndex] = wert;
		}
		
	}
	
	public short getWert(int regIndex){
		if(regIndex == 0x00){
			regIndex = getWert(0x04);
		}
		switch(regIndex){
		case 0x0:	return register[0x0];
		case 0x2:	return register[0x2];
		case 0x3:	return register[0x3];
		case 0x4:	return register[0x4];
		case 0xA:	return register[0xA];
		case 0xB:	return register[0xB];
		case 0x80:	return register[0x0];
		case 0x82:	return register[0x2];
		case 0x83:	return register[0x3];
		case 0x84:	return register[0x4];
		case 0x8A:	return register[0xA];
		case 0x8B:	return register[0xB];
		default:	
					if(getBank()==1 && regIndex < 0x80){
						regIndex = regIndex + 0x80;	
					}
					return register[regIndex];
		}
		
	}
	
	public short getWertOhneBank(int regIndex){
		return register[regIndex];
	}
	
	public void setWertOhneBank(int regIndex, short val){
		register[regIndex] = val;
	}
	
	private int getBank() {
		int shift = 1;
		shift = shift << 5;
		if ((this.register[0x3] & shift) == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	public void setBit(int regIndex, int bitNr){
		int bit = 1;
		bit = bit << bitNr;
		
		switch(regIndex){
		case 0x0:	register[0x0] = (short) (register[0x0] | bit);
					register[0x80] = (short) (register[0x80] | bit);
					break;
		case 0x2:	register[0x2] = (short) (register[0x2] | bit);
					register[0x82] = (short) (register[0x82] | bit);
					break;
		case 0x3:	register[0x3] = (short) (register[0x3] | bit);
					register[0x83] = (short) (register[0x83] | bit);
					break;
		case 0x4:	register[0x4] = (short) (register[0x4] | bit);
					register[0x84] = (short) (register[0x84] | bit);
					break;
		case 0xA:	register[0xA] = (short) (register[0xA] | bit);
					register[0x8A] = (short) (register[0x8A] | bit);
					break;
		case 0xB:	register[0xB] = (short) (register[0xB] | bit);
					register[0x8B] = (short) (register[0x8B] | bit);
					break;
		case 0x80:	register[0x0] = (short) (register[0x0] | bit);
					register[0x80] = (short) (register[0x80] | bit);
					break;
		case 0x82:	register[0x2] = (short) (register[0x2] | bit);
					register[0x82] = (short) (register[0x82] | bit);
					break;
		case 0x83:	register[0x3] = (short) (register[0x3] | bit);
					register[0x83] = (short) (register[0x83] | bit);
					break;
		case 0x84:	register[0x4] = (short) (register[0x4] | bit);
					register[0x84] = (short) (register[0x84] | bit);
					break;
		case 0x8A:	register[0xA] = (short) (register[0xA] | bit);
					register[0x8A] = (short) (register[0x8A] | bit);
					break;
		case 0x8B:	register[0xB] = (short) (register[0xB] | bit);
					register[0x8B] = (short) (register[0x8B] | bit);
					break;
		default:	
					if(getBank()==1 && regIndex < 0x80){
						regIndex = regIndex + 0x80;	
					}
					register[regIndex] = (short) (register[regIndex] | bit);
		}
		
	}
	
	public void clearBit(int regIndex, int bitNr){
		int bit = 1;
		bit = bit << bitNr;
		bit = ~bit;
		
		switch(regIndex){
		case 0x0:	register[0x0] = (short) (register[0x0] & bit);
					register[0x80] = (short) (register[0x80] & bit);
					break;
		case 0x2:	register[0x2] = (short) (register[0x2] & bit);
					register[0x82] = (short) (register[0x82] & bit);
					break;
		case 0x3:	register[0x3] = (short) (register[0x3] & bit);
					register[0x83] = (short) (register[0x83] & bit);
					break;
		case 0x4:	register[0x4] = (short) (register[0x4] & bit);
					register[0x84] = (short) (register[0x84] & bit);
					break;
		case 0xA:	register[0xA] = (short) (register[0xA] & bit);
					register[0x8A] = (short) (register[0x8A] & bit);
					break;
		case 0xB:	register[0xB] = (short) (register[0xB] & bit);
					register[0x8B] = (short) (register[0x8B] & bit);
					break;
		case 0x80:	register[0x0] = (short) (register[0x0] & bit);
					register[0x80] = (short) (register[0x80] & bit);
					break;
		case 0x82:	register[0x2] = (short) (register[0x2] & bit);
					register[0x82] = (short) (register[0x82] & bit);
					break;
		case 0x83:	register[0x3] = (short) (register[0x3] & bit);
					register[0x83] = (short) (register[0x83] & bit);
					break;
		case 0x84:	register[0x4] = (short) (register[0x4] & bit);
					register[0x84] = (short) (register[0x84] & bit);
					break;
		case 0x8A:	register[0xA] = (short) (register[0xA] & bit);
					register[0x8A] = (short) (register[0x8A] & bit);
					break;
		case 0x8B:	register[0xB] = (short) (register[0xB] & bit);
					register[0x8B] = (short) (register[0x8B] & bit);
					break;
		default:	
					if(getBank()==1 && regIndex < 0x80){
						regIndex = regIndex + 0x80;	
					}
					register[regIndex] = (short) (register[regIndex] & bit);
		}
		
	}

	public int getStackHead() {
		// TODO Auto-generated method stub
		return stack.pop();
	}

	public void reset() {
		register = new short[0x100];
		for(int i = 0; i< this.register.length; i++){
			register[i]=0;
		}
		this.register[0x3] = Short.parseShort("00011000", 2);
		this.register[0x83] = Short.parseShort("00011000", 2);
		this.register[0x81] = Short.parseShort("11111111", 2);
		this.register[0x85] = Short.parseShort("00011111", 2);
		this.register[0x86] = Short.parseShort("11111111", 2);
		stack = new Stack<Integer>();
		setW(0);
		
	}

	
}
