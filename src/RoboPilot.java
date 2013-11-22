import lejos.nxt.*;
import	lejos.robotics.*;
import	lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;

//TODO: finish adding listeners to behavior classes. add the buttons for terminating programs
//maybe add the sound behavior when everything works
//light calibration (realized she wanted us to use the lightsensor so i changed it back)
//test tha baby bot

public class RoboPilot {

		private double trackWidth =11; //size of base, centimeters
		private double wheelDiameter = 5.6; //size of wheel diameter, centimeters
		private DifferentialPilot nav; //Pilot to guide robot's rotating and traversing	
		boolean amIMoving;//is the robot moving
		
		//TouchSensor	tSensor	=	new	TouchSensor(SensorPort.S1);	
		UltrasonicSensor us;
		LightSensor ls;	

		Behavior b1;
		Behavior b2;
		Behavior b3;
		Behavior[] bArray; 
		
		public RoboPilot (boolean m, char f) throws InterruptedException{
			amIMoving = m;
			nav = new DifferentialPilot (wheelDiameter, trackWidth, Motor.C, Motor.A);
			us = new UltrasonicSensor(SensorPort.S4);
			ls = new LightSensor(SensorPort.S1);
			initializePilot();
			b1 = new Wander(nav);
			b2 = new Avoid(nav,us);
			b3 = new Feed(nav,ls);
			bArray = new Behavior[] {b1,b2,b3};
		}
		
		//Set up pilot control and speed
		public void initializePilot(){	
			nav.reset(); //Reset tachoCount on both motors
			nav.setTravelSpeed(8);//Set travel and rotate speed
			nav.setRotateSpeed(30);
		}
		
		public void waitRobot(){
			Button.waitForAnyPress();
		}
		
				
		
		//Set robot in motion
		public void moveForward (float distanceInCM){
			//Distance to move
			float distance = distanceInCM;
			//Lejos method to have pilot drive bot forward
			nav.travel(distance, false);
			//empty loop to prevent immediateReturn, which terminates robot motion
			while (nav.isMoving()) {
			    }
		}
		//return true if robot is moving
		public boolean checkIfMoving(){
			amIMoving= nav.isMoving();
			return amIMoving;
		}

	

}
