package frc.robot.commands

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.Command
import frc.robot.subsystems.SwerveSubsystem
import frc.robot.utils.GlobalsValues.MotorGlobalValues
import frc.robot.utils.GlobalsValues.SwerveGlobalValues
import frc.robot.utils.LogitechGamingPad
import kotlin.math.abs

/** Command to control the robot's swerve drive using a Logitech gaming pad. */
class PadDrive(
  private val swerveSubsystem: SwerveSubsystem,
  private val pad: LogitechGamingPad,
  private val isFieldOriented: Boolean,
) : Command() {
  /**
   * Constructs a new PadDrive command.
   *
   * @param swerveSubsystem The swerve subsystem used by this command.
   * @param pad The Logitech gaming pad used to control the robot.
   * @param isFieldOriented Whether the drive is field-oriented.
   */
  init {
    addRequirements(this.swerveSubsystem)
  }

  /** Called every time the scheduler runs while the command is scheduled. */
  override fun execute() {
    val position = positionSet(pad)

    var rotation = -pad.rightAnalogXAxis * MotorGlobalValues.MAX_ANGULAR_SPEED
    if (abs(pad.rightAnalogXAxis) < 0.2) {
      rotation = 0.0
    }

    SmartDashboard.putNumber("X Jostick", position.x)
    SmartDashboard.putNumber("Y Joystick", position.y)

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
      var x = -pad.leftAnalogXAxis * MotorGlobalValues.MAX_SPEED
      if (abs(x) < SwerveGlobalValues.xDEADZONE) {
        x = 0.0
      }

      var y = -pad.leftAnalogYAxis * MotorGlobalValues.MAX_SPEED
      if (abs(y) < SwerveGlobalValues.yDEADZONE) {
        y = 0.0
      }

      return Coordinate(x, y)
    }
  }
}
