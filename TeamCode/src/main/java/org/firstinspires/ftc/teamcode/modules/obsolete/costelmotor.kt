package org.firstinspires.ftc.teamcode.modules.obsolete

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareDevice
import org.firstinspires.ftc.teamcode.modules.mecanumV2_module.RobotModule

class costelmotor(override val opMode: OpMode) : RobotModule {
    override var components: HashMap<String, HardwareDevice> = hashMapOf()

    override fun init(){
        components["costel1"] = hardwareMap.get(DcMotor::class.java , "costel1")
        components["costel2"] = hardwareMap.get(DcMotor::class.java , "costel2")

    }
    fun runfwd(){
        get<DcMotor>("costel1").power = -1.0
        get<DcMotor>("costel2").power = 1.0

    }
    fun runbck(){
        get<DcMotor>("costel1").power = 1.0
        get<DcMotor>("costel2").power = +1.0

    }
}

