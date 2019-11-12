package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.modules.HookModule
import org.firstinspires.ftc.teamcode.opmode.BBOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@TeleOp(name = "Controlled", group = "SkyStone")
class Controlled : BBOpMode() {
    override val robot = Robot(this, setOf(Mecanum(this), Hook(this), Arm(this)))
	var maxSpeed = 1.0
	var speedModifier = 0.00001

    override fun init() {
        get<Mecanum>().init()
		get<Hook>().init()
	    get<Arm>().init()
    }

    override fun loop() {
        get<Mecanum>().stop()

	    when {
		    gamepad2.b -> get<Hook>().grab()
		    gamepad2.x -> get<Hook>().ungrab()
		    gamepad2.y -> get<Arm>().grab()
		    gamepad2.a -> get<Arm>().ungrab()
		    gamepad1.dpad_up -> maxSpeed = Range.clip(-1.0, 1.0, maxSpeed + speedModifier)
		    gamepad1.dpad_down -> maxSpeed = Range.clip(-1.0, 1.0, maxSpeed - speedModifier)
	    }

        get<Mecanum>()
            .motorsWithNames
            .forEach { (name, motor) ->
                motor.power = Range.clip((-gamepad1.left_trigger) + gamepad1.right_trigger +
                    when (name) {
                        "lf" -> {
                            (gamepad1.left_stick_x + -gamepad1.right_stick_x).toDouble()
                        }
                        "rf" -> {
                            (-gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble()
                        }
                        "lb" -> {
                            (-gamepad1.left_stick_x + -gamepad1.right_stick_x).toDouble()
                        }
                        "rb" -> {
                            (gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble()
                        }
                        else -> 0.0
                    },
                -maxSpeed, maxSpeed)
            }

	    get<Arm>().move(-gamepad2.left_stick_y.toDouble())

        get<Mecanum>().motorsWithNames.forEach {
            telemetry.addData("MOTOR", "${it.key}: ${(it.value as DcMotor).power}")
        }
	    telemetry.addData("ARM", get<Arm>().get<DcMotor>("marm").power)
    }
}