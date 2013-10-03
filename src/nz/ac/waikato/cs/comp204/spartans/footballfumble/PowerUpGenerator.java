package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import java.util.Random;

public class PowerUpGenerator extends Thread{

	public PowerUp powerUp = null;
	
	public PowerUpGenerator(){
		
	}
	
	public void run(){
		while(true){

			if((int)Math.random()*(0-20) == 7){
				powerUp = new PowerUp((int)Math.random()*(0-Match.drawView.screenWidth), (int)Math.random()*(0-Match.drawView.screenHeight), Match.drawView, "speed");
			}
			else if((int)Math.random()*(0-20) == 17){
				powerUp = new PowerUp((int)Math.random()*(0-Match.drawView.screenWidth), (int)Math.random()*(0-Match.drawView.screenHeight), Match.drawView, "strength");
			}
			
			try{
				Thread.sleep(5000);
				if(powerUp != null){
					powerUp.deleteInstance();
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
	
}
