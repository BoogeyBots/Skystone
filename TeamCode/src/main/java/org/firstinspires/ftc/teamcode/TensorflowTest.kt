package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@Autonomous(name = "Tensorflow Test", group = "SkyStone")
class TensorflowTest : BBLinearOpMode() {
    override val robot = Robot(this)

    override fun runOpMode() {
        get<Camera>().tensorflowStart()

        waitForStart()

        while (opModeIsActive()) {
            val recognitions = get<Camera>().tensorflowScan()
            telemetry.addData("# Recognitions", recognitions.count())

            recognitions.forEachIndexed { index, recognition ->
                telemetry.addData(String.format("label (%d)", index), recognition.label)
                telemetry.addData(String.format("  left,top (%d)", index), "%.03f , %.03f",
                        recognition.left, recognition.top)
                telemetry.addData(String.format("  right,bottom (%d)", index), "%.03f , %.03f",
                        recognition.right, recognition.bottom)
            }

            telemetry.update()
        }

        get<Camera>().tensorflowStop()
    }
}