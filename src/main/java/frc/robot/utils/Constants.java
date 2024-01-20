// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
// import frc.robot.utils.PID;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  private static final String UTILITY_CLASS = "Utility class";

  private Constants() {
    throw new IllegalStateException(UTILITY_CLASS);
  }

  public static class MotorConstants {
    private MotorConstants() {
      throw new IllegalStateException(UTILITY_CLASS);
    }

    public static final int FRONT_LEFT_STEER_ID = 1;
    public static final int FRONT_LEFT_DRIVE_ID = 2;
    public static final int FRONT_RIGHT_STEER_ID = 3;
    public static final int FRONT_RIGHT_DRIVE_ID = 4;
    public static final int BACK_LEFT_STEER_ID = 5;
    public static final int BACK_LEFT_DRIVE_ID = 6;
    public static final int BACK_RIGHT_STEER_ID = 7;
    public static final int BACK_RIGHT_DRIVE_ID = 8;
    public static final int FRONT_LEFT_CAN_CODER_ID = 9;
    public static final int FRONT_RIGHT_CAN_CODER_ID = 10;
    public static final int BACK_LEFT_CAN_CODER_ID = 11;
    public static final int BACK_RIGHT_CAN_CODER_ID = 12;

    public static final double MAX_SPEED = 4.96824;
    public static final double MAX_ANGULAR_SPEED = (14 * Math.PI) / 3;
    public static final double ENCODER_COUNTS_PER_ROTATION = 1; // 2048 for v5, 1 for v6 (rotations)
    public static final double STEER_MOTOR_GEAR_RATIO = 150.0 / 7; // 24
    public static final double DRIVE_MOTOR_GEAR_RATIO = 6.75;
    public static final double WHEEL_DIAMETER = 0.1;
    public static final double SPEED_CONSTANT = 0.6; // 0.4
    public static final double AACORN_SPEED = 0.95;
    public static final double SLOW_SPEED = 0.3;
    public static final double TURN_CONSTANT = 0.3; // 0.3

    public static double HEADING = 0.0;
    public static boolean SLOW_MODE = false;
    public static boolean AACORN_MODE = true;
  }

  public static class SwerveConstants {
    private SwerveConstants() {
      throw new IllegalStateException(UTILITY_CLASS);
    }

    public static final double robotSize = 26; // Keep constant *ideally*

    public static final Translation2d frontLeftLocation = new Translation2d(0.263525, -0.263525);
    public static final Translation2d frontRightLocation = new Translation2d(0.263525, 0.263525);
    public static final Translation2d backLeftLocation = new Translation2d(-0.263525, -0.263525);
    public static final Translation2d backRightLocation = new Translation2d(-0.263525, 0.263525);
    public static final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        frontLeftLocation,
        frontRightLocation,
        backLeftLocation,
        backRightLocation);
    public static final Pose2d STARTING_POSE = new Pose2d(2, 1, new Rotation2d());
    // STARTING_POSE = new Pose2d(14, 3, new Rotation2d());
    public static final double STATE_SPEED_THRESHOLD = 0.05;

    public static final double CANCoderValue9 = 0.915283 + 0.5; // 0.9174805
    public static final double CANCoderValue10 = 0.327881; // 0.328613 + 0.5 add 0.5
    public static final double CANCoderValue11 = 0.979736 - 0.5; // 0.539794 - 0.5
    public static final double CANCoderValue12 = 0.536133; // 0.984863
    public static final double JOYSTICK_DEADBAND = 0.05;
    public static final boolean usingVision = false;
    public static final boolean isFieldOriented = true;

    public static final double DEADBAND = 0.08;

    public static class PIDConstants {
      public static final PID STEER_PID = new PID(0.14, 0.00002, 0.008, 0);
      public static final PID DRIVE_PID = new PID(0.01, 0.0, 0.0, 0);
    }

    public static final double offBalanceAngleThreshold = 10;
    public static final double onBalanceAngleThreshold = 5;
  }
}
