package org.firstinspires.ftc.teamcode.test

import com.arcrobotics.ftclib.vision.SkystoneDetector
import com.arcrobotics.ftclib.vision.SkystoneDetector.SkystonePosition
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import org.firstinspires.ftc.teamcode.modules.RobotModule
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera


class OpenCvModule(override val opMode: OpMode) : RobotModule {
	override lateinit var components: HashMap<String, HardwareDevice>
	var camera: OpenCvCamera? = null
	var pipeline: SkystoneDetector? = SkystoneDetector(15.0, 20.0, 50.0, 40.0)

	override fun init() {
		val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.packageName)
		camera = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId)
		camera!!.openCameraDevice()
		pipeline = SkystoneDetector(40.0, 25.0, 50.0, 40.0)
		camera!!.setPipeline(pipeline)
		camera!!.startStreaming(640, 480, OpenCvCameraRotation.SIDEWAYS_LEFT)
	}

	fun run(): SkystonePosition? {
		return pipeline!!.skystonePosition

	}


}
