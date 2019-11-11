package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.HardwareMap

class GyroModule(override val opMode: OpMode) : RobotModule {
    override lateinit var components: HashMap<String, HardwareDevice>

}