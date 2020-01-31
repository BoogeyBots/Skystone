package org.firstinspires.ftc.teamcode.robot.mecanumv2

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.*
import org.firstinspires.ftc.teamcode.modules.mecanumV2_module.MecanumDriveTrainModule
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed
import org.firstinspires.ftc.teamcode.modules.mecanumV2_module.ColorSensorModule as ColorSensorModule1

@Autonomous(name = "Auto Left Full", group = "SKYSTONE")
class AutoFullLeftV2 : BBLinearOpMode(){
    override val robot: Robot = Robot(this, setOf(Mecanum(this),Arm(this),Touch(this),Distance(this),Color(this),Hook(this)))
    override fun runOpMode() {
        robot.modules.forEach {it.init()}

        val color = get<Color>()
        val distance = get<Distance>()
        val mecanum = get<Mecanum>()
        val hook = get<Hook>()
        val cub: Int

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
        get<Mecanum>().sidewaysUntil(0.9, {distance.getDistance() < 5.0})
        get<Arm>().grab()
        get<Mecanum>().sideways(-6.0, 0.9, 4.0)
        get<Mecanum>().forward(48.0 + 24.0 + cub*8, 0.9, 4.0)
        get<Arm>().ungrab()
        //TODO("CAND ROBOTUL ESTE GATA, VEDEM CUM NE INVARTIM PE AICI(VALORILE NU SUNT CALCULATE")
        mecanum.sideways(-10.0, 0.9,5.0)
        mecanum.rotate(-90.0, 0.9, 4.0)
        mecanum.forwardUntil(0.9) {get<Touch>().isPressed()}
        hook.grab()
        get<Mecanum>().rotate(-90.0, 0.9, 3.0)
        get<Mecanum>().sideways(30.0, 0.9, 3.0)
        get<Hook>().ungrab()
        get<Mecanum>().forward(40.0, 0.9, 3.0)
    }

}