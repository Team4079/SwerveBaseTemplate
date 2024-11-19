// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
class Robot : TimedRobot() {
  private var autonomousCommand: Command? = null

  private var robotContainer: RobotContainer? = null

  private val timer = Timer()
  private val avgTimer = Timer()
  private var timesRan = 0
  private val timesRanArr = ArrayList<Int>()

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  override fun robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    avgTimer.start()
    timer.start()
    robotContainer = RobotContainer()
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * This runs after the mode specific periodic functions, but before LiveWindow and SmartDashboard
   * integrated updating.
   */
  override fun robotPeriodic() {
    CommandScheduler.getInstance().run()
    timesRan++

    if (timer.advanceIfElapsed(1.0)) {
      println("1 second has passed, times ran is $timesRan")
      SmartDashboard.putNumber("Hurtz Ketchup", timesRan.toDouble())
      timesRanArr.add(timesRan)
      timesRan = 0
    }

    if (avgTimer.advanceIfElapsed(15.0)) {
      val avgTimesRan =
        timesRanArr
          .stream()
          .mapToInt { a: Int? -> a!! }
          .average()
          .orElse(404.0) // Something's wrong
      println("15 seconds have passed, times ran is $avgTimesRan")
      SmartDashboard.putNumber("Hurtz Ketchup Average", avgTimesRan)
      timesRanArr.clear()
    }
  }

  /** This function is called once each time the robot enters Disabled mode. */
  override fun disabledInit() {}

  override fun disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your [RobotContainer] class. */
  override fun autonomousInit() {
    autonomousCommand = robotContainer!!.autonomousCommand

    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand!!.schedule()
    }
  }

  /** This function is called periodically during autonomous. */
  override fun autonomousPeriodic() {}

  override fun teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand!!.cancel()
    }
  }

  /** This function is called periodically during operator control. */
  override fun teleopPeriodic() {}

  override fun testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll()
  }

  /** This function is called periodically during test mode. */
  override fun testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  override fun simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  override fun simulationPeriodic() {}
}
