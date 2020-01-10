package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareDevice

class LiftModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val liftMotor get() = get<DcMotor>("lift")

	val maxPower: Double = 0.1

	override fun init() {
		components["lift"] = hardwareMap.get(DcMotor::class.java, "lift")
	}

	fun run(p: Double) {
		liftMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
		liftMotor.power = p * maxPower
	}

	fun float() {
		liftMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
	}
}