package org.firstinspires.ftc.teamcode.roadrunner.opmode

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.dashboard.config.ValueProvider
import com.acmerobotics.dashboard.config.variable.BasicVariable
import com.acmerobotics.dashboard.config.variable.CustomVariable
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.profile.MotionProfile
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator
import com.acmerobotics.roadrunner.profile.MotionState
import com.acmerobotics.roadrunner.util.NanoClock
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.RobotLog
import org.firstinspires.ftc.teamcode.roadrunner.DriveConstants
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveBase
import org.firstinspires.ftc.teamcode.roadrunner.mecanum.SampleMecanumDriveREV

/*
 * This routine is designed to tune the PID coefficients used by the REV Expansion Hubs for closed-
 * loop velocity control. Although it may seem unnecessary, tuning these coefficients is just as
 * important as the positional parameters. Like the other manual tuning routines, this op mode
 * relies heavily upon the dashboard. To access the dashboard, connect your computer to the RC's
 * WiFi network and navigate to https://192.168.49.1:8080/dash in your browser. Once you've
 * successfully connected, start the program, and your robot will begin moving forward and backward
 * according to a motion profile. Your job is to graph the velocity errors over time and adjust the
 * PID coefficients. Once you've found a satisfactory set of gains, add them to your roadrunner class
 * ctor.
 */
@Config
@Autonomous(group = "roadrunner")
class roadrunnerVelocityPIDTuner : LinearOpMode() {
    private val dashboard = FtcDashboard.getInstance()
    private var catName: String? = null
    private var catVar: CustomVariable? = null
    private var roadrunner: SampleMecanumDriveBase? = null
    private fun addPidVariable() {
        catName = javaClass.simpleName
        catVar = dashboard.configRoot.getVariable(catName) as CustomVariable
        if (catVar == null) { // this should never happen...
            catVar = CustomVariable()
            dashboard.configRoot.putVariable(catName, catVar)
            RobotLog.w("Unable to find top-level category %s", catName)
        }
        val pidVar = CustomVariable()
        pidVar.putVariable("kP", BasicVariable(object : ValueProvider<Double> {
            override fun get(): Double {
                return roadrunner!!.getPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER).kP
            }

            override fun set(value: Double) {
                val (_, kI, kD) = roadrunner!!.getPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER)
                roadrunner!!.setPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,
                        PIDCoefficients(value, kI, kD))
            }
        }))
        pidVar.putVariable("kI", BasicVariable(object : ValueProvider<Double> {
            override fun get(): Double {
                return roadrunner!!.getPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER).kI
            }

            override fun set(value: Double) {
                val (kP, _, kD) = roadrunner!!.getPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER)
                roadrunner!!.setPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,
                        PIDCoefficients(kP, value, kD))
            }
        }))
        pidVar.putVariable("kD", BasicVariable(object : ValueProvider<Double> {
            override fun get(): Double {
                return roadrunner!!.getPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER).kD
            }

            override fun set(value: Double) {
                val (kP, kI) = roadrunner!!.getPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER)
                roadrunner!!.setPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,
                        PIDCoefficients(kP, kI, value))
            }
        }))
        catVar!!.putVariable(PID_VAR_NAME, pidVar)
        dashboard.updateConfig()
    }

    private fun removePidVariable() {
        if (catVar!!.size() > 1) {
            catVar!!.removeVariable(PID_VAR_NAME)
        } else {
            dashboard.configRoot.removeVariable(catName)
        }
        dashboard.updateConfig()
    }

    override fun runOpMode() {
        if (!DriveConstants.RUN_USING_ENCODER) {
            RobotLog.setGlobalErrorMsg("%s does not need to be run if the built-in motor velocity" +
                    "PID is not in use", javaClass.simpleName)
        }
        telemetry = MultipleTelemetry(telemetry, dashboard.telemetry)
        roadrunner = SampleMecanumDriveREV(hardwareMap)
        addPidVariable()
        val clock = NanoClock.system()
        telemetry.addLine("Ready!")
        telemetry.update()
        telemetry.clearAll()
        waitForStart()
        if (isStopRequested) return
        var movingForwards = true
        var activeProfile = generateProfile(true)
        var profileStart = clock.seconds()
        while (!isStopRequested) { // calculate and set the motor power
            val profileTime = clock.seconds() - profileStart
            if (profileTime > activeProfile.duration()) { // generate a new profile
                movingForwards = !movingForwards
                activeProfile = generateProfile(movingForwards)
                profileStart = clock.seconds()
            }
            val motionState = activeProfile[profileTime]
            val targetPower = DriveConstants.kV * motionState.v
            roadrunner!!.setDrivePower(Pose2d(targetPower, 0.0, 0.0))
            val velocities = roadrunner!!.getWheelVelocities()
            // update telemetry
            telemetry.addData("targetVelocity", motionState.v)
            for (i in velocities.indices) {
                telemetry.addData("velocity$i", velocities[i])
                telemetry.addData("error$i", motionState.v - velocities[i])
            }
            telemetry.update()
        }
        removePidVariable()
    }

    companion object {
        var DISTANCE = 72.0
        private const val PID_VAR_NAME = "VELO_PID"
        private fun generateProfile(movingForward: Boolean): MotionProfile {
            val start = MotionState(if (movingForward) 0.0 else DISTANCE, 0.0, 0.0, 0.0)
            val goal = MotionState(if (movingForward) DISTANCE else 0.0, 0.0, 0.0, 0.0)
            return MotionProfileGenerator.generateSimpleMotionProfile(start, goal,
                    DriveConstants.BASE_CONSTRAINTS.maxVel,
                    DriveConstants.BASE_CONSTRAINTS.maxAccel,
                    DriveConstants.BASE_CONSTRAINTS.maxJerk)
        }
    }
}