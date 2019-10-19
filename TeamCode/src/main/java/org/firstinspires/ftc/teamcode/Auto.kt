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
        get<Camera>().init()

        waitForStart()

        get<DriveTrain>().motors.forEach { it.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT }
        get<DriveTrain>().advance(0.5)

        @Suppress("ControlFlowWithEmptyBody")
        while (opModeIsActive()) {}

        get<DriveTrain>().stop()
    }
}
