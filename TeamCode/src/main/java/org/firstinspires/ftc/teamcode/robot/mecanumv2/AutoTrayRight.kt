package org.firstinspires.ftc.teamcode.robot.mecanumv2

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.Hook
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.TouchSensorModule
import org.firstinspires.ftc.teamcode.modules.TrayTouchModule
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "AUTO TRAY RIGHT", group = "SKYSTONE")
@Disabled
class AutoTrayRight : BBLinearOpMode() {
	override val robot: Robot = Robot(this, setOf(Mecanum(this), TrayTouchModule(this)))
	override fun runOpMode() {
		robot.modules.forEach { it.init() }



		waitForStartFixed()
		//Robotul incepe cu fata lipita de perete, cu partea dreapta a robotului langa prima linie
		get<Mecanum>().sideways(12.0, 0.9, 2.0)
		get<Mecanum>().forwardUntil(-0.9) { get<TrayTouchModule>().isPressed }
		get<Hook>().grab()
		get<Mecanum>().rotate(-90.0, 0.9, 3.0)
		get<Mecanum>().sideways(30.0, 0.9, 3.0)
		get<Hook>().ungrab()
		get<Mecanum>().forward(40.0, 0.9, 3.0)

	}

}