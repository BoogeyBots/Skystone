package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareDevice

class IntakeModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val leftMotor get() = get<DcMotor>("int_l")
	val rightMotor get() = get<DcMotor>("int_r")

	override fun init() {
		components["int_l"] = hardwareMap.get(DcMotor::class.java, "int_l")
		components["int_r"] = hardwareMap.get(DcMotor::class.java, "int_r")
		rightMotor.direction = DcMotorSimple.Direction.REVERSE
	}

	fun run(power: Double) {
		leftMotor.power = power
		rightMotor.power = power
	}

	override fun stop() {
		run(0.0)
	}
}