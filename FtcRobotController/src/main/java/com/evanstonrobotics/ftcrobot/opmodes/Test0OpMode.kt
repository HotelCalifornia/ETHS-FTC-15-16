package com.evanstonrobotics.ftcrobot.opmodes

import com.evanstonrobotics.ftcrobot.utility.SensorService
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor

/**
 * @author Alex Brooke
 * @date 12 Dec, 2015
 */

public class Test0OpMode : OpMode() {

    private val motorLeft : DcMotor = hardwareMap.dcMotor.get("motorLeft")
    private val motorRight : DcMotor = hardwareMap.dcMotor.get("motorRight")

    private val X = 0
    private val Y = 1
    private val Z = 2

    override fun init() {
        motorLeft.direction = DcMotor.Direction.REVERSE
    }

    override fun loop() {
        var accel = SensorService.accelerometer
        telemetry.addData("accelerometer", "(${accel[X]}, ${accel[Y]}, ${accel[Z]}")

        motorLeft.power = 0.5
        motorRight.power = 0.5

        //Do testing to confirm values and axes
        if(accel[X] > 1) {
            //do testing to confirm power
            motorLeft.power = 0.75
            motorRight.power = 0.75
        }
    }

}