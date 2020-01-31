package org.firstinspires.ftc.teamcode.robot.mecanumv2

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.*
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "Auto Left Full", group = "SKYSTONE")
class AutoFulleftV2 : BBLinearOpMode(){
    override val robot: Robot = Robot(this, setOf(Mecanum(this),Arm(this),Touch(this),Distance(this),Color(this),Hook(this)))
    override fun runOpMode() {
        robot.modules.forEach {it.init()}

        val color = get<Color>()
        val distance = get<Distance>()
        val mecanum = get<Mecanum>()
        val hook = get<Hook>()

        waitForStartFixed()
        //Robotul incepe cu fata lipita de perete, cu partea stanga a robotului langa a doua linie
        get<Mecanum>().sideways(18.0, 0.9, 2.0)
        get<Mecanum>().forwardUntil(-0.9) { get<Touch>().isPressed() }
        get<Hook>().grab()
        get<Mecanum>().rotate(-90.0, 0.9, 3.0)
        get<Mecanum>().sideways(30.0, 0.9, 3.0)
        get<Hook>().ungrab()


    }

}