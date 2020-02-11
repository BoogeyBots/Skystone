package org.firstinspires.ftc.teamcode.robot.mecanumv2

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name="AUTO PARK (DUTE DREAPTA)", group="SKYSTONE")
class AutoPark2 : BBLinearOpMode() {
	override val robot: Robot = Robot(this, setOf(Mecanum(this)))
	override fun runOpMode() {
		robot.modules.forEach { it.init() }

		waitForStartFixed()

		get<Mecanum>().forward(24.0, timeout = 5.0)
		get<Mecanum>().sideways(10.0, power = 0.6, timeout = 5.0)
	}
}