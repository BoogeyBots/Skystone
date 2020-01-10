package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.*
import com.qualcomm.robotcore.util.ElapsedTime
import kotlin.math.PI
import kotlin.math.abs

class MecanumDriveTrainModule(override val opMode: OpMode) : DriveTrainModule() {
    override var components: HashMap<String, HardwareDevice> = hashMapOf()

    override fun init() {
        listOf("lf", "rf", "lb", "rb")
            .forEach { name -> components[name] = hardwareMap.get(DcMotorEx::class.java, name) }

        motorsWithNames
            .forEach { (name, motor) ->
                when (name) { "rf", "rb" -> motor.direction = DcMotorSimple.Direction.REVERSE }
            }
    }

    override fun stop() {
        motors.forEach { it.power = 0.0 }
    }

    override fun encoderDrive(inches: Double, power: Double, timeout: Double) {
        val newTarget = (inches * COUNTS_PER_INCH).toInt()
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
        val newTarget = ((degrees * MAGIC_VALUE) / 360 * COUNTS_PER_INCH).toInt()
        val stopwatch = ElapsedTime()

	    motors.forEach { it.direction = DcMotorSimple.Direction.FORWARD }
        motors.forEach { it.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER }
        motors.forEach { it.targetPosition = newTarget }
        motors.forEach { it.mode = DcMotor.RunMode.RUN_TO_POSITION }
        motors.forEach { it.power = abs(power) }

        while (linearOpMode.opModeIsActive() &&
            stopwatch.seconds() < timeout &&
            motors.all { it.isBusy }) {
            // telemetry?
        }

        stop()
    }

    fun forwardUntil(power: Double, predicate: () -> Boolean) {
        while (!predicate()) {
            motors.forEach { it.power = power }
        }
    }

    companion object {
        const val COUNTS_PER_MOTOR_REV = 383.6
        const val WHEEL_DIAMETER = 4.0 // in inches
        const val DRIVE_GEAR_REDUCTION = 2.0
        const val COUNTS_PER_INCH = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION / (WHEEL_DIAMETER * PI)
        const val MAGIC_VALUE = 92.5
	    const val DEFAULT_POWER = 0.5
    }
}