// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.utils.GlobalsValues.MotorGlobalValues;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.utils.LogitechGamingPad;

public class PadDrive extends Command {

  private final SwerveSubsystem swerveSubsystem;
  private final boolean isFieldOriented;
  private final LogitechGamingPad pad;

  /** Creates a new SwerveJoystick. */
  public PadDrive(SwerveSubsystem swerveSubsystem,
      LogitechGamingPad pad,
      boolean isFieldOriented //,
      // Limelight limelety,
      // LED led
      ) {
    this.swerveSubsystem = swerveSubsystem;
    this.pad = pad;
    this.isFieldOriented = isFieldOriented;
    // this.limelety = limelety;
    // this.led = led;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.swerveSubsystem); // this.limelety, this.led
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

    SmartDashboard.putNumber("Y Joystick", pad.getLeftAnalogXAxis());
    SmartDashboard.putNumber("X Dashboard", pad.getLeftAnalogYAxis());

    if (MotorGlobalValues.SLOW_MODE) {
      y = pad.getLeftAnalogXAxis() * MotorGlobalValues.MAX_SPEED * MotorGlobalValues.SLOW_SPEED;
      x = pad.getLeftAnalogYAxis() * -MotorGlobalValues.MAX_SPEED * MotorGlobalValues.SLOW_SPEED;
    }

    else if (MotorGlobalValues.AACORN_MODE) {
      y = pad.getLeftAnalogXAxis() * MotorGlobalValues.MAX_SPEED * MotorGlobalValues.AACORN_SPEED;
      x = pad.getLeftAnalogYAxis() * -MotorGlobalValues.MAX_SPEED * MotorGlobalValues.AACORN_SPEED;
    }

    else {
      y = pad.getLeftAnalogXAxis() * MotorGlobalValues.MAX_SPEED * 0.6;
      x = pad.getLeftAnalogYAxis() * -MotorGlobalValues.MAX_SPEED * 0.6;
    }

    if (Math.abs(pad.getLeftAnalogXAxis()) < SwerveGlobalValues.JOYSTICK_DEADBAND) {
      y = 0;
    }

    if (Math.abs(pad.getLeftAnalogYAxis()) < SwerveGlobalValues.JOYSTICK_DEADBAND) {
      x = 0;
    }

    rotation = pad.getRightAnalogXAxis();

    double turn = 0;
    double heading_deadband = 0.2;
    double controller_deadband = 0.1;

    // If the right joystick is within the deadband, don't turn
    if (Math.abs(pad.getRightAnalogXAxis()) <= controller_deadband) {
      if (MotorGlobalValues.HEADING > (swerveSubsystem.getHeading() + heading_deadband)) {
        turn = -MotorGlobalValues.MAX_ANGULAR_SPEED;
      } else if (MotorGlobalValues.HEADING < (swerveSubsystem.getHeading() - heading_deadband)) {
        turn = MotorGlobalValues.MAX_ANGULAR_SPEED;
      } else {
        turn = 0;
      }
    } else {
      turn = pad.getRightAnalogXAxis() * MotorGlobalValues.MAX_ANGULAR_SPEED;
      MotorGlobalValues.HEADING = swerveSubsystem.getHeading();
    }

    if (Math.abs(rotation) < 0.05) {
      rotation = 0;
    }

    turn = rotation * MotorGlobalValues.MAX_ANGULAR_SPEED * 2 * 1.5;

    if (MotorGlobalValues.AACORN_MODE) {
      swerveSubsystem.getDriveSpeeds(x * MotorGlobalValues.AACORN_SPEED, y * MotorGlobalValues.AACORN_SPEED, turn, isFieldOriented);
    }

    else {
      System.out.println(x + " " + y);
      swerveSubsystem.getDriveSpeeds(x * MotorGlobalValues.SPEED_CONSTANT, y * MotorGlobalValues.SPEED_CONSTANT,
          turn, isFieldOriented);
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