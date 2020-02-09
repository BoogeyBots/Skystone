package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.obsolete.TestModule
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "Test Servo", group = "TEST")
class TestServo : BBLinearOpMode() {
	override val robot = Robot(this, setOf(TestModule(this)))
	var resolution = 0.0005
	var resChangeSpeed = 0.000000001

	// NOTES
	// A - 0.40 for grabbing the stones
	// B - 0.65
	// 1.0 servo right back is strâns, 0.06 trage tava
	// 0.845 trage tava stanga spate, 0.0 sus


	override fun runOpMode() {
		val servoMod = robot.modules.first()
		servoMod.components["servo"] = hardwareMap.get(Servo::class.java, "testservo")
		val servo = get<TestModule>().get<Servo>("servo")
		servo.position = 0.5

		waitForStart()

		while (opModeIsActive()) {
			resolution += when {
				gamepad1.dpad_up -> resChangeSpeed
				gamepad1.dpad_down -> -resChangeSpeed
				else -> 0.0
			}

			servo.position = when {
				gamepad1.y -> servo.position + resolution
				gamepad1.a -> servo.position - resolution
				else -> servo.position
			}

			telemetry.addData("res", resolution)
			telemetry.addData("servo", servo.position)
			telemetry.update()
		}
	}
}