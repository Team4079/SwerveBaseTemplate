@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package frc.robot.utils

import com.ctre.phoenix6.signals.InvertedValue
import com.pathplanner.lib.config.PIDConstants
import com.pathplanner.lib.config.RobotConfig
import com.pathplanner.lib.controllers.PPHolonomicDriveController
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.kinematics.SwerveDriveKinematics

/** Object containing global values for the robot. */
object RobotParameters {
  /** Object containing global values related to motors. */
  object MotorParameters {
    // Motor CAN ID Values
    const val FRONT_LEFT_STEER_ID: Int = 1
    const val FRONT_LEFT_DRIVE_ID: Int = 2
    const val FRONT_RIGHT_STEER_ID: Int = 3
    const val FRONT_RIGHT_DRIVE_ID: Int = 4
    const val BACK_LEFT_STEER_ID: Int = 5
    const val BACK_LEFT_DRIVE_ID: Int = 6
    const val BACK_RIGHT_STEER_ID: Int = 7
    const val BACK_RIGHT_DRIVE_ID: Int = 8
    const val FRONT_LEFT_CAN_CODER_ID: Int = 9
    const val FRONT_RIGHT_CAN_CODER_ID: Int = 10
    const val BACK_LEFT_CAN_CODER_ID: Int = 11
    const val BACK_RIGHT_CAN_CODER_ID: Int = 12
    const val PIDGEY_ID: Int = 16

    // Motor Property Values
    const val MAX_SPEED: Double = 5.76
    const val MAX_ANGULAR_SPEED: Double = (14 * Math.PI) / 3
    const val ENCODER_COUNTS_PER_ROTATION: Double = 1.0
    const val STEER_MOTOR_GEAR_RATIO: Double = 150.0 / 7
    const val DRIVE_MOTOR_GEAR_RATIO: Double = 5.9
    const val WHEEL_DIAMETER: Double = 0.106
    const val SPEED_CONSTANT: Double = 0.6
    const val AACORN_SPEED: Double = 0.95
    const val SLOW_SPEED: Double = 0.3
    const val TURN_CONSTANT: Double = 0.3
    const val METERS_PER_REV: Double = WHEEL_DIAMETER * Math.PI * 0.99
    var HEADING: Double = 0.0

    // Limit Values
    const val DRIVE_SUPPLY_LIMIT: Double = 45.0
    const val DRIVE_STATOR_LIMIT: Double = 80.0
    const val DRIVE_SUPPLY_THRESHOLD: Double = 30.0
    const val DRIVE_TIME_THRESHOLD: Double = 0.25
    const val STEER_SUPPLY_LIMIT: Double = 30.0
    const val STEER_SUPPLY_THRESHOLD: Double = 30.0
    const val STEER_TIME_THRESHOLD: Double = 0.25

    // Motor Speed Manipulation Values
    var SLOW_MODE: Boolean = false
    var AACORN_MODE: Boolean = true
  }

  /** Object containing global values related to the swerve drive system. */
  object SwerveParameters {
    /** Object containing PID constants for the swerve drive system. */
    object PIDParameters {
      val STEER_PID_TELE: PID = PID(13.0, 0.000, 0.1, 0.0)
      val STEER_PID_AUTO: PID = PID(15.0, 0.000, 0.1, 0.0)
      val DRIVE_PID_AUTO: PID = PID(7.0, 0.0, 0.00)
      const val DRIVE_PID_V_AUTO: Double = 0.5
      val DRIVE_PID_TELE: PID = PID(1.5, 0.0, 0.0)
      const val DRIVE_PID_V_TELE: Double = 0.0
      val ROTATIONAL_PID: PID = PID(0.2, 0.0, 0.0, 0.0)
      val PASS_ROTATIONAL_PID: PID = PID(0.1, 0.0, 0.0, 0.0)
      var pathFollower: PPHolonomicDriveController =
        PPHolonomicDriveController(
          PIDConstants(5.0, 0.00, 0.0), // translation
          PIDConstants(5.0, 0.0, 0.0), // rotation
        )
      var config: RobotConfig = RobotConfig.fromGUISettings()
    }

    /** Object containing physical dimensions and kinematics for the swerve drive system. */
    object PhysicalParameters {
      const val ROBOT_SIZE: Double = 0.43105229381
      val FRONT_LEFT: Translation2d = Translation2d(0.3048, 0.3048)
      val FRONT_RIGHT: Translation2d = Translation2d(0.3048, -0.3048)
      val BACK_LEFT: Translation2d = Translation2d(-0.3048, 0.3048)
      val BACK_RIGHT: Translation2d = Translation2d(-0.3048, -0.3048)
      val kinematics: SwerveDriveKinematics =
        SwerveDriveKinematics(FRONT_LEFT, FRONT_RIGHT, BACK_LEFT, BACK_RIGHT)
      const val BASE_LENGTH_ERICK_TRAN: Double = 0.3048 * 2
    }

    /** Object containing various thresholds and constants for the swerve drive system. */
    object Thresholds {
      const val STATE_SPEED_THRESHOLD: Double = 0.05
      const val CANCODER_VAL9: Double = -0.419189
      const val CANCODER_VAL10: Double = -0.825928 - 0.5
      const val CANCODER_VAL11: Double = -0.475098
      const val CANCODER_VAL12: Double = -0.032959 + 0.5
      val DRIVE_MOTOR_INVERETED: InvertedValue = InvertedValue.CounterClockwise_Positive
      val STEER_MOTOR_INVERTED: InvertedValue = InvertedValue.Clockwise_Positive
      const val JOYSTICK_DEADBAND: Double = 0.05
      const val USING_VISION: Boolean = false
      const val FIELD_ORIENTATED: Boolean = true
      const val AUTO_ALIGN: Boolean = false
      const val LIMELIGHT_DEADBAND: Double = 1.5
      const val MOTOR_DEADBAND: Double = 0.05
      const val IS_FIELD_ORIENTED: Boolean = true
      const val SHOULD_INVERT = false
      const val ENCODER_OFFSET: Double = (0 / 360).toDouble()
      var currentPose = Pose2d(0.0, 0.0, Rotation2d(0.0))
      val GREEN_LED: IntArray = intArrayOf(0, 255, 0)
      val ORANGE_LED: IntArray = intArrayOf(255, 165, 0)
      val HIGHTIDE_LED: IntArray = intArrayOf(0, 182, 174)
      const val X_DEADZONE: Double = 0.15 * 5.76
      const val Y_DEADZONE: Double = 0.15 * 5.76
      const val OFF_BALANCE_ANGLE_THRESHOLD: Double = 10.0
      const val ON_BALANCE_ANGLE_THRESHOLD: Double = 5.0
      var TEST_MODE: Boolean = true
    }
  }

  /** Object containing constants for the PhotonVision system. */
  object PhotonVisionConstants {
    const val CAMERA_ONE_HEIGHT_METER: Double = 0.47
    const val CAMERA_ONE_ANGLE_DEG: Double = 33.0
    const val OFFSET_TOWARD_MID_LEFT: Double = -15.00
    const val CAMERA_TWO_HEIGHT_METER: Double = 0.61
    const val CAMERA_TWO_ANGLE_DEG: Double = 37.5
    const val OFFSET_TOWARD_MID_RIGHT: Double = 15.0
  }
}
