package org.firstinspires.ftc.teamcode.test

import com.arcrobotics.ftclib.command.CommandOpMode
import com.arcrobotics.ftclib.vision.SkystoneDetector
import com.arcrobotics.ftclib.vision.SkystoneDetector.SkystonePosition
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera


// You don't need CommandOpMode, LinearOpMode, and other OpModes work well
class SkystoneSample : CommandOpMode() {
    var camera: OpenCvCamera? = null
    private var pipeline: SkystoneDetector? = null
    override fun initialize() {
        val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.packageName)
        camera = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId)
        camera!!.openCameraDevice()
        pipeline = SkystoneDetector()
        camera!!.setPipeline(pipeline)
        camera!!.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT)
    }

    override fun run() { // Assuming threaded. It hopefully found the skystone at the end of init.
        when (pipeline!!.skystonePosition) {
            SkystonePosition.LEFT_STONE -> {
            }
            SkystonePosition.CENTER_STONE -> {
            }
            SkystonePosition.RIGHT_STONE -> {
            }
            else -> {
            }
        }
    }


}