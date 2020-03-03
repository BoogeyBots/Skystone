package org.firstinspires.ftc.teamcode.roadrunner.opmode

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveBase
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveREV

/*
 * This is a simple routine to test translational drive capabilities.
 */
@Config
@Autonomous(group = "drive")
class StraightTest : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val drive: SampleMecanumDriveBase = SampleMecanumDriveREV(hardwareMap)
        val trajectory = drive.trajectoryBuilder()
                .forward(DISTANCE)
                .build()
        waitForStart()
        if (isStopRequested) return
        drive.followTrajectorySync(trajectory)
    }

    companion object {
        var DISTANCE = 60.0
    }
}