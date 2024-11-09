package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TorqueCurrentConfigs;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.PID;
import frc.robot.utils.GlobalsValues.MotorGlobalValues;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues.BasePIDGlobal;

/** The {@link SwerveModule} class includes all the motors to control the swerve drive. */
public class SwerveModule {
  private final TalonFX driveMotor;

  private final CANcoder canCoder;
  private final TalonFX steerMotor;

  private final PositionVoltage positionSetter;
  private final VelocityTorqueCurrentFOC velocitySetter;

  private final SwerveModulePosition swerveModulePosition;
  private SwerveModuleState state;

  private double driveVelocity;
  private double drivePosition;
  private double steerPosition;
  private double steerVelocity;

  private TalonFXConfiguration driveConfigs;
  private TalonFXConfiguration steerConfigs;

  private TorqueCurrentConfigs driveTorqueConfigs;

  /**
   * Constructs a new SwerveModule.
   *
   * @param driveId The CAN ID of the drive motor.
   * @param steerId The CAN ID of the steer motor.
   * @param canCoderID The CAN ID of the CANcoder.
   * @param canCoderDriveStraightSteerSetPoint The setpoint for the CANcoder when driving straight.
   */
  public SwerveModule(
      int driveId, int steerId, int canCoderID, double canCoderDriveStraightSteerSetPoint) {
    driveMotor = new TalonFX(driveId);
    steerMotor = new TalonFX(steerId);
    canCoder = new CANcoder(canCoderID);

    positionSetter = new PositionVoltage(0).withSlot(0);
    velocitySetter = new VelocityTorqueCurrentFOC(0);

    positionSetter.EnableFOC = true;

    swerveModulePosition = new SwerveModulePosition();
    state = new SwerveModuleState(0, Rotation2d.fromDegrees(0));

    driveConfigs = new TalonFXConfiguration();
    steerConfigs = new TalonFXConfiguration();
    driveTorqueConfigs = new TorqueCurrentConfigs();
    CANcoderConfiguration canCoderConfiguration = new CANcoderConfiguration();

    driveConfigs.Slot0.kP = BasePIDGlobal.DRIVE_PID_AUTO.p;
    driveConfigs.Slot0.kI = BasePIDGlobal.DRIVE_PID_AUTO.i;
    driveConfigs.Slot0.kD = BasePIDGlobal.DRIVE_PID_AUTO.d;
    driveConfigs.Slot0.kV = BasePIDGlobal.DRIVE_PID_V_AUTO;

    steerConfigs.Slot0.kP = BasePIDGlobal.STEER_PID_AUTO.p;
    steerConfigs.Slot0.kI = BasePIDGlobal.STEER_PID_AUTO.i;
    steerConfigs.Slot0.kD = BasePIDGlobal.STEER_PID_AUTO.d;
    steerConfigs.Slot0.kV = 0;

    driveConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    steerConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    // driveConfigs.MotorOutput.DutyCycleNeutralDeadband = 0.02;
    // steerConfigs.MotorOutput.DutyCycleNeutralDeadband = 0.001;

    driveConfigs.MotorOutput.Inverted = SwerveGlobalValues.DRIVE_MOTOR_INVERETED;
    steerConfigs.MotorOutput.Inverted = SwerveGlobalValues.STEER_MOTOR_INVERTED;

    steerConfigs.Feedback.FeedbackRemoteSensorID = canCoderID;
    steerConfigs.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
    steerConfigs.Feedback.RotorToSensorRatio = MotorGlobalValues.STEER_MOTOR_GEAR_RATIO;
    steerConfigs.ClosedLoopGeneral.ContinuousWrap = true;

    driveConfigs.CurrentLimits.SupplyCurrentLimit = MotorGlobalValues.DRIVE_SUPPLY_LIMIT;
    driveConfigs.CurrentLimits.SupplyCurrentLimitEnable = true;

    driveConfigs.CurrentLimits.StatorCurrentLimit = MotorGlobalValues.DRIVE_STATOR_LIMIT;
    driveConfigs.CurrentLimits.StatorCurrentLimitEnable = true;

    steerConfigs.CurrentLimits.SupplyCurrentLimit = MotorGlobalValues.STEER_SUPPLY_LIMIT;
    steerConfigs.CurrentLimits.SupplyCurrentLimitEnable = true;

    // driveTorqueConfigs.PeakForwardTorqueCurrent = 45;
    // driveTorqueConfigs.PeakReverseTorqueCurrent = 45;
    // driveTorqueConfigs.TorqueNeutralDeadband = 0;

    canCoderConfiguration.MagnetSensor.AbsoluteSensorRange =
        AbsoluteSensorRangeValue.Signed_PlusMinusHalf;
    canCoderConfiguration.MagnetSensor.SensorDirection =
        SensorDirectionValue.CounterClockwise_Positive;
    canCoderConfiguration.MagnetSensor.MagnetOffset =
        SwerveGlobalValues.ENCODER_OFFSET + canCoderDriveStraightSteerSetPoint;

    driveMotor.getConfigurator().apply(driveConfigs);
    steerMotor.getConfigurator().apply(steerConfigs);
    canCoder.getConfigurator().apply(canCoderConfiguration);


    driveVelocity = driveMotor.getVelocity().getValueAsDouble();
    drivePosition = driveMotor.getPosition().getValueAsDouble();
    steerVelocity = steerMotor.getVelocity().getValueAsDouble();
    steerPosition = steerMotor.getPosition().getValueAsDouble();
  }

