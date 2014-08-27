package org.usfirst.frc4068.code;

public class Teleop {
	//This code was pulled, slightly modified, off a tutorial found at http://wpilib.screenstepslive.com/s/3120/m/7885/l/79459-the-hello-world-of-frc-robot-programming
	chassis.setSafetyEnabled(true);
	while (isOperatorControl() && inEnabled()) {
		chassis.tankDrive(leftStick, rightStick);
		Timer.delay(0.01);
	}
}
