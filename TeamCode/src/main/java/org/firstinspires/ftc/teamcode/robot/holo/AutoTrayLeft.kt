package org.firstinspires.ftc.teamcode.robot.holo

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.hardware.DcMotorEx
import org.firstinspires.ftc.teamcode.Holonomic
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.obsolete.HoloGrabber
import org.firstinspires.ftc.teamcode.modules.obsolete.HoloHook
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "HOLO: AUTO PARK LEFT", group = "SKYSTONE HOLO")
class AutoTrayLeft : BBLinearOpMode() {
	override val robot = Robot(this, setOf(Holonomic(this), HoloHook(this), HoloGrabber(this)))

	override fun runOpMode() {
		get<HoloHook>().init()
		get<Holonomic>().init()
		get<HoloGrabber>().init()

		waitForStartFixed()

/*		get<Holonomic>().sideways(36.0, power = 0.9, timeout = 3.0)
		get<Holonomic>().forward(-29.5, power = 0.9, timeout = 3.0)
		get<HoloHook>().grab()
		get<Holonomic>().forward(29.5,0.9,3.0)
		get<HoloHook>().ungrab()
		get<Holonomic>().sideways(-36.0, power = 0.9, timeout = 3.0)
		get<Holonomic>().forward(-22.0, power = 0.9, timeout = 3.0)
		get<Holonomic>().sideways(22.0,0.9,2.0)
*/		get<Holonomic>().sideways(-36.0, power = 0.9, timeout = 1.0)

		get<Holonomic>().get<DcMotorEx>("l").power = 0.5
		get<Holonomic>().get<DcMotorEx>("r").power = 0.5
		wait(1.0)

		get<Holonomic>().stop()
	}}
