package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

public final class Main {
  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * If you change your main robot class, change the parameter type.
   */
  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
  }
}