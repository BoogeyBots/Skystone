package org.firstinspires.ftc.teamcode.modules

import android.os.SystemClock.sleep
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.Arm


class ArmV3Module(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val arm get() = get<Servo>("arm")
	val grabber get() = get<Servo>("grabber")

	override fun init() {
		components["arm"] = hardwareMap.get(Servo::class.java, "arm")
		components["grabber"] = hardwareMap.get(Servo::class.java, "grabber")
		ungrab()
		goUp()
	}

	fun goUp() {
		arm.position = ARM_UNGRAB_POS
	}

	fun grab() {

		grabber.position = GRABBER_GRAB_POS
	}

	fun ungrab() {
		grabber.position = GRABBER_UNGRAB_POS
	}

	fun goDown() {
		arm.position = ARM_GRAB_POS
	}


	companion object {
		const val ARM_GRAB_POS = 0.5583
		const val ARM_UNGRAB_POS = 1.0
		const val GRABBER_UNGRAB_POS = 0.3
		const val GRABBER_GRAB_POS = 0.18
	}
}