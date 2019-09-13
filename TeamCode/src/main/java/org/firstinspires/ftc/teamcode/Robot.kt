package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.modules.CameraModule
import org.firstinspires.ftc.teamcode.modules.DriveTrainModule
import org.firstinspires.ftc.teamcode.modules.GyroModule
import org.firstinspires.ftc.teamcode.modules.RobotModule

typealias DriveTrain = DriveTrainModule
typealias Camera = CameraModule

class Robot(val opMode: OpMode) {
    var modules: Set<RobotModule> = setOf(
            CameraModule(opMode.hardwareMap, opMode.telemetry),
            DriveTrainModule(opMode.hardwareMap),
            GyroModule(opMode.hardwareMap)
    )

    inline fun <reified T: RobotModule> get(): T = modules.first { x -> x is T } as T
}