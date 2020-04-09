package org.firstinspires.ftc.teamcode.utils

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime


fun LinearOpMode.wait(seconds: Double) {
	val stopwatch = ElapsedTime()
	while (stopwatch.seconds() < seconds && opModeIsActive()) { }
}

fun LinearOpMode.waitForStartFixed() {
	while (!opModeIsActive() && !isStopRequested) {
		telemetry.addData("INIT OVER", "")
	}
}