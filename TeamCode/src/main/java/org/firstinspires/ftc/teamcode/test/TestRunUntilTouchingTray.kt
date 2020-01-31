package org.firstinspires.ftc.teamcode.test

import org.firstinspires.ftc.teamcode.Arm
import org.firstinspires.ftc.teamcode.Hook
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.obsolete.TrayTouchModule
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

class TestRunUntilTouchingTray : BBLinearOpMode() {
	override val robot: Robot = Robot(this, setOf(TrayTouchModule(this), Mecanum(this)))

	override fun runOpMode() {
		robot.modules.forEach { it.init() }

		waitForStartFixed()

		get<Mecanum>().forwardUntil(0.5) { get<TrayTouchModule>().isPressed }
		get<Hook>().grab()
	}
}