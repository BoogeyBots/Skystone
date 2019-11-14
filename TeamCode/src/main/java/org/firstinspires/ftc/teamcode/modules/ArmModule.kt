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

		marm.setVelocityPIDFCoefficients(1.0, 1.5, 1.5, 10.0)
		marm.targetPositionTolerance = 5
		marm.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
		marm.targetPosition = 0
		marm.mode = DcMotor.RunMode.RUN_TO_POSITION
		marm.power = 0.5
	}

	fun grab() {
		get<Servo>("sarm").position = SARM_GRAB_POS
	}

	fun ungrab() {
		get<Servo>("sarm").position = SARM_UNGRAB_POS
	}

	fun move(factor: Double) {
		marm.targetPosition = Range.clip(marm.targetPosition + factor * COUNTS_PER_DEGREE, 0.0, MAX_TARGET_POS).toInt()
	}

	companion object {
		const val SARM_GRAB_POS = 0.4
		const val SARM_UNGRAB_POS = 0.7
		const val MARM_FINUȚ = 0.35
		const val COUNTS_PER_MOTOR_REV = 1120.0
		const val DRIVE_GEAR_REDUCTION = 1.0
		const val COUNTS_PER_DEGREE = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / 360
		const val MAX_TARGET_POS = COUNTS_PER_DEGREE * 65
	}
}