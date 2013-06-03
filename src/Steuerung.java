import java.io.File;


public class Steuerung {

	/**
	 * @param args
	 */
	private Takt takt;
	Oberflaeche gui;
	FileHandler read;
	Programm programm;
	FileChooser chooser;
	private Befehle befehle;
	private Register register;
	
	public Steuerung (){
		setRegister(new Register());
		gui = new Oberflaeche(this);
		befehle = new Befehle(this);
		takt = new Takt(this);
	}
	
	public void oeffneDatei(){
		chooser = new FileChooser();
		String pfad = chooser.oeffnen();
		leseDatei(pfad);
	}
	
	
	private void leseDatei(String pfad){
		int AnzahlZeilen = gui.getAnzahlZeilen();
		for (int i=1; i < AnzahlZeilen; i++){
		gui.clearErsteZeile();
		}
		read = new FileHandler();
		programm = new Programm(read.getDatei(pfad));
		//HIER GEÄNDERT


		for (int i=0; i < programm.getDatei().size(); i++){
		gui.setZeile(programm.getDatei().get(i));
		}
		//HIER GEÄNDERT
		gui.clearErsteZeile();
	}
	
	public void nextStep(){
		
		isInterrupt();
		
		int opcode = programm.getOpcode(register.getPC());
		
		if (opcode <= 2047 && opcode >= 1792){
			befehle.addwf(opcode);
		}
		else if( opcode <= 1535 && opcode >=1280){
			befehle.andwf(opcode);
		}
		else if( opcode <= 511 && opcode >=256){
			befehle.clr(opcode);
		}
		else if( opcode <= 2559 && opcode >=2304){
			befehle.comf(opcode);
		}
		else if( opcode <= 1023 && opcode >=768){
			befehle.decf(opcode);
		}
		else if( opcode <= 3070 && opcode >=2816){
			befehle.decfsz(opcode);
		}
		else if( opcode <= 2815 && opcode >=2560){
			befehle.incf(opcode);
		}
		else if( opcode <= 4095 && opcode >=3840){
			befehle.incfsz(opcode);
		}
		else if( opcode <=1279 && opcode >=1024){
			befehle.iorwf(opcode);
		}
		else if( opcode <= 0x08FF && opcode >=0x0800){
			befehle.movf(opcode);
		}
		else if( opcode <= 0x00FF && opcode >=0x0080){
			befehle.movwf(opcode);
		}
	/*	else if( opcode <= 0x0060 && opcode >=0x0000){
			befehle.nop(opcode);
		}*/
		else if( opcode <= 0x0dff && opcode >=0x0d00){
			befehle.rlf(opcode);
		}
		else if( opcode <= 0x0cff && opcode >=0x0c00){
			befehle.rrf(opcode);
		}
		else if( opcode <= 0x02ff && opcode >=0x0200){
			befehle.subwf(opcode);
		}
		else if( opcode <= 0x0eff && opcode >=0x0e00){
			befehle.swapf(opcode);
		}
		else if( opcode <= 0x06ff && opcode >=0x0600){
			befehle.xorwf(opcode);
		}
		else if( opcode <= 0x2fff && opcode >=0x2800){
			befehle.gooto(opcode);
		}
		else if( opcode <= 0x33ff && opcode >=0x3000){
			befehle.movlw(opcode);
		}
		else if( opcode <= 0x17ff && opcode >=0x1400){
			befehle.bsf(opcode);
		}
		else if( opcode <= 0x13ff && opcode >=0x1000){
			befehle.bcf(opcode);
		}
		else if( opcode <= 0x27ff && opcode >=0x2000){
			befehle.call(opcode);
		}
		else if( opcode <= 0x3fff && opcode >=0x3e00){
			befehle.addlw(opcode);
		}
		else if( opcode <= 0x37ff && opcode >=0x3400){
			befehle.retlw(opcode);
		}
		else if( opcode <= 0x1bff && opcode >=0x1800){
			befehle.btfsc(opcode);
		}
		else if( opcode <= 0x1fff && opcode >=0x1c00){
			befehle.btfss(opcode);
		}
		else if( opcode <= 0x0dff && opcode >=0x0d00){
			befehle.rlf(opcode);
		}
		else if( opcode <= 0x0cff && opcode >=0x0c00){
			befehle.rrf(opcode);
		}
		else if( opcode <= 0x3dff && opcode >=0x3c00){
			befehle.sublw(opcode);
		}
		else if( opcode <= 0x3aff && opcode >=0x3a00){
			befehle.xorlw(opcode);
		}
		else if( opcode <= 0x38ff && opcode >=0x3800){
			befehle.iorlw(opcode);
		}
		else if( opcode <= 0x39ff && opcode >=0x3900){
			befehle.andlw(opcode);
		}
		else if( opcode  == 0x0008){
			befehle.ret();
		}
		else if( opcode  == 0x0009){
			befehle.retfie();
		}
	}
	
