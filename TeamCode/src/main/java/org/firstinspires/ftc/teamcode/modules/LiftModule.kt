package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareDevice

class LiftModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val motor get() = get<DcMotor>("lift")

	val maxPower: Double =  1.0

	override fun init() {
		components["lift"] = hardwareMap.get(DcMotor::class.java, "lift")
	}

	fun run(p: Double) {
		//motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
		motor.power = p * maxPower
	}

	fun float() {
		motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
	}
}