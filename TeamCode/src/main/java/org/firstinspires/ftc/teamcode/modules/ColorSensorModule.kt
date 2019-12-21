package org.firstinspires.ftc.teamcode.modules
import com.qualcomm.robotcore.hardware.SwitchableLight

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.HardwareDevice

data class RGBAColor(val r: Int, val g: Int, val b: Int, val a: Int)

class ColorSensorModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val sensor get() = get<ColorSensor>("color")

	override fun init() {
		components["color"] = hardwareMap.get(ColorSensor::class.java, "color")
		sensor.enableLed(false)
	}


	fun RGB(): RGBAColor {
		return RGBAColor(sensor.red(), sensor.green(), sensor.blue(), sensor.alpha())
	}
}