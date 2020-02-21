package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo

class ServoLinear(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val servolinear get() = get<Servo>("servolinear")

	override fun init() {
		components["servolinear"] = hardwareMap.get(Servo::class.java, "servolinear")
		servolinear.position = 0.1
	}

	fun grab() {
		servolinear.position += resolution
	}

	fun ungrab() {
		servolinear.position -= resolution
	}

	companion object {
		const val GRAB_POS = 0.27
		const val UNGRAB_POS = 0.55
		var resolution = 0.0015
	}
}