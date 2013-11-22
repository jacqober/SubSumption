import	lejos.robotics.*;	
import	lejos.robotics.subsumption.*;	
import	lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import java.io.File;

public class Feed implements Behavior {
	
	DifferentialPilot robot;
	LightSensor ls;
	File music = new File("christmas.wav");
	boolean suppressed = false; 
	int generalLight;
	int currentLight;
	
	public Feed (DifferentialPilot p, LightSensor l) throws InterruptedException{
		
		robot = p;
		ls = l;	
		ls.setFloodlight(Color.WHITE);
		generalLight = ls.readValue();
		currentLight = ls.readValue();
		//Thread.sleep(5000);	
		
	}

	@Override
	public boolean takeControl() {
		currentLight = ls.readValue();
		if(generalLight - currentLight > 20) { //we probably need to calibrate
			return true;
		}else{
			suppressed = true;
			return false;
		}
	}

	@Override
	public void suppress() {
		suppressed = true; 
		//music should stop
	}
	
	@Override
	public void action() {
		
		try	{		
			Thread.yield();	
			Thread.sleep(1000);	
		}catch(InterruptedException	ie)	{}	
			robot.stop();
			lejos.nxt.Sound.playSample(music, 90);
			
			robot.travel(10); //move away from the food
			//should i make suppressed = true; or call suppress?
	}

}
