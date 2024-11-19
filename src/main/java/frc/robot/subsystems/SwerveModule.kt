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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.utils.GlobalsValues.MotorGlobalValues
import frc.robot.utils.GlobalsValues.SwerveGlobalValues
import frc.robot.utils.GlobalsValues.SwerveGlobalValues.BasePIDGlobal
import frc.robot.utils.PID

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

  private val swerveModulePosition: SwerveModulePosition = TODO()
  var state: SwerveModuleState = TODO()
    get() {
      state.angle = Rotation2d.fromRotations(steerMotor.position.valueAsDouble)
      state.speedMetersPerSecond =
        (driveMotor.rotorVelocity.valueAsDouble / MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO *
          MotorGlobalValues.MetersPerRevolution)
      return field
    }
    set(value) {
      val newPosition = position
      val optimized = SwerveModuleState.optimize(state, newPosition.angle)

      val angleToSet = optimized.angle.rotations
      steerMotor.setControl(positionSetter.withPosition(angleToSet))

      val velocityToSet =
        (optimized.speedMetersPerSecond *
          (MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO / MotorGlobalValues.MetersPerRevolution))
      driveMotor.setControl(velocitySetter.withVelocity(velocityToSet))

      if (BasePIDGlobal.TEST_MODE) {
        SmartDashboard.putNumber(
          "drive actual speed " + canCoder.deviceID,
          driveMotor.velocity.valueAsDouble,
        )

        SmartDashboard.putNumber("drive set speed " + canCoder.deviceID, velocityToSet)

        SmartDashboard.putNumber(
          "steer actual angle " + canCoder.deviceID,
          steerMotor.rotorPosition.valueAsDouble,
        )

        SmartDashboard.putNumber("steer set angle " + canCoder.deviceID, angleToSet)

        SmartDashboard.putNumber(
          "desired state after optimize " + canCoder.deviceID,
          optimized.angle.rotations,
        )
      }

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

    swerveModulePosition = SwerveModulePosition()
    state = SwerveModuleState(0.0, Rotation2d.fromDegrees(0.0))

    driveConfigs = TalonFXConfiguration()
    steerConfigs = TalonFXConfiguration()
    driveTorqueConfigs = TorqueCurrentConfigs()
    val canCoderConfiguration = CANcoderConfiguration()

    driveConfigs.Slot0.kP = BasePIDGlobal.DRIVE_PID_AUTO.p
    driveConfigs.Slot0.kI = BasePIDGlobal.DRIVE_PID_AUTO.i
    driveConfigs.Slot0.kD = BasePIDGlobal.DRIVE_PID_AUTO.d
    driveConfigs.Slot0.kV = BasePIDGlobal.DRIVE_PID_V_AUTO

    steerConfigs.Slot0.kP = BasePIDGlobal.STEER_PID_AUTO.p
    steerConfigs.Slot0.kI = BasePIDGlobal.STEER_PID_AUTO.i
    steerConfigs.Slot0.kD = BasePIDGlobal.STEER_PID_AUTO.d
    steerConfigs.Slot0.kV = 0.0

    driveConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake
    steerConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake

    // driveConfigs.MotorOutput.DutyCycleNeutralDeadband = 0.02;
    // steerConfigs.MotorOutput.DutyCycleNeutralDeadband = 0.001;
    driveConfigs.MotorOutput.Inverted = SwerveGlobalValues.DRIVE_MOTOR_INVERETED
    steerConfigs.MotorOutput.Inverted = SwerveGlobalValues.STEER_MOTOR_INVERTED

    steerConfigs.Feedback.FeedbackRemoteSensorID = canCoderID
    steerConfigs.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder
    steerConfigs.Feedback.RotorToSensorRatio = MotorGlobalValues.STEER_MOTOR_GEAR_RATIO
    steerConfigs.ClosedLoopGeneral.ContinuousWrap = true

    driveConfigs.CurrentLimits.SupplyCurrentLimit = MotorGlobalValues.DRIVE_SUPPLY_LIMIT
    driveConfigs.CurrentLimits.SupplyCurrentLimitEnable = true

    driveConfigs.CurrentLimits.StatorCurrentLimit = MotorGlobalValues.DRIVE_STATOR_LIMIT
    driveConfigs.CurrentLimits.StatorCurrentLimitEnable = true

    steerConfigs.CurrentLimits.SupplyCurrentLimit = MotorGlobalValues.STEER_SUPPLY_LIMIT
    steerConfigs.CurrentLimits.SupplyCurrentLimitEnable = true

    // driveTorqueConfigs.PeakForwardTorqueCurrent = 45;
    // driveTorqueConfigs.PeakReverseTorqueCurrent = 45;
    // driveTorqueConfigs.TorqueNeutralDeadband = 0;
    canCoderConfiguration.MagnetSensor.AbsoluteSensorRange =
      AbsoluteSensorRangeValue.Signed_PlusMinusHalf
    canCoderConfiguration.MagnetSensor.SensorDirection =
      SensorDirectionValue.CounterClockwise_Positive
    canCoderConfiguration.MagnetSensor.MagnetOffset =
      SwerveGlobalValues.ENCODER_OFFSET + canCoderDriveStraightSteerSetPoint

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
        (drivePosition / MotorGlobalValues.DRIVE_MOTOR_GEAR_RATIO *
          MotorGlobalValues.MetersPerRevolution)
      return swerveModulePosition
    }

  fun stop() {
    steerMotor.stopMotor()
    driveMotor.stopMotor()
  }

  fun setTELEPID() {
    driveConfigs.Slot0.kP = BasePIDGlobal.DRIVE_PID_TELE.p
    driveConfigs.Slot0.kI = BasePIDGlobal.DRIVE_PID_TELE.i
    driveConfigs.Slot0.kD = BasePIDGlobal.DRIVE_PID_TELE.d
    driveConfigs.Slot0.kV = BasePIDGlobal.DRIVE_PID_V_TELE

    steerConfigs.Slot0.kP = BasePIDGlobal.STEER_PID_TELE.p
    steerConfigs.Slot0.kI = BasePIDGlobal.STEER_PID_TELE.i
    steerConfigs.Slot0.kD = BasePIDGlobal.STEER_PID_TELE.d
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
    driveConfigs.Slot0.kP = BasePIDGlobal.DRIVE_PID_AUTO.p
    driveConfigs.Slot0.kI = BasePIDGlobal.DRIVE_PID_AUTO.i
    driveConfigs.Slot0.kD = BasePIDGlobal.DRIVE_PID_AUTO.d
    driveConfigs.Slot0.kV = BasePIDGlobal.DRIVE_PID_V_AUTO

    driveMotor.configurator.apply(driveConfigs)
  }

  fun resetDrivePosition() {
    driveMotor.setPosition(0.0)
  }
}
