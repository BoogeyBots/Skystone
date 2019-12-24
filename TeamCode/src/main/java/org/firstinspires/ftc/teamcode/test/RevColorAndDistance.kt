package org.firstinspires.ftc.teamcode.test

import com.qualcomm.hardware.rev.Rev2mDistanceSensor
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
		telemetry.addData("COLOR", "R: ${color.r} | G: ${color.g} | B: ${color.b} | A: ${color.a}")

		telemetry.addData("deviceName", sensorRange!!.deviceName)
		telemetry.addData("range", String.format("%.01f in", sensorRange!!.getDistance(DistanceUnit.INCH)))

		telemetry.addData("ID", String.format("%x", sensorTimeOfFlight!!.modelID))
		telemetry.addData("did time out", java.lang.Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()))

		/*if(sensorRange!!.getDistance(DistanceUnit.INCH) < 12 && sensorRange!!.getDistance(DistanceUnit.INCH) > 4)
		{
			if()
		}
		*/

		telemetry.update()
	}

}