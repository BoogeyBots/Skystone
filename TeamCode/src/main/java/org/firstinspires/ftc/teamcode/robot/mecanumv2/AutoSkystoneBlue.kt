package org.firstinspires.ftc.teamcode.robot.mecanumv2

import com.arcrobotics.ftclib.vision.SkystoneDetector.SkystonePosition
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.ArmV3Module
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.test.OpenCvModule
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "AUTO SKYSTONE BLUE", group = "SKYSTONE")
class AutoSkystoneBlue : BBLinearOpMode() {
	override val robot: Robot = Robot(this, setOf(Mecanum(this), ArmV3Module(this), OpenCvModule(this)))
	private var pos = 0
	override fun runOpMode() {
		robot.modules.forEach { it.init() }



		waitForStartFixed()

		pos = when (get<OpenCvModule>().run()) {
			SkystonePosition.LEFT_STONE -> 0
			SkystonePosition.CENTER_STONE -> 1
			SkystonePosition.RIGHT_STONE -> 2
			null -> 3
		}

		get<Mecanum>().sideways(-30.0, timeout = 2.0)



		get<Mecanum>().forward(-9.5 + (pos * 8), timeout = 2.0)
		get<ArmV3Module>().goDown()
		wait(0.5)
		get<ArmV3Module>().grab()
		wait(0.5)
		get<ArmV3Module>().goUp()
		wait(0.5)

		get<Mecanum>().forward(-24.0 * 4.5, timeout = 7.0)
		get<ArmV3Module>().goDown()
		wait(0.5)
		get<ArmV3Module>().ungrab()
		wait(0.5)
		get<ArmV3Module>().goUp()
		wait(0.5)
		get<Mecanum>().forward(24 * 2.5, timeout = 3.0)


	}


}