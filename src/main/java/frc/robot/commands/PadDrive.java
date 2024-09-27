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
  public PadDrive(SwerveSubsystem swerveSubsystem, LogitechGamingPad pad, boolean isFieldOriented) {
    this.swerveSubsystem = swerveSubsystem;
    this.pad = pad;
    this.isFieldOriented = isFieldOriented;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.swerveSubsystem); // this.limelety, this.led
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double y = -pad.getLeftAnalogYAxis() * MotorGlobalValues.MAX_SPEED;
    double x = -pad.getLeftAnalogXAxis() * MotorGlobalValues.MAX_SPEED;
    double rotation = pad.getRightAnalogXAxis() * MotorGlobalValues.MAX_ANGULAR_SPEED;

    SmartDashboard.putNumber("Y Joystick", y);
    SmartDashboard.putNumber("X Jostick", x);

    swerveSubsystem.getDriveSpeeds(y, x, rotation, isFieldOriented);
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