package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.hardware.DcMotorEx

abstract class DriveTrainModule : RobotModule {
	val motors get() = components.filter { it.value is DcMotorEx }.map { it.value as DcMotorEx }.toList()
	val motorsWithNames get() = components.map { Pair(it.key, it.value as DcMotorEx) }.toMap()

	abstract fun encoderDrive(inches: Double, power: Double, timeout: Double)
	abstract fun forward(inches: Double, power: Double = DEFAULT_POWER, timeout: Double)
	abstract fun sideways(inches: Double, power: Double = DEFAULT_POWER, timeout: Double)
	abstract fun rotate(degrees: Double, power: Double = DEFAULT_POWER, timeout: Double)

	companion object {
		const val DEFAULT_POWER = 0.5
	}
}