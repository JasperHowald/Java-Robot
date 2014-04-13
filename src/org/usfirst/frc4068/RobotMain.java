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
    
    Hashtable refs = new Hashtable();
    PeriodicTasks tasks = new PeriodicTasks();
    
    public void robotInit() {
        
        /*
         * Initialize code here - create joysticks/drive trains/any other objects to be used in code
         * To initialize an object - create a new object of that type ex. String s = "test";
         * - add it to the reference table with a reference name ex. refs.put("stringex", s);
         * **note - when referencing the object later, use refs.get("reference"), and use a cast to the
         * original object type ex. String y = ((string)refs.get("stringex")); - make sure to include an
         * extra set of prenthisis around the whole thing to affect the result of the whole statement (String),
         * rather than the object returned (Object)**
         */
        
        RobotDrive drive = new RobotDrive(1, 2, 3, 4);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        refs.put("drive", drive);
        
        Joystick driver = new Joystick(1);
        refs.put("driver", driver);
        
        Joystick coDriver = new Joystick(1);
        refs.put("coDriver", coDriver);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        tasks.run("Autonomous", refs);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        tasks.run("Teleop", refs);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        tasks.run("Test", refs);
    }
    
}
