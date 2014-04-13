package org.usfirst.frc4068.code;

import java.util.Hashtable;

public class PeriodicTasks {
    public void run(String mode, Hashtable refs){
        //This code runs periodicly while the robot is enabled (in any state)
        if(mode.equals("Teleop")){
            //This code runs periodicly during Teleop
        }else if(mode.equals("Autonomous")){
            //This code runs periodicly during Autonomous
        }else if(mode.equals("Test")){
            //This code runs periodicly durng Test mode
        }
    }
}
