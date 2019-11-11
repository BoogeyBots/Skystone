package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.robotcore.external.tfod.Recognition
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector

class CameraModule(override val opMode: OpMode) : RobotModule {
    override lateinit var components: HashMap<String, HardwareDevice>
    lateinit var tfod: TFObjectDetector
    lateinit var localizer: VuforiaLocalizer
    var started = false

    /***
     * The camera sees only the first two stones.
     * If none of them is a skystone, the third one is.
     * @return the index of the SkyStone (0 to 2)
     */
    fun getSkystoneIndex(time: Double): Int {
        if (!started) {
            tensorflowStart()
        }
        val indexedValue = tfod.updatedRecognitions
            .sortedBy { it.left }
            .withIndex()
            .firstOrNull { it.value.label == LABEL_SKYSTONE }
        return (indexedValue?.index ?: 2)
    }

    override fun stop() {
        tensorflowStop()
    }

    override fun start() {
        tensorflowStart()
    }

    fun tensorflowStart() {
        started = true
        initVuforia()

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod()
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD")
        }

        tfod.activate()
    }

    fun tensorflowStop() = tfod.shutdown()

    private fun initVuforia() {
        val parameters = VuforiaLocalizer.Parameters()
        parameters.vuforiaLicenseKey = VUFORIA_KEY
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK

        localizer = ClassFactory.getInstance().createVuforia(parameters)
    }

    private fun initTfod() {
        val tfodMonitorViewId = hardwareMap.appContext.resources.getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.packageName)
        val tfodParameters = TFObjectDetector.Parameters(tfodMonitorViewId)
        tfodParameters.minimumConfidence = 0.8
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, localizer)
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_STONE, LABEL_SKYSTONE)
    }

    private companion object {
        const val VUFORIA_KEY = "AWo7bzb/////AAABmcbdWZ79Y049lfMcsRS8waNYev8AbC1EwUWqhJnr1poItrv7+etQ1bwW4BiQpg151evO66Pzt3L2LvfbBgzn4aQ3QzVBXYQBjqMScjg/gQEj0g3ldi/0ENHSKwnT48YDxtQQb5/twpwjew9wlaSkZuZ8KtZGwOZHh7vhV0xQmjh1akuPF0zmKvCn5HPnd/O9YxXR5Ef7eyQ+r15XMT7Vd7kG/PUbpCvkexwsRZ4BKGv+oV1ZWOqrYrP5WKbpzHmEOl8RggfJKD707G2Q61vTUW+MEksQwrydbwTCqzTxDUTWdOlgzG9JfGjS+jUdQ3CAN+EETNZDOQs8fIxn3Q+Bdmi823AJLEU3GDhptc7KHcjo"
        const val TFOD_MODEL_ASSET = "Skystone.tflite"
        const val LABEL_STONE = "Stone"
        const val LABEL_SKYSTONE = "Skystone"
    }
}