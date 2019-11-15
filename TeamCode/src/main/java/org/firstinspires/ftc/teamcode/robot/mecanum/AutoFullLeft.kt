package org.firstinspires.ftc.teamcode.robot.mecanum

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.Camera
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "MECANUM: AUTO FULL LEFT", group = "SKYSTONE MECANUM")
class AutoFullLeft : BBLinearOpMode() {
	override val robot = Robot(this, setOf(Mecanum(this), Camera(this)))

	override fun runOpMode() {
		get<Mecanum>().init()
		get<Camera>().init()

		waitForStartFixed()

		get<Camera>().stop()
	}
}