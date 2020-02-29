package org.firstinspires.ftc.teamcode.modules

import android.os.SystemClock.sleep
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.Arm


class ArmV3Module(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val arm get() = get<Servo>("armleft")
	val grabber get() = get<Servo>("grabberleft")
	var upPos = 1.0
	var downPos = 0.5583
	var grabPos = 0.18
	var ungrabPos = 0.3

	override fun init() {
		components["armleft"] = hardwareMap.get(Servo::class.java, "armleft")
		components["grabberleft"] = hardwareMap.get(Servo::class.java, "grabberleft")
		ungrab()
		goUp()
	}

	fun setup(grabPos: Double, ungrabPos: Double, upPos: Double, downPos: Double) {
		this.grabPos = grabPos
		this.ungrabPos = ungrabPos
		this.upPos = upPos
		this.downPos = downPos
	}

	fun goUp() {
		arm.position = upPos
	}

	fun grab() {
		grabber.position = grabPos
	}

	fun ungrab() {
		grabber.position = ungrabPos
	}

	fun goDown() {
		arm.position = downPos
	}


	companion object {
		const val ARM_GRAB_POS = 0.5583
		const val ARM_UNGRAB_POS = 1.0
		const val GRABBER_UNGRAB_POS = 0.3
		const val GRABBER_GRAB_POS = 0.18
	}
}