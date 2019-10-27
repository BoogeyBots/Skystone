package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.util.ElapsedTime

@Autonomous(name = "Auto", group = "SkyStone")
class Auto : LinearOpMode() {

    internal var robot = Robot(this)
    private val runtime = ElapsedTime()

    internal val COUNTS_PER_MOTOR_REV = 383.6
    internal val DRIVE_GEAR_REDUCTION = 2.0
    internal val WHEEL_DIAMETER_INCHES = 4.0
    internal val COUNTS_PER_INCH = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION / (WHEEL_DIAMETER_INCHES * 3.1415)
    internal val DRIVE_SPEED = 1.0
    internal val TURN_SPEED = 0.5


    override fun runOpMode() {
        robot.init()
        telemetry.addData("Status", "Resetting Encoders")
        telemetry.update()

        robot.lf.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        robot.rf.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        robot.lb.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        robot.rb.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER

        robot.lf.mode = DcMotor.RunMode.RUN_USING_ENCODER
        robot.rf.mode = DcMotor.RunMode.RUN_USING_ENCODER
        robot.lb.mode = DcMotor.RunMode.RUN_USING_ENCODER
        robot.rb.mode = DcMotor.RunMode.RUN_USING_ENCODER

        telemetry.addData("Path0", "Starting at %7d :%7d",
                robot.lf.currentPosition,
                robot.rf.currentPosition,
                robot.lb.currentPosition,
                robot.rb.currentPosition)
        telemetry.update()

        waitForStart()

        driveForward(24.0, 5.0)
        sleep(1000)
        driveSideways(24.0, 5.0)
        sleep(1000)
        driveForward(-24.0, 5.0)
        sleep(1000)
        driveSideways(-24.0, 5.0)


        telemetry.addData("Path", "Complete")
        telemetry.update()
    }

    fun encoderDrive(speed: Double,
                     inches: Double,
                     timeoutS: Double) {
        val newLeftFrontTarget: Int
        val newRightFrontTarget: Int
        val newLeftBackTarget: Int
        val newRightBackTarget: Int


        if (opModeIsActive()) {

            newLeftFrontTarget = robot.lf.currentPosition + (inches * COUNTS_PER_INCH).toInt()
            newRightFrontTarget = robot.rf.currentPosition + (inches * COUNTS_PER_INCH).toInt()
            newLeftBackTarget = robot.lb.currentPosition + (inches * COUNTS_PER_INCH).toInt()
            newRightBackTarget = robot.rb.currentPosition + (inches * COUNTS_PER_INCH).toInt()
            robot.lf.targetPosition = newLeftFrontTarget
            robot.rf.targetPosition = newRightFrontTarget
            robot.lb.targetPosition = newLeftBackTarget
            robot.rb.targetPosition = newRightBackTarget

            robot.lf.mode = DcMotor.RunMode.RUN_TO_POSITION
            robot.rf.mode = DcMotor.RunMode.RUN_TO_POSITION
            robot.lb.mode = DcMotor.RunMode.RUN_TO_POSITION
            robot.rb.mode = DcMotor.RunMode.RUN_TO_POSITION

            runtime.reset()
            robot.lf.power = Math.abs(speed)
            robot.rf.power = Math.abs(speed)
            robot.lb.power = Math.abs(speed)
            robot.rb.power = Math.abs(speed)

            while (opModeIsActive() &&
                    runtime.seconds() < timeoutS &&
                    robot.lf.isBusy && robot.rf.isBusy && robot.lb.isBusy && robot.rb.isBusy) {

                telemetry.addData("Path1", "Running to %7d :%7d", newLeftFrontTarget, newRightFrontTarget, newLeftBackTarget, newRightBackTarget)
                telemetry.addData("Path2", "Running at %7d :%7d",
                        robot.lf.currentPosition,
                        robot.rf.currentPosition,
                        robot.lb.currentPosition,
                        robot.rb.currentPosition)
                telemetry.update()
            }

            robot.lf.power = 0.0
            robot.rf.power = 0.0
            robot.lb.power = 0.0
            robot.rb.power = 0.0

            robot.lf.mode = DcMotor.RunMode.RUN_USING_ENCODER
            robot.rf.mode = DcMotor.RunMode.RUN_USING_ENCODER
            robot.lb.mode = DcMotor.RunMode.RUN_USING_ENCODER
            robot.rb.mode = DcMotor.RunMode.RUN_USING_ENCODER


        }
    }

    internal var hwMap: HardwareMap? = null

    public fun driveForward(inches: Double,
                            timeout: Double) {
        robot.lf.direction = DcMotorSimple.Direction.FORWARD
        robot.rf.direction = DcMotorSimple.Direction.REVERSE
        robot.lb.direction = DcMotorSimple.Direction.FORWARD
        robot.rb.direction = DcMotorSimple.Direction.REVERSE
        encoderDrive(DRIVE_SPEED, inches, timeout)

    }

    public fun driveSideways(inches: Double,
                             timeout: Double) {
        robot.lf.direction = DcMotorSimple.Direction.FORWARD
        robot.rf.direction = DcMotorSimple.Direction.REVERSE
        robot.lb.direction = DcMotorSimple.Direction.REVERSE
        robot.rb.direction = DcMotorSimple.Direction.FORWARD
        encoderDrive(DRIVE_SPEED, inches, timeout)
    }

    companion object {

        internal val COUNTS_PER_MOTOR_REV = 383.6
        internal val DRIVE_GEAR_REDUCTION = 2.0
        internal val WHEEL_DIAMETER_INCHES = 4.0
        internal val COUNTS_PER_INCH = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION / (WHEEL_DIAMETER_INCHES * 3.1415)
        internal val DRIVE_SPEED = 0.6
    }
}
