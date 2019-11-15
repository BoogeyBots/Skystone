package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareDevice

class HolonomicDriveTrainModule(override val opMode: OpMode) : DriveTrainModule() {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()

	override fun init() {
		listOf("f", "b", "l", "r")
			.forEach { name -> components[name] = hardwareMap.get(DcMotorEx::class.java, name) }

		motorsWithNames
			.forEach { (name, motor) ->
				when (name) { "r", "b" -> motor.direction = DcMotorSimple.Direction.REVERSE }
			}
	}

	override fun stop() {
		motors.forEach { it.power = 0.0 }
	}

	override fun encoderDrive(inches: Double, power: Double, timeout: Double) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun forward(inches: Double, power: Double, timeout: Double) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun sideways(inches: Double, power: Double, timeout: Double) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun rotate(degrees: Double, power: Double, timeout: Double) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}