package org.firstinspires.ftc.teamcode.robot.mecanum

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.Camera
import org.firstinspires.ftc.teamcode.Hook
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@Autonomous(name = "MECANUM: AUTO TRAY RIGHT", group = "SKYSTONE MECANUM")
class AutoTrayRight : BBLinearOpMode() {
	override val robot = Robot(this, setOf(Mecanum(this), Camera(this), Hook(this)))

	override fun runOpMode() {
		get<Mecanum>().init()
		get<Camera>().init()
		get<Hook>().init()

		get<Camera>().start()

		waitForStart()

		get<Mecanum>().sideways(-36.0, power = 0.9, timeout = 3.0)
		get<Mecanum>().forward(-27.5, power = 0.9, timeout = 3.0)
		get<Hook>().grab()
		sleep(1000)
		get<Mecanum>().forward(27.5, power = 0.5, timeout = 3.0)
		get<Hook>().ungrab()
		sleep(1000)
		get<Mecanum>().sideways(36.0, power = 0.9, timeout = 3.0)
		get<Mecanum>().forward(-18.0, power = 0.9, timeout = 3.0)
		get<Mecanum>().sideways(12.0, power = 0.9, timeout = 1.0)

		get<Mecanum>().stop()
		get<Camera>().stop()
	}
}