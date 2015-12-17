package com.evanstonrobotics.ftcrobot.opmodes

import com.evanstonrobotics.ftcrobot.utility.STATES
import com.evanstonrobotics.ftcrobot.utility.SensorService
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor

/**
 * @author Alex Brooke
 * @date 12 Dec, 2015
 */

public class AutoOpMode : OpMode() {
    override fun init() {
        throw UnsupportedOperationException()
    }

    private val motorLeft : DcMotor = hardwareMap.dcMotor.get("motorLeft")
    private val motorRight : DcMotor = hardwareMap.dcMotor.get("motorRight")

    private val X = 0
    private val Y = 1
    private val Z = 2

    private var state : STATES = STATES.MoveToMountain


    override fun loop() {
        when(state) {
            STATES.MoveToMountain -> moveToMountain()
            STATES.ClimbMountain -> climbMountain()
        }

        telemetry.addData("Text", "*** Robot Data***")
        telemetry.addData("left tgt pwr", "left  pwr: " + "%.2f".format(motorLeft.power))
        telemetry.addData("right tgt pwr", "right pwr: " + "%.2f".format(motorRight.power))
    }

    fun moveToMountain() {
        //TODO: figure this out
        state = STATES.ClimbMountain
    }

    fun climbMountain() {
        //TODO: test these values pl0x
        var accel = SensorService.accelerometer
        telemetry.addData("accelerometer", "(${accel[X]}, ${accel[Y]}, ${accel[Z]}")

        motorLeft.power = 0.5
        motorRight.power = 0.5

        if(accel[X] > 1) {
            motorLeft.power = 0.75
            motorRight.power = 0.75
        }
    }

}