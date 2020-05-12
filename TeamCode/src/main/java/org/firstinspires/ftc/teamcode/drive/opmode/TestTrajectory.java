package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveREV;

@Autonomous(group = "DRIVE")
public class TestTrajectory extends LinearOpMode {



    @Override
    public void runOpMode()   {
        SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap);
        drive.setPoseEstimate(new Pose2d(-33, 63,0));
        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .splineTo(new Pose2d(-33,33 , 0))

                        .splineTo(new Pose2d(0, 50, 0))
                        .splineTo(new Pose2d(47, 33, 0))

                        .build()
        );

        sleep(2000);
    }
}
