package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.modules.*
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.kotlinFunction

typealias Mecanum = MecanumDriveTrainModule
typealias Holonomic = HolonomicDriveTrainModule
typealias Hook = HookModule
typealias Lift = LiftModule
typealias Intake = IntakeModule
typealias Output = OutputModule

class Robot(val opMode: OpMode, val modules: Set<RobotModule>) {
    inline fun <reified T: RobotModule> get(): T = modules.first { x -> x is T } as T
}