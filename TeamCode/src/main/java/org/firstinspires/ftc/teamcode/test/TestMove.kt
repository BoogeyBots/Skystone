package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "TEST: Move", group = "TEST")
class TestMove : BBLinearOpMode() {
	override val robot = Robot(this, setOf(Mecanum(this)))
	val power = 0.95
	override fun runOpMode() {
		robot.modules.forEach { it.init() }

		waitForStartFixed()

		get<Mecanum>().forward(-72.0, power, timeout = 5.0)
		wait(0.5)
		get<Mecanum>().rotate(-90.0, power, timeout = 5.0)
		wait(0.5)
		get<Mecanum>().sideways(-72.0, power, 5.0)
		wait(0.5)


/*
		get<Mecanum>().sideways(24.0,1.0,2.0)
		get<Mecanum>().sideways(-24.0,1.0,2.0)
		get<Mecanum>().sideways(5.0,1.0,4.0)
		get<Mecanum>().sideways(-5.0,1.0,4.0)
*/

	}
}