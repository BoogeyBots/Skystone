package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.*
import org.firstinspires.ftc.teamcode.modules.ColorSensorModule
import org.firstinspires.ftc.teamcode.modules.DistanceSensorModule
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "AutoSenzori", group = "TEST")
@Disabled
class AutoTestSenzori : BBLinearOpMode() {
    override val robot = Robot(this, setOf(Mecanum(this),  ColorSensorModule(this), DistanceSensorModule(this)))
    val color = get<ColorSensorModule>()
    val distance = get<DistanceSensorModule>()
    var sau = ""
    override fun runOpMode() {
        //get<Mecanum>().init()
        get<ColorSensorModule>().init()
        //get<Arm>().init()
        get<DistanceSensorModule>().init()


        if( IsSkystone() == "skystone")


        telemetry.addData("COLOR", "R:  ${color.frac()} | STONE: ${sau} : DIST: ${distance.getDistance()}")

        telemetry.update()


    }



    fun IsSkystone(): String {

        if(color.frac() > 2 && distance.getDistance() < 4.5 && color.frac() < 5) sau = "stone"
        else if (color.frac() <= 1 && distance.getDistance() < 4.5) sau = "skystone"
        else if (distance.getDistance() > 4.5) sau = "apropie te"
        else sau = "nu e skystone"
        return sau

    }
}

