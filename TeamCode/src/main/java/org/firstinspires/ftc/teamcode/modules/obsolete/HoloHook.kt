package org.firstinspires.ftc.teamcode.modules.obsolete

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.modules.mecanumV2_module.RobotModule

class HoloHook(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()

	override fun init() {
        components["hservo"] = hardwareMap.get(Servo::class.java, "hservo")
		ungrab()
	}

	fun grab() {
		get<Servo>("hservo").position = 0.9
	}

	fun ungrab() {
		get<Servo>("hservo").position = 0.0
	}
}