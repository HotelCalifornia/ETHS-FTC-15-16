package com.evanstonrobotics.ftcrobot.opmodes;

import com.evanstonrobotics.ftcrobot.utility.SensorService;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

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
        motorRight.setDirection(DcMotor.Direction.REVERSE); // set up front wheel drive
    }

    @Override
    public void loop() {
        float left = -gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;

        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);

        right = (float) scaleInput(right);
        left = (float) scaleInput(left);
        //testing accelerometer values
        float[] accel = SensorService.INSTANCE.getAccelerometer();
        telemetry.addData("accelerometer",
                String.format("(%.5f, %.5f, %.5f)", accel[0], accel[1], accel[2]));
        telemetry.addData("gamepad1",
                String.format("Left Stick: %.5f, Right Stick %.5f", gamepad1.left_stick_y, gamepad1.right_stick_y));
        motorRight.setPower(right);
        motorLeft.setPower(left);
    }

    private double scaleInput(double input) {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};
        int index = (int) (input * 16.0);
        if (index < 0) {
            index = -index;
        }
        if (index > 16) {
            index = 16;
        }
        double scale = 0.0;
        if (input < 0) {
            scale = -scaleArray[index];
        } else {
            scale = scaleArray[index];
        }
        return scale;
    }
}
