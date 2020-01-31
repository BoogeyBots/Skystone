package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.mecanumV2_module.TouchSensorModule
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get


@TeleOp(name = "Test Touch Module", group = "TEST")
class TestTouchModule : BBOpMode(){
    override val robot: Robot = Robot(this, setOf(TouchSensorModule(this)))

    override fun init() {
        get<TouchSensorModule>().init()
    }

    override fun loop() {
        val touch = get<TouchSensorModule>().isPressed()
        telemetry.addData("TOUCH" , "$touch")

    }
}