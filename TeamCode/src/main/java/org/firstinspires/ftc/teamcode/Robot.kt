package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor

class Robot(val opMode: OpMode) {
    lateinit var lf: DcMotor
    lateinit var rf: DcMotor
    lateinit var lb: DcMotor
    lateinit var rb: DcMotor

    fun init() {
        lf = opMode.hardwareMap.get(DcMotor::class.java, "lf")
        rf = opMode.hardwareMap.get(DcMotor::class.java, "rf")
        lb = opMode.hardwareMap.get(DcMotor::class.java, "lb")
        rb = opMode.hardwareMap.get(DcMotor::class.java, "rb")

        lf.power = 0.0
        lb.power = 0.0
        rf.power = 0.0
        rb.power = 0.0
    }
}