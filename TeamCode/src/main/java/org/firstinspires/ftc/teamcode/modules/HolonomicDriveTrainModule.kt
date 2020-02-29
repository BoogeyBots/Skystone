package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.util.ElapsedTime
import kotlin.math.PI
import kotlin.math.abs

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
		val newTarget = (inches * MecanumDriveTrainModule.COUNTS_PER_INCH).toInt()
		val stopwatch = ElapsedTime()

		motors.forEach { it.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER }
		motors.forEach { it.targetPosition = newTarget }
		motors.forEach { it.mode = DcMotor.RunMode.RUN_TO_POSITION }
		motors.forEach { it.power = abs(power) }

		while (linearOpMode.opModeIsActive() &&
			stopwatch.seconds() < timeout &&
			motors.all { it.isBusy }) {
			motorsWithNames.forEach {
				telemetry.addData(it.key, "${it.value.currentPosition} ->> ${it.value.targetPosition}")
			}
			telemetry.update()
		}

		stop()
	}

	override fun forward(inches: Double, power: Double, timeout: Double) {
		get<DcMotorEx>("lf").direction = DcMotorSimple.Direction.FORWARD
		get<DcMotorEx>("rf").direction = DcMotorSimple.Direction.REVERSE
		get<DcMotorEx>("lb").direction = DcMotorSimple.Direction.FORWARD
		get<DcMotorEx>("rb").direction = DcMotorSimple.Direction.REVERSE
		encoderDrive(inches, power, timeout)

	}

	override fun sideways(inches: Double, power: Double, timeout: Double) {
		get<DcMotorEx>("lf").direction = DcMotorSimple.Direction.FORWARD
		get<DcMotorEx>("rf").direction = DcMotorSimple.Direction.FORWARD
		get<DcMotorEx>("lb").direction = DcMotorSimple.Direction.REVERSE
		get<DcMotorEx>("rb").direction = DcMotorSimple.Direction.REVERSE
		encoderDrive(inches, power, timeout)
	}

	override fun rotate(degrees: Double, power: Double, timeout: Double) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	companion object {
		const val COUNTS_PER_REV = 1120.0
		const val WHEEL_DIAMETER = 4.0 // in inches
		const val DRIVE_GEAR_REDUCTION = 1.0
		const val COUNTS_PER_INCH = MecanumDriveTrainModule.COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION / (WHEEL_DIAMETER * PI)
		const val MAGIC_VALUE = 92.5
		const val DEFAULT_POWER = 0.5
	}
}