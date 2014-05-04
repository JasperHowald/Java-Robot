/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4068;

import org.usfirst.frc4068.code.*;
import org.usfirst.frc4068.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

import java.util.Hashtable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {
    
    //Global variables - Class refs
    Hashtable refs = new Hashtable();
    PeriodicTasks tasks = new PeriodicTasks();
    Begin begin = new Begin(refs);
    
    //Called when the robot is turned on - initialize objects here
    public void robotInit() {
        
        begin.init();
        
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
        ((Compressor)refs.get("Compressor")).stop();
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
        t.start();
        int auto_time = 10; //Amount of time allocated to autonomous in seconds
        RobotDrive drive = ((RobotDrive)refs.get("drive"));
        
        (new Thread(new Begin(refs, "auto_1"))).start();
        
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
    	Joystick driver = ((Joystick)refs.get("driver"));
        ((RobotDrive)refs.get("drive")).arcadeDrive(driver.getRawAxis(1), driver.getRawAxis(2));
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
