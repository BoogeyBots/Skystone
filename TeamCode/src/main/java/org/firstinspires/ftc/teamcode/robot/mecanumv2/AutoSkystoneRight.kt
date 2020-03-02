package org.firstinspires.ftc.teamcode.robot.mecanumv2


import com.arcrobotics.ftclib.vision.SkystoneDetector.SkystonePosition
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.Hook
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.ArmV3Module
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.test.OpenCvModule
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "AUTO SKYSTONE RED", group = "SKYSTONE")
class AutoSkystoneRight : BBLinearOpMode() {
	override val robot: Robot = Robot(this, setOf(Hook(this), Mecanum(this), ArmV3Module(this), OpenCvModule(this)))
	private var pos = 0
	override fun runOpMode() {
		robot.modules.forEach { it.init() }



		waitForStartFixed()

		pos = when (get<OpenCvModule>().run()) {
			SkystonePosition.LEFT_STONE -> 2
			SkystonePosition.CENTER_STONE -> 1
			SkystonePosition.RIGHT_STONE -> 0
			null -> 0
		}

		get<Mecanum>().sideways(-29.4, timeout = 2.0)



		get<Mecanum>().forward(6.0 - (pos * 8), timeout = 2.0)
		get<ArmV3Module>().goDown()
		wait(0.4)
		get<ArmV3Module>().grab()
		wait(0.4)
		get<ArmV3Module>().goUp()
		wait(0.4)
		get<Mecanum>().sideways(7.0, timeout = 2.0)

		get<Mecanum>().forward((24.0 * 3.4) + (pos * 8), timeout = 7.0)
		get<Mecanum>().sideways(-13.0, timeout = 3.0)
		get<ArmV3Module>().ungrab()



		get<Mecanum>().sideways(10.0, timeout = 3.0)
		get<ArmV3Module>().grab()
		get<Mecanum>().rotate(-98.0, timeout = 3.0)
		get<Mecanum>().forward(-15.0, 0.4, timeout = 4.0)
		get<Hook>().grab()
		wait(0.5)
		get<Mecanum>().forward(27.0, timeout = 4.0)
		get<Mecanum>().rotate(-160.0, timeout = 3.0)

		get<Hook>().ungrab()
		get<Mecanum>().sideways(7.0, timeout = 3.0)
		get<Mecanum>().forward(32.0, timeout = 4.0)

		/*
		get<Mecanum>().forward(24 * 4.0+ pos*8, timeout = 4.0)
		get<Mecanum>().sideways(-4.0, timeout = 3.0)

		get<ArmV3Module>().goDown()
		wait(0.5)
		get<ArmV3Module>().grab()
		wait(0.5)
		get<ArmV3Module>().goUp()
		get<Mecanum>().sideways(4.0, timeout = 3.0)


		get<Mecanum>().forward(-24*4.3 + pos*8, timeout = 4.0)
		get<Mecanum>().sideways(-4.0, timeout = 3.0)

		get<ArmV3Module>().goDown()
		wait(0.5)
		get<ArmV3Module>().ungrab()
		wait(0.5)
		get<ArmV3Module>().goUp()
		get<Mecanum>().sideways(10.0, timeout = 4.0)
		get<Mecanum>().rotate(-90.0, timeout = 4.0)
		get<Mecanum>().forward(-10.0, timeout =  4.0)
		get<Hook>().grab()
		get<Mecanum>().rotate(90.0, timeout =  4.0)
		*/
	}


}