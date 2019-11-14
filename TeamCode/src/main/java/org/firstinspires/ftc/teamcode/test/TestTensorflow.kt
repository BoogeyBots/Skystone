package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.Camera
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@Autonomous(name = "Test Tensorflow", group = "TEST")
class TestTensorflow : BBLinearOpMode() {
	override val robot = Robot(this, setOf(Camera(this)))

	override fun runOpMode() {
		get<Camera>().init()
		get<Camera>().start()

		waitForStart()

		val skystoneIndex = get<Camera>().getSkystoneIndex()
		telemetry.addData("POS", skystoneIndex)
		telemetry.update()

		while (opModeIsActive()) {  }
	}
}