package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.BBOpModeBase
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "Controlled", group = "SkyStone")
class Controlled : BBOpMode() {
    override val robot = Robot(this)

    override fun init() {
        robot.init()

        get<Camera>().init()
        get<DriveTrain>().init()
    }

    override fun loop() {
        get<DriveTrain>().stop()

        get<DriveTrain>().components
            .filter { it.value is DcMotor }
            .map {
                Pair(it.key, it.value as DcMotor)
            }
            .forEach { (name, motor) ->
                motor.power = Range.clip((-gamepad1.left_trigger) +
                    when (name) {
                        "lf" -> {
                            (-gamepad1.left_stick_x + -gamepad1.right_stick_x).toDouble()
                        }
                        "rf" -> {
                            (gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble()
                        }
                        "lb" -> {
                            (gamepad1.left_stick_x + -gamepad1.right_stick_x).toDouble()
                        }
                        "rb" -> {
                            (-gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble()
                        }
                        else -> 0.0
                    },
                -1.0, 1.0)
            }
    }
}