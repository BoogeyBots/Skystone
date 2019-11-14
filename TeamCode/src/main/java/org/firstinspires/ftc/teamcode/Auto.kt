package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get

@Autonomous(name = "AUTONOM", group = "asdasdas")
class Auto : BBLinearOpMode() {
    override val robot = Robot(this, setOf(Mecanum(this), Camera(this), Hook(this), Arm(this)))

    override fun runOpMode() {
	    get<Mecanum>().init()
	    get<Camera>().init()
	    get<Hook>().init()
	    get<Arm>().init()

        waitForStart()

	    get<Camera>().start()

	    get<Mecanum>().sideways(-27.0, power = 0.7, timeout = 3.0)
	    get<Mecanum>().forward(-27.5, power = 0.7, timeout = 3.0)
	    get<Hook>().grab()
	    sleep(1500)
	    get<Mecanum>().forward(27.5, power = 0.7, timeout = 3.0)
	    get<Hook>().ungrab()
	    sleep(1000)
	    get<Mecanum>().sideways(27.0, power = 0.7, timeout = 3.0)
		get<Mecanum>().forward(-24.0, timeout = 3.0)
	    get<Mecanum>().rotate(90.0, timeout = 5.0)
	    //get<Mecanum>().forward(-43.0, timeout = 5.0)
		//val skystonePos = get<Camera>().getSkystoneIndex()

	    /*
	    driveSideways(-26.75,3.0)
        driveForward(29.5,3.0)
        //hâț()
        driveForward(-29.5,3.0)
        //dezhâț()
        driveSideways(26.75,3.0)
        driveForward(24.0,3.0)
        driveSideways(43.0,5.0)
        //poza()

        val poza = 0
        driveSideways(8.0*poza,5.0)
        driveForward(5.5,3.0)
        //hâț2()
        driveForward(-5.5,3.0)
        driveSideways(-8.0*poza-43.0,7.0)
        rotate(180.0,5)
        driveForward(-16.0,3.0)
        driveSideways(9.0+9.5+17.25,5.0)
	     */

        get<Mecanum>().stop()
    }
}
