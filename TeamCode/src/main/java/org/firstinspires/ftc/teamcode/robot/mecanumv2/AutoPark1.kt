package org.firstinspires.ftc.teamcode.robot.mecanumv2

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.Hook
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name="AUTO PARK (DUTE STANGA)", group="SKYSTONE")
class AutoPark1 : BBLinearOpMode() {
	override val robot: Robot = Robot(this, setOf(Mecanum(this)))
	override fun runOpMode() {
		robot.modules.forEach { it.init() }

		waitForStartFixed()

		get<Mecanum>().sideways(24.0, timeout = 5.0)
		get<Mecanum>().sideways(-24.0, timeout = 5.0)

		//RISC
		/*get<Mecanum>().sideways(-24.0 - 9.0, timeout = 5.0)
		get<Mecanum>().forward(-5.0 - 24.0, timeout = 5.0)
		get<Hook>().grab()
		wait(1.0)
		get<Mecanum>().forward(6.0 + 24.0, timeout = 5.0)
		get<Hook>().ungrab()
		wait(1.0)
		get<Mecanum>().sideways(24.0 + 9.0 + 18.0, timeout = 5.0)*/
	}
}