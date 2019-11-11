package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.modules.CameraModule
import org.firstinspires.ftc.teamcode.modules.MecanumDriveTrainModule
import org.firstinspires.ftc.teamcode.modules.GyroModule
import org.firstinspires.ftc.teamcode.modules.RobotModule
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.kotlinFunction

typealias Mecanum = MecanumDriveTrainModule
typealias Camera = CameraModule

class Robot(val opMode: OpMode, val modules: Set<RobotModule>) {
    inline fun <reified T: RobotModule> get(): T = modules.first { x -> x is T } as T
}