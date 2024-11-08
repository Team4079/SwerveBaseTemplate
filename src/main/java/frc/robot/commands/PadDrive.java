package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.utils.GlobalsValues.MotorGlobalValues;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues;
import frc.robot.utils.LogitechGamingPad;

/** Command to control the robot's swerve drive using a Logitech gaming pad. */
public class PadDrive extends Command {

  private final SwerveSubsystem swerveSubsystem;
  private final boolean isFieldOriented;
  private final LogitechGamingPad pad;

  /**
   * Constructs a new PadDrive command.
   *
   * @param swerveSubsystem The swerve subsystem used by this command.
   * @param pad The Logitech gaming pad used to control the robot.
   * @param isFieldOriented Whether the drive is field-oriented.
   */
  public PadDrive(SwerveSubsystem swerveSubsystem, LogitechGamingPad pad, boolean isFieldOriented) {
    this.swerveSubsystem = swerveSubsystem;
    this.pad = pad;
    this.isFieldOriented = isFieldOriented;
    addRequirements(this.swerveSubsystem);
  }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() {
    Coordinate position = positionSet(pad);

    double rotation = -pad.getRightAnalogXAxis() * MotorGlobalValues.MAX_ANGULAR_SPEED;
    if (Math.abs(pad.getRightAnalogXAxis()) < 0.2) {
      rotation = 0;
    }

    SmartDashboard.putNumber("X Jostick", position.getX());
    SmartDashboard.putNumber("Y Joystick", position.getY());

    swerveSubsystem.setDriveSpeeds(
        position.getY(), position.getX(), rotation * 0.8, isFieldOriented);
  }

  /**
   * Sets the position based on the input from the Logitech gaming pad.
   *
   * @param pad The Logitech gaming pad.
   * @return The coordinate representing the position.
   */
  public static Coordinate positionSet(LogitechGamingPad pad) {
    double x = -pad.getLeftAnalogXAxis() * MotorGlobalValues.MAX_SPEED;
    if (Math.abs(x) < SwerveGlobalValues.xDEADZONE) {
      x = 0;
    }

    double y = -pad.getLeftAnalogYAxis() * MotorGlobalValues.MAX_SPEED;
    if (Math.abs(y) < SwerveGlobalValues.yDEADZONE) {
      y = 0;
    }

    return new Coordinate(x, y);
  }

  /**
   * Returns true when the command should end.
   *
   * @return Always returns false, as this command never ends on its own.
   */
  @Override
  public boolean isFinished() {
    return false;
  }

  /** Record representing a coordinate with x and y values. */
  public record Coordinate(double x, double y) {
    public double getX() {
      return x;
    }

    public double getY() {
      return y;
    }
  }
}
