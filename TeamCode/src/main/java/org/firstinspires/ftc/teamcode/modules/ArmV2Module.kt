package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.Range

class ArmV2Module(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val arm get() = get<Servo>("arm")

	override fun init() {
		components["arm"] = hardwareMap.get(Servo::class.java, "arm")
		ungrab()

	}

	fun grab() {
		arm.position = SARM_GRAB_POS
	}

	fun ungrab() {
		arm.position = SARM_UNGRAB_POS
	}


	companion object {
		const val SARM_GRAB_POS = 0.35
		const val SARM_UNGRAB_POS = 0.775
	}
}