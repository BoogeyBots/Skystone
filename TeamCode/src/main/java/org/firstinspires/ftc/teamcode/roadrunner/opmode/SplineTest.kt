package org.firstinspires.ftc.teamcode.roadrunner.opmode

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveBase
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveREV

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "drive")
class SplineTest : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val drive: SampleMecanumDriveBase = SampleMecanumDriveREV(hardwareMap)
        waitForStart()
        if (isStopRequested) return
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .splineTo(Pose2d(24.0, (-24).toDouble(), 0.0))
                        .build()
        )
        sleep(2000)
        /*
        drive.followTrajectorySync(
                drive.trajectoryBuilder()

                        .splineTo(new Pose2d(0, 0, 0))
                        .build()
        );

 */
    }
}