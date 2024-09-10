// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.concurrent.CancellationException;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.kinematics.SwerveModulePosition;

/**
 * The {@link SwerveModule} class includes all the motors to control the swerve drive.
 */
public class SwerveModule {
  /** Creates a new SwerveModule. */
  private TalonFX driveMotor;
  private TalonFX steerMotor;
  private CANcoder canCoder;

  private PositionVoltage positionSetter;
  private VelocityTorqueCurrentFOC velocitySetter;

  private SwerveModulePosition swerveModulePosition;
  private SwerveModule state;

  /** Creates a new SwerveModule. */
  public SwerveModule(int driveId, int steerId, int canCoderID, double CANCoderDriveStraightSteerSetPoint) {
    driveMotor = new TalonFX(driveId);
    steerMotor = new TalonFX(steerId);
    canCoder = new CANcoder(canCoderID);
    
    positionSetter = new PositionVoltage(0, 0, true, 0, 0, true, false, false);
    velocitySetter = new VelocityTorqueCurrentFOC(0);

    swerveModulePosition = new SwerveModulePosition();

    TalonFXConfiguration talonConfigs = new TalonFXConfiguration();

    Slot0Configs driveSlot0Configs = new Slot0Configs();
    Slot0Configs turnSlot0Configs = new Slot0Configs();

    

  }

}