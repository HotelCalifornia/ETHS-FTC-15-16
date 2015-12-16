package com.evanstonrobotics.ftcrobot.opmodes

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.Range

/**
 * @author Alex Brooke
 * @date 16 Dec, 2015
 */

public class ManualOpMode : OpMode() {

    private val motorLeft : DcMotor = hardwareMap.dcMotor.get("motorLeft")
    private val motorRight : DcMotor = hardwareMap.dcMotor.get("motorRight")

    override fun loop() {
        var left : Float = -gamepad1.left_stick_y
        var right : Float = -gamepad1.right_stick_y

        left = Range.clip(left, -1f, 1f)
        right = Range.clip(right, -1f, 1f)

        left = scaleInput(left.toDouble()).toFloat()
        right = scaleInput(right.toDouble()).toFloat()

        motorLeft.power = left.toDouble()
        motorRight.power = right.toDouble()

        //TODO: add scooper functionality

        telemetry.addData("Text", "*** Robot Data***")
        telemetry.addData("left tgt pwr", "left  pwr: " + "%.2f".format(left))
        telemetry.addData("right tgt pwr", "right pwr: " + "%.2f".format(right))
    }

    override fun init() {
        motorLeft.direction = DcMotor.Direction.REVERSE
    }

    private fun scaleInput(input : Double) : Double {
        val scaleArray = arrayOf(0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00)

        var index : Int = if((input * 16.00).toInt() < 0) {
            -(input * 16.00).toInt()
        } else {
            (input * 16.00).toInt()
        }

        index = if(index > 16) {
            16
        } else {
            index
        }

        var scale : Double = if(input < 0) {
            -scaleArray[index]
        } else {
            scaleArray[index]
        }

        return scale
    }

}