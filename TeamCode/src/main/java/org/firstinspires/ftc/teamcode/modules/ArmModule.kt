package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.*
import com.qualcomm.robotcore.util.Range

class ArmModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val marm get() = get<DcMotorEx>("marm")

	override fun init() {
		components["sarm"] = hardwareMap.get(Servo::class.java, "sarm")
		ungrab()

		components["marm"] = hardwareMap.get(DcMotorEx::class.java, "marm")

		marm.direction = DcMotorSimple.Direction.REVERSE
	}

	fun grab() {
		get<Servo>("sarm").position = SARM_GRAB_POS
	}

	fun ungrab() {
		get<Servo>("sarm").position = SARM_UNGRAB_POS
	}

	fun move(factor: Double) {
		marm.power = Range.clip(factor * MARM_FINUȚ, -0.5, 0.5)
	}

	companion object {
		const val SARM_GRAB_POS = 0.4
		const val SARM_UNGRAB_POS = 0.775
		const val MARM_FINUȚ = 0.5
		const val COUNTS_PER_MOTOR_REV = 1120.0
		const val DRIVE_GEAR_REDUCTION = 1.0
		const val COUNTS_PER_DEGREE = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / 360
	}
}