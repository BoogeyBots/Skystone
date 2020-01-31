package org.firstinspires.ftc.teamcode.robot.mecanumv2

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.Arm
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.mecanumV2_module.ColorSensorModule
import org.firstinspires.ftc.teamcode.modules.mecanumV2_module.DistanceSensorModule
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "DEMO : SKYSTONE LEFT", group = "DEMO AUTO")
abstract class AutoSkystoneLeftV2 : BBLinearOpMode() {
	override val robot = Robot(this, setOf(Mecanum(this), Arm(this), ColorSensorModule(this), DistanceSensorModule(this)))
	private var cub : Int = 0
	override fun runOpMode() {
		robot.modules.forEach {it.init()}

		val color = get<ColorSensorModule>()
		val distance = get<DistanceSensorModule>()

		waitForStartFixed()

		get<Mecanum>().forward(5.0 , 0.8, 4.0)
		get<Mecanum>().sidewaysUntil(0.9) {distance.getDistance() < 5.0 }

		// primul cub
		if(color.IsSkystone() == "skystone"){
			get<Arm>().grab()
			cub = 0
		}
		else{
			get<Mecanum>().forward(-8.0, 0.9, 4.0)
			if(color.IsSkystone() == "skystone"){
				get<Arm>().grab()
				cub = 1
			}
			else{
				get<Mecanum>().forward(-8.0, 0.9, 4.0)
				get<Arm>().grab()
				cub = 2
			}
		}
		get<Mecanum>().sideways(-6.0, 0.9, 4.0)
		get<Mecanum>().forward(48.0 + cub * 8, 0.9, 4.0)
		get<Arm>().ungrab()
		get<Mecanum>().forward(-48.0 - 24.0 - cub*8, 0.9, 4.0)
		get<Mecanum>().sidewaysUntil(0.9) {distance.getDistance() < 5.0}
		get<Arm>().grab()
		get<Mecanum>().sideways(-6.0, 0.9, 4.0)
		get<Mecanum>().forward(48.0 + 24.0 + cub*8, 0.9, 4.0)
		get<Arm>().ungrab()
		get<Mecanum>().forward(-24.0, 0.9, 4.0)

	}
}