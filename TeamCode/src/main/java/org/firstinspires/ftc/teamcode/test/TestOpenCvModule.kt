package org.firstinspires.ftc.teamcode.test

import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.openftc.easyopencv.OpenCvCamera

@TeleOp(name = "Test Open Cv Module", group = "TEST")
class TestOpenCvModule : BBOpMode() {
	override val robot = Robot(this, setOf(OpenCvModule(this)))
	var dashboard = FtcDashboard.getInstance()
	var dashboardTelemetry = dashboard.telemetry

	override fun init() {
		get<OpenCvModule>().init()
	}

	override fun loop() {
		telemetry.addData("Position ", "${get<OpenCvModule>().run()}")
		dashboardTelemetry.addData("Position","${get<OpenCvModule>().run()}")
		dashboardTelemetry.update()
		//FtcDashboard.getInstance().startCameraStream(OpenCvCamera)
	}
}