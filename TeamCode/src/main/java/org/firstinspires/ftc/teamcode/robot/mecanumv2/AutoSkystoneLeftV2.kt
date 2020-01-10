package org.firstinspires.ftc.teamcode.robot.mecanumv2

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.Arm
import org.firstinspires.ftc.teamcode.Camera
import org.firstinspires.ftc.teamcode.Mecanum
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.modules.ColorSensorModule
import org.firstinspires.ftc.teamcode.modules.DistanceSensorModule
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "DEMO : SKYSTONE LEFT", group = "DEMO AUTO")
abstract class AutoSkystoneLeftV2 : BBLinearOpMode() {
	override val robot = Robot(this, setOf(Mecanum(this), Arm(this),  ColorSensorModule(this), DistanceSensorModule(this)))
    // Pana la demo, trebuie schimbate obiectele din "robot"

	override fun runOpMode() {

		get<Mecanum>().init()
		get<ColorSensorModule>().init()
		get<DistanceSensorModule>().init()
		//get<arm>) ..daca avem:(

		val color = get<ColorSensorModule>()
		val distance = get<DistanceSensorModule>()

		waitForStartFixed()

		get<Mecanum>().forward(5.0 , 0.8, 4.0)
		get<Mecanum>().sideways(29.0, 0.8, 4.0)

		// primul cub
		if(color.IsSkystone() == "skystone"){
			// coaie ce plm se face cu unghiuri & shit si n avem autonom
			//ba eu bag pl fac boti pe reddit
		}
	}
}