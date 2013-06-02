import java.util.Timer;
import java.util.TimerTask;


public class Takt {
	Timer timer;
	private Steuerung strg;
	
	public Takt(Steuerung strg){
		this.strg = strg;
	}
	

	public void taktgeber(){
		TimerTask action = new TimerTask() {
			public void run() {
				strg.nextStep();
				strg.setMarkierungtxt();
				strg.refreshRegister();
			}
		};

		timer = new Timer();
		timer.schedule(action, 0, 1000);
	}


	public void stoptaktgeber() {
		timer.cancel();
		timer.purge();
		
	}
	
	
	
}
