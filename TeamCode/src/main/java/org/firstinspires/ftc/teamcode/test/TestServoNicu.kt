package org.firstinspires.ftc.teamcode.test


import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.TestModule
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "Test Servo Nicu", group = "TEST")
class TestServoNicu : BBLinearOpMode() {
    override val robot = Robot(this, setOf(TestModule(this)))
    var resolution = 0.0005
    var resChangeSpeed = 0.00000001

    // NOTES
    //


    override fun runOpMode() {
        val servoMod = robot.modules.first()
	    servoMod.components["servo1"] = hardwareMap.get(Servo::class.java, "arm")
	    servoMod.components["servo2"] = hardwareMap.get(Servo::class.java, "grabber")

        val servo1 = get<TestModule>().get<Servo>("servo1")
        val servo2 = get<TestModule>().get<Servo>("servo2")

	    servo1.position = 1.0


        waitForStart()

        while (opModeIsActive()) {
            resolution += when {
                gamepad1.dpad_up -> resChangeSpeed
                gamepad1.dpad_down -> -resChangeSpeed
                else -> 0.0
            }

            servo1.position = when {
                gamepad1.y -> servo1.position + resolution
                gamepad1.a -> servo1.position - resolution
                else -> servo1.position
            }
            servo2.position = when {
                gamepad1.dpad_right -> servo2.position + resolution
                gamepad1.dpad_left -> servo2.position - resolution
                else -> servo2.position
            }

            telemetry.addData("res", resolution)
            telemetry.addData("servo1", servo1.position)
            telemetry.addData("servo2", servo2.position)

            telemetry.update()
        }
    }
}