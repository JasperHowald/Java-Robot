package org.usfirst.frc4068.code;

import java.util.Hashtable;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PeriodicTasks {
    public void run(String mode, Hashtable refs){
    	Begin code = new Begin(refs);
        //This code runs periodically while the robot is enabled (in any state)
    	((Compressor)refs.get("Compressor")).start();
    	SmartDashboard.putNumber("Ultrasonic distance", code.ultrasonicGetDistance(0.0125));
        if(mode.equals("Teleop")){
            //This code runs periodically during Teleop
        }else if(mode.equals("Autonomous")){
            //This code runs periodically during Autonomous
        }else if(mode.equals("Test")){
            //This code runs periodically during Test mode
        }
    }
}
