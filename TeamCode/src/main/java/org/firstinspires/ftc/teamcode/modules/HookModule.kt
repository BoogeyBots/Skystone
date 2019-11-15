package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo

class HookModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()

	override fun init() {
		components["lshook"] = hardwareMap.get(Servo::class.java, "lshook")
		components["rshook"] = hardwareMap.get(Servo::class.java, "rshook")

		ungrab()
	}

	fun grab() {
		get<Servo>("lshook").position = 0.865
		get<Servo>("rshook").position = 0.00
	}

	fun ungrab() {
		get<Servo>("lshook").position = 0.1
		get<Servo>("rshook").position = 0.9
	}
}