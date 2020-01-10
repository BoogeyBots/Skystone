package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.SwitchableLight
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.ColorSensorModule
import org.firstinspires.ftc.teamcode.modules.TestModule
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "TEST COLOR SENSOR", group = "TEST")
class TestColorSensor : BBOpMode() {
	override val robot: Robot = Robot(this, setOf(ColorSensorModule(this)))

	override fun init() {
		get<ColorSensorModule>().init()
	}

	override fun loop() {
		val color = get<ColorSensorModule>().RGB()
		var frac = (color.r * color.g ) / (color.b * color.b)

		telemetry.addData("COLOR", "R: ${color.r} | G: ${color.g} | B: ${color.b} | A: ${color.a} | FRAC: ${frac}")
	}
}

