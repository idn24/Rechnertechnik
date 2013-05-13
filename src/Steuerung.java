import java.io.File;


public class Steuerung {

	/**
	 * @param args
	 */
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
	
	public int run(){
		boolean run = true;
		int opcode =0;
		register.setPC(0);
		while (run){
			
			opcode = programm.getOpcode(register.getPC());
			
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
			else if( opcode <= 0x0060 && opcode >=0x0000){
				befehle.nop(opcode);
			}
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
		}
		
		return 0;
	}
	
	public void nextStep(){
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
		else if( opcode <= 0x0060 && opcode >=0x0000){
			befehle.nop(opcode);
		}
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

}
