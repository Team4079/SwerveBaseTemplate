// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot

import com.pathplanner.lib.commands.PathPlannerAuto
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.button.JoystickButton
import frc.robot.commands.PadDrive
import frc.robot.subsystems.Photonvision
import frc.robot.subsystems.SwerveSubsystem
import frc.robot.utils.LogitechGamingPad
import frc.robot.utils.RobotParameters.SwerveParameters

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the [Robot]
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
class RobotContainer {
  private val swerveSubsystem: SwerveSubsystem
  private val photonvision: Photonvision

  private val padA: JoystickButton
  private val padB: JoystickButton
  private val padX: JoystickButton
  private val padY: JoystickButton

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  init {
    val pad = LogitechGamingPad(0)
    padA = JoystickButton(pad, 1)
    padB = JoystickButton(pad, 2)
    padX = JoystickButton(pad, 3)
    padY = JoystickButton(pad, 4)

    photonvision = Photonvision()
    swerveSubsystem = SwerveSubsystem(photonvision)
    swerveSubsystem.defaultCommand =
      PadDrive(swerveSubsystem, pad, SwerveParameters.IS_FIELD_ORIENTATED)

    configureBindings()
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * [Trigger.Trigger] or our [JoystickButton] constructor with an arbitrary predicate, or via the
   * named factories in [ ]'s subclasses for [ ]/[
   * PS4][edu.wpi.first.wpilibj2.command.button.CommandPS4Controller] controllers or
   * [Flight][edu.wpi.first.wpilibj2.command.button.CommandJoystick].
   */
  private fun configureBindings() {
    // padA.onTrue(new InstantCommand(swerveSubsystem::addRotorPositionsforModules));
    // padB.onTrue(new InstantCommand(swerveSubsystem::zeroHeading));
    // padY.onTrue(new InstantCommand(swerveSubsystem::configAAcornMode));
    // padX.onTrue(new InstantCommand(swerveSubsystem::configSlowMode));
  }

  val autonomousCommand: Command
    /**
     * Use this to pass the autonomous command to the main [Robot] class.
     *
     * @return the command to run in autonomous
     */
    get() = PathPlannerAuto("Straight Auto")
}
