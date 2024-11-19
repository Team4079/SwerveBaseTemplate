// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.utils

import com.ctre.phoenix6.signals.InvertedValue
import com.pathplanner.lib.config.PIDConstants
import com.pathplanner.lib.config.RobotConfig
import com.pathplanner.lib.controllers.PPHolonomicDriveController
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.kinematics.SwerveDriveKinematics

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
class GlobalsValues private constructor() {
  init {
    throw IllegalStateException(UTILITY_CLASS)
  }

  class MotorGlobalValues private constructor() {
    init {
      throw IllegalStateException(UTILITY_CLASS)
    }

    companion object {
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
      const val ENCODER_COUNTS_PER_ROTATION: Double = 1.0 // 2048 for v5, 1 for v6 (rotations)
      const val STEER_MOTOR_GEAR_RATIO: Double = 150.0 / 7 // 24
      const val DRIVE_MOTOR_GEAR_RATIO: Double = 5.9
      const val WHEEL_DIAMETER: Double = 0.106
      const val SPEED_CONSTANT: Double = 0.6 // 0.4
      const val AACORN_SPEED: Double = 0.95
      const val SLOW_SPEED: Double = 0.3
      const val TURN_CONSTANT: Double = 0.3 // 0.3
      const val MetersPerRevolution: Double = WHEEL_DIAMETER * Math.PI * 0.99
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
  }

  class SwerveGlobalValues private constructor() {
    object BasePIDGlobal {
      val STEER_PID_TELE: PID = PID(13.0, 0.000, 0.1, 0.0)
      val STEER_PID_AUTO: PID = PID(15.0, 0.000, 0.1, 0.0)

      // public static final PID STEER_PID = new PID(0.15, 0.0000, 0.000005, 0);
      // 0.05 P, 0 D
      @JvmField val DRIVE_PID_AUTO: PID = PID(7.0, 0.0, 0.00) // 0.00031
      const val DRIVE_PID_V_AUTO: Double = 0.5

      // public static final PID DRIVE_PID_TELE = new PID(0.0005, 0.0, 0.00); //0.00031
      // public static final double DRIVE_PID_V_TELE = 1;
      val DRIVE_PID_TELE: PID = PID(1.5, 0.0, 0.0)
      const val DRIVE_PID_V_TELE: Double = 0.0

      // DON'T SET D PAST 0.03 - Erick or else the swerve moduls make funny nosie
      // AutoAlign PID
      val ROTATIONAL_PID: PID = PID(0.2, 0.0, 0.0, 0.0)
      val PASS_ROTATIONAL_PID: PID = PID(0.1, 0000.0, 0.00, 0.0)

      // PathPlanner Push Variable Turn smartdasboard on or off
      @JvmField var TEST_MODE: Boolean = true

      var pathFollower: PPHolonomicDriveController =
        PPHolonomicDriveController(
          PIDConstants(5.0, 0.00, 0.0), // translation
          PIDConstants(5.0, 0.0, 0.0),
        ) // rotation); // Default path replanning config. See the API for the options here

      @JvmField var config: RobotConfig? = null

      init {
        try {
          config = RobotConfig.fromGUISettings()
        } catch (e: Exception) {
          // Handle exception as needed
          e.printStackTrace()
        }
      }
    }

    init {
      throw IllegalStateException(UTILITY_CLASS)
    }

    companion object {
      const val ROBOT_SIZE: Double = 0.43105229381 // Keep constant *ideally*

      // Motor Locations (Relative to the center in meters)
      val FRONT_LEFT: Translation2d = Translation2d(0.3048, 0.3048) // (0.263525, -0.263525);
      val FRONT_RIGHT: Translation2d = Translation2d(0.3048, -0.3048) // (0.263525, 0.263525);
      val BACK_LEFT: Translation2d = Translation2d(-0.3048, 0.3048) // (-0.263525, -0.263525);
      val BACK_RIGHT: Translation2d = Translation2d(-0.3048, -0.3048) // (-0.263525, 0.263525);
      @JvmField
      val kinematics: SwerveDriveKinematics =
        SwerveDriveKinematics(FRONT_LEFT, FRONT_RIGHT, BACK_LEFT, BACK_RIGHT)

      const val BASE_LENGTH_ERICK_TRAN: Double = 0.3048 * 2

      // uselses
      const val STATE_SPEED_THRESHOLD: Double = 0.05

      // The values of the can coders when the wheels are straight according to Mr.
      // Wright
      const val CANCoderValue9: Double = -0.419189 // 0.4198189
      const val CANCoderValue10: Double = -0.825928 - 0.5 // 0.328613 + 0.5 add 0.5
      const val CANCoderValue11: Double = -0.475098 // 0.539794 - 0.5%
      const val CANCoderValue12: Double = -0.032959 + 0.5 // 0.984863

      // Whether the motors are inverted
      val DRIVE_MOTOR_INVERETED: InvertedValue = InvertedValue.CounterClockwise_Positive
      val STEER_MOTOR_INVERTED: InvertedValue = InvertedValue.Clockwise_Positive

      // THe deadband of the joystick to combat drift
      const val JOYSTICK_DEADBAND: Double = 0.05

      const val USING_VISION: Boolean = false
      const val FIELD_ORIENTATED: Boolean = true

      // Whether the limelight auto aligns and its deadband
      const val AUTO_ALIGN: Boolean = false
      const val LIMELIGHT_DEADBAND: Double = 1.5

      const val MOTOR_DEADBAND: Double = 0.05
      const val IS_FIELD_ORIENTATED: Boolean = true
      const val ENCODER_OFFSET: Double =
        (0 / 360 // TODO add an offset for the canCoder getting the position at the beginning
          )
          .toDouble()

      @JvmField var currentPose: Pose2d = Pose2d(0.0, 0.0, Rotation2d(0.0))

      // RGB Values for LED
      val GREEN_LED: IntArray = intArrayOf(0, 255, 0)
      val ORANGE_LED: IntArray = intArrayOf(255, 165, 0)
      val HIGHTIDE_LED: IntArray = intArrayOf(0, 182, 174)

      // Controller X and Y deadbands
      const val xDEADZONE: Double = 0.15 * 5.76
      const val yDEADZONE: Double = 0.15 * 5.76

      const val offBalanceAngleThreshold: Double = 10.0
      const val onBalanceAngleThreshold: Double = 5.0
    }
  }

  class PhotonVisionConstants private constructor() {
    init {
      throw IllegalStateException(UTILITY_CLASS)
    }

    companion object {
      // Offset Values
      var tx: Double = 0.0
      var ty: Double = 0.0
      var ta: Double = 0.0
      var tv: Double = 0.0

      // Camera One
      const val CAMERA_ONE_HEIGHT_METER: Double = 0.47
      const val CAMERA_ONE_ANGLE_DEG: Double = 33.0 // up is positive
      const val OFFSET_TOWARD_MID_LEFT: Double = -15.00

      // Camera Two
      const val CAMERA_TWO_HEIGHT_METER: Double = 0.61
      const val CAMERA_TWO_ANGLE_DEG: Double = 37.5 // up is positive
      const val OFFSET_TOWARD_MID_RIGHT: Double = 15.0
    }
  }

  companion object {
    private const val UTILITY_CLASS = "Utility class"
  }
}
