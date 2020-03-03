package org.firstinspires.ftc.teamcode.roadrunner.opmode

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.tuning.AccelRegression
import com.acmerobotics.roadrunner.tuning.RampRegression
import com.acmerobotics.roadrunner.util.NanoClock
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.RobotLog
import org.firstinspires.ftc.robotcore.internal.system.Misc
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveBase
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveREV
import org.firstinspires.ftc.teamcode.roadrunner.DriveConstants
import org.firstinspires.ftc.teamcode.roadrunner.util.LoggingUtil

/*
 * Op mode for computing kV, kStatic, and kA from various drive routines. For the curious, here's an
 * outline of the procedure:
 *   1. Slowly ramp the motor power and record encoder values along the way.
 *   2. Run a linear regression on the encoder velocity vs. motor power plot to obtain a slope (kV)
 *      and an optional intercept (kStatic).
 *   3. Accelerate the robot (apply constant power) and record the encoder counts.
 *   4. Adjust the encoder data based on the velocity tuning data and find kA with another linear
 *      regression.
 */
@Config
@Autonomous(group = "drive")
class DriveFeedforwardTuner : LinearOpMode() {
    override fun runOpMode() {
        if (DriveConstants.RUN_USING_ENCODER) {
            RobotLog.setGlobalErrorMsg("Feedforward constants usually don't need to be tuned " +
                    "when using the built-in drive motor velocity PID.")
        }
        telemetry = MultipleTelemetry(telemetry, FtcDashboard.getInstance().telemetry)
        val drive: SampleMecanumDriveBase = SampleMecanumDriveREV(hardwareMap)
        val clock = NanoClock.system()
        telemetry.addLine("Press play to begin the feedforward tuning routine")
        telemetry.update()
        waitForStart()
        if (isStopRequested) return
        telemetry.clearAll()
        telemetry.addLine("Would you like to fit kStatic?")
        telemetry.addLine("Press (A) for yes, (B) for no")
        telemetry.update()
        var fitIntercept = false
        while (!isStopRequested) {
            if (gamepad1.a) {
                fitIntercept = true
                while (!isStopRequested && gamepad1.a) {
                    idle()
                }
                break
            } else if (gamepad1.b) {
                while (!isStopRequested && gamepad1.b) {
                    idle()
                }
                break
            }
            idle()
        }
        telemetry.clearAll()
        telemetry.addLine(Misc.formatInvariant(
                "Place your robot on the field with at least %.2f in of room in front", DISTANCE))
        telemetry.addLine("Press (A) to begin")
        telemetry.update()
        while (!isStopRequested && !gamepad1.a) {
            idle()
        }
        while (!isStopRequested && gamepad1.a) {
            idle()
        }
        telemetry.clearAll()
        telemetry.addLine("Running...")
        telemetry.update()
        val maxVel = DriveConstants.rpmToVelocity(DriveConstants.getMaxRpm())
        val finalVel = MAX_POWER * maxVel
        val accel = finalVel * finalVel / (2.0 * DISTANCE)
        val rampTime = Math.sqrt(2.0 * DISTANCE / accel)
        var startTime = clock.seconds()
        val rampRegression = RampRegression()
        drive.poseEstimate = Pose2d()
        while (!isStopRequested) {
            val elapsedTime = clock.seconds() - startTime
            if (elapsedTime > rampTime) {
                break
            }
            val vel = accel * elapsedTime
            val power = vel / maxVel
            rampRegression.add(elapsedTime, drive.poseEstimate.x, power)
            drive.setDrivePower(Pose2d(power, 0.0, 0.0))
            drive.updatePoseEstimate()
        }
        drive.setDrivePower(Pose2d(0.0, 0.0, 0.0))
        val (kV, kStatic, rSquare) = rampRegression.fit(fitIntercept)
        rampRegression.save(LoggingUtil.getLogFile(Misc.formatInvariant(
                "DriveRampRegression-%d.csv", System.currentTimeMillis())))
        telemetry.clearAll()
        telemetry.addLine("Quasi-static ramp up test complete")
        if (fitIntercept) {
            telemetry.addLine(Misc.formatInvariant("kV = %.5f, kStatic = %.5f (R^2 = %.2f)",
                    kV, kStatic, rSquare))
        } else {
            telemetry.addLine(Misc.formatInvariant("kV = %.5f (R^2 = %.2f)",
                    kStatic, rSquare))
        }
        telemetry.addLine("Would you like to fit kA?")
        telemetry.addLine("Press (A) for yes, (B) for no")
        telemetry.update()
        var fitAccelFF = false
        while (!isStopRequested) {
            if (gamepad1.a) {
                fitAccelFF = true
                while (!isStopRequested && gamepad1.a) {
                    idle()
                }
                break
            } else if (gamepad1.b) {
                while (!isStopRequested && gamepad1.b) {
                    idle()
                }
                break
            }
            idle()
        }
        if (fitAccelFF) {
            telemetry.clearAll()
            telemetry.addLine("Place the robot back in its starting position")
            telemetry.addLine("Press (A) to continue")
            telemetry.update()
            while (!isStopRequested && !gamepad1.a) {
                idle()
            }
            while (!isStopRequested && gamepad1.a) {
                idle()
            }
            telemetry.clearAll()
            telemetry.addLine("Running...")
            telemetry.update()
            val maxPowerTime = DISTANCE / maxVel
            startTime = clock.seconds()
            val accelRegression = AccelRegression()
            drive.poseEstimate = Pose2d()
            drive.setDrivePower(Pose2d(MAX_POWER, 0.0, 0.0))
            while (!isStopRequested) {
                val elapsedTime = clock.seconds() - startTime
                if (elapsedTime > maxPowerTime) {
                    break
                }
                accelRegression.add(elapsedTime, drive.poseEstimate.x, MAX_POWER)
                drive.updatePoseEstimate()
            }
            drive.setDrivePower(Pose2d(0.0, 0.0, 0.0))
            val (kA, rSquare1) = accelRegression.fit(
                    kV, kStatic)
            accelRegression.save(LoggingUtil.getLogFile(Misc.formatInvariant(
                    "DriveAccelRegression-%d.csv", System.currentTimeMillis())))
            telemetry.clearAll()
            telemetry.addLine("Constant power test complete")
            telemetry.addLine(Misc.formatInvariant("kA = %.5f (R^2 = %.2f)",
                    kA, rSquare1))
            telemetry.update()
        }
        while (!isStopRequested) {
            idle()
        }
    }

    companion object {
        const val MAX_POWER = 0.7
        const val DISTANCE = 100.0
    }
}