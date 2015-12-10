package com.evanstonrobotics.ftcrobot.opmodes;

import com.evanstonrobotics.ftcrobot.utility.SensorService;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * @author Alex
 */
public class TestOpMode extends OpMode {

    private DcMotor motorLeft;
    private DcMotor motorRight;

    @Override
    public void init() {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft.setDirection(DcMotor.Direction.REVERSE); // set up front wheel drive
    }

    @Override
    public void loop() {
        //testing accelerometer values
        float[] accel = SensorService.getAccelerometer();
        telemetry.addData("accelerometer",
                String.format("(%.5f, %.5f, %.5f)", accel[0], accel[1], accel[2]));
        //testing ready-smoothed accelerometer values
        float[] grav = SensorService.getGravity();
        telemetry.addData("gravity",
                String.format("(%.5f, %.5f, %.5f)", grav[0], grav[1], grav[2]));
        //motorRight.setPower(0.25D);
        //motorLeft.setPower(0.25D);
    }
}
