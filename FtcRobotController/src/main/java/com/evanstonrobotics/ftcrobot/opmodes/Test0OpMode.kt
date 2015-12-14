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

    override fun init() {
        motorLeft.direction = DcMotor.Direction.REVERSE
    }

    override fun loop() {
        var accel = SensorService.accelerometer
        telemetry.addData("accelerometer", "(${accel[0]}, ${accel[1]}, ${accel[2]}")
    }

}