package frc.robot.utils

class PID {
  var p: Double
  var i: Double
  var d: Double
  var f: Double = 0.0
  var s: Int = 0

  constructor(p: Double, i: Double, d: Double) {
    this.p = p
    this.i = i
    this.d = d
  }

  constructor(p: Double, i: Double, d: Double, f: Double, s: Int) {
    this.p = p
    this.i = i
    this.d = d
    this.f = f
    this.s = s
  }

  constructor(p: Double, i: Double, d: Double, f: Double) {
    this.p = p
    this.i = i
    this.d = d
    this.f = f
    this.s = -1
  }

  fun updatePID(p: Double, i: Double, d: Double, f: Double, s: Int) {
    this.p = p
    this.i = i
    this.d = d
    this.f = f
    this.s = s
  }

  fun updatePID(p: Double, i: Double, d: Double, f: Double) {
    this.p = p
    this.i = i
    this.d = d
    this.f = f
    this.s = -1
  }

  fun updatePID(p: Double, i: Double, d: Double) {
    this.p = p
    this.i = i
    this.d = d
    this.f = 0.0
    this.s = -1
  }

  var previous_error: Double = 0.0
  var integral: Double = 0.0
  var setpoint: Double = 0.0
  var error: Double = 0.0
  var output: Double = 0.0
    private set

  fun calculate(actual: Double, setpoint: Double): Double {
    error = setpoint - actual // Error = Target - Actual
    this.integral +=
      (error * .02) // Integral is increased by the error*time (which is .02 seconds using normal
    val derivative = (error - this.previous_error) / .02
    this.output = p * error + i * this.integral + d * derivative
    this.previous_error = error
    return output
  }

  fun resetI() {
    this.integral = 0.0
  }
}
