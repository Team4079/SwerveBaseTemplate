// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.ClosedLoopRampsConfigs;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.kinematics.ChassisSpeeds;
// import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.Constants;
// import frc.robot.utils.PID;
import frc.robot.utils.Constants.MotorConstants;
import frc.robot.utils.Constants.SwerveConstants;

@SuppressWarnings("unused") // Used in order to remove warnings
public class SwerveModule {
  public TalonFX driveMotor;
  public TalonFX steerMotor;
  public CANcoder canCoder;

  private MotorOutputConfigs motorConfigs;

  private TalonFXConfigurator driveConfigurator;
  private TalonFXConfigurator steerConfigurator;

  private Slot0Configs driveslot0Configs;
  private Slot0Configs steerslot0Configs;

  private DutyCycleOut m_request;

  private PositionVoltage m_position;
  private PositionDutyCycle m_cycle;
  private double initialCANCoderValue;
  private double initialPos;

  private final double CANCoderDriveStraightSteerSetPoint;

  private CurrentLimitsConfigs driveCurrentLimitsConfigs;
  private CurrentLimitsConfigs steerCurrentLimitsConfigs;

  private ClosedLoopRampsConfigs driveClosedRampsConfigs;
  private ClosedLoopRampsConfigs steerClosedRampsConfigs;

  // static int printSlower = 0;

  /** Creates a new SwerveModule. */
  public SwerveModule(int driveId, int steerId, int canCoderID, double CANCoderDriveStraightSteerSetPoint) {
    driveMotor = new TalonFX(driveId);
    steerMotor = new TalonFX(steerId);
    canCoder = new CANcoder(canCoderID);

    this.CANCoderDriveStraightSteerSetPoint = CANCoderDriveStraightSteerSetPoint;

    motorConfigs = new MotorOutputConfigs();

    driveConfigurator = driveMotor.getConfigurator();
    steerConfigurator = steerMotor.getConfigurator();

    driveslot0Configs = new Slot0Configs();
    steerslot0Configs = new Slot0Configs();

    m_request = new DutyCycleOut(0);
    m_position = new PositionVoltage(0);
    m_cycle = new PositionDutyCycle(0);

    driveMotor.getConfigurator().apply(new TalonFXConfiguration());
    steerMotor.getConfigurator().apply(new TalonFXConfiguration());

    motorConfigs.NeutralMode = NeutralModeValue.Brake;
    driveConfigurator.apply(motorConfigs);
    steerConfigurator.apply(motorConfigs);

    driveslot0Configs.kP = SwerveConstants.PIDConstants.DRIVE_PID.p;
    driveslot0Configs.kI = SwerveConstants.PIDConstants.DRIVE_PID.i;
    driveslot0Configs.kD = SwerveConstants.PIDConstants.DRIVE_PID.d;

    steerslot0Configs.kP = SwerveConstants.PIDConstants.STEER_PID.p; // original 0.06
    steerslot0Configs.kI = SwerveConstants.PIDConstants.STEER_PID.i;
    steerslot0Configs.kD = SwerveConstants.PIDConstants.STEER_PID.d; // Original 0.008

    driveMotor.getConfigurator().apply(driveslot0Configs);
    steerMotor.getConfigurator().apply(steerslot0Configs);

    steerConfigurator.setPosition(CANCoderDriveStraightSteerSetPoint);

    driveCurrentLimitsConfigs = new CurrentLimitsConfigs();
    steerCurrentLimitsConfigs = new CurrentLimitsConfigs();
    driveClosedRampsConfigs = new ClosedLoopRampsConfigs();
    steerClosedRampsConfigs = new ClosedLoopRampsConfigs();

    // currentLimitsConfigs.SupplyCurrentLimit = 40;
    // currentLimitsConfigs.StatorCurrentLimit = 40;
    // currentLimitsConfigs.Enable = true;

    driveClosedRampsConfigs.DutyCycleClosedLoopRampPeriod = 0.1;
    steerClosedRampsConfigs.DutyCycleClosedLoopRampPeriod = 0.05;

    driveMotor.getConfigurator().apply(driveCurrentLimitsConfigs);
    steerMotor.getConfigurator().apply(steerCurrentLimitsConfigs);

  }

