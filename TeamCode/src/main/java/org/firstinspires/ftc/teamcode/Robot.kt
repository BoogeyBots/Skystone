package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.modules.mecanumV2_module.*
import org.firstinspires.ftc.teamcode.modules.obsolete.CameraModule
import org.firstinspires.ftc.teamcode.modules.mecanumV2_module.HookModule

typealias Mecanum = MecanumDriveTrainModule
typealias Holonomic = HolonomicDriveTrainModule
typealias Camera = CameraModule
typealias Hook = HookModule
typealias Arm = ArmModule
typealias Lift = LiftModule
typealias Intake = IntakeModule
typealias Output = OutputModule
typealias Distance = DistanceSensorModule
typealias Color = ColorSensorModule
typealias Touch = TouchSensorModule


class Robot(val opMode: OpMode, val modules: Set<RobotModule>) {
    inline fun <reified T: RobotModule> get(): T = modules.first { x -> x is T } as T
}