package org.firstinspires.ftc.teamcode.drive.opmode

import android.graphics.Interpolator
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.acmerobotics.roadrunner.path.heading.ConstantInterpolator
import com.acmerobotics.roadrunner.path.heading.HeadingInterpolator
import com.acmerobotics.roadrunner.path.heading.TangentInterpolator
import com.acmerobotics.roadrunner.trajectory.MarkerCallback
import com.arcrobotics.ftclib.vision.SkystoneDetector
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.Arm
import org.firstinspires.ftc.teamcode.Hook
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveREV
import org.firstinspires.ftc.teamcode.modules.ArmV3Module
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.test.OpenCvModule
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "ROADRUNNER", group =  "TEST")
class TestOPMode: BBLinearOpMode(){
    override val robot: Robot = Robot(this, setOf(Hook(this), ArmV3Module(this), OpenCvModule(this)))
    private var pos = 1

    override fun runOpMode() {
        robot.modules.forEach { it.init() }


        val drive = SampleMecanumDriveREV(hardwareMap)
        drive.poseEstimate = Pose2d(-33.0, 63.0, 0.0)

        waitForStartFixed()

        pos = when (get<OpenCvModule>().run()) {
            SkystoneDetector.SkystonePosition.LEFT_STONE -> 0
            SkystoneDetector.SkystonePosition.CENTER_STONE -> 1
            SkystoneDetector.SkystonePosition.RIGHT_STONE -> 2
            null -> 2
        }

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeTo(Vector2d(-18.5 - pos*8, 34.5))
                        .build()
        )
        wait(0.3)
        get<ArmV3Module>().goDown()
        wait(1.0)
        get<ArmV3Module>().grab()
        wait(0.4)
        get<ArmV3Module>().goUp()
        wait(0.4)

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeTo(Vector2d(0.0, 50.0))
                        .build()
        )



        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeTo(Vector2d(62.5, 29.0))
                        .build()
        )


        get<ArmV3Module>().ungrab()
        wait(0.2)
        get<ArmV3Module>().grab()

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeTo(Vector2d(0.0, 54.0))
                        .build()
        )

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeTo(Vector2d(-47.0 - pos* 8, 34.8))
                        .build()
        )
        get<ArmV3Module>().ungrab()
        wait(0.3)
        get<ArmV3Module>().goDown()
        wait(1.0)
        get<ArmV3Module>().grab()
        wait(0.4)
        get<ArmV3Module>().goUp()
        wait(0.4)

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeTo(Vector2d(0.0, 53.0))
                        .build()
        )

        drive.followTrajectorySync(
                drive.trajectoryBuilder()

                        .strafeTo(Vector2d(49.0, 30.5))
                        .build()
        )
        get<ArmV3Module>().ungrab()

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeTo(Vector2d(49.0,35.0 ))
                        .build()
        )

        drive.turnSync(Math.toRadians(-90.0))

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .lineTo(Vector2d(49.0,29.0))
                        .build()
        )

        wait(0.2)
        get<Hook>().grab()
        wait(0.2)

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .lineTo(Vector2d(49.0,56.0))
                        .build()
        )

        drive.turnSync(Math.toRadians(130.0))

        get<Hook>().ungrab()

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .forward(12.0)
                        .build()
        )

        get<Hook>().ungrab()

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .back(50.0)
                        .build()
        )





    }

}