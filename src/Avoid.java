import	lejos.robotics.*;	
import	lejos.robotics.subsumption.*;	
import	lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Avoid implements Behavior {

	@Override
	public boolean takeControl() {
		// TODO if sensor has detected a wall/obstacle
	
		return true;
	}

	@Override
	public void action() {
		// TODO//randomly turn right or left

	}

	@Override
	public void suppress() {
		// TODO do nothing, wait for next obstacle

	}

}

