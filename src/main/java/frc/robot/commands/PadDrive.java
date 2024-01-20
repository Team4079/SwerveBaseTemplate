// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.utils.Constants;
import frc.robot.utils.Constants.MotorConstants;
import frc.robot.utils.Constants.SwerveConstants;
import frc.robot.subsystems.LED;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.utils.LogitechGamingPad;

public class PadDrive extends Command {

  private final SwerveSubsystem swerveSubsystem;
  private final boolean isFieldOriented;
  private final LogitechGamingPad pad;
  private final Limelight limelety;
  private final LED led;

  /** Creates a new SwerveJoystick. */
  public PadDrive(SwerveSubsystem swerveSubsystem,
      LogitechGamingPad pad,
      boolean isFieldOriented,
      Limelight limelety,
      LED led) {
    this.swerveSubsystem = swerveSubsystem;
    this.pad = pad;
    this.isFieldOriented = isFieldOriented;
    this.limelety = limelety;
    this.led = led;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.swerveSubsystem, this.limelety, this.led);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("hey big boi ********************************************");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double y;
    double x;
    double rotation;

    if (MotorConstants.SLOW_MODE) {
      y = pad.getLeftAnalogXAxis() * MotorConstants.MAX_SPEED * MotorConstants.SLOW_SPEED;
      x = pad.getLeftAnalogYAxis() * -MotorConstants.MAX_SPEED * MotorConstants.SLOW_SPEED;
    }

    else if (MotorConstants.AACORN_MODE) {
      y = pad.getLeftAnalogXAxis() * MotorConstants.MAX_SPEED * MotorConstants.AACORN_SPEED;
      x = pad.getLeftAnalogYAxis() * -MotorConstants.MAX_SPEED * MotorConstants.AACORN_SPEED;
    }

    else {
      y = pad.getLeftAnalogXAxis() * MotorConstants.MAX_SPEED * 0.6;
      x = pad.getLeftAnalogYAxis() * -MotorConstants.MAX_SPEED * 0.6;
    }

    if (Math.abs(pad.getLeftAnalogXAxis()) < SwerveConstants.JOYSTICK_DEADBAND) {
      y = 0;
    }

    if (Math.abs(pad.getLeftAnalogYAxis()) < SwerveConstants.JOYSTICK_DEADBAND) {
      x = 0;
    }

    rotation = pad.getRightAnalogXAxis();

    double turn = 0;
    double heading_deadband = 0.2;
    double controller_deadband = 0.1;

    // If the right joystick is within the deadband, don't turn
    if (Math.abs(pad.getRightAnalogXAxis()) <= controller_deadband) {
      if (MotorConstants.HEADING > (swerveSubsystem.pgetHeading() + heading_deadband)) {
        turn = -MotorConstants.MAX_ANGULAR_SPEED;
      } else if (MotorConstants.HEADING < (swerveSubsystem.pgetHeading() - heading_deadband)) {
        turn = MotorConstants.MAX_ANGULAR_SPEED;
      } else {
        turn = 0;
      }
    } else {
      turn = pad.getRightAnalogXAxis() * MotorConstants.MAX_ANGULAR_SPEED;
      MotorConstants.HEADING = swerveSubsystem.pgetHeading();
    }

    if (Math.abs(rotation) < 0.05) {
      rotation = 0;
    }

    turn = rotation * MotorConstants.MAX_ANGULAR_SPEED * 2 * 1.5;

    if (MotorConstants.AACORN_MODE) {
      swerveSubsystem.drive(x * MotorConstants.AACORN_SPEED, y * MotorConstants.AACORN_SPEED, turn, isFieldOriented);
    }

    else {
      System.out.println(x + " " + y);
      swerveSubsystem.drive(x * MotorConstants.SPEED_CONSTANT, y * MotorConstants.SPEED_CONSTANT,
          turn, isFieldOriented);
    }

    // Vision LED
    if (limelety.isTarget()) {
      led.rainbowOn();
      swerveSubsystem.addVision(limelety.getRobotPosition());
    } else {
      led.rainbowOff();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
