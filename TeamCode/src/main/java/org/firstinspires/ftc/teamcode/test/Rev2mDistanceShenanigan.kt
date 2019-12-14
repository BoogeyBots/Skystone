/*
Copyright (c) 2018 FIRST

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of FIRST nor the names of its contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode.test

import com.qualcomm.hardware.rev.Rev2mDistanceSensor
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DistanceSensor

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit

@TeleOp(name = "Sensor: REV2mDistance", group = "Sensor")
class SensorREV2mDistance : LinearOpMode() {

	private var sensorRange: DistanceSensor? = null

	override fun runOpMode() {

		sensorRange = hardwareMap.get(DistanceSensor::class.java, "distanta")

		val sensorTimeOfFlight = sensorRange as Rev2mDistanceSensor?

		telemetry.addData(">>", "Press start to continue")
		telemetry.update()

		waitForStart()
		while (opModeIsActive()) {
			telemetry.addData("deviceName", sensorRange!!.deviceName)
			telemetry.addData("range", String.format("%.01f in", sensorRange!!.getDistance(DistanceUnit.INCH)))

			telemetry.addData("ID", String.format("%x", sensorTimeOfFlight!!.modelID))
			telemetry.addData("did time out", java.lang.Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()))

			telemetry.update()
		}
	}

}