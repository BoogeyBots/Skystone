package org.firstinspires.ftc.teamcode.modules.mecanumV2_module

import com.qualcomm.hardware.rev.Rev2mDistanceSensor
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DistanceSensor
import com.qualcomm.robotcore.hardware.HardwareDevice
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit

class DistanceSensorModule(override val opMode: OpMode) : RobotModule {
	override var components: HashMap<String, HardwareDevice> = hashMapOf()
	private val sensor = get<DistanceSensor>("dist_sensor")


	override fun init() {
		components["dist_sensor"] = hardwareMap.get(DistanceSensor::class.java, "dist_sensor")
	}

	fun getDistance(): Double {
		return sensor.getDistance(DistanceUnit.CM)
	}


}