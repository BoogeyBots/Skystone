package org.firstinspires.ftc.teamcode.test.TensorFlowAttempt

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.teamcode.vision.MasterVision
import org.firstinspires.ftc.teamcode.vision.SampleRandomizedPositions

@TeleOp(name = "TensorFlowAttempt ", group = "TEST")
class VisionTest : LinearOpMode() {
	internal lateinit var vision: MasterVision
	internal lateinit var goldPosition: SampleRandomizedPositions

	@Throws(InterruptedException::class)
	override fun runOpMode() {
		val parameters = VuforiaLocalizer.Parameters()
		parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK// recommended camera direction
		parameters.vuforiaLicenseKey = "AdTyVKT/////AAABmUFwDmIEzEifqyVnytCVM0Q/Vcv/4uzUVlMvuABEpgZdZ5A6fVI+rlYfQ3WwiG2GOxsfEHFjrmJcYxavcjY+jM2Q451WXbFOP9AHNbSFiICZRgYQD1v3CO43xlN6G1bxAHMj39zQkbmflUxeDkhuS5PJkKlhWw2vfUc2kpDEToupXkVUB+BvyD6T4dHwg0LPO0X+HBEuD5LzcuTMRaIL8kGhmhxMxefldydlHs6Lk4j/3ys52DQkTYl+zbtTWsg+HVZe2/c06kJfiBvRAzKSiVByYfYEtYkeRis7euG2g3jMmUiwnkp/NJUf27v3EIVaq5Vu1FV4t6J4xXCRp3wKouOn2sr6Rs/wKWcT+IuRZIQW"

		waitForStart()

		vision = MasterVision(parameters, hardwareMap, true, MasterVision.TFLiteAlgorithm.INFER_RIGHT)
		vision.init()// enables the camera overlay. this will take a couple of seconds
		vision.enable()// enables the tracking algorithms. this might also take a little time

		goldPosition = vision.tfLite.lastKnownSampleOrder

		while (opModeIsActive()) {
			telemetry.addData("SkystonePosition was", goldPosition)// giving feedback

			sleep(2000)

			when (goldPosition) {
				// using for things in the autonomous program
				SampleRandomizedPositions.RIGHT -> telemetry.addLine("going to the right")
				SampleRandomizedPositions.UNKNOWN -> telemetry.addLine("staying put")
				SampleRandomizedPositions.UNDETERMINED -> telemetry.addLine("Checking...")
			}

			telemetry.update()
		}
		vision.disable()
		vision.shutdown()

		if(goldPosition == SampleRandomizedPositions.UNDETERMINED) {

			telemetry.addLine("Preparing...")
			telemetry.update()

			sleep(5000)

			vision.init()
			vision.enable()

			vision = MasterVision(parameters, hardwareMap, true, MasterVision.TFLiteAlgorithm.INFER_LEFT)

			telemetry.addLine("Standby...")
			telemetry.update()

			while (opModeIsActive()) {
				telemetry.addData("SkystonePosition was", goldPosition)// giving feedback

				when (goldPosition) {
					// using for things in the autonomous program
					SampleRandomizedPositions.LEFT -> telemetry.addLine("going to the left")
					SampleRandomizedPositions.CENTER -> telemetry.addLine("going straight")
					SampleRandomizedPositions.UNKNOWN -> telemetry.addLine("staying put")
				}

				telemetry.update()
			}

			vision.disable()

			vision.shutdown()
		}
	}
}