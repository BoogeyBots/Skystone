package org.firstinspires.ftc.teamcode.roadrunner.opmode

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveBase
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveREV

/*
 * Op mode for tuning follower PID coefficients (located in the drive base classes). The robot
 * drives in a DISTANCE-by-DISTANCE square indefinitely.
 */
@Config
@Autonomous(group = "drive")
class FollowerPIDTuner : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val drive: SampleMecanumDriveBase = SampleMecanumDriveREV(hardwareMap)
        drive.poseEstimate = Pose2d(-DISTANCE / 2, -DISTANCE / 2, 0.0)
        waitForStart()
        if (isStopRequested) return
        while (!isStopRequested) {
            drive.followTrajectorySync(
                    drive.trajectoryBuilder()
                            .forward(DISTANCE)
                            .build()
            )
            drive.turnSync(Math.toRadians(90.0))
        }
    }

    companion object {
        var DISTANCE = 48.0
    }
}