	public int getProgramcounter() {
		return register.getPC();
	}

	public void setProgramcounter(int programcounter) {
		register.setPC(programcounter);
	}
	
	public void incPCounter() {
		register.setPC(register.getPC()+1);
	}

	public Register getRegisterClass() {
		return register;
	}
	
	public short[] getRegister(){
		return register.getRegister();
	}

	public void setRegister(Register register) {
		this.register = register;
	}
	
	public void setW(int wert){
		register.setW(wert);
	}
	public int getW(){
		return register.getW();
	}
	
	public void pushCall(){
		register.setStack(register.getPC()+1);
	}
	
	public void setMarkierungtxt(){
		gui.markiereZeile(programm.getZeile(register.getPC()));
	}
	
	public void startTimer(){
		if (takt.isStartet() == false){
			takt.taktgeber();
		}
	}
	
	public void stopTimer()
	{
		takt.stoptaktgeber();
	}
	
	public void reset(){
		register.reset();
		refreshRegister();
		setMarkierungtxt();
		takt.stoptaktgeber();
	}
	
	
	public String[][] getRegisterArray()
	{
		String[][] DatenSpeicher = new String[32][9];
		DatenSpeicher[0][0]="00";
		DatenSpeicher[1][0]="08";
		DatenSpeicher[2][0]="10";
		DatenSpeicher[3][0]="18";
		DatenSpeicher[4][0]="20";
		DatenSpeicher[5][0]="28";
		DatenSpeicher[6][0]="30";
		DatenSpeicher[7][0]="38";
		DatenSpeicher[8][0]="40";
		DatenSpeicher[9][0]="48";
		DatenSpeicher[10][0]="50";
		DatenSpeicher[11][0]="58";
		DatenSpeicher[12][0]="60";
		DatenSpeicher[13][0]="68";
		DatenSpeicher[14][0]="70";
		DatenSpeicher[15][0]="78";
		DatenSpeicher[16][0]="80";
		DatenSpeicher[17][0]="88";
		DatenSpeicher[18][0]="90";
		DatenSpeicher[19][0]="98";
		DatenSpeicher[20][0]="A0";
		DatenSpeicher[21][0]="A8";
		DatenSpeicher[22][0]="B0";
		DatenSpeicher[23][0]="B8";
		DatenSpeicher[24][0]="C0";
		DatenSpeicher[25][0]="C8";
		DatenSpeicher[26][0]="D0";
		DatenSpeicher[27][0]="D8";
		DatenSpeicher[28][0]="E0";
		DatenSpeicher[29][0]="E8";
		DatenSpeicher[30][0]="F0";
		DatenSpeicher[31][0]="F8";
		int k = 0;
		for(int i=0; i<32; i++)
		{
			for(int j=1; j<9; j++)
			{
				DatenSpeicher[i][j] = Integer.toHexString((int) getRegister()[k]);
				k++;
	
			}
		}
		return DatenSpeicher;
	}
	
