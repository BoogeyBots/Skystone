package org.firstinspires.ftc.teamcode.robot.holo

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.Holonomic
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.HoloGrabber
import org.firstinspires.ftc.teamcode.modules.HoloHook
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "HOLO: CONTROLLED", group = "SKYSTONE HOLO")
class Controlled : BBOpMode()  {
	override val robot = Robot(this, setOf(Holonomic(this), HoloGrabber(this), HoloHook(this)))

	override fun init() {
		get<Holonomic>().init()
		get<HoloGrabber>().init()
		get<HoloHook>().init()
	}

	override fun loop() {
		get<Holonomic>().stop()

		get<Holonomic>().motorsWithNames.forEach { (name, motor) ->
			motor.power = Range.clip(when (name) {
				"f" -> gamepad1.left_stick_x - gamepad1.right_stick_x
				"b" -> gamepad1.left_stick_x + gamepad1.right_stick_x
				"l" -> gamepad1.left_trigger - gamepad1.right_trigger - gamepad1.right_stick_x
				"r" -> gamepad1.left_trigger - gamepad1.right_trigger + gamepad1.right_stick_x
				else -> 0.0f
			}.toDouble(), -1.0, 1.0)
		}

		when {
			gamepad2.a -> get<HoloGrabber>().grab()
			gamepad2.y -> get<HoloGrabber>().ungrab()
			gamepad2.b -> get<HoloHook>().grab()
			gamepad2.x -> get<HoloHook>().ungrab()
		}
		telemetry.addData("ewrt",get<HoloHook>().get<Servo>("hservo").position)

	}
}