package frc.robot.subsystems

import com.ctre.phoenix6.configs.CANcoderConfiguration
import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.configs.TorqueCurrentConfigs
import com.ctre.phoenix6.controls.PositionVoltage
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC
import com.ctre.phoenix6.hardware.CANcoder
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue
import com.ctre.phoenix6.signals.NeutralModeValue
import com.ctre.phoenix6.signals.SensorDirectionValue
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.kinematics.SwerveModulePosition
import edu.wpi.first.math.kinematics.SwerveModuleState
import frc.robot.utils.PID
import frc.robot.utils.RobotParameters.MotorParameters
import frc.robot.utils.RobotParameters.SwerveParameters
import frc.robot.utils.RobotParameters.SwerveParameters.PIDParameters
import frc.robot.utils.dash

/** The [SwerveModule] class includes all the motors to control the swerve drive. */
class SwerveModule(
  driveId: Int,
  steerId: Int,
  canCoderID: Int,
  canCoderDriveStraightSteerSetPoint: Double,
) {
  private val driveMotor = TalonFX(driveId)

  private val canCoder = CANcoder(canCoderID)
  private val steerMotor = TalonFX(steerId)

  private val positionSetter: PositionVoltage = PositionVoltage(0.0).withSlot(0)
  private val velocitySetter = VelocityTorqueCurrentFOC(0.0)

  private val swerveModulePosition = SwerveModulePosition()
  var state = SwerveModuleState(0.0, Rotation2d.fromDegrees(0.0))
    get() {
      state.angle = Rotation2d.fromRotations(steerMotor.position.valueAsDouble)
      state.speedMetersPerSecond =
        (driveMotor.rotorVelocity.valueAsDouble / MotorParameters.DRIVE_MOTOR_GEAR_RATIO *
          MotorParameters.METERS_PER_REV)
      return field
    }
    set(value) {
      val newPosition = position
      state.optimize(newPosition.angle)

      val angleToSet = state.angle.rotations
      steerMotor.setControl(positionSetter.withPosition(angleToSet))

      val velocityToSet =
        (state.speedMetersPerSecond *
          (MotorParameters.DRIVE_MOTOR_GEAR_RATIO / MotorParameters.METERS_PER_REV))
      driveMotor.setControl(velocitySetter.withVelocity(velocityToSet))

      dash(
        "drive actual speed " + canCoder.deviceID to driveMotor.velocity.valueAsDouble,
        "drive set speed " + canCoder.deviceID to velocityToSet,
        "steer actual angle " + canCoder.deviceID to steerMotor.rotorPosition.valueAsDouble,
        "steer set angle " + canCoder.deviceID to angleToSet,
        "desired state after optimize " + canCoder.deviceID to state.angle.rotations,
      )

      field = value
    }

  private var driveVelocity: Double
  private var drivePosition: Double
  private var steerPosition: Double
  private var steerVelocity: Double

  private val driveConfigs: TalonFXConfiguration
  private val steerConfigs: TalonFXConfiguration

  private val driveTorqueConfigs: TorqueCurrentConfigs

  init {
    positionSetter.EnableFOC = true

    driveConfigs = TalonFXConfiguration()
    steerConfigs = TalonFXConfiguration()
    driveTorqueConfigs = TorqueCurrentConfigs()
    val canCoderConfiguration = CANcoderConfiguration()

    driveConfigs.Slot0.kP = PIDParameters.DRIVE_PID_AUTO.p
    driveConfigs.Slot0.kI = PIDParameters.DRIVE_PID_AUTO.i
    driveConfigs.Slot0.kD = PIDParameters.DRIVE_PID_AUTO.d
    driveConfigs.Slot0.kV = PIDParameters.DRIVE_PID_V_AUTO

    steerConfigs.Slot0.kP = PIDParameters.STEER_PID_AUTO.p
    steerConfigs.Slot0.kI = PIDParameters.STEER_PID_AUTO.i
    steerConfigs.Slot0.kD = PIDParameters.STEER_PID_AUTO.d
    steerConfigs.Slot0.kV = 0.0

    driveConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake
    steerConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake

    // driveConfigs.MotorOutput.DutyCycleNeutralDeadband = 0.02;
    // steerConfigs.MotorOutput.DutyCycleNeutralDeadband = 0.001;
    driveConfigs.MotorOutput.Inverted = SwerveParameters.Thresholds.DRIVE_MOTOR_INVERETED
    steerConfigs.MotorOutput.Inverted = SwerveParameters.Thresholds.STEER_MOTOR_INVERTED

    steerConfigs.Feedback.FeedbackRemoteSensorID = canCoderID
    steerConfigs.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder
    steerConfigs.Feedback.RotorToSensorRatio = MotorParameters.STEER_MOTOR_GEAR_RATIO
    steerConfigs.ClosedLoopGeneral.ContinuousWrap = true

    driveConfigs.CurrentLimits.SupplyCurrentLimit = MotorParameters.DRIVE_SUPPLY_LIMIT
    driveConfigs.CurrentLimits.SupplyCurrentLimitEnable = true

    driveConfigs.CurrentLimits.StatorCurrentLimit = MotorParameters.DRIVE_STATOR_LIMIT
    driveConfigs.CurrentLimits.StatorCurrentLimitEnable = true

    steerConfigs.CurrentLimits.SupplyCurrentLimit = MotorParameters.STEER_SUPPLY_LIMIT
    steerConfigs.CurrentLimits.SupplyCurrentLimitEnable = true

    // driveTorqueConfigs.PeakForwardTorqueCurrent = 45;
    // driveTorqueConfigs.PeakReverseTorqueCurrent = 45;
    // driveTorqueConfigs.TorqueNeutralDeadband = 0;
    canCoderConfiguration.MagnetSensor.AbsoluteSensorRange =
      AbsoluteSensorRangeValue.Signed_PlusMinusHalf
    canCoderConfiguration.MagnetSensor.SensorDirection =
      SensorDirectionValue.CounterClockwise_Positive
    canCoderConfiguration.MagnetSensor.MagnetOffset =
      SwerveParameters.Thresholds.ENCODER_OFFSET + canCoderDriveStraightSteerSetPoint

    driveMotor.configurator.apply(driveConfigs)
    steerMotor.configurator.apply(steerConfigs)
    canCoder.configurator.apply(canCoderConfiguration)

    driveVelocity = driveMotor.velocity.valueAsDouble
    drivePosition = driveMotor.position.valueAsDouble
    steerVelocity = steerMotor.velocity.valueAsDouble
    steerPosition = steerMotor.position.valueAsDouble
  }

  val position: SwerveModulePosition
    /**
     * Gets the current position of the swerve module.
     *
     * @return The current position of the swerve module.
     */
    get() {
      driveVelocity = driveMotor.velocity.valueAsDouble
      drivePosition = driveMotor.rotorPosition.valueAsDouble
      steerVelocity = steerMotor.velocity.valueAsDouble
      steerPosition = steerMotor.position.valueAsDouble

      // swerveModulePosition.angle = Rotation2d.fromRotations(steerPosition);
      swerveModulePosition.angle =
        Rotation2d.fromDegrees(((360 * canCoder.absolutePosition.valueAsDouble) % 360 + 360) % 360)
      swerveModulePosition.distanceMeters =
        (drivePosition / MotorParameters.DRIVE_MOTOR_GEAR_RATIO * MotorParameters.METERS_PER_REV)
      return swerveModulePosition
    }

  fun stop() {
    steerMotor.stopMotor()
    driveMotor.stopMotor()
  }

  fun setTELEPID() {
    driveConfigs.Slot0.kP = PIDParameters.DRIVE_PID_TELE.p
    driveConfigs.Slot0.kI = PIDParameters.DRIVE_PID_TELE.i
    driveConfigs.Slot0.kD = PIDParameters.DRIVE_PID_TELE.d
    driveConfigs.Slot0.kV = PIDParameters.DRIVE_PID_V_TELE

    steerConfigs.Slot0.kP = PIDParameters.STEER_PID_TELE.p
    steerConfigs.Slot0.kI = PIDParameters.STEER_PID_TELE.i
    steerConfigs.Slot0.kD = PIDParameters.STEER_PID_TELE.d
    steerConfigs.Slot0.kV = 0.0

    driveMotor.configurator.apply(driveConfigs)
    steerMotor.configurator.apply(steerConfigs)
  }

  fun setAUTOPID(pid: PID, velocity: Double) {
    driveConfigs.Slot0.kP = pid.p
    driveConfigs.Slot0.kI = pid.i
    driveConfigs.Slot0.kD = pid.d
    driveConfigs.Slot0.kV = velocity

    driveMotor.configurator.apply(driveConfigs)
  }

  fun setAUTOPID() {
    driveConfigs.Slot0.kP = PIDParameters.DRIVE_PID_AUTO.p
    driveConfigs.Slot0.kI = PIDParameters.DRIVE_PID_AUTO.i
    driveConfigs.Slot0.kD = PIDParameters.DRIVE_PID_AUTO.d
    driveConfigs.Slot0.kV = PIDParameters.DRIVE_PID_V_AUTO

    driveMotor.configurator.apply(driveConfigs)
  }

  fun resetDrivePosition() {
    driveMotor.setPosition(0.0)
  }
}
