package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DigitalChannel
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.TouchSensor

class TouchSensorModule(override val opMode: OpMode) : RobotModule {
    override var components: HashMap<String, HardwareDevice> = hashMapOf()
    var sensor // Hardware Device Object
            : DigitalChannel? = null


    override fun init() {
        sensor = hardwareMap.get(DigitalChannel::class.java , "touch_sensor")
        sensor!!.setMode(DigitalChannel.Mode.INPUT)

    }

    fun is_pressed() : Boolean{
        return sensor!!.state}



}