package frc.robot.subsystems;

import java.util.Optional;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.DriverStation;
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
  private SwerveModule[] modules;

  private Photonvision photonvision;

  double turnSpeed;

  private double rot;
  private boolean shouldInvert = false;

  /** Creates a new DriveTrain. */
  public SwerveSubsystem(Photonvision photonvision) {
    frontLeft = new SwerveModule(MotorGlobalValues.FRONT_LEFT_DRIVE_ID, MotorGlobalValues.FRONT_LEFT_STEER_ID,
        MotorGlobalValues.FRONT_LEFT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue9);
    frontRight = new SwerveModule(MotorGlobalValues.FRONT_RIGHT_DRIVE_ID, MotorGlobalValues.FRONT_RIGHT_STEER_ID,
        MotorGlobalValues.FRONT_RIGHT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue10);
    backLeft = new SwerveModule(MotorGlobalValues.BACK_LEFT_DRIVE_ID, MotorGlobalValues.BACK_LEFT_STEER_ID,
        MotorGlobalValues.BACK_LEFT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue11);
    backRight = new SwerveModule(MotorGlobalValues.BACK_RIGHT_DRIVE_ID, MotorGlobalValues.BACK_RIGHT_STEER_ID,
        MotorGlobalValues.BACK_RIGHT_CAN_CODER_ID, SwerveGlobalValues.CANCoderValue12);

    // TODO: temporary addition
    modules = new SwerveModule[] {
        new SwerveModule(
            MotorGlobalValues.FRONT_LEFT_DRIVE_ID,
            MotorGlobalValues.FRONT_LEFT_STEER_ID,
            MotorGlobalValues.FRONT_LEFT_CAN_CODER_ID,
            SwerveGlobalValues.CANCoderValue9),
        new SwerveModule(
            MotorGlobalValues.FRONT_RIGHT_DRIVE_ID,
            MotorGlobalValues.FRONT_RIGHT_STEER_ID,
            MotorGlobalValues.FRONT_RIGHT_CAN_CODER_ID,
            SwerveGlobalValues.CANCoderValue10),
        new SwerveModule(
            MotorGlobalValues.BACK_LEFT_DRIVE_ID,
            MotorGlobalValues.BACK_LEFT_STEER_ID,
            MotorGlobalValues.BACK_LEFT_CAN_CODER_ID,
            SwerveGlobalValues.CANCoderValue11),
        new SwerveModule(
            MotorGlobalValues.BACK_RIGHT_DRIVE_ID,
            MotorGlobalValues.BACK_RIGHT_STEER_ID,
            MotorGlobalValues.BACK_RIGHT_CAN_CODER_ID,
            SwerveGlobalValues.CANCoderValue12)
    };

    field = new Field2d();

    pidgey = new Pigeon2(MotorGlobalValues.PIDGEY_ID);
    pidgey.reset();
    states = new SwerveModuleState[4];
    this.photonvision = photonvision;

    AutoBuilder.configureHolonomic(
        this::getPose, // Robot pose supplier
        this::newPose, // Method to reset odometry (will be called if your auto has a starting pose)
        this::getAutoSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
        this::chassisSpeedsDrive, // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds
        SwerveGlobalValues.BasePIDGlobal.pathFollower,
        () -> {
          Optional<DriverStation.Alliance> alliance = DriverStation.getAlliance();
          if (alliance.isPresent()) {
            if (shouldInvert) {
              return (alliance.get() == DriverStation.Alliance.Red);
            }

        else {
              return !(alliance.get() == DriverStation.Alliance.Blue);
            }

          }
          return false;
        },
        this // Reference to this subsystem to set requirements
    );
  }

  @Override
  public void periodic() {}

  /**
   * Sets the desired module states.
   * @param states SwerveModuleState[]
   * @return void
   */
  public void setModuleStates(SwerveModuleState[] states) {
    
    for (int i = 0; i < states.length; i++) {
      modules[i].setState(states[i]);
    }
  }

  /**
   * Gets the module states.
   * @param states SwerveModuleState[]
   * @return SwerveModuleState[]
   */
  public SwerveModuleState[] getModuleStates(SwerveModuleState[] states) {
    for (int i = 0; i < states.length; i++) {
      states[i] = modules[i].getState();
    }
    return states;
  }

  /**
   * Gets the module positions.
   * @param forwardSpeed double
   * @param leftSpeed double
   * @param joyStickInput double
   * @param isFieldOriented boolean
   * @return SwerveModulePosition[]
   */
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

  /**
   * Gets the pidgey rotation.
   * @return Rotation2d
   */
  public Rotation2d getPidgeyRotation() {
    return pidgey.getRotation2d();
  }

  /**
   * Gets the pidgey angle.
   * @return double
   */
  public double getHeading() {
    return pidgey.getAngle();
  }

  /**
   * Gets the pose.
   * @return Pose2d
   */
  public Pose2d getPose() {
    return poseEstimator.getEstimatedPosition();
  }

  /**
   * Zeros the pose.
   * @return void
   */
  public void zeroPose() {
    poseEstimator.resetPosition(Rotation2d.fromDegrees(getHeading()), getModulePositions(),
        new Pose2d(0, 0, Rotation2d.fromDegrees(0)));
  }

  /**
   * Resets the pose.
   * @param pose Pose2d
   * @return void
   */
  public void newPose(Pose2d pose) {
    poseEstimator.resetPosition(pidgey.getRotation2d(), getModulePositions(), pose);
  }

  /**
   * Sets the field.
   * @return void
   */
  public ChassisSpeeds getAutoSpeeds() {
    ChassisSpeeds speeds = SwerveGlobalValues.kinematics.toChassisSpeeds(getModuleStates());
    return speeds;
  }

  // TODO: Look at this code later
  /**
   * Gets the rotation pidggy.
   * @return Rotation2d
   */
  public Rotation2d getRotationPidggy() {
    rot = -pidgey.getRotation2d().getDegrees();
    return Rotation2d.fromDegrees(rot);
  }

  /**
   * Drives the robot using the chassis speeds.
   * @param chassisSpeeds ChassisSpeeds
   * @return void
   */
  public void chassisSpeedsDrive(ChassisSpeeds chassisSpeeds) {
    ChassisSpeeds speeds = ChassisSpeeds.fromRobotRelativeSpeeds(chassisSpeeds, getRotationPidggy());
    SwerveModuleState[] states = SwerveGlobalValues.kinematics.toSwerveModuleStates(speeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(
        states, MotorGlobalValues.MAX_SPEED);
    setModuleStates(states);
  }

  /**
   * Gets the field.
   * @return Field2d
   */
  private SwerveModuleState[] getModuleStates() {
    SwerveModuleState[] moduleStates = new SwerveModuleState[modules.length];
    for (int i = 0; i < modules.length; i++) {
      moduleStates[i] = modules[i].getState();
    }
    return moduleStates;
  }

  /**
   * Prints the module states
   */
  private void printModuleStates() {
    System.out.println("");
  }
  /**
   * Gets the module positions.
   * @return SwerveModulePosition[]
   */
  public SwerveModulePosition[] getModulePositions() {
    SwerveModulePosition[] positions = new SwerveModulePosition[states.length];

    positions[0] = frontLeft.getPosition();
    positions[1] = frontRight.getPosition();
    positions[2] = backLeft.getPosition();
    positions[3] = backRight.getPosition();

    return positions;
  }

}