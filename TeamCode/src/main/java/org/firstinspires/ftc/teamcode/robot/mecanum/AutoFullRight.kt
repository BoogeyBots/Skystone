package org.firstinspires.ftc.teamcode.robot.mecanum

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.*
import org.firstinspires.ftc.teamcode.opmode.BBLinearOpMode
import org.firstinspires.ftc.teamcode.opmode.get
import org.firstinspires.ftc.teamcode.utils.waitForStartFixed

@Autonomous(name = "MECANUM: AUTO FULL RIGHT", group = "SKYSTONE MECANUM")
@Disabled
class AutoFullRight : BBLinearOpMode() {
    override val robot = Robot(this, setOf(Mecanum(this), Camera(this), Hook(this), Arm(this)))

    override fun runOpMode() {
	    get<Mecanum>().init()
	    get<Camera>().init()
	    get<Hook>().init()
	    get<Arm>().init()

	    // PRESS START
        waitForStartFixed()

	    get<Camera>().start()

		// GO TO TRAY
	    get<Mecanum>().sideways(-34.0, power = 0.8, timeout = 3.0)
	    get<Mecanum>().forward(-27.5, power = 0.8, timeout = 3.0)
	    get<Hook>().grab()
	    sleep(1000)
	    get<Mecanum>().forward(27.5, power = 0.8, timeout = 3.0)
	    get<Hook>().ungrab()
	    sleep(1000)
	    // FINISHED WITH TRAY
	    // MOVE FROM BEHIND THE TRAY
	    get<Mecanum>().sideways(34.0, power = 0.8, timeout = 3.0)
		get<Mecanum>().forward(-18.0, power = 0.8, timeout = 3.0)
	    // ROTATE TO FACE THE SKYSTONES WITH THE CAMERA
	    get<Mecanum>().rotate(90.0, power = 0.8, timeout = 5.0)
	    // DROP THE ARM TO GO BELOW THE BRIDGE
	    get<Arm>().marm.power = -0.5
	    wait(1.0)
	    get<Arm>().marm.power = 0.0
	    // PASS THE BRIDGE
	    get<Mecanum>().forward(-43.0, power = 0.8, timeout = 5.0)
		// SCAN THE SKYSTONE & MOVE TO IT & GRAB IT
	    val skystonePos = get<Camera>().getSkystoneIndex()
		get<Mecanum>().forward(-8.0 * skystonePos, power = 0.8, timeout = 5.0)
	    get<Mecanum>().rotate(90.0, timeout = 2.0)
	    get<Mecanum>().forward(8.5, power = 0.8, timeout = 2.0)
	    get<Arm>().grab()
	    get<Mecanum>().forward(-8.5, power = 0.8, timeout = 2.0)
	    // ROTATE SO THE ARM FACES THE TAVĂ
	    get<Mecanum>().rotate(-90.0, timeout = 2.0)
	    // PARK UNDER THE BRIDGE
	    get<Mecanum>().forward(8.0 * skystonePos + 4.0 + 30.0, power = 0.9, timeout = 10.0)
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
