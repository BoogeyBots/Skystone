package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo


class ArmV3Module(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val arm get() = get<Servo>("arm")
	val grabber get() = get<Servo>("grabber")

	override fun init() {
		components["arm"] = hardwareMap.get(Servo::class.java, "arm")
		components["grabber"] = hardwareMap.get(Servo::class.java, "grabber")
		ungrab()
		ungrabWith()
	}

	fun ungrabWith() {
		grabber.position = GRABBER_UNGRAB_POS
	}

	fun grab() {
		arm.position = ARM_GRAB_POS
		grabber.position = GRABBER_GRAB_POS
	}

	fun ungrab() {
		arm.position = ARM_UNGRAB_POS
	}


	companion object {
		const val ARM_GRAB_POS = 0.35
		const val ARM_UNGRAB_POS = 0.775
		const val GRABBER_UNGRAB_POS = 0.7
		const val GRABBER_GRAB_POS = 0.1372
	}
}