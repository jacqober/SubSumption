import lejos.nxt.*;
import	lejos.robotics.*;
import	lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;

public class RoboPilot {

		private double trackWidth =11; //size of base, centimeters
		private double wheelDiameter = 5.6; //size of wheel diameter, centimeters
		private DifferentialPilot nav; //Pilot to guide robot's rotating and traversing	
		char facing = 'N'; //robot initially faces North - NSWE
		boolean amIMoving;//is the robot moving
		
		//TouchSensor	tSensor	=	new	TouchSensor(SensorPort.S1);	
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S4);
		ColorSensor cs = new ColorSensor(SensorPort.S1);
		
		Behavior b1;
		
		public RoboPilot (boolean m, char f) throws InterruptedException{
		amIMoving = m;
		facing = f; 
		nav = new DifferentialPilot (wheelDiameter, trackWidth, Motor.C, Motor.A);
		initializePilot();
		b1 = new Feed(nav,cs);
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Turning functions for rotating robot in tile, 
		//param is the direction robot is to face before moving forward
		public void turnLeft (char direction){	
			char goalDirection = direction; //store param locally
			nav.rotate(90,false); //rotate 90 degrees counter clockwise
			char turned = 'L'; 	//turning left
			updateFacing(turned); //update current direction robot is facing
			checkDirection(turned, goalDirection); //pass check direction turning path and goal direction	
		}
		
		//Turning functions for rotating robot in tile, 
		//param is direction robot is to face before moving forward
		public void turnRight (char direction){		
			char goalDirection = direction; //store param locally
			nav.rotate(-90,false);
			char turned = 'R';
			updateFacing(turned);	
			checkDirection(turned, goalDirection);
		}
		//update which direction robot is facing
		private void updateFacing(char turn){
			char turned = turn;
			//depending on which direction the robot was facing and which was it turned
			//it is now facing N or S or W or E
			if(facing == 'N' && turned == 'L')
				facing = 'W';
			else if(facing == 'N' && turned == 'R')
				facing = 'E';	
			else if (facing == 'W' && turned == 'L')
				facing = 'S';
			else if (facing == 'W' && turned == 'R')
				facing = 'N';
			else if (facing == 'S' && turned == 'L')
				facing = 'E';
			else if (facing == 'S' && turned == 'R')
				facing = 'W';
			else if (facing == 'E' && turned == 'L')
				facing = 'N';
			else if (facing == 'E' && turned == 'R')
				facing = 'S';
			else
				System.out.println("Provided bad direction, check char.");
		}
		//Check direction robot is supposed to move
		public void checkDirection (char turn, char direct){
			//robot turns right 'R' or left 'L'
			char turning = turn;
			//goal direction can be 'N' 'S' 'W' 'E'
			char goalDirection = direct;
			//if robot is not facing goal direction, turn R or L
			if(goalDirection != facing && turning == 'L')
				turnLeft(goalDirection);
			else if(direct != facing && turning == 'R')
				turnRight(goalDirection);
			else{
				System.out.println("Facing correct direction, send me forward.");
				System.out.println("I have turned" + " " + nav.getAngleIncrement() + " "+ "degrees");
				//Set robot in motion given cell distance
				moveForward((float) 50);
			}
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
		//getters/setters for instance properties
		public void setFacing (char f){
			facing = f;
		}
		public char getFacing(){
			return facing;
		}


}
