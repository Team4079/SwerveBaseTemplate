package frc.robot.subsystems;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.GlobalsValues.MotorGlobalValues;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues;

// import edu.wpi.first.math.controller.PIDController;
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * The {@link SwerveSubsystem} class includes all the motors to drive the robot.
 */
public class SwerveSubsystem extends SubsystemBase {
  private SwerveModule frontLeft;
  private SwerveModule frontRight;
  private SwerveModule backLeft;
  private SwerveModule backRight;

  private SwerveDrivePoseEstimator poseEstimator;
  private Field2d field;

  /** Creates a new DriveTrain. */
  public SwerveSubsystem(Photonvision photonvision) {
    frontLeft = new SwerveModule(MotorGlobalValues.FRONT_LEFT_DRIVE_ID, MotorGlobalValues.FRONT_LEFT_STEER_ID, MotorGlobalValues.FRONT_LEFT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue9);
    frontRight = new SwerveModule(MotorGlobalValues.FRONT_RIGHT_DRIVE_ID, MotorGlobalValues.FRONT_RIGHT_STEER_ID, MotorGlobalValues.FRONT_RIGHT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue10);
    backLeft = new SwerveModule(MotorGlobalValues.BACK_LEFT_DRIVE_ID, MotorGlobalValues.BACK_LEFT_STEER_ID, MotorGlobalValues.BACK_LEFT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue11);
    backRight = new SwerveModule(MotorGlobalValues.BACK_RIGHT_DRIVE_ID, MotorGlobalValues.BACK_RIGHT_STEER_ID, MotorGlobalValues.BACK_RIGHT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue12);
    
    field = new Field2d();
    
  }


  @Override
  public void periodic() {
    
  }

}