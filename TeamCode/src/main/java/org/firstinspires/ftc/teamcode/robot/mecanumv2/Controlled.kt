package org.firstinspires.ftc.teamcode.robot.mecanumv2

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.Hook
import org.firstinspires.ftc.teamcode.Lift
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "MECANUM: CONTROLLED V2", group = "SKYSTONE MECANUM")
class Controlled : BBOpMode() {
	override val robot: Robot = Robot(this,
		setOf(
			Mecanum(this),
			Hook(this),
			Lift(this)))

	var maxSpeed = 1.0
	var speedModifier = 0.0005

	override fun init() {
		get<Mecanum>().init()
		get<Hook>().init()
		get<Lift>().init()
	}

	override fun loop() {
		when {
			gamepad2.b -> get<Hook>().grab()
			gamepad2.x -> get<Hook>().ungrab()
			gamepad1.y -> get<Lift>().float()
		}

		if (gamepad1.dpad_up) {
			maxSpeed += speedModifier
		} else if (gamepad1.dpad_down) {
			maxSpeed -= speedModifier
		}

		get<Lift>().run(-gamepad1.right_trigger.toDouble())

		maxSpeed = Range.clip(maxSpeed, 0.0, 1.0)

		get<Mecanum>().motorsWithNames.forEach { (name, motor) ->
			motor.power = Range.clip((gamepad1.left_trigger) - gamepad1.right_trigger +
				when (name) {
					"lf" -> {
						(gamepad1.left_stick_x + -gamepad1.right_stick_x).toDouble()
					}
					"rf" -> {
						(-gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble()
					}
					"lb" -> {
						(-gamepad1.left_stick_x + -gamepad1.right_stick_x).toDouble()
					}
					"rb" -> {
						(gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble()
					}
					else -> 0.0
				},
				-maxSpeed, maxSpeed)
		}

		get<Mecanum>().motorsWithNames.forEach {
			telemetry.addData("MOTOR", "${it.key}: ${(it.value as DcMotor).power}")
		}
		telemetry.addData("maxSpeed", maxSpeed)
		telemetry.addData("lift speed", get<Lift>().liftMotor.power)
	}
}