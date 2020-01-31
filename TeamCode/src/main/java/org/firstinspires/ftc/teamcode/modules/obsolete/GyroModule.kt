package org.firstinspires.ftc.teamcode.modules.obsolete

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import org.firstinspires.ftc.teamcode.modules.mecanumV2_module.RobotModule

class GyroModule(override val opMode: OpMode) : RobotModule {
    override lateinit var components: HashMap<String, HardwareDevice>

}