package org.firstinspires.ftc.teamcode.modules
import com.qualcomm.robotcore.hardware.SwitchableLight

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.DistanceSensor
import com.qualcomm.robotcore.hardware.HardwareDevice
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.teamcode.opmode.get

data class RGBAColor(val r: Int, val g: Int, val b: Int, val a: Int)

class ColorSensorModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	private val sensor get() = get<ColorSensor>("color")
	private val distance get() = get<DistanceSensor>("distance")
	private val color get() = RGB()
	private val frac get() = (color.g * color.g) / (color.b * color.b)


	override fun init() {
		components["color"] = hardwareMap.get(ColorSensor::class.java, "color")
		components["distance"] = hardwareMap.get(ColorSensor::class.java, "color")
		sensor.enableLed(false)
	}


	fun RGB(): RGBAColor {
		return RGBAColor(sensor.red(), sensor.green(), sensor.blue(), sensor.alpha())
	}

	fun frac(): Int {
		return frac
	}
	fun IsSkystone(): String {
		var sau = ""

		if(frac() in 3..4) sau = "stone"
		else if (frac() <= 1 ) sau = "skystone"

		return sau

	}

	fun Distance(): Double {
		return distance.getDistance(DistanceUnit.CM)
	}
}