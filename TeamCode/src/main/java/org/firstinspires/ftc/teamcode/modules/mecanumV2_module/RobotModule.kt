package org.firstinspires.ftc.teamcode.modules.mecanumV2_module

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice

interface RobotModule {
    var components: HashMap<String, HardwareDevice>
    val opMode: OpMode
    val hardwareMap get() = opMode.hardwareMap
    val telemetry get() = opMode.telemetry
    val linearOpMode get() = opMode as LinearOpMode

    fun init() { }
    fun start() { }
    fun stop() { }

    fun <T: HardwareDevice> get(name: String): T = components[name] as T
}
