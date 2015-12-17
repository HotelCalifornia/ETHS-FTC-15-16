package com.evanstonrobotics.ftcrobot.opmodes;

import com.evanstonrobotics.ftcrobot.utility.STATES;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * @author Alex Brooke
 * @date 17 Dec, 2015
 */
public class AutoTestOpMode extends LinearOpMode {

    private static final int     ENCODER_CPR              = 1120;
    private static final double  EFFECTIVE_WHEEL_RADIUS   = 8.25d;
    private static final double  EFFECTIVE_WHEEL_DIAMETER = 2d * EFFECTIVE_WHEEL_RADIUS;

    private static final double  CIRCUMFERENCE            = Math.PI * EFFECTIVE_WHEEL_DIAMETER;

    private              DcMotor motorLeft;
    private              DcMotor motorRight;
    private              Servo   wheelieBar;

    private              STATES  state;
    private              double  currentTarget;

    @Override
    public void runOpMode() {
        this.motorLeft  = hardwareMap.dcMotor.get("motorLeft");
        this.motorRight = hardwareMap.dcMotor.get("motorRight");
        this.wheelieBar = hardwareMap.servo.get("wheelieBar");
        this.state      = STATES.MoveToMountain;

        this.motorRight.setDirection(DcMotor.Direction.REVERSE);

        this.wheelieBar.setPosition(0.25d);

        this.motorLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        this.motorRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        run: while(this.opModeIsActive()) {
            switch(this.state) {
                case MoveToMountain:
                    this.moveToMountain();
                    break;
                case ClimbMountain:
                    this.climbMountain();
                    break;
                case End:
                    break run; //wow I never knew that was how that worked
                default:
                    throw new
                            UnsupportedOperationException("Something went horribly wrong in " + this.getClass().getName());
            }
        }
    }

    private void moveToMountain() {
        this.currentTarget = calculateEncoderPulses(24d);
        this.motorLeft.setTargetPosition((int) this.currentTarget);
        this.motorRight.setTargetPosition((int) this.currentTarget);

        this.motorLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        this.motorRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        this.motorLeft.setPower(0.25d);
        this.motorRight.setPower(0.25d);

        this.state = STATES.ClimbMountain;
    }

    private void climbMountain() {
        this.state = STATES.End;
    }

    private double calculateEncoderPulses(double distance) {
        return ENCODER_CPR * (distance / CIRCUMFERENCE);
    }
}
