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

@Autonomous(name = "AUTO SKYSTONE BLUE", group = "SKYSTONE")
class AutoSkystoneBlue : BBLinearOpMode() {
	override val robot: Robot = Robot(this, setOf(Hook(this), Mecanum(this), ArmV3Module(this), OpenCvModule(this)))
	private var pos = 0
	override fun runOpMode() {
		robot.modules.forEach { it.init() }

		waitForStartFixed()

		pos = when (get<OpenCvModule>().run()) {
			SkystonePosition.LEFT_STONE -> 0
			SkystonePosition.CENTER_STONE -> 1
			SkystonePosition.RIGHT_STONE -> 2
			null -> 2
		}

		// MA DUC LA STONE
		get<Mecanum>().sideways(-28.0, 1.0, timeout = 2.0)
		get<Mecanum>().forward(-12.0 + (pos * 8), 1.0, timeout = 2.0)

		// PRIND SKYSTONU
		get<ArmV3Module>().goDown()
		wait(0.4)
		get<ArmV3Module>().grab()
		wait(0.4)
		get<ArmV3Module>().goUp()
		wait(0.4)

		// MA DUC LA TAVA
		get<Mecanum>().sideways(7.0, 1.0, timeout = 2.0)
		get<Mecanum>().forward((-24.0 * 3.1) - (pos * 8), 1.0, timeout = 7.0)
		get<Mecanum>().sideways(-10.0, 1.0, timeout = 3.0)

		// DAU DRUMU LA STON
		wait(0.3)
		get<ArmV3Module>().ungrab()

		// MA DUC SA MA ROTESC CA SA MA DUC SA IAU TAVA
		get<Mecanum>().sideways(2.0, 1.0, timeout = 1.0)
		get<Mecanum>().rotate(-90.0, 1.0, timeout = 2.0)
		get<Mecanum>().forward(-6.5, 1.0, timeout = 1.0)

		// PRIND TAVA
		wait(0.5)
		get<Hook>().grab()
		wait(0.5)

		// O MUT
		//get<Mecanum>().sideways(-5.0, power = 0.9, timeout = 3.0)
		get<Mecanum>().forward(24.0, 1.0, timeout = 4.0)
		get<Mecanum>().rotate(202.0, 1.0, timeout = 3.0)

		// DAU DRUMU LA TAVA
		wait(0.5)
		get<Hook>().ungrab()
		wait(0.4)

		// MA DUC SUB POD
		//get<Mecanum>().forward(3.0, power = 0.7, timeout = 3.0)
		//get<Mecanum>().sideways(-4.5,0.9, timeout = 3.0)
		//get<Mecanum>().forward(-4.0, power = 0.9, timeout = 3.0)
		get<Mecanum>().forward(37.0, 1.0, timeout = 5.0)

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