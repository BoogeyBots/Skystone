package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.HardwareMap

class DriveTrainModule(override val hardwareMap: HardwareMap) : RobotModule {
    override var components: HashMap<String, HardwareDevice> = hashMapOf()
    val motors get() = components.map { it.value as DcMotor }
    val names = listOf("lf", "rf", "lb", "rb")

    companion object {
        val TICKS_PER_REV = 1440
        val WHEEL_DIAMETER = 4 // in inches
    }

    override fun init() {
        names.forEach { name -> components[name] = hardwareMap.get(DcMotor::class.java, name) }

        components
                .map { Pair(it.key, it.value as DcMotor) }
                .forEach { (name, motor) ->
                    motor.power = 0.0
                    motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
                    when (name) { "rf", "rb" -> motor.direction = DcMotorSimple.Direction.REVERSE }
                }
    }

    fun advance(power: Double) {
        motors.forEach { it.power = power }
    }

    fun strafe(power: Double) {
        listOf("lf", "rb").forEach { get<DcMotor>(it).power = power }
        listOf("rf", "lb").forEach { get<DcMotor>(it).power = -power }
    }

    fun advanceByDistance(power: Double, inches: Double) {
        motors.forEach {
            it.power = 0.0
            it.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
            it.targetPosition = ((inches / WHEEL_DIAMETER) * TICKS_PER_REV).toInt()
            it.mode = DcMotor.RunMode.RUN_TO_POSITION
        }
    }

    override fun stop() {
        components.map { it.value as DcMotor }.forEach { it.power = 0.0 }
    }
}