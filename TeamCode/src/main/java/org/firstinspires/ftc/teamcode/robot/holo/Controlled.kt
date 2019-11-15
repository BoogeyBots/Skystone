package org.firstinspires.ftc.teamcode.robot.holo

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.Holonomic
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "HOLO: CONTROLLED", group = "SKYSTONE HOLO")
class Controlled : BBOpMode()  {
	override val robot = Robot(this, setOf(Holonomic(this)))

	override fun init() {
		get<Holonomic>().init()
	}

	override fun loop() {
		get<Holonomic>().stop()

		get<Holonomic>().motorsWithNames.forEach { (name, motor) ->
			motor.power = Range.clip(when (name) {
				"f" -> gamepad1.left_stick_x
				"b" -> gamepad1.left_stick_x
				"l" -> gamepad1.left_trigger - gamepad1.right_trigger
				"r" -> gamepad1.left_trigger - gamepad1.right_trigger
				else -> 0.0f
			}.toDouble(), -1.0, 1.0)
		}
	}
}