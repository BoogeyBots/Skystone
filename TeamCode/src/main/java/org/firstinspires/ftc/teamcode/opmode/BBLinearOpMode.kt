package org.firstinspires.ftc.teamcode.opmode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime

abstract class BBLinearOpMode : LinearOpMode(), BBOpModeBase {
    fun wait(seconds: Double) {
        val stopwatch = ElapsedTime()
        stopwatch.reset()
        while (stopwatch.seconds() < seconds && opModeIsActive()) { }
    }
}
