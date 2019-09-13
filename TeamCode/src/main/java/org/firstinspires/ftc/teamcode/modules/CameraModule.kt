package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector

class CameraModule(override val hardwareMap: HardwareMap, val telemetry: Telemetry) : RobotModule {
    override lateinit var components: HashMap<String, HardwareDevice>
    lateinit var tfod: TFObjectDetector
    lateinit var localizer: VuforiaLocalizer

    fun tensorflowScan() {
        // getUpdatedRecognitions() will return null if no new information is available since
        // the last time that call was made.
        val updatedRecognitions = tfod.updatedRecognitions
        if (updatedRecognitions != null) {
            telemetry.addData("# Object Detected", updatedRecognitions.size)

            // step through the list of recognitions and display boundary info.
            val i = 0
            for (recognition in updatedRecognitions) {
                telemetry.addData(String.format("label (%Kd)", i), recognition.label)
                telemetry.addData(String.format("  left,top (%Kd)", i), "%.03f , %.03f",
                        recognition.left, recognition.top)
                telemetry.addData(String.format("  right,bottom (%Kd)", i), "%.03f , %.03f",
                        recognition.right, recognition.bottom)
            }
            telemetry.update()
        }
    }

    fun tensorflowStart() {
        initVuforia()

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod()
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD")
        }

        tfod.activate()
    }

    fun tensorflowStop() {
        tfod.shutdown()
    }

    fun initVuforia() {
        val parameters = VuforiaLocalizer.Parameters()
        parameters.vuforiaLicenseKey = VUFORIA_KEY
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK

        localizer = ClassFactory.getInstance().createVuforia(parameters)
    }

    fun initTfod() {
        val tfodMonitorViewId = hardwareMap.appContext.resources.getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.packageName)
        val tfodParameters = TFObjectDetector.Parameters(tfodMonitorViewId)
        tfodParameters.minimumConfidence = 0.8
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, localizer)
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT)
    }

    companion object {
        const val VUFORIA_KEY = " -- YOUR NEW VUFORIA KEY GOES HERE  --- "
        const val TFOD_MODEL_ASSET = "Skystone.tflite"
        const val LABEL_FIRST_ELEMENT = "Stone"
        const val LABEL_SECOND_ELEMENT = "Skystone"
    }
}