package frc.robot.commands

import edu.wpi.first.wpilibj2.command.Command
import frc.robot.subsystems.SwerveSubsystem
import frc.robot.utils.LogitechGamingPad
import frc.robot.utils.RobotParameters.MotorParameters
import frc.robot.utils.RobotParameters.SwerveParameters.Thresholds.X_DEADZONE
import frc.robot.utils.RobotParameters.SwerveParameters.Thresholds.Y_DEADZONE
import frc.robot.utils.dash
import kotlin.math.abs

/** Command to control the robot's swerve drive using a Logitech gaming pad. */
class PadDrive(
  private val swerveSubsystem: SwerveSubsystem,
  private val pad: LogitechGamingPad,
  private val isFieldOriented: Boolean,
) : Command() {
  init {
    addRequirements(this.swerveSubsystem)
  }

  /**
   * Called every time the scheduler runs while the command is scheduled. This method retrieves the
   * current position from the gaming pad, calculates the rotation, logs the joystick values, and
   * sets the drive speeds for the swerve subsystem.
   */
  override fun execute() {
    val position = positionSet(pad)

    var rotation = -pad.rightAnalogXAxis * MotorParameters.MAX_ANGULAR_SPEED
    if (abs(pad.rightAnalogXAxis) < 0.2) {
      rotation = 0.0
    }

    dash("X Joystick" to position.x, "Y Joystick" to position.y, "Rotation" to rotation)

    swerveSubsystem.setDriveSpeeds(position.y, position.x, rotation * 0.8, isFieldOriented)
  }

  /**
   * Returns true when the command should end.
   *
   * @return Always returns false, as this command never ends on its own.
   */
  override fun isFinished(): Boolean {
    return false
  }

  /** Record representing a coordinate with x and y values. */
  @JvmRecord data class Coordinate(val x: Double, val y: Double)

  companion object {
    /**
     * Sets the position based on the input from the Logitech gaming pad.
     *
     * @param pad The Logitech gaming pad.
     * @return The coordinate representing the position.
     */
    fun positionSet(pad: LogitechGamingPad): Coordinate {
      var x = -pad.leftAnalogXAxis * MotorParameters.MAX_SPEED
      if (abs(x) < X_DEADZONE) {
        x = 0.0
      }

      var y = -pad.leftAnalogYAxis * MotorParameters.MAX_SPEED
      if (abs(y) < Y_DEADZONE) {
        y = 0.0
      }

      return Coordinate(x, y)
    }
  }
}
