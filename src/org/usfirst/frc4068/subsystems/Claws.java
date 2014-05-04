package org.usfirst.frc4068.subsystems;

import java.util.Hashtable;
import edu.wpi.first.wpilibj.Talon;

public class Claws {
    Hashtable refs;
    public Claws(Hashtable refs) {
        this.refs = refs;
    }
    
    public void openClose(double speed) {
        ((Talon)refs.get("Open/Close")).set(speed);
    }
    
    public void upDown(double speed) {
        ((Talon)refs.get("Open/Close")).set(speed);
    }
}
