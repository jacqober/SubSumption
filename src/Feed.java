import	lejos.robotics.*;	
import	lejos.robotics.subsumption.*;	
import	lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import java.io.File;

public class Feed implements Behavior {
	
	DifferentialPilot robot;
	ColorSensor cs;
	File music = new File("christmas.wav");
	boolean suppressed = false; 
	
	public Feed (DifferentialPilot p, ColorSensor c) throws InterruptedException{
		
		robot = p;
		cs = c;	
		cs.setFloodlight(Color.WHITE);
		System.out.println( cs.isFloodlightOn() + " " + cs.getColorID());
		Thread.sleep(5000);	
		
	}

	@Override
	public boolean takeControl() {
		
		if(cs.getColorID() == 7) {		//this might have to be changed after setting a threshold, need robot
			return true;
		}else{
			suppressed = true;
			return false;
		}
	}

	@Override
	public void suppress() {
		if(suppressed == true){
		Thread.yield();	
		}
	}
	
	@Override
	public void action() {
		
		try	{		
			Thread.yield();	
			Thread.sleep(1000);	
		}catch(InterruptedException	ie)	{}	
			robot.stop();
			lejos.nxt.Sound.playSample(music);
	}

}