  /**
   * Gets the current position of the swerve module.
   *
   * @return The current position of the swerve module.
   */
  public SwerveModulePosition getPosition() {
    driveVelocity = driveMotor.getVelocity().getValueAsDouble();
    drivePosition = driveMotor.getRotorPosition().getValueAsDouble();
    steerVelocity = steerMotor.getVelocity().getValueAsDouble();
    steerPosition = steerMotor.getPosition().getValueAsDouble();

    // swerveModulePosition.angle = Rotation2d.fromRotations(steerPosition);

    swerveModulePosition.angle =
        Rotation2d.fromDegrees(
            ((360 * canCoder.getAbsolutePosition().getValueAsDouble()) % 360 + 360) % 360);
    swerveModulePosition.distanceMeters =
        drivePosition
            / MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO
            * MotorGlobalValues.MetersPerRevolution;
    // System.out.println(swerveModulePosition.distanceMeters);
    return swerveModulePosition;
  }

  /**
   * Sets the desired state of the swerve module.
   *
   * @param state The desired state of the swerve module.
   */
  public void setState(SwerveModuleState state) {
    SwerveModulePosition newPosition = getPosition();
    SwerveModuleState optimized = SwerveModuleState.optimize(state, newPosition.angle);

    double angleToSet = optimized.angle.getRotations();
    steerMotor.setControl(positionSetter.withPosition(angleToSet));

    double velocityToSet =
        optimized.speedMetersPerSecond
            * (MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO / MotorGlobalValues.MetersPerRevolution);
    driveMotor.setControl(velocitySetter.withVelocity(velocityToSet));

    if (BasePIDGlobal.TEST_MODE) {
      SmartDashboard.putNumber(
          "drive actual speed " + canCoder.getDeviceID(), driveMotor.getVelocity().getValueAsDouble());

      SmartDashboard.putNumber(
          "drive set speed " + canCoder.getDeviceID(), velocityToSet);

      SmartDashboard.putNumber(
          "steer actual angle " + canCoder.getDeviceID(), steerMotor.getRotorPosition().getValueAsDouble());

      SmartDashboard.putNumber(
          "steer set angle " + canCoder.getDeviceID(), angleToSet);

      SmartDashboard.putNumber(
          "desired state after optimize " + canCoder.getDeviceID(), optimized.angle.getRotations());
    }

    this.state = state;
  }

  /**
   * Gets the current state of the swerve module.
   *
   * @return The current state of the swerve module.
   */
  public SwerveModuleState getState() {
    state.angle = Rotation2d.fromRotations(steerMotor.getPosition().getValueAsDouble());
    state.speedMetersPerSecond =
        driveMotor.getRotorVelocity().getValueAsDouble()
            / MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO * MotorGlobalValues.MetersPerRevolution;
    return state;
  }

  public void stop() {
    steerMotor.stopMotor();
    driveMotor.stopMotor();
  }

  public void setTELEPID() {
    driveConfigs.Slot0.kP = BasePIDGlobal.DRIVE_PID_TELE.p;
    driveConfigs.Slot0.kI = BasePIDGlobal.DRIVE_PID_TELE.i;
    driveConfigs.Slot0.kD = BasePIDGlobal.DRIVE_PID_TELE.d;
    driveConfigs.Slot0.kV = BasePIDGlobal.DRIVE_PID_V_TELE;

    steerConfigs.Slot0.kP = BasePIDGlobal.STEER_PID_TELE.p;
    steerConfigs.Slot0.kI = BasePIDGlobal.STEER_PID_TELE.i;
    steerConfigs.Slot0.kD = BasePIDGlobal.STEER_PID_TELE.d;
    steerConfigs.Slot0.kV = 0;

    driveMotor.getConfigurator().apply(driveConfigs);
    steerMotor.getConfigurator().apply(steerConfigs);
  }

  public void setAUTOPID(PID pid, double velocity) {
    driveConfigs.Slot0.kP = pid.p;
    driveConfigs.Slot0.kI = pid.i;
    driveConfigs.Slot0.kD = pid.d;
    driveConfigs.Slot0.kV = velocity;

    driveMotor.getConfigurator().apply(driveConfigs);
  }

  public void setAUTOPID() {
    driveConfigs.Slot0.kP = SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.p;
    driveConfigs.Slot0.kI = SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.i;
    driveConfigs.Slot0.kD = SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.d;
    driveConfigs.Slot0.kV = SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_V_AUTO;

    driveMotor.getConfigurator().apply(driveConfigs);
  }

  public void resetDrivePosition() {
    driveMotor.setPosition(0);
  }

}
