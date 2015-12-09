package com.evanstonrobotics.ftcrobot.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * @author Alex
 */
public class TestOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motorLeft = hardwareMap.dcMotor.get("motorLeft");
        DcMotor motorRight = hardwareMap.dcMotor.get("motorRight");
        motorRight.setDirection(DcMotor.Direction.REVERSE);

        motorRight.setPower(0.5D);
        motorLeft.setPower(0.5D);
        sleep(1000L);

        motorRight.setPower(0.25D);
        sleep(500L);

        motorRight.setPower(0.5D);
        sleep(1000L);

        motorLeft.setPower(0.25D);
        sleep(500L);
    }
}
