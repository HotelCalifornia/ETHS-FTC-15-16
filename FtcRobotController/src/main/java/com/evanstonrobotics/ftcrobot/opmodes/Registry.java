package com.evanstonrobotics.ftcrobot.opmodes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
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
        REGISTRY = new HashMap<>();
        REGISTRY.put("TestOpMode", TestOpMode.class);
    }
}
