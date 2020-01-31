package org.firstinspires.ftc.teamcode.modules.mecanumV2_module

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo

class OutputModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	val motor get() = get<DcMotor>("om")
	val servo get () = get<Servo>("os")
	val maxPower = 0.7

	override fun init() {
		components["om"] = hardwareMap.get(DcMotor::class.java, "om")
		components["os"] = hardwareMap.get(Servo::class.java, "os")
	}

	fun run(power: Double) {
		motor.power = power * maxPower
	}

	fun grab() {
		servo.position = 0.0
	}

	fun ungrab() {
		servo.position = 0.22
	}

	override fun stop() {
		motor.power = 0.0
	}
}