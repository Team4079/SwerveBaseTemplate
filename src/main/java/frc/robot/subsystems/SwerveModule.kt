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

    driveConfigs =
      TalonFXConfiguration().apply {
        Slot0.apply {
          kP = PIDParameters.DRIVE_PID_AUTO.p
          kI = PIDParameters.DRIVE_PID_AUTO.i
          kD = PIDParameters.DRIVE_PID_AUTO.d
          kV = PIDParameters.DRIVE_PID_V_AUTO
        }
        MotorOutput.apply {
          NeutralMode = NeutralModeValue.Brake
          // DutyCycleNeutralDeadband = 0.02;
          Inverted = SwerveParameters.Thresholds.DRIVE_MOTOR_INVERETED
        }
        CurrentLimits.apply {
          SupplyCurrentLimit = MotorParameters.DRIVE_SUPPLY_LIMIT
          SupplyCurrentLimitEnable = true
          StatorCurrentLimit = MotorParameters.DRIVE_STATOR_LIMIT
          StatorCurrentLimitEnable = true
        }
      }
    steerConfigs =
      TalonFXConfiguration().apply {
        Slot0.apply {
          kP = PIDParameters.STEER_PID_AUTO.p
          kI = PIDParameters.STEER_PID_AUTO.i
          kD = PIDParameters.STEER_PID_AUTO.d
          kV = 0.0
        }
        MotorOutput.apply {
          NeutralMode = NeutralModeValue.Brake
          // DutyCycleNeutralDeadband = 0.001;
          Inverted = SwerveParameters.Thresholds.STEER_MOTOR_INVERTED
        }
        Feedback.apply {
          FeedbackRemoteSensorID = canCoderID
          FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder
          RotorToSensorRatio = MotorParameters.STEER_MOTOR_GEAR_RATIO
        }
        CurrentLimits.apply {
          SupplyCurrentLimit = MotorParameters.STEER_SUPPLY_LIMIT
          SupplyCurrentLimitEnable = true
        }
      }

    driveTorqueConfigs = TorqueCurrentConfigs()
    // driveTorqueConfigs.PeakForwardTorqueCurrent = 45;
    // driveTorqueConfigs.PeakReverseTorqueCurrent = 45;
    // driveTorqueConfigs.TorqueNeutralDeadband = 0;

    val canCoderConfiguration =
      CANcoderConfiguration().apply {
        MagnetSensor.apply {
          AbsoluteSensorRange = AbsoluteSensorRangeValue.Signed_PlusMinusHalf
          SensorDirection = SensorDirectionValue.CounterClockwise_Positive
          MagnetOffset =
            SwerveParameters.Thresholds.ENCODER_OFFSET + canCoderDriveStraightSteerSetPoint
        }
      }

    driveMotor.configurator.apply(driveConfigs)
    steerMotor.configurator.apply(steerConfigs)
    canCoder.configurator.apply(canCoderConfiguration)

    driveMotor.apply {
      driveVelocity = velocity.valueAsDouble
      drivePosition = position.valueAsDouble
    }
    steerMotor.apply {
      steerVelocity = velocity.valueAsDouble
      steerPosition = position.valueAsDouble
    }
  }

  val position: SwerveModulePosition
    /**
     * Gets the current position of the swerve module.
     *
     * @return The current position of the swerve module.
     */
    get() {
      driveMotor.apply {
        driveVelocity = velocity.valueAsDouble
        drivePosition = position.valueAsDouble
      }
      steerMotor.apply {
        steerVelocity = velocity.valueAsDouble
        steerPosition = position.valueAsDouble
      }

      return swerveModulePosition.apply {
        angle =
          Rotation2d.fromDegrees(
            ((360 * canCoder.absolutePosition.valueAsDouble) % 360 + 360) % 360
          )
        distanceMeters =
          (drivePosition / MotorParameters.DRIVE_MOTOR_GEAR_RATIO * MotorParameters.METERS_PER_REV)
      }
    }

  fun stop() {
    steerMotor.stopMotor()
    driveMotor.stopMotor()
  }

  fun setDrivePID(pid: PID, velocity: Double) {
    driveConfigs.Slot0.apply {
      kP = pid.p
      kI = pid.i
      kD = pid.d
      kV = velocity
    }

    driveMotor.configurator.apply(driveConfigs)
  }

  fun setSteerPID(pid: PID, velocity: Double) {
    steerConfigs.Slot0.apply {
      kP = pid.p
      kI = pid.i
      kD = pid.d
      kV = velocity
    }

    steerMotor.configurator.apply(steerConfigs)
  }

  fun setTELEPID() {
    setDrivePID(PIDParameters.DRIVE_PID_TELE, PIDParameters.DRIVE_PID_V_TELE)
    setSteerPID(PIDParameters.STEER_PID_TELE, 0.0)
  }

  fun setAUTOPID() {
    setDrivePID(PIDParameters.DRIVE_PID_AUTO, PIDParameters.DRIVE_PID_V_AUTO)
  }

  fun resetDrivePosition() {
    driveMotor.setPosition(0.0)
  }
}
