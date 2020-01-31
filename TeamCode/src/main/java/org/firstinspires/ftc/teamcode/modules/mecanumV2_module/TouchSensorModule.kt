package org.firstinspires.ftc.teamcode.modules.mecanumV2_module

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DigitalChannel
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.TouchSensor

class TouchSensorModule(override val opMode: OpMode) : RobotModule {
    override var components: HashMap<String, HardwareDevice> = hashMapOf()
    val sensor = get<TouchSensor>("touch")


    override fun init() {
        components["touch"] = hardwareMap.get(TouchSensor::class.java, "touch")

    }

    fun isPressed(): Boolean {
        return sensor.isPressed
    }

}