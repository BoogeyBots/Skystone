package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DistanceSensor
import com.qualcomm.robotcore.hardware.HardwareDevice

class DistanceSensorModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	var sensor = get<DistanceSensor>("dist_sensor")


	override fun init() {
		components["dist_sensor"] = hardwareMap.get(DistanceSensor::class.java, "dist_sensor")
	}

	fun getDistance() {

	}
}