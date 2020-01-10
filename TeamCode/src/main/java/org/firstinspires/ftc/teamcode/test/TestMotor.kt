package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.TestModule
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "TEST MOTOR", group = "TEST")
class TestMotor : BBOpMode() {
	override val robot: Robot = Robot(this, setOf(TestModule(this)))
	lateinit var motor: DcMotorEx

	override fun init() {
		motor = hardwareMap.get(DcMotorEx::class.java, "lf")
	}

	override fun loop() {

			motor.power = -gamepad1.left_stick_y.toDouble()

	}
}