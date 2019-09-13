package org.firstinspires.ftc.teamcode

import kotlin.math.abs

// PID controller courtesy of Peter Tischler, with modifications.

class PIDController
/**
 * Allocate a PID object with the given constants for P, I, D
 * @param Kp the proportional coefficient
 * @param Ki the integral coefficient
 * @param Kd the derivative coefficient
 */
(var Kp: Double, var Ki: Double, var Kd: Double) {
    private var input: Double = 0.toDouble()                 // sensor input for pid controller
    private var maximumOutput = 1.0    // |maximum output|
    private var minimumOutput = -1.0    // |minimum output|
    private var maximumInput = 0.0    // maximum input - limit setpoint to this
    private var minimumInput = 0.0    // minimum input - limit setpoint to this
    private var continuous = false    // do the endpoints wrap around? eg. Absolute encoder
    private var enabled = false      // is the pid controller enabled
    private var prevError = 0.0       // the prior sensor input (used to compute velocity)
    private var totalError = 0.0      // the sum of the errors for use in the integral calc
    private var tolerance = 0.05      // the percentage error that is considered on target
    /**
     * Returns the current setpoint of the PIDController
     * @return the current setpoint
     */
    /**
     * Set the setpoint for the PIDController
     * @param setpoint the desired setpoint
     */
    var setpoint = 0.0
        set(setpoint) {
            var sign = 1

            if (maximumInput > minimumInput) {
                if (setpoint < 0) sign = -1

                field = when {
                    abs(setpoint) > maximumInput -> maximumInput * sign
                    abs(setpoint) < minimumInput -> minimumInput * sign
                    else -> setpoint
                }
            } else
                field = setpoint
        }
    /**
     * Retruns the current difference of the input from the setpoint
     * @return the current error
     */
    @get:Synchronized
    var error = 0.0
        private set
    private var result = 0.0

    /**
     * Read the input, calculate the output accordingly, and write to the output.
     * This should only be called by the PIDTask
     * and is created during initialization.
     */
    private fun calculate() {
        var sign = 1

        // If enabled then proceed into controller calculations
        if (enabled) {
            // Calculate the error signal
            error = setpoint - input

            // If continuous is set to true allow wrap around
            if (continuous) {
                if (abs(error) > (maximumInput - minimumInput) / 2) {
                    error = if (error > 0)
                        error - maximumInput + minimumInput
                    else
                        error + maximumInput - minimumInput
                }
            }

            // Integrate the errors as long as the upcoming integrator does
            // not exceed the minimum and maximum output thresholds.

            if (abs(totalError + error) * Ki < maximumOutput && abs(totalError + error) * Ki > minimumOutput)
                totalError += error

            // Perform the primary PID calculation
            result = Kp * error + Ki * totalError + Kd * (error - prevError)

            // Set the current error to the previous error for the next cycle.
            prevError = error

            if (result < 0) sign = -1    // Record sign of result.

            // Make sure the final result is within bounds. If we constrain the result, we make
            // sure the sign of the constrained result matches the original result sign.
            if (abs(result) > maximumOutput)
                result = maximumOutput * sign
            else if (abs(result) < minimumOutput)
                result = minimumOutput * sign
        }
    }

    /**
     * Set the PID Controller gain parameters.
     * Set the proportional, integral, and differential coefficients.
     * @param p Proportional coefficient
     * @param i Integral coefficient
     * @param d Differential coefficient
     */
    fun setPID(p: Double, i: Double, d: Double) {
        this.Kp = p
        this.Ki = i
        this.Kd = d
    }

    /**
     * Return the current PID result for the last input set with setInput().
     * This is always centered on zero and constrained the the max and min outs
     * @return the latest calculated output
     */
    fun performPID(): Double {
        calculate()
        return result
    }

    /**
     * Return the current PID result for the specified input.
     * @param input The input value to be used to calculate the PID result.
     * This is always centered on zero and constrained the the max and min outs
     * @return the latest calculated output
     */
    fun performPID(input: Double): Double {
        setInput(input)
        return performPID()
    }

    /**
     * Set the PID controller to consider the input to be continuous,
     * Rather then using the max and min in as constraints, it considers them to
     * be the same point and automatically calculates the shortest route to
     * the setpoint.
     * @param continuous Set to true turns on continuous, false turns off continuous
     */
    fun setContinuous(continuous: Boolean) {
        this.continuous = continuous
    }

    /**
     * Set the PID controller to consider the input to be continuous,
     * Rather then using the max and min in as constraints, it considers them to
     * be the same point and automatically calculates the shortest route to
     * the setpoint.
     */
    fun setContinuous() {
        this.setContinuous(true)
    }

    /**
     * Sets the maximum and minimum values expected from the input.
     *
     * @param minimumInput the minimum value expected from the input, always positive
     * @param maximumInput the maximum value expected from the output, always positive
     */
    fun setInputRange(minimumInput: Double, maximumInput: Double) {
        this.minimumInput = Math.abs(minimumInput)
        this.maximumInput = Math.abs(maximumInput)
        setpoint = setpoint
    }

    /**
     * Sets the minimum and maximum values to write.
     *
     * @param minimumOutput the minimum value to write to the output, always positive
     * @param maximumOutput the maximum value to write to the output, always positive
     */
    fun setOutputRange(minimumOutput: Double, maximumOutput: Double) {
        this.minimumOutput = abs(minimumOutput)
        this.maximumOutput = abs(maximumOutput)
    }

    /**
     * Set the percentage error which is considered tolerable for use with
     * OnTarget. (Input of 15.0 = 15 percent)
     * @param percent error which is tolerable
     */
    fun setTolerance(percent: Double) {
        tolerance = percent
    }

    /**
     * Return true if the error is within the percentage of the total input range,
     * determined by setTolerance. This assumes that the maximum and minimum input
     * were set using setInputRange.
     * @return true if the error is less than the tolerance
     */
    fun onTarget(): Boolean {
        return abs(error) < abs(tolerance / 100.0 * (maximumInput - minimumInput))
    }

    /**
     * Begin running the PIDController
     */
    fun enable() {
        enabled = true
    }

    /**
     * Stop running the PIDController.
     */
    fun disable() {
        enabled = false
    }

    /**
     * Reset the previous error,, the integral term, and disable the controller.
     */
    fun reset() {
        disable()
        prevError = 0.0
        totalError = 0.0
        result = 0.0
    }

    /**
     * Set the input value to be used by the next call to performPID().
     * @param input Input value to the PID calculation.
     */
    fun setInput(input: Double) {
        var sign = 1

        if (maximumInput > minimumInput) {
            if (input < 0) sign = -1

            when {
                abs(input) > maximumInput -> this.input = maximumInput * sign
                abs(input) < minimumInput -> this.input = minimumInput * sign
                else -> this.input = input
            }
        } else
            this.input = input
    }
}
