package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Lift
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@TeleOp(name = "TEST LIFT MANUAL", group = "TEST")
class TestLiftManual : BBLinearOpMode() {
	override val robot = Robot(this, setOf(Lift(this)))

	override fun runOpMode() {
		robot.modules.forEach { it.init() }

		waitForStartFixed()

		while (opModeIsActive()) {
			val oldTarget = get<Lift>().motor.targetPosition
			val newTarget = oldTarget - gamepad1.left_trigger * 2.0 + gamepad1.right_trigger * 2.0

			get<Lift>().motor.targetPosition = (when {
				newTarget <= 0.0 -> 0
				newTarget >= 1000.0 -> 1000
				else -> newTarget.toInt()
			})

			telemetry.addData("TARGET POS", get<Lift>().motor.targetPosition)
			telemetry.update()
		}
	}
}