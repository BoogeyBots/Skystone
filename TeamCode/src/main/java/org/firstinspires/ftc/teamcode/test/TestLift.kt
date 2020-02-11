package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.Lift
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.LiftModule
import org.firstinspires.ftc.teamcode.modules.TestModule
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@TeleOp(name = "Test Lift", group = "TEST")
class TestLift : BBLinearOpMode() {
	override val robot = Robot(this, setOf(Lift(this)))

	override fun runOpMode() {
		robot.modules.forEach { it.init() }

		waitForStartFixed()


		get<Lift>().currentLevel++

		wait(5.0)

		get<Lift>().currentLevel++

		wait(5.0)

		get<Lift>().currentLevel = 0

		wait(5.0)

		get<Lift>().currentLevel = 2

		wait(5.0)


		get<Lift>().currentLevel = 0

		wait(5.0)

	}
}