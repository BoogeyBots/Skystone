package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.HardwareMap

interface RobotModule {
    var components: HashMap<String, HardwareDevice>
    val hardwareMap: HardwareMap

    fun init() { }
    fun start() { }
    fun stop() { }

    fun <T: HardwareDevice> get(name: String): T = components[name] as T
}