	public void refreshRegister(){
		gui.refreshSpeicher(this.getRegisterArray());
	}
	
	private boolean isInterrupt(){
			// Global Interrupt
			boolean interrupt = false;
			if ((register.getWert(0xB) & 128) == 128) {
				// TMR0 Interrupt
				if (((register.getWert(0xB) & 32) == 32) && ((register.getWert(0xB)&4) == 4)) {
					interrupt = true;
					System.err.println("TMR0 Interrupt!");
				}
				// RB0 Interrupt
				if (((register.getWert(0xB) & 16) == 16) && ((register.getWert(0xB)&2) == 2)) {
					interrupt = true;
					System.err.println("RB0 Interrupt!");
				}
				// RB-Change Interrupt
				if (((register.getWert(0xB) & 8) == 8) && ((register.getWert(0xB)&1) == 1)) {
					interrupt = true;
					System.err.println("Port RB Interrupt!");
				}
			}
			if (interrupt) {
				register.clearBit(0xB, 7);
				register.setStack(getProgramcounter());
				setProgramcounter(0x4);
				register.setWert(0x2, (short)0x4);
			}
			return interrupt;
	}
	
	public void portBInterrupt(int pNewVal, int pOldVal) {
		int oldVal = pOldVal;
		int newVal = pNewVal;
		boolean oldB;
		if (oldVal == 1){
			oldB = true;
		}
		else{
			oldB = false;
		}
		boolean newB;
		if (newVal == 1){
			newB = true;
		}
		else{
			newB = false;
		}
		final int MASK = 0xF0;
		// set PortB Pin 4:7 Interruptflag
		if (((oldVal & MASK) ^ (newVal & MASK)) != 0) {
			register.setBit(0xB, 0);
		}
		// check Option-Register Bit 6 (RB0 Interrupt Flank)
		
		if ((register.getWertOhneBank(0x81) & 64) == 64) {
			if (newB && !oldB) {
				register.setBit(0xB, 1);
			}
		} else {
			if (!newB && oldB) {
				register.setBit(0xB, 1);
			}
		}
	}
	
	public void incTMR0(){
		int val = register.getWertOhneBank(0x01);
		val++;
		if(val > 255){
			val = val % 256;
		}
		if(val == 0){
			register.setBit(0xb, 2);
		}
		register.setWertOhneBank(0x01, (short)val);
	}
	
	public void portATMR0(int pNewVal, int pOldVal) {
		int oldVal = pOldVal;
		int newVal = pNewVal;
		boolean oldB;
		if (oldVal == 1){
			oldB = true;
		}
		else{
			oldB = false;
		}
		boolean newB;
		if (newVal == 1){
			newB = true;
		}
		else{
			newB = false;
		}
		if ((register.getWertOhneBank(0x81) & 32) == 32) {
			if ((register.getWertOhneBank(0x81) & 16) == 16) {
				if (!newB && oldB) {
					incTMR0();
				} 
				
			}else{
				if (newB && !oldB) {
					incTMR0();
				}
			}
		}
	}

	public void editPort(String port, int spalte, String val) {
		int bit = 0;
		int value = Integer.valueOf(val);
		switch(spalte){ 
		case 1: bit = 7; break;
		case 2: bit = 6;break;
		case 3: bit = 5;break;
		case 4: bit = 4;break;
		case 5: bit = 3;break;
		case 6: bit = 2;break;
		case 7: bit = 1;break;
		case 8: bit = 0;break;
		}
		if(port.equals("A")){
			if(value == 1){
				register.setBit(0x05, bit);
			}else{
				register.clearBit(0x05, bit);
			}
		}else if(port.equals("B")){
			if(value == 1){
				register.setBit(0x06, bit);
			}else{
				register.clearBit(0x06, bit);
			}
		}else if(port.equals("C")){
			if(value == 1){
				register.setBit(0x07, bit);
			}else{
				register.clearBit(0x07, bit);
			}
		}
		refreshRegister();
	}
	
}