  public SwerveModulePosition getPosition() {
    return new SwerveModulePosition(
        encoderToMeters(
            driveMotor.getRotorPosition().getValue(), MotorConstants.DRIVE_MOTOR_GEAR_RATIO),
        Rotation2d.fromDegrees(
            encoderToAngle(steerMotor.getRotorPosition().getValue(),
                MotorConstants.STEER_MOTOR_GEAR_RATIO)));
  }

  public void setDriveSpeed(double speed) {
    driveMotor.setControl(m_request.withOutput(speed));
  }

  public void setSteerSpeed(double speed) {
    steerMotor.setControl(m_request.withOutput(speed));
  }

  public void setSteerPosition(double rotations) {
    steerMotor.setControl(m_cycle.withPosition(rotations));
  }

  public void resetEncoders() {
    driveMotor.setPosition(0);
  }

  /** Converts encoder counts to degrees. */
  public double encoderToAngle(double encoderCount, double gearRatio) {
    return encoderCount * 360 /
        MotorConstants.ENCODER_COUNTS_PER_ROTATION * gearRatio;
  }

  public double rotationsToAngle(double rotations, double gearRatio) {
    return rotations * 360 / gearRatio;
  }

  public double angleToRotations(double angle, double gearRatio) {
    return angle / 360 * gearRatio;
  }

  /** Converts degrees to encoder counts. */
  public double angleToEncoder(double angle, double gearRatio) {
    return angle * MotorConstants.ENCODER_COUNTS_PER_ROTATION / 360 /
        gearRatio;
  }

  public double encoderToMeters(double encoderCount, double gearRatio) {
    return encoderCount / (MotorConstants.ENCODER_COUNTS_PER_ROTATION *
        gearRatio) * MotorConstants.WHEEL_DIAMETER * Math.PI;
  }

  public double metersToEncoder(double meters, double gearRatio) {
    return meters / (MotorConstants.WHEEL_DIAMETER * Math.PI) *
        MotorConstants.ENCODER_COUNTS_PER_ROTATION * gearRatio;
  }

  public void setState(SwerveModuleState state) {
    state = optimizeJ(state,
        Rotation2d.fromDegrees(
            rotationsToAngle(steerMotor.getRotorPosition().getValue(), MotorConstants.STEER_MOTOR_GEAR_RATIO)),
        steerMotor.getDeviceID());

    double currentRotations = (steerMotor.getRotorPosition().getValue());
    Rotation2d currentAngle = Rotation2d
        .fromDegrees(rotationsToAngle(currentRotations, MotorConstants.STEER_MOTOR_GEAR_RATIO));

    setDriveSpeed(state.speedMetersPerSecond / MotorConstants.MAX_SPEED);

    if (Math.abs(state.speedMetersPerSecond) > SwerveConstants.STATE_SPEED_THRESHOLD) {
      double newRotations;
      Rotation2d delta = state.angle.minus(currentAngle);

      double change = delta.getDegrees();

      if (change > 90) {
        change -= 180;
      } else if (change < -90) {
        change += 180;
      }

      newRotations = currentRotations + angleToRotations(change, MotorConstants.STEER_MOTOR_GEAR_RATIO);
      SmartDashboard.putNumber("Set Rotations " + steerMotor.getDeviceID(), newRotations);
      SmartDashboard.putNumber("Actual Rotations " + steerMotor.getDeviceID(),
          steerMotor.getRotorPosition().getValue());
      SmartDashboard.putNumber("Jayden Sun" + steerMotor.getDeviceID(),
          newRotations - steerMotor.getRotorPosition().getValue());
      setSteerPosition(newRotations);

    }
  }

  public void stop() {
    setDriveSpeed(0);
    setSteerSpeed(0);
  }

  public void setRotorPos() {
    initialCANCoderValue = canCoder.getAbsolutePosition().refresh().getValue() % 360;
    steerMotor.setPosition(
        -(initialCANCoderValue - CANCoderDriveStraightSteerSetPoint) * MotorConstants.STEER_MOTOR_GEAR_RATIO);
  }

  // public static SwerveModuleState optimize(SwerveModuleState desiredState,
  // Rotation2d currentAngle) {
  // double targetAngle = placeInAppropriate0To360Scope(currentAngle.getDegrees(),
  // desiredState.angle.getDegrees());
  // double targetSpeed = desiredState.speedMetersPerSecond;
  // double delta = targetAngle - currentAngle.getDegrees();
  // if (Math.abs(delta) > 90){
  // targetSpeed = -targetSpeed;
  // targetAngle = delta > 90 ? (targetAngle -= 180) : (targetAngle += 180);
  // }
  // return new SwerveModuleState(targetSpeed,
  // Rotation2d.fromDegrees(targetAngle));
  // }

