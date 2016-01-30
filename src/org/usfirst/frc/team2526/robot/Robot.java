package org.usfirst.frc.team2526.robot;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it 
 * contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    RobotDrive myRobot;  // class that handles basic drive operations
    
    CANTalon talonLeft;
    CANTalon talonRight;
    
    Joystick leftStick;  // set to ID 1 in DriverStation
    Joystick rightStick; // set to ID 2 in DriverStation
    
    public Robot() {
        leftStick = new Joystick(0);
        rightStick = new Joystick(1);
        
        talonLeft = new CANTalon(1);
        talonRight = new CANTalon(2);
        
        myRobot = new RobotDrive(talonLeft, talonRight);
    }

    
    /**
     * Runs the motors with tank steering.
     */
    public void operatorControl() {
        
        while (isOperatorControl() && isEnabled()) {
        	//myRobot.arcadeDrive(leftStick, true);
        	boolean crane = leftStick.getZ() >= 0.5;
        	boolean tank = leftStick.getZ() < 0.5 && leftStick.getZ() > -0.5;
        	
        	if (tank) {
        		myRobot.tankDrive(leftStick, rightStick);
        	} else {
	        	double x = 0;
	        	
	        	if (crane) 
	        		x = rightStick.getX();
	        	else
	        		x = leftStick.getX();
	        	
	        	myRobot.arcadeDrive(-leftStick.getY(), -x);
        	}
        	
        	//talonLeft.set(leftStick.getY());
            //talonRight.set(rightStick.getY());
        	
            Timer.delay(0.005);		// wait for a motor update time
        }
    }

}
