package org.firstinspires.ftc.teamcode.modules

import android.text.method.Touch
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.TouchSensor

class TrayTouchModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val isPressed get() = get<TouchSensor>("touch").isPressed

	override fun init() {
		components["touch"] = hardwareMap.get(TouchSensor::class.java, "touch")
	}
}