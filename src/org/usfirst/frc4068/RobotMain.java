/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4068;

import org.usfirst.frc4068.code.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import java.util.Hashtable;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {
    
    //Global variables
    Hashtable refs = new Hashtable();
    PeriodicTasks tasks = new PeriodicTasks();
    
    //Called when the robot is turned on - initialize objects here
    public void robotInit() {
        
        /*
         * Initialize code here - create joysticks/drive trains/any other objects to be used in code
         * To initialize an object - create a new object of that type ex. String s = "test";
         * - add it to the reference table with a reference name ex. refs.put("stringex", s);
         * **note - when referencing the object later, use refs.get("reference"), and use a cast to the
         * original object type ex. String y = ((string)refs.get("stringex")); - make sure to include an
         * extra set of parenthesis around the whole thing to affect the result of the whole statement (String),
         * rather than the object returned (Object)**
         */
        
        RobotDrive drive = new RobotDrive(1, 2, 3, 4);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
        refs.put("drive", drive);
        
        Joystick driver = new Joystick(1);
        refs.put("driver", driver);
        
        Joystick coDriver = new Joystick(1);
        refs.put("coDriver", coDriver);
    }
    
    /*
     * For each state of the robot (disabled, autonomous, teleop, and test) there are three
     * methods - Init, Periodic, and Continuous. Init is run when the robot enters that state,
     * either for the first time, or when switching between states. Periodic is called
     * periodically, every driver station update, or about every .2 seconds. Continuous is
     * called, and runs it's code. Once the code has been run, continuous is called again
     * assuming the sate of the robot has not changed. If the robot's state changes during the
     * continuous method, the code that is currently running will immediately stop.
     */
    
    //Disabled
    public void disabledInit() {
        //Sets safety on drive train to on
        ((RobotDrive)refs.get("drive")).setSafetyEnabled(true);
    }
    public void disabledPeriodic() {
        
    }
    public void disabledContinuous() {
        //continuously sets actuators to 0, to avoid unintended motion
        ((RobotDrive)refs.get("drive")).arcadeDrive(0, 0);
    }
    
    //Autonomous
    public void autonomousInit() {
        ((RobotDrive)refs.get("drive")).setSafetyEnabled(false);
    }
    //Called periodically during the autonomous part of the match
    public void autonomousPeriodic() {
        tasks.run("Autonomous", refs);
    }
    public void autonomousContinuous() {
        Timer t = new Timer(); //Start the timer as soon as autonomous starts
        int auto_time = 10; //Amount of time allocated to autonomous in seconds
        RobotDrive drive = ((RobotDrive)refs.get("drive"));
        
        t.start();
        drive.drive(-0.5, 0.0);
        Timer.delay(2.0);
        drive.drive(0, 0);
        
        while(t.get()!=auto_time) {Timer.delay(0.1);} //If autonomous is not over yet, wait until it is. This should prevent autonomous code from running more than once
    }

    //Teleop
    //Called when the robot enters the teleop period for the first time
    public void teleopInit() {
        
    }
    //Called periodically during the teleoperation part of the match
    public void teleopPeriodic() {
        tasks.run("Teleop", refs);
    }
    //Called continuously while in the teleop part of the match
    public void teleopContinuous() {
        
    }
    
    //Called when the robot first enters test mode
    //Test
    public void testInit() {
        
    }
    //Called periodically during test mode
    public void testPeriodic() {
        tasks.run("Test", refs);
    }
    //called continuously during test mode
    public void testContinuous(){
        
    }
    
}
