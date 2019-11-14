package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@Autonomous(name = "AUTONOMasdadsa", group = "asdasdas")
class Auto : BBLinearOpMode() {
    override val robot = Robot(this, setOf(Mecanum(this), Camera(this)))

    override fun runOpMode() {
        get<Mecanum>().init()

        waitForStart()

        get<Mecanum>().forward(12.0, 0.6, 3.0)
        get<Mecanum>().forward(-12.0, 0.6, 3.0)
        get<Mecanum>().rotate(90.0, 0.6, 3.0)

        get<Mecanum>().stop()
    }
}
