import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;


public class Takt {
	private boolean startet = false;
	


	Timer timer;
	private Steuerung strg;
	
	public Takt(Steuerung strg){
		this.strg = strg;
	}
	
	/**
	 * Takt f�r den Programmablauf
	 */
	public void taktgeber(){
		startet = true;
		TimerTask action = new TimerTask() {
			
			@Override
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						strg.nextStep();
						strg.setMarkierungtxt();
						strg.refreshRegister();
					}
				});
			}
		};

		timer = new Timer();
		timer.schedule(action, 0, 200);
	}
	
	/**
	 * Stoppt den takt f�r Pause oder Reset
	 */
	public void stoptaktgeber() {
		startet = false;
		timer.cancel();
		timer.purge();
		
	}
	
	/**
	 * �berpr�ft ob das Programm bereits l�uft
	 * @return
	 */
	public boolean isStartet() {
		return startet;
	}
}
