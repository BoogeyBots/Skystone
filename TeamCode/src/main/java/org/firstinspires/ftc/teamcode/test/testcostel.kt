package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.TestModule
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "TEST COSTEL" , group = "TEST")
@Disabled
class TestCostel : BBOpMode() {
    override val robot: Robot = Robot(this, setOf(TestModule(this)))
    lateinit var motor1: DcMotorEx
    lateinit var motor2: DcMotorEx

    override fun init() {
        motor1 = hardwareMap.get(DcMotorEx::class.java, "costel1")
        motor2 = hardwareMap.get(DcMotorEx::class.java, "costel2")
    }

    override fun loop() {
        if (gamepad1.dpad_up) {
            motor1.power = 1.0
            motor2.power = -1.0
        }
        if (gamepad1.dpad_down) {
            motor1.power = -1.0
            motor2.power = 1.0
        }
    }
}
