package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "Test Open Cv Module", group = "TEST")
class TestOpenCvModule() : BBOpMode() {
	override val robot = Robot(this, setOf(OpenCvModule(this)))
	override fun init() {
		get<OpenCvModule>().init()
	}

	override fun loop() {
		telemetry.addData("Position ", "${get<OpenCvModule>().run()}")
	}
}