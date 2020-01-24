package org.firstinspires.ftc.teamcode.vision

import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector

/**
 * Created by David Lukens on 10/31/2018.
 */
class TFLite(private val master: MasterVision) {
    companion object {
        private const val TFOD_MODEL_ASSET = "Skystone.tflite"
        private const val LABEL_SKYSTONE = "Skystone"
        private const val LABEL_STONE = "Stone"


    }

    private var tfod: TFObjectDetector? = null
    private val tfodMoniterViewId = master.hMap.appContext.resources.getIdentifier("tfodMonitorViewId", "id", master.hMap.appContext.packageName)
    private val parameters = TFObjectDetector.Parameters(tfodMoniterViewId)

    fun init() {
        if (tfod == null) {
            tfod = ClassFactory.getInstance().createTFObjectDetector(parameters, master.vuforiaLocalizer)
            tfod?.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_STONE, LABEL_SKYSTONE)
        }
    }

    var lastKnownSampleOrder = SampleRandomizedPositions.UNKNOWN

    internal fun updateSampleOrder() {
        if (tfod != null) {
            val updatedRecognitions = tfod?.updatedRecognitions
            if (updatedRecognitions != null) {
                if (updatedRecognitions.size == 3 || updatedRecognitions.size == 2) {
                    var skystoneBlock: Int? = null
                    var stoneBlock: Int? = null
                    var stoneBlockSecond: Int? = null

                    for (recognition in updatedRecognitions) {
                        if (recognition.label == LABEL_SKYSTONE)
                            skystoneBlock = recognition.left.toInt()
                        else if (stoneBlock == null)
                            stoneBlock = recognition.left.toInt()
                        else
                            stoneBlockSecond = recognition.left.toInt()
                    }
                    when (master.tfLiteAlgorithm) {
                        MasterVision.TFLiteAlgorithm.INFER_NONE  -> if (skystoneBlock != null && stoneBlock != null && stoneBlockSecond != null)
                            if (updatedRecognitions.size == 3)
                                lastKnownSampleOrder =
                                        if (skystoneBlock < stoneBlock && skystoneBlock < stoneBlockSecond)
                                            SampleRandomizedPositions.LEFT
                                        else if (skystoneBlock > stoneBlock && skystoneBlock > stoneBlockSecond)
                                            SampleRandomizedPositions.RIGHT
                                        else
                                            SampleRandomizedPositions.CENTER
                        MasterVision.TFLiteAlgorithm.INFER_LEFT  -> {
                            if(updatedRecognitions.size == 2) {
                                if (skystoneBlock == null)
                                    lastKnownSampleOrder = SampleRandomizedPositions.LEFT
                                else if (stoneBlock != null)
                                    lastKnownSampleOrder = SampleRandomizedPositions.CENTER
                            }
                        }
                        MasterVision.TFLiteAlgorithm.INFER_RIGHT -> {
                            if(updatedRecognitions.size == 2) {
                                if (skystoneBlock == null)
                                    lastKnownSampleOrder = SampleRandomizedPositions.RIGHT
                                else if (stoneBlock != null)
                                    lastKnownSampleOrder = SampleRandomizedPositions.UNDETERMINED
                            }
                        }
                    }
                }
            }
        }
    }

    fun enable() {
        tfod?.activate()
    }

    fun disable() {
        tfod?.deactivate()
    }

    fun shutdown() {
        tfod?.shutdown()
    }

}