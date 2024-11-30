// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot

import edu.wpi.first.wpilibj.PowerDistribution
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler
import frc.robot.utils.dash
import org.littletonrobotics.junction.LogFileUtil
import org.littletonrobotics.junction.LoggedRobot
import org.littletonrobotics.junction.Logger
import org.littletonrobotics.junction.networktables.NT4Publisher
import org.littletonrobotics.junction.wpilog.WPILOGReader
import org.littletonrobotics.junction.wpilog.WPILOGWriter

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
class Robot : LoggedRobot() {
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
    avgTimer.start()
    timer.start()
    robotContainer = RobotContainer()

    Logger.recordMetadata("ProjectName", "MyProject")
    // We need to add the ProjectName and MyProject // Set a metadata value

    if (isReal()) {
      Logger.addDataReceiver(WPILOGWriter())
      // Log to a USB stick ("/U/logs")
      Logger.addDataReceiver(NT4Publisher())
      // Publish data to NetworkTables
      PowerDistribution(1, PowerDistribution.ModuleType.kRev)
      // Enables power distribution logging
    } else {
      setUseTiming(false)
      // Run as fast as possible
      val logPath = LogFileUtil.findReplayLog()
      // Pull the replay log from AdvantageScope (or prompt the user)
      Logger.setReplaySource(WPILOGReader(logPath))
      // Read replay log
      Logger.addDataReceiver(WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim")))
      // Save outputs to a new log
    }

    // Logger.disableDeterministicTimestamps() // See "Deterministic Timestamps" in the
    // "Understanding Data Flow" page
    Logger
      .start() // Start logging! No more data receivers, replay sources, or metadata values may be
    // added.

    // TODO: Replace inputted project names
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
      dash("Hurtz Ketchup" to timesRan)
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
      dash("Hurtz Ketchup Average" to avgTimesRan)
      timesRanArr.clear()
    }
  }

  /** This function is called once each time the robot enters Disabled mode. */
  override fun disabledInit() {}

  override fun disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your [RobotContainer] class. */
  override fun autonomousInit() {
    autonomousCommand = robotContainer!!.autonomousCommand.also { it.schedule() }
  }

  /** This function is called periodically during autonomous. */
  override fun autonomousPeriodic() {}

  override fun teleopInit() {
    if (autonomousCommand != null) {
      autonomousCommand!!.cancel()
    }
  }

  /** This function is called periodically during operator control. */
  override fun teleopPeriodic() {}

  override fun testInit() {
    CommandScheduler.getInstance().cancelAll()
  }

  /** This function is called periodically during test mode. */
  override fun testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  override fun simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  override fun simulationPeriodic() {}
}
