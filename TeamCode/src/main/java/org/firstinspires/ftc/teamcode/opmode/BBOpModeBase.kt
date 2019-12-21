package org.firstinspires.ftc.teamcode.opmode

import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.RobotModule

interface BBOpModeBase  {
    val robot: Robot
}

inline fun <reified T: RobotModule> BBOpModeBase.get(): T = robot.get()