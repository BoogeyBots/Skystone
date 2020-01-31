package org.firstinspires.ftc.teamcode.modules.obsolete

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.modules.mecanumV2_module.RobotModule

class HoloGrabber(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()

	override fun init() {
		components["glservo"] = hardwareMap.get(Servo::class.java, "glservo")
		components["grservo"] = hardwareMap.get(Servo::class.java, "grservo")
		ungrab()
	}

	fun ungrab() {
		get<Servo>("glservo").position = 0.45
		get<Servo>("grservo").position = 0.5
	}

	fun grab() {
		get<Servo>("glservo").position = 0.85
		get<Servo>("grservo").position = 0.00
	}
}