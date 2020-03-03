package org.firstinspires.ftc.teamcode.roadrunner.opmode

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveBase
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveREV

/**
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */
@Config
@TeleOp(group = "drive")
class LocalizationTest : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        telemetry = MultipleTelemetry(telemetry, FtcDashboard.getInstance().telemetry)
        val drive: SampleMecanumDriveBase = SampleMecanumDriveREV(hardwareMap)
        waitForStart()
        while (!isStopRequested) {
            val baseVel = Pose2d(
                    (-gamepad1.left_stick_y).toDouble(),
                    (-gamepad1.left_stick_x).toDouble(),
                    (-gamepad1.right_stick_x).toDouble()
            )
            var vel: Pose2d
            vel = if (Math.abs(baseVel.x) + Math.abs(baseVel.y) + Math.abs(baseVel.heading) > 1) { // re-normalize the powers according to the weights
                val denom = VX_WEIGHT * Math.abs(baseVel.x) + VY_WEIGHT * Math.abs(baseVel.y) + OMEGA_WEIGHT * Math.abs(baseVel.heading)
                Pose2d(
                        VX_WEIGHT * baseVel.x,
                        VY_WEIGHT * baseVel.y,
                        OMEGA_WEIGHT * baseVel.heading
                ).div(denom)
            } else {
                baseVel
            }
            drive.setDrivePower(vel)
            drive.update()
            val poseEstimate = drive.poseEstimate
            telemetry.addData("x", poseEstimate.x)
            telemetry.addData("y", poseEstimate.y)
            telemetry.addData("heading", poseEstimate.heading)
            telemetry.update()
        }
    }

    companion object {
        var VX_WEIGHT = 1.0
        var VY_WEIGHT = 1.0
        var OMEGA_WEIGHT = 1.0
    }
}