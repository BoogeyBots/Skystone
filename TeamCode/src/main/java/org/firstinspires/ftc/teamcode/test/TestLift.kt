package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.ElapsedTime
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
	var timer = ElapsedTime()

	override fun runOpMode() {
		robot.modules.forEach { it.init() }

		waitForStartFixed()

		while (opModeIsActive()) {
			if (!get<Lift>().isBusy) {
				if (timer.seconds() > TIME_TO_TAP) {
					when {
						gamepad1.dpad_left -> {
							get<Lift>().level = 0
							timer.reset()
						}
						gamepad1.dpad_right -> {
							get<Lift>().level++
							timer.reset()
						}
					}
				}

				when {
					gamepad1.dpad_up -> get<Lift>().update()
					gamepad1.dpad_down -> {
						val oldLvl = get<Lift>().level
						get<Lift>().level = 0
						get<Lift>().update()
						get<Lift>().level = oldLvl
					}
				}
			}

			telemetry.addData("click time", timer.seconds())
			telemetry.addData("selected level", get<Lift>().level)
			telemetry.addData("actual level", get<Lift>().actualLevel)
			telemetry.update()
		}
	}

	companion object {
		const val TIME_TO_TAP = 0.5
	}
}