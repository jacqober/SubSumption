import java.io.File;

import	lejos.robotics.*;	
import	lejos.robotics.subsumption.*;	
import	lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Avoid implements Behavior, SensorPortListener {

	DifferentialPilot robot;
	UltrasonicSensor us;
	int blockInRange;
	boolean blockClose;
	//boolean suppressed = false; 

	
	public Avoid (DifferentialPilot p, UltrasonicSensor u){
		
		robot = p;
		us = u;	
		SensorPort.S4.addSensorPortListener(this);
		blockInRange = 30; //should back up if 1 ft from an object
	}
	@Override
	public boolean takeControl() {
		if(blockClose == true){
			blockClose = false; //reset range
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void action() {
		int rdNum = 0 ;
		robot.travel(-30,true); // travel backwards 30 cm
	
		try	{		
			Thread.yield();	
			Thread.sleep(1000);		//	Stops	for	a	short	time	(one	second)		
		}catch(InterruptedException	ie)	{}		
		rdNum = (int) Math.random() * 10;
		if(rdNum % 2 == 0) {
			robot.rotate(90);
		}else{
			robot.rotate(-90);
		}
	}

	@Override
	public void suppress() {
		// TODO do nothing, wait for next obstacle
       robot.stop();
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		// TODO Auto-generated method stub
		if (us.getDistance() <= blockInRange){
			blockClose = true;
		}else{
			blockClose = false;
		}	
	}	
}

