package org.firstinspires.ftc.teamcode.robot.mecanum

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.Arm
import org.firstinspires.ftc.teamcode.Camera
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "MECANUM: AUTO SKYSTONE RIGHT", group = "SKYSTONE MECANUM")
@Disabled
class AutoSkystoneRight : BBLinearOpMode() {
	override val robot = Robot(this, setOf(Mecanum(this), Camera(this),Arm(this)))

	override fun runOpMode() {
		get<Mecanum>().init()
		get<Camera>().init()
		get<Arm>().init()

		waitForStartFixed()

		wait(5.0)

		get<Mecanum>().sideways(-24.0,0.9,3.0)
		val skystonePos = get<Camera>().getSkystoneIndex()
		get<Mecanum>().rotate(90.0,0.8,3.0)
		get<Mecanum>().forward(3.5,0.9,3.0)
//		get<Mecanum>().sideways(when (skystonePos) {
//			0 -> -4.0
//			1, 2 -> 4.0 + (skystonePos - 1) * 8.0
//			else -> 0.0
//		}, timeout = 0.3)
		get<Mecanum>().sideways(-4.0, 0.5, 3.0)

		get<Arm>().marm.power = -0.5
		wait(1.0)
		get<Arm>().marm.power = 0.0

		get<Mecanum>().forward(1.0, 0.5, 1.0)
		get<Arm>().grab()
		wait(1.0)
		get<Mecanum>().forward(-1.0, 0.5, 1.0)
//		get<Mecanum>().sideways(when (skystonePos) {
//			0 -> -4.0
//			1, 2 -> +4.0 + (skystonePos - 1) * 8.0
//			else -> 0.0
//		}, timeout = 0.3)
		get<Mecanum>().forward(-32.0,0.5,3.0)
		get<Mecanum>().forward(6.0, 0.5, 3.0)
		get<Mecanum>().rotate(-90.0,0.9,3.0)
		get<Mecanum>().sideways(9.0, 0.5, 1.0)
		get<Mecanum>().forward(50.0, timeout = 5.0)
		get<Arm>().ungrab()
		wait(1.0)
		get<Mecanum>().forward(-20.0, timeout = 2.0)

		get<Mecanum>().stop()
		get<Camera>().stop()
	}
}