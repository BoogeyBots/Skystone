package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.TestModule
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "TEST MOTOR", group = "TEST")
class TestMotor : BBLinearOpMode() {
	override val robot: Robot = Robot(this, setOf(TestModule(this)))
	lateinit var motor: DcMotorEx
	var resolution = 0.0005
	var resChangeSpeed = 0.00000001

	override fun runOpMode() {
		motor = hardwareMap.get(DcMotorEx::class.java, "motor")
		val servoMod = robot.modules.first()
		servoMod.components["servo1"] = hardwareMap.get(Servo::class.java, "servo1")
		motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

		val servo1 = get<TestModule>().get<Servo>("servo1")

		servo1.position = 0.5


		waitForStart()

		while (opModeIsActive()) {
			motor.power = -gamepad1.right_stick_y.toDouble()

			resolution += when {
				gamepad1.dpad_up -> resChangeSpeed
				gamepad1.dpad_down -> -resChangeSpeed
				else -> 0.0
			}

			servo1.position = when {
				gamepad1.y -> servo1.position + resolution
				gamepad1.a -> servo1.position - resolution
				else -> servo1.position
			}

			telemetry.addData("res", resolution)
			telemetry.addData("servo1", servo1.position)

			telemetry.update()
		}
	}
}