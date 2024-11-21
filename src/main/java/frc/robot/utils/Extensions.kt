package frc.robot.utils

import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.geometry.Translation3d
import edu.wpi.first.util.sendable.Sendable
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

/**
 * Extension function to convert a 2D translation to a 3D translation.
 *
 * @param z The z-coordinate for the 3D translation.
 * @return [Translation3d] The resulting 3D translation.
 * @receiver [Translation2d] The 2D translation to convert.
 */
fun Translation2d.to3D(z: Double): Translation3d = Translation3d(x, y, z)

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
