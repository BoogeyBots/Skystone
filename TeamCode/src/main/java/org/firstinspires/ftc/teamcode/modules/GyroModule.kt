package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.HardwareMap

class GyroModule(override val hardwareMap: HardwareMap) : RobotModule {
    override lateinit var components: HashMap<String, HardwareDevice>

}