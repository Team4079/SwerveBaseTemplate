package frc.robot

import edu.wpi.first.wpilibj.RobotBase

object Main {
  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * If you change your main robot class, change the parameter type.
   */
  @JvmStatic
  fun main(args: Array<String>) {
    RobotBase.startRobot { Robot() }
  }
}
