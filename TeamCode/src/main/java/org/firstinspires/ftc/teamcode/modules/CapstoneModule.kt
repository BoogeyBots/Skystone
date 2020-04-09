package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareDevice
import com.qualcomm.robotcore.hardware.Servo

class CapstoneModule(override val opMode: OpMode): RobotModule {
    override var components: HashMap<String, HardwareDevice> = hashMapOf()

    val servocapstone get() = get<Servo>("servocapstone")

    override fun init() {
        components["servocapstone"] = hardwareMap.get(Servo::class.java, "servocapstone")
        not_put()
    }

    fun put(){
        servocapstone.position = 0.9
    }

    fun not_put(){
        servocapstone.position = 0.09
    }

}