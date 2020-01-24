package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.ColorSensorModule
import org.firstinspires.ftc.teamcode.modules.TouchSensorModule
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get


@TeleOp(name = "Test Touch Module", group = "TEST")
class TestTouchModule : BBOpMode(){
    override val robot: Robot = Robot(this, setOf(TouchSensorModule(this)))

    override fun init() {
        get<TouchSensorModule>().init()
    }

    override fun loop() {
        var touch = get<TouchSensorModule>().is_pressed()
        telemetry.addData("TOUCH" , "${touch}")

    }
}