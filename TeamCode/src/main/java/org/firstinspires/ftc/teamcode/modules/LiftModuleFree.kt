package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.util.Range

class LiftModuleFree(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val motor get() = get<DcMotorEx>("lift")
	val isBusy get() = motor.isBusy
	val maxPos = 980
	val minPos = 0

	override fun init() {
		components["lift"] = hardwareMap.get(DcMotorEx::class.java, "lift")
		motor.targetPosition = 0
		motor.power = 0.0
		// ORIGINAL PIDF: p=9.999847 i=2.999954 d=0.000000 f=0.000000
		motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
		motor.mode = DcMotor.RunMode.RUN_TO_POSITION
		motor.setVelocityPIDFCoefficients(5.0, 4.0, 2.0, 0.0)
		//motor.targetPosition = (0.05 * COUNTS_PER_REV).toInt()
	}

	fun goUp() {
		if (motor.targetPosition in (minPos) until (maxPos + 1))
			motor.targetPosition = Range.clip(motor.targetPosition + 15, minPos, maxPos)
		motor.power = 0.4
	}

	fun goDown() {
		if (motor.targetPosition in (minPos) until (maxPos + 1))
			motor.targetPosition = Range.clip(motor.targetPosition - 15, minPos, maxPos)
		motor.power = 0.4

	}
}


