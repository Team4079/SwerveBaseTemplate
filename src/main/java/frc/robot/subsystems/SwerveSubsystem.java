package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  private Pigeon2 pidgey;
  private SwerveModuleState[] states;

  private Photonvision photonvision;

  double turnSpeed;

  /** Creates a new DriveTrain. */
  public SwerveSubsystem(Photonvision photonvision) {
    frontLeft = new SwerveModule(MotorGlobalValues.FRONT_LEFT_DRIVE_ID, MotorGlobalValues.FRONT_LEFT_STEER_ID, MotorGlobalValues.FRONT_LEFT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue9);
    frontRight = new SwerveModule(MotorGlobalValues.FRONT_RIGHT_DRIVE_ID, MotorGlobalValues.FRONT_RIGHT_STEER_ID, MotorGlobalValues.FRONT_RIGHT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue10);
    backLeft = new SwerveModule(MotorGlobalValues.BACK_LEFT_DRIVE_ID, MotorGlobalValues.BACK_LEFT_STEER_ID, MotorGlobalValues.BACK_LEFT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue11);
    backRight = new SwerveModule(MotorGlobalValues.BACK_RIGHT_DRIVE_ID, MotorGlobalValues.BACK_RIGHT_STEER_ID, MotorGlobalValues.BACK_RIGHT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue12);
    

    field = new Field2d();

    pidgey = new Pigeon2(MotorGlobalValues.PIDGEY_ID);
    pidgey.reset();
    states = new SwerveModuleState[4];

    this.photonvision = photonvision;
  }


  @Override
  public void periodic() {
    
  }

  public enum Motor {
    FRONT_LEFT,
    FRONT_RIGHT,
    BACK_LEFT,
    BACK_RIGHT
  }

  public void setModuleStates(SwerveModuleState[] states) {
    frontLeft.setState(states[0], Motor.FRONT_LEFT);
    frontRight.setState(states[1], Motor.FRONT_RIGHT);
    backLeft.setState(states[2], Motor.BACK_LEFT);
    backRight.setState(states[3], Motor.BACK_RIGHT);
  }

  public SwerveModuleState[] getModuleStates(SwerveModuleState[] states) {
    states[0] = frontLeft.getState();
    states[1] = frontRight.getState();
    states[2] = backLeft.getState();
    states[3] = backRight.getState();
    return states;
  }

  public void getDriveSpeeds(double forwardSpeed, double leftSpeed, double joyStickInput, boolean isFieldOriented) {
    ChassisSpeeds speeds;

    SmartDashboard.putNumber("Forward speed", forwardSpeed);
    SmartDashboard.putNumber("Left speed", leftSpeed);

    turnSpeed = joyStickInput * MotorGlobalValues.TURN_CONSTANT;

    if (isFieldOriented) {
      speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
          forwardSpeed,
          leftSpeed,
          turnSpeed,
          getPidgeyRotation());
    } else {
      speeds = new ChassisSpeeds(
          forwardSpeed,
          leftSpeed,
          joyStickInput);
    }

    SwerveModuleState[] states = SwerveGlobalValues.kinematics.toSwerveModuleStates(speeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(
        states, MotorGlobalValues.MAX_SPEED);
    setModuleStates(states);
  }

  public Rotation2d getPidgeyRotation() {
    return pidgey.getRotation2d();
  }

  public double getHeading() {
    return pidgey.getAngle();
  }
}