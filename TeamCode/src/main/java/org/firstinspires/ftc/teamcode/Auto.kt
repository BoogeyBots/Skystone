package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@Autonomous(name = "wee", group = "asdasdas")
class Auto : BBLinearOpMode() {
    override val robot = Robot(this)

    override fun runOpMode() {
        robot.init()

        get<DriveTrain>().init()

        waitForStart()

        get<DriveTrain>().forward(24.0, 0.5, 3.0)
        get<DriveTrain>().forward(-24.0, 0.5, 3.0)

        get<DriveTrain>().stop()
    }
}
