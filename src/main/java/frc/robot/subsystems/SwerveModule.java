// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.concurrent.CancellationException;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.GlobalsValues.MotorGlobalValues;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues.BasePIDGlobal;

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
  private SwerveModuleState state;

  private double driveVelocity;
  private double drivePosition;
  private double steerPosition;
  private double steerVelocity;

  /** Creates a new SwerveModule. */
  public SwerveModule(int driveId, int steerId, int canCoderID, double CANCoderDriveStraightSteerSetPoint) {
    driveMotor = new TalonFX(driveId);
    steerMotor = new TalonFX(steerId);
    canCoder = new CANcoder(canCoderID);

    Slot0Configs driveSlot0Configs = new Slot0Configs();
    Slot0Configs turnSlot0Configs = new Slot0Configs();

    driveSlot0Configs.kP = BasePIDGlobal.DRIVE_PID.p;
    driveSlot0Configs.kI = BasePIDGlobal.DRIVE_PID.i;
    driveSlot0Configs.kD = BasePIDGlobal.DRIVE_PID.d;
    
    turnSlot0Configs.kP = BasePIDGlobal.STEER_PID.p;
    turnSlot0Configs.kI = BasePIDGlobal.STEER_PID.i;
    turnSlot0Configs.kD = BasePIDGlobal.STEER_PID.d;
    
    positionSetter = new PositionVoltage(0, 0, true, 0.0, 0, true, false, false).withSlot(0);
    velocitySetter = new VelocityTorqueCurrentFOC(0);

    swerveModulePosition = new SwerveModulePosition();
    state = new SwerveModuleState(
      0, Rotation2d.fromDegrees(0));

    TalonFXConfiguration driveConfigs = new TalonFXConfiguration();
    TalonFXConfiguration steerConfigs = new TalonFXConfiguration();
    CANcoderConfiguration canCoderConfiguration = new CANcoderConfiguration();



    // turnSlot0Configs.

    driveConfigs.Slot0 = driveSlot0Configs;
    steerConfigs.Slot0 = turnSlot0Configs;

    driveConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    steerConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    driveConfigs.MotorOutput.Inverted = SwerveGlobalValues.DRIVE_MOTOR_INVERETED;
    steerConfigs.MotorOutput.Inverted = SwerveGlobalValues.STEER_MOTOR_INVERTED;

    //TDO Add more configs since I likely forgot some

    steerConfigs.Feedback.FeedbackRemoteSensorID = canCoderID;
    steerConfigs.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
    steerConfigs.Feedback.RotorToSensorRatio = MotorGlobalValues.STEER_MOTOR_GEAR_RATIO;
    steerConfigs.ClosedLoopGeneral.ContinuousWrap = true;

    canCoderConfiguration.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Signed_PlusMinusHalf;
    canCoderConfiguration.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
    canCoderConfiguration.MagnetSensor.MagnetOffset = SwerveGlobalValues.ENCODER_OFFSET + CANCoderDriveStraightSteerSetPoint;

    driveMotor.getConfigurator().apply(driveConfigs);
    steerMotor.getConfigurator().apply(steerConfigs);
    canCoder.getConfigurator().apply(canCoderConfiguration);

    driveVelocity = driveMotor.getVelocity().getValueAsDouble();
    drivePosition = driveMotor.getPosition().getValueAsDouble();
    steerVelocity = steerMotor.getVelocity().getValueAsDouble();
    steerPosition = steerMotor.getPosition().getValueAsDouble();

  }

  public SwerveModulePosition getPosition() {
    driveVelocity = driveMotor.getVelocity().getValueAsDouble();
    drivePosition = driveMotor.getPosition().getValueAsDouble();
    steerVelocity = steerMotor.getVelocity().getValueAsDouble();
    steerPosition = steerMotor.getPosition().getValueAsDouble();    

    swerveModulePosition.angle = Rotation2d.fromRotations(steerPosition);
    swerveModulePosition.distanceMeters = drivePosition / (MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO / MotorGlobalValues.MetersPerRevolution);

    return swerveModulePosition;
  }

  public void setState(SwerveModuleState state, int i) {
    SwerveModulePosition newPosition = getPosition();
    SmartDashboard.putNumber("desired state before optimize " + i, state.angle.getDegrees());
    SmartDashboard.putNumber("voltage " + i, steerMotor.getMotorVoltage().getValueAsDouble());
    SmartDashboard.putNumber("Applived " + i, steerMotor.getSupplyCurrent().getValueAsDouble());
    var optimized = SwerveModuleState.optimize(state, newPosition.angle);

    double angleToSet = optimized.angle.getRotations();
    SmartDashboard.putNumber("desired state after optimize " + i, optimized.angle.getRotations());
    SmartDashboard.putNumber("current angle" + i, steerPosition);
    SmartDashboard.putNumber("steer angle " + i, steerMotor.getPosition().getValueAsDouble());

    steerMotor.setControl(positionSetter.withPosition(angleToSet));

    double velocityToSet = optimized.speedMetersPerSecond * (MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO / MotorGlobalValues.MetersPerRevolution);
    driveMotor.setControl(velocitySetter.withVelocity(velocityToSet));

    this.state = state;
  }
  
  public SwerveModuleState getState() {
    state.angle = Rotation2d.fromRotations(steerMotor.getPosition().getValueAsDouble());
    state.speedMetersPerSecond = driveMotor.getVelocity().getValueAsDouble() * (MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO / MotorGlobalValues.MetersPerRevolution);
    return state;
  }

}