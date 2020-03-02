package org.firstinspires.ftc.robotcontroller.external.samples

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector

@TeleOp(name = "Concept: TensorFlow Object Detection", group = "Concept")
@Disabled
class ConceptTensorFlowObjectDetection : LinearOpMode() {

	private var vuforia: VuforiaLocalizer? = null

	private var tfod: TFObjectDetector? = null

	override fun runOpMode() {

		initVuforia()
		if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
			initTfod()
		} else {
			telemetry.addData("Sorry!", "This device is not compatible with TFOD")
		}

		telemetry.addData(">", "Press Play to start tracking")
		telemetry.update()
		waitForStart()
		if (opModeIsActive()) {

			if (tfod != null) {
				tfod!!.activate()
			}
			while (opModeIsActive()) {
				if (tfod != null) {

					val updatedRecognitions = tfod!!.updatedRecognitions
					if (updatedRecognitions != null) {
						telemetry.addData("# Object Detected", updatedRecognitions.size)
						if (updatedRecognitions.size == 3) {
							var goldMineralX = -1
							var silverMineral1X = -1
							var silverMineral2X = -1
							for (recognition in updatedRecognitions) {
								if (recognition.label == LABEL_SKYSTONE) {
									goldMineralX = recognition.left.toInt()
								} else if (silverMineral1X == -1) {
									silverMineral1X = recognition.left.toInt()
								} else {
									silverMineral2X = recognition.left.toInt()
								}
							}
							if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
								if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
									telemetry.addData("Gold Mineral Position", "Left")
								} else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
									telemetry.addData("Gold Mineral Position", "Right")
								} else {
									telemetry.addData("Gold Mineral Position", "Center")
								}
							}
						}
						telemetry.update()
					}
				}
			}
		}
		if (tfod != null) {
			tfod!!.shutdown()
		}
	}

	private fun initVuforia() {

		val parameters = VuforiaLocalizer.Parameters()
		parameters.vuforiaLicenseKey = VUFORIA_KEY
		parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK

		vuforia = ClassFactory.getInstance().createVuforia(parameters)
	}

	private fun initTfod() {
		val tfodMonitorViewId = hardwareMap.appContext.resources.getIdentifier(
			"tfodMonitorViewId", "id", hardwareMap.appContext.packageName)
		val tfodParameters = TFObjectDetector.Parameters(tfodMonitorViewId)
		tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia)
		tfod!!.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_SKYSTONE, LABEL_STONE)
	}

	companion object {
		private const val TFOD_MODEL_ASSET = "Skystone.tflite"
		private const val LABEL_SKYSTONE = "Skystone"
		private const val LABEL_STONE = "Stone"

		private const val VUFORIA_KEY = "AdTyVKT/////AAABmUFwDmIEzEifqyVnytCVM0Q/Vcv/4uzUVlMvuABEpgZdZ5A6fVI+rlYfQ3WwiG2GOxsfEHFjrmJcYxavcjY+jM2Q451WXbFOP9AHNbSFiICZRgYQD1v3CO43xlN6G1bxAHMj39zQkbmflUxeDkhuS5PJkKlhWw2vfUc2kpDEToupXkVUB+BvyD6T4dHwg0LPO0X+HBEuD5LzcuTMRaIL8kGhmhxMxefldydlHs6Lk4j/3ys52DQkTYl+zbtTWsg+HVZe2/c06kJfiBvRAzKSiVByYfYEtYkeRis7euG2g3jMmUiwnkp/NJUf27v3EIVaq5Vu1FV4t6J4xXCRp3wKouOn2sr6Rs/wKWcT+IuRZIQW"

	}
}