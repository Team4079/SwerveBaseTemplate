package frc.robot.utils

import edu.wpi.first.util.sendable.Sendable
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

/**
 * Function to update values from the [SmartDashboard].
 *
 * @param pairs The pairs of keys and values to update.
 */
fun dash(vararg pairs: Pair<String, Any>) {
  if (RobotParameters.SwerveParameters.Thresholds.TEST_MODE) {
    pairs.forEach { (key, value) ->
      when (value) {
        is Number -> SmartDashboard.putNumber(key, value.toDouble())
        is Boolean -> SmartDashboard.putBoolean(key, value)
        is String -> SmartDashboard.putString(key, value)
        is Sendable -> SmartDashboard.putData(key, value)
        else -> {
          print("Jayden had a skill issue like always")
          throw IllegalArgumentException("Unsupported type: ${value::class.java}")
        }
      }
    }
  }
}

/**
 * Function to update PIDV values from the [SmartDashboard].
 *
 * @param pid The PID object to update.
 * @param velocity The velocity to update.
 * @param prefix The prefix for the [SmartDashboard] keys.
 */
fun dashPID(prefix: String, pid: PID, velocity: Double, changeV: (Double) -> Unit) {
  pid.p = SmartDashboard.getNumber("$prefix Auto P", pid.p)
  pid.i = SmartDashboard.getNumber("$prefix Auto I", pid.i)
  pid.d = SmartDashboard.getNumber("$prefix Auto D", pid.d)
  changeV(SmartDashboard.getNumber("$prefix Auto V", velocity))
}
