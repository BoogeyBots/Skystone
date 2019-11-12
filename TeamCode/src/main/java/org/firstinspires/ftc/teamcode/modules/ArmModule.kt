package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo

class ArmModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()

	override fun init() {
		components["sarm"] = hardwareMap.get(Servo::class.java, "sarm")
		ungrab()

		components["marm"] = hardwareMap.get(DcMotor::class.java, "marm")
		get<DcMotor>("marm").zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
	}

	fun grab() {
		get<Servo>("sarm").position = SARM_GRAB_POS
	}

	fun ungrab() {
		get<Servo>("sarm").position = SARM_UNGRAB_POS
	}

	fun move(factor: Double) {
		get<DcMotor>("marm").power = factor * MARM_FINUȚ
	}

	companion object {
		const val SARM_GRAB_POS = 0.4
		const val SARM_UNGRAB_POS = 0.65
		const val MARM_FINUȚ = 0.4
	}
}