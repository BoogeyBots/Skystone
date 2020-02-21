package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo

class DcLinear(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val dclinear get() = get<DcMotorEx>("dclinear")

	override fun init() {
		components["dclinear"] = hardwareMap.get(DcMotorEx::class.java, "dclinear")

	}

	fun fwd() {
		dclinear.power = 0.3
	}

	fun bck() {
		dclinear.power = -0.3
	}

	fun stopOvr() {
		dclinear.power = 0.0
	}

}