  // private static double placeInAppropriate0To360Scope(double scopeReference,
  // double newAngle) {
  // double lowerBound;
  // double upperBound;
  // double lowerOffset = scopeReference % 360;
  // if (lowerOffset >= 0) {
  // lowerBound = scopeReference - lowerOffset;
  // upperBound = scopeReference + (360 - lowerOffset);
  // } else {
  // upperBound = scopeReference - lowerOffset;
  // lowerBound = scopeReference - (360 + lowerOffset);
  // }
  // while (newAngle < lowerBound) {
  // newAngle += 360;
  // }
  // while (newAngle > upperBound) {
  // newAngle -= 360;
  // }
  // if (newAngle - scopeReference > 180) {
  // newAngle -= 360;
  // } else if (newAngle - scopeReference < -180) {
  // newAngle += 360;
  // }
  // return newAngle;
  // }
  // public static SwerveModuleState optimize(
  // SwerveModuleState desiredState, Rotation2d currentAngle) {

  // var currentDegrees = currentAngle.getDegrees();
  // var desiredDegrees = desiredState.angle.getDegrees();

  // while (currentDegrees - desiredDegrees > 180.0){
  // desiredDegrees = desiredDegrees + 360;
  // }

  // while (currentDegrees - desiredDegrees < -180.0){
  // desiredDegrees = desiredDegrees - 360;
  // }

  // var delta = desiredState.angle.minus(currentAngle);

  // if (delta.getDegrees() > 90.0) {

  // return new SwerveModuleState(

  // -desiredState.speedMetersPerSecond,
  // Rotation2d.fromDegrees(desiredDegrees - 180));

  // }

  // else if (delta.getDegrees() < - 90) {
  // return new SwerveModuleState(

  // -desiredState.speedMetersPerSecond,
  // Rotation2d.fromDegrees(desiredDegrees + 180));

  // } else {
  // return new SwerveModuleState(desiredState.speedMetersPerSecond,
  // Rotation2d.fromDegrees(desiredDegrees));
  // }
  // }

  public static SwerveModuleState optimize(SwerveModuleState desiredState, Rotation2d currentAngle, int deviceID) {
    double targetAngle = placeInAppropriate0To360Scope(
        currentAngle.getDegrees(), desiredState.angle.getDegrees());
    double targetSpeed = desiredState.speedMetersPerSecond;
    double delta = targetAngle - currentAngle.getDegrees();

    if (Math.abs(delta) > 90) {
      targetSpeed = -targetSpeed;
      targetAngle = delta > 90 ? (targetAngle -= 180) : (targetAngle += 180);
    }
    SmartDashboard.putNumber(deviceID + " Motor", currentAngle.getDegrees() - targetAngle);
    return new SwerveModuleState(targetSpeed, Rotation2d.fromDegrees(targetAngle));
  }

  public static SwerveModuleState optimizeJ(SwerveModuleState desiredState, Rotation2d currentAngle, int deviceID) {
    SwerveModuleState swerveState = SwerveModuleState.optimize(desiredState, currentAngle);
    return swerveState;
  }

  private static double placeInAppropriate0To360Scope(double scopeReference, double newAngle) {
    double lowerBound;
    double upperBound;
    double lowerOffset = scopeReference % 360;
    if (lowerOffset >= 0) {
      lowerBound = scopeReference - lowerOffset;
      upperBound = scopeReference + (360 - lowerOffset);
    } else {
      upperBound = scopeReference - lowerOffset;
      lowerBound = scopeReference - (360 + lowerOffset);
    }
    while (newAngle < lowerBound) {
      newAngle += 360;
    }
    while (newAngle > upperBound) {
      newAngle -= 360;
    }
    if (newAngle - scopeReference > 180) {
      newAngle -= 360;
    } else if (newAngle - scopeReference < -180) {
      newAngle += 360;
    }
    return newAngle;
  }

  public double getCanCoderValue() {
    return canCoder.getAbsolutePosition().getValue();
  }

  public double getRotationDegree() {
    return rotationsToAngle(steerMotor.getRotorPosition().getValue(), MotorConstants.STEER_MOTOR_GEAR_RATIO);
  }
}
