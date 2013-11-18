import	lejos.robotics.*;	
import	lejos.robotics.subsumption.*;	
import	lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Wander implements Behavior {

	DifferentialPilot robot;
	
	
	public Wander (DifferentialPilot p){
		
		robot = p; 
	}
	
	@Override
	public boolean takeControl() {
		// if all sensors are clear, take control?
		return true;
	}

	@Override
	public void action() {
		

	}

	@Override
	public void suppress() {
		// update coordinates? stop.

	}

}
