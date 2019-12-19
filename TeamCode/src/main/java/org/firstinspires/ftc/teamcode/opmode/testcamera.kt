package org.firstinspires.ftc.teamcode.opmode



import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer

/*
 * This sample demonstrates how to stream frames from Vuforia to the dashboard. Make sure to fill in
 * your Vuforia key below and select the 'Camera' preset on top right of the dashboard. This sample
 * also works for UVCs with slight adjustments.
 */
@Autonomous
class VuforiaStreamOpMode : LinearOpMode() {

    @Throws(InterruptedException::class)
    override fun runOpMode() {
        // gives Vuforia more time to exit before the watchdog notices
        msStuckDetectStop = 2500

        val vuforiaParams = VuforiaLocalizer.Parameters()
        vuforiaParams.vuforiaLicenseKey = VUFORIA_LICENSE_KEY
        vuforiaParams.cameraDirection = VuforiaLocalizer.CameraDirection.BACK
        val vuforia = ClassFactory.getInstance().createVuforia(vuforiaParams)


        waitForStart()

        while (opModeIsActive());
    }

    companion object {

        // TODO: fill in
        val VUFORIA_LICENSE_KEY = "AWo7bzb/////AAABmcbdWZ79Y049lfMcsRS8waNYev8AbC1EwUWqhJnr1poItrv7+etQ1bwW4BiQpg151evO66Pzt3L2LvfbBgzn4aQ3QzVBXYQBjqMScjg/gQEj0g3ldi/0ENHSKwnT48YDxtQQb5/twpwjew9wlaSkZuZ8KtZGwOZHh7vhV0xQmjh1akuPF0zmKvCn5HPnd/O9YxXR5Ef7eyQ+r15XMT7Vd7kG/PUbpCvkexwsRZ4BKGv+oV1ZWOqrYrP5WKbpzHmEOl8RggfJKD707G2Q61vTUW+MEksQwrydbwTCqzTxDUTWdOlgzG9JfGjS+jUdQ3CAN+EETNZDOQs8fIxn3Q+Bdmi823AJLEU3GDhptc7KHcjo"
    }
}