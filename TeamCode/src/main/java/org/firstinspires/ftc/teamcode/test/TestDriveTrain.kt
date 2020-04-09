package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get


@TeleOp(name= "TEST DRIVE TRAIN ", group = "TEST")
class TestDriveTrain : BBOpMode(){
    override val robot: Robot = Robot(this, setOf(Mecanum(this)))
    var pwr  = 0.01
    private val incr = 0.001
    override fun init() {
        get<Mecanum>().init()
        get<Mecanum>().motors.forEach{
              it.mode = DcMotor.RunMode.RUN_TO_POSITION
              it.mode = DcMotor.RunMode.RUN_TO_POSITION
        }
    }


    override fun loop() {
        /*
        if (gamepad1.a) get<Mecanum>().motors.forEach{it.power = pwr}
        else get<Mecanum>().motors.forEach{it.power = 0.0}
        if(gamepad1.dpad_down) pwr -= incr
        if(gamepad1.dpad_up) pwr += incr

       telemetry.addData("POWER  ", pwr)
         */


    }
}

