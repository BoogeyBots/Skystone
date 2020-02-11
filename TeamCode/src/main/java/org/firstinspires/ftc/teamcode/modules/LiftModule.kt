package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.robotcore.internal.android.dex.util.Unsigned
import kotlin.math.abs

class LiftModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val motor get() = get<DcMotorEx>("lift")
	val maxPower: Double = 0.25
	var currentLevel: Int = 0
		set(value) {
			val oldField = field

			when {
				value < 0 -> field = 0
				value > 4 -> field = MAX_LEVEL
				else -> field = value
			}

			if (oldField > field) {
				if (field == 0) {
					motor.targetPosition = ((field * COUNTS_PER_REV * 0.5).toInt()) + 20
				}
			} else {
				motor.targetPosition = ((field * COUNTS_PER_REV * 0.5).toInt())
			}
			motor.power = LIFT_POWER
		}

	val isCloseToTarget: Boolean get() = (abs(motor.currentPosition - motor.targetPosition) < THRESHOLD)

	override fun init() {
		components["lift"] = hardwareMap.get(DcMotorEx::class.java, "lift")
		motor.targetPosition = 0
		motor.power = 0.0
		// ORIGINAL PIDF: p=9.999847 i=2.999954 d=0.000000 f=0.000000
		motor.mode = DcMotor.RunMode.RUN_TO_POSITION
		motor.setVelocityPIDFCoefficients(5.0, 4.0, 2.0, 0.0)
	}

	companion object {
		const val COUNTS_PER_REV = 560
		const val LIFT_POWER = 0.3
		const val MAX_LEVEL = 4
		const val THRESHOLD = 10
	}
}