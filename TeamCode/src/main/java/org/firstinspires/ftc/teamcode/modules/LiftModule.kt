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
	val isBusy get() = motor.isBusy
	val maxPower: Double = 0.25
	val targets = listOf(0.5 * COUNTS_PER_REV, 0.3 * COUNTS_PER_REV, 0.3 * COUNTS_PER_REV, 0.3 * COUNTS_PER_REV, 0.25 * COUNTS_PER_REV)
	val maxLevel get() = targets.size
	var level = 0
		set(value) {
			when {
				value < 0 -> field = 0
				value > maxLevel -> field = maxLevel
				else -> field = value
			}
		}

	var actualLevel: Int = 0
		private set(value) {
			val oldLevel = field

			when {
				value < 0 -> field = 0
				value > maxLevel -> field = maxLevel
				else -> field = value
			}

			motor.targetPosition = targets.take(field).sum().toInt()
			if (oldLevel > field && field == 0) {
				motor.targetPosition += 20
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

	fun update() {
		actualLevel = level
	}

	companion object {
		const val COUNTS_PER_REV = 560
		const val LIFT_POWER = 0.3
		const val THRESHOLD = 10
	}
}