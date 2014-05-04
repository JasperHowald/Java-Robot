package org.usfirst.frc4068.subsystems;

import java.util.Hashtable;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;

public class Launcher {
    
    Hashtable refs;
    
    public Launcher(Hashtable refs) {
        this.refs = refs;
    }
    
    public void launch() {
        ((DoubleSolenoid)refs.get("Launcher")).set(DoubleSolenoid.Value.kForward);
    }
    
    public void launch(double wait) {
        Timer.delay(wait);
        ((DoubleSolenoid)refs.get("Launcher")).set(DoubleSolenoid.Value.kForward);
    }
    
    public void release() {
        ((DoubleSolenoid)refs.get("Launcher")).set(DoubleSolenoid.Value.kReverse);
    }
}
