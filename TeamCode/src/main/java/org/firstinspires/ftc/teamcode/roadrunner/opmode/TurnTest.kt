package org.firstinspires.ftc.teamcode.drive.opmode

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveBase
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveREV

/*
 * This is a simple routine to test turning capabilities.
 */
@Config
@Autonomous(group = "drive")
class TurnTest : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val drive: SampleMecanumDriveBase = SampleMecanumDriveREV(hardwareMap)
        waitForStart()
        if (isStopRequested) return
        drive.turnSync(Math.toRadians(ANGLE))
    }

    companion object {
        var ANGLE = 90.0 // deg
    }
}