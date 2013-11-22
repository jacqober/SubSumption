import	lejos.robotics.*;	
import	lejos.robotics.subsumption.*;	
import	lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Wander implements Behavior {

	DifferentialPilot robot;
	int upperThresh = 45;
	int lowerThresh = 60;
	int range = 100;
	
	public Wander (DifferentialPilot p){
		
		robot = p; 
	}
	
	@Override
	public boolean takeControl() {
		// if all sensors are clear, take control
		return true;
	}

	@Override
	public void action() {
		int rdNum = 0;
		try	{		
			Thread.yield();	
			Thread.sleep(1000);	
		}catch(InterruptedException	ie)	{}	
	   
		while(rdNum<lowerThresh && rdNum > upperThresh){
			robot.forward();
			rdNum = (int)Math.random() * range; 
	    } 
		if(rdNum % 2 == 0){
	    	robot.rotateLeft();
	    }else{
	    	robot.rotateRight();
	    }
	}

	@Override
	public void suppress() {
		// update coordinates? stop.
		robot.stop();

	}

}
