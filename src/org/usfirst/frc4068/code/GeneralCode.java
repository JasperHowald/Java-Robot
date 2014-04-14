package org.usfirst.frc4068.code;

import java.util.Hashtable;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.camera.*;
import edu.wpi.first.wpilibj.Talon;

public class GeneralCode implements Runnable{
    RobotDrive drive;
    Hashtable refs;
    Launcher launcher = new Launcher(refs);
    Claws claws = new Claws(refs);
    String thread;
    
    public GeneralCode(Hashtable refs) {
        this.refs = refs;
    }
    
    public GeneralCode(Hashtable refs, String thread) {
        this.refs = refs;
        this.thread = thread;
    }
    
    public void run() {
        run(thread);
    }
    
    public void run(String thread) {
        if (thread.equals("auto_1")) {
            int time = 3; //waits for 3 seconds until launching
            claws.upDown(0.3);
            launcher.release();
            launcher.launch(time);
        }
    }
    
    public void init() {
        /*
         * Initialize code here - create joysticks/drive trains/any other objects to be used in code
         * To initialize an object - create a new object of that type ex. String s = "test";
         * - add it to the reference table with a reference name ex. refs.put("stringex", s);
         * **note - when referencing the object later, use refs.get("reference"), and use a cast to the
         * original object type ex. String y = ((string)refs.get("stringex")); - make sure to include an
         * extra set of parenthesis around the whole thing to affect the result of the whole statement (String),
         * rather than the object returned (Object)**
         */
        
        //Initialize the robot drive
        drive = new RobotDrive(3, 4, 1, 2);
        //frontLeft, rearLeft, frontRight, rearRight
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
        refs.put("drive", drive);
        
        //Initialize claw motors
        Talon arm = new Talon(5);
        Talon close = new Talon(6);
        refs.put("Up/Down", arm);
        refs.put("Open/Close", close);
        
        //Initialize Joysticks
        Joystick driver = new Joystick(1);
        Joystick coDriver = new Joystick(1);
        refs.put("driver", driver);
        refs.put("coDriver", coDriver);
        
        //Initialize pneumatics system
        Compressor compressor = new Compressor(14, 1);
        DoubleSolenoid launcher = new DoubleSolenoid(2, 3);
        refs.put("Compressor", compressor);
        refs.put("Launcher", launcher);
        
        //Initialize camera
        AxisCamera camera = AxisCamera.getInstance("10.40.68.2");
        camera.writeResolution(AxisCamera.ResolutionT.k160x120);
        camera.writeMaxFPS(12);
        camera.writeWhiteBalance(AxisCamera.WhiteBalanceT.fixedFlour1);
        camera.writeExposureControl(AxisCamera.ExposureT.automatic);
        camera.writeCompression(40);
        camera.writeBrightness(50);
        Relay leds = new Relay(1, 3, Relay.Direction.kForward);
        refs.put("Camera", camera);
        refs.put("Camera-leds", leds);
        
        //Additional sensors
        AnalogModule analog1 = AnalogModule.getInstance(1);
        AnalogChannel sonar = new AnalogChannel(1, 6);
        refs.put("AM1", analog1);
        refs.put("Ultrasonic", sonar);
    }
    public double ultrasonicGetDistance(int scale) {
        double distance;
        AnalogChannel ultrasonic = ((AnalogChannel)refs.get("Ultrasonic"));
        ultrasonic.setAverageBits(3);
        ultrasonic.setOversampleBits(0);
        ((AnalogModule)refs.get("AM1")).setSampleRate(10);
        distance = (ultrasonic.getAverageVoltage()/scale);
        return distance;
    }

}
