package org.firstinspires.ftc.teamcode.test

import com.qualcomm.hardware.rev.Rev2mDistanceSensor
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DistanceSensor
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.ColorSensorModule
import org.firstinspires.ftc.teamcode.modules.TestModule
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "ColorAndDistance", group = "TEST")
@Disabled
class TestColorAndDistanceSensor : BBOpMode() {
	override val robot: Robot = Robot(this, setOf(ColorSensorModule(this)))
	private var sensorRange: DistanceSensor? = null


	override fun init() {
		get<ColorSensorModule>().init()
	}

	override fun loop() {
		sensorRange = hardwareMap.get(DistanceSensor::class.java, "distanta")

		val sensorTimeOfFlight = sensorRange as Rev2mDistanceSensor?
		val color = get<ColorSensorModule>().RGB()
		var frac = (color.r * color.g ) / (color.b * color.b)
		var distcm = (sensorRange!!.getDistance(DistanceUnit.INCH)) * 2.54
		var sau = ""
		if(frac > 2 && distcm < 4.5 && frac < 5) sau = "stone"
		else if (frac <= 1 && distcm < 4.5) sau = "skystone"
		else if (distcm > 4.5) sau = "apropie te"
		else sau = "nu e skystone"

		telemetry.addData("COLOR", "R: ${color.r} | G: ${color.g} | B: ${color.b} | A: ${color.a} | FRAC: ${frac} | STONE: ${sau}")

		telemetry.addData("deviceName", sensorRange!!.deviceName)
		telemetry.addData("range", String.format("%.01f cm", distcm))

		telemetry.addData("ID", String.format("%x", sensorTimeOfFlight!!.modelID))
		telemetry.addData("did time out", java.lang.Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()))

		//if(sensorRange!!.getDistance(DistanceUnit.INCH) < )

		telemetry.update()
	}

}