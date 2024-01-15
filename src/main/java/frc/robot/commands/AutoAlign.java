// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LED;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.utils.PID;

@SuppressWarnings("unused")
public class AutoAlign extends Command {
  /** Creates a new AutoAlign. */
  private Limelight limelight;
  private SwerveSubsystem swerveSubsystem;
  private LED led;
  private PID horizontalPID;
  private double horizontalError;

  private PID verticalPID;
  private double verticalError;

  private PID rotationalPID;

  public AutoAlign(SwerveSubsystem swerveSubsystem, Limelight limelight, LED led) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.swerveSubsystem = swerveSubsystem;
    this.limelight = limelight;
    this.led = led;
    horizontalPID = new PID(0.1, 0, 0);
    verticalPID = new PID(0.1, 0.0, 0.0);
    rotationalPID = new PID(0.1, 0, 0);
    addRequirements(swerveSubsystem, limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (limelight.isTarget()) {
      led.setRainbow();
    }
    else 
    {
      led.setColor(0, 182, 174);
    }
    horizontalError = -limelight.getTx();
    verticalError = -limelight.getTy();
    swerveSubsystem.drive(verticalPID.calculate(verticalError, 0), horizontalPID.calculate(horizontalError, 0), 0, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}