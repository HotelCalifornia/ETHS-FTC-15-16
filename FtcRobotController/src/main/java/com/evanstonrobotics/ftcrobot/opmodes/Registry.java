package com.evanstonrobotics.ftcrobot.opmodes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex Brooke
 * A registry containing a map of our custom opmodes ("Name"=>Name.class)
 * {@see com.qualcomm.ftcrobotcontroller.opmodes.FtcOpModeRegister#register(OpModeManager)}
 */
public class Registry {
    /**
     * Whenever a new opmode is created, add it to this list so it can be registered by the
     * controller activity
     */
    public static final Map<String, Class> REGISTRY;
    static {

        //Technically, we could omit the explicit type parameter declaration, but Java didn't let you do that before
        // 1.6, so we'll leave it in, 'k
        REGISTRY = new HashMap<String, Class>();

        REGISTRY.put("Manual OpMode", ManualOpMode.class);
        REGISTRY.put("Autonomous OpMode", AutoTestOpMode.class);
        REGISTRY.put("Test Manual", TestOpMode.class);
    }
}
