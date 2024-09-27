package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.GlobalsValues.MotorGlobalValues;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues.BasePIDGlobal;

/** The {@link SwerveModule} class includes all the motors to control the swerve drive. */
public class SwerveModule {
  /** Creates a new SwerveModule. */
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

  /**
   * Constructs a new SwerveModule.
   *
   * @param driveId The CAN ID of the drive motor.
   * @param steerId The CAN ID of the steer motor.
   * @param canCoderID The CAN ID of the CANcoder.
   * @param CANCoderDriveStraightSteerSetPoint The setpoint for the CANcoder when driving straight.
   */
  public SwerveModule(
      int driveId, int steerId, int canCoderID, double CANCoderDriveStraightSteerSetPoint) {
    driveMotor = new TalonFX(driveId);
    steerMotor = new TalonFX(steerId);
    canCoder = new CANcoder(canCoderID);

    positionSetter = new PositionVoltage(0, 0, true, 0.0, 0, true, false, false).withSlot(0);
    velocitySetter = new VelocityTorqueCurrentFOC(0);

    swerveModulePosition = new SwerveModulePosition();
    state = new SwerveModuleState(0, Rotation2d.fromDegrees(0));

    TalonFXConfiguration driveConfigs = new TalonFXConfiguration();
    TalonFXConfiguration steerConfigs = new TalonFXConfiguration();
    CANcoderConfiguration canCoderConfiguration = new CANcoderConfiguration();

    driveConfigs.Slot0.kP = BasePIDGlobal.DRIVE_PID.p;
    driveConfigs.Slot0.kI = BasePIDGlobal.DRIVE_PID.i;
    driveConfigs.Slot0.kD = BasePIDGlobal.DRIVE_PID.d;

    steerConfigs.Slot0.kP = BasePIDGlobal.STEER_PID.p;
    steerConfigs.Slot0.kI = BasePIDGlobal.STEER_PID.i;
    steerConfigs.Slot0.kD = BasePIDGlobal.STEER_PID.d;

    driveConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    steerConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    driveConfigs.MotorOutput.Inverted = SwerveGlobalValues.DRIVE_MOTOR_INVERETED;
    steerConfigs.MotorOutput.Inverted = SwerveGlobalValues.STEER_MOTOR_INVERTED;

    steerConfigs.Feedback.FeedbackRemoteSensorID = canCoderID;
    steerConfigs.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
    steerConfigs.Feedback.RotorToSensorRatio = MotorGlobalValues.STEER_MOTOR_GEAR_RATIO;
    steerConfigs.ClosedLoopGeneral.ContinuousWrap = true;

    canCoderConfiguration.MagnetSensor.AbsoluteSensorRange =
        AbsoluteSensorRangeValue.Signed_PlusMinusHalf;
    canCoderConfiguration.MagnetSensor.SensorDirection =
        SensorDirectionValue.CounterClockwise_Positive;
    canCoderConfiguration.MagnetSensor.MagnetOffset =
        SwerveGlobalValues.ENCODER_OFFSET + CANCoderDriveStraightSteerSetPoint;

    driveMotor.getConfigurator().apply(driveConfigs);
    steerMotor.getConfigurator().apply(steerConfigs);
    canCoder.getConfigurator().apply(canCoderConfiguration);

    driveVelocity = driveMotor.getVelocity().getValueAsDouble();
    drivePosition = driveMotor.getPosition().getValueAsDouble();
    steerVelocity = steerMotor.getVelocity().getValueAsDouble();
    steerPosition = steerMotor.getPosition().getValueAsDouble();

    steerMotor.setInverted(true);
  }

  /**
   * Gets the current position of the swerve module.
   *
   * @return The current position of the swerve module.
   */
  public SwerveModulePosition getPosition() {
    driveVelocity = driveMotor.getVelocity().getValueAsDouble();
    drivePosition = driveMotor.getPosition().getValueAsDouble();
    steerVelocity = steerMotor.getVelocity().getValueAsDouble();
    steerPosition = steerMotor.getPosition().getValueAsDouble();

    swerveModulePosition.angle = Rotation2d.fromRotations(steerPosition);
    swerveModulePosition.distanceMeters =
        drivePosition
            / (MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO / MotorGlobalValues.MetersPerRevolution);

    return swerveModulePosition;
  }

  /**
   * Sets the desired state of the swerve module.
   *
   * @param state The desired state of the swerve module.
   * @param motor The motor associated with the swerve module.
   */
  public void setState(SwerveModuleState state) { // SwerveSubsystem.Motor motor
    SwerveModulePosition newPosition = getPosition();
    // SmartDashboard.putNumber("desired state before optimize " + motor.name(), state.angle.getDegrees());
    // SmartDashboard.putNumber("voltage " + motor.name(), steerMotor.getMotorVoltage().getValueAsDouble());
    // SmartDashboard.putNumber("Applied " + motor.name(), steerMotor.getSupplyCurrent().getValueAsDouble());
    SwerveModuleState optimized = SwerveModuleState.optimize(state, newPosition.angle);


    double angleToSet = optimized.angle.getRotations();
    SmartDashboard.putNumber("desired state after optimize " + canCoder.getDeviceID(), optimized.angle.getRotations());
    // SmartDashboard.putNumber("current angle " + motor.name(), steerPosition);
    // SmartDashboard.putNumber("steer angle " + motor.name(), steerMotor.getPosition().getValueAsDouble());

    steerMotor.setControl(positionSetter.withPosition(angleToSet));

    double velocityToSet =
        optimized.speedMetersPerSecond
            * (MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO / MotorGlobalValues.MetersPerRevolution);
    driveMotor.setControl(velocitySetter.withVelocity(velocityToSet));

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
        driveMotor.getVelocity().getValueAsDouble()
            * (MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO / MotorGlobalValues.MetersPerRevolution);
    return state;
  }

}