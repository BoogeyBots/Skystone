package org.firstinspires.ftc.teamcode.roadrunner.opmode

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.util.Angle.norm
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.MovingStatistics
import org.firstinspires.ftc.robotcore.internal.system.Misc
import org.firstinspires.ftc.teamcode.roadrunner.DriveConstants
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveBase
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveREV

/*
 * This routine determines the effective track width. The procedure works by executing a point turn
 * with a given angle and measuring the difference between that angle and the actual angle (as
 * indicated by an external IMU/gyro, track wheels, or some other localizer). The quotient
 * given angle / actual angle gives a multiplicative adjustment to the estimated track width
 * (effective track width = estimated track width * given angle / actual angle). The routine repeats
 * this procedure a few times and averages the values for additional accuracy. Note: a relatively
 * accurate track width estimate is important or else the angular constraints will be thrown off.
 */
@Config
@Autonomous(group = "drive")
class TrackWidthTuner : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        telemetry = MultipleTelemetry(telemetry, FtcDashboard.getInstance().telemetry)
        val drive: SampleMecanumDriveBase = SampleMecanumDriveREV(hardwareMap)
        // TODO: if you haven't already, set the localizer to something that doesn't depend on
// drive encoders for computing the heading
        telemetry.addLine("Press play to begin the track width tuner routine")
        telemetry.addLine("Make sure your robot has enough clearance to turn smoothly")
        telemetry.update()
        waitForStart()
        if (isStopRequested) return
        telemetry.clearAll()
        telemetry.addLine("Running...")
        telemetry.update()
        val trackWidthStats = MovingStatistics(NUM_TRIALS)
        for (i in 0 until NUM_TRIALS) {
            drive.poseEstimate = Pose2d()
            // it is important to handle heading wraparounds
            var headingAccumulator = 0.0
            var lastHeading = 0.0
            drive.turn(Math.toRadians(ANGLE))
            while (!isStopRequested && drive.isBusy) {
                val heading = drive.poseEstimate.heading
                headingAccumulator += norm(heading - lastHeading)
                lastHeading = heading
                drive.update()
            }
            val trackWidth = DriveConstants.TRACK_WIDTH * Math.toRadians(ANGLE) / headingAccumulator
            trackWidthStats.add(trackWidth)
            sleep(DELAY.toLong())
        }
        telemetry.clearAll()
        telemetry.addLine("Tuning complete")
        telemetry.addLine(Misc.formatInvariant("Effective track width = %.2f (SE = %.3f)",
                trackWidthStats.mean,
                trackWidthStats.standardDeviation / Math.sqrt(NUM_TRIALS.toDouble())))
        telemetry.update()
        while (!isStopRequested) {
            idle()
        }
    }

    companion object {
        var ANGLE = 180.0 // deg
        var NUM_TRIALS = 5
        var DELAY = 1000 // ms
    }
}