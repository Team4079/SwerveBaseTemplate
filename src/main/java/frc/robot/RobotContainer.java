

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.commands.AutoAlign;
import frc.robot.commands.PadDrive;
// import frc.robot.subsystems.LED;
// import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.utils.LogitechGamingPad;
import frc.robot.utils.GlobalsValues;
// import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
// import frc.robot.utils.Constants;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */

@SuppressWarnings("unused")
public class RobotContainer {
  private final SwerveSubsystem swerveSubsystem;
  private final LogitechGamingPad pad = new LogitechGamingPad(0); // Placeholder LogitechGamingPad
  // private final LED led;
  // private final Limelight limelety;
  
  private final JoystickButton padA;
  private final JoystickButton padB;
  private final JoystickButton padX;
  private final JoystickButton padY;
  private final JoystickButton rightBumper;
  private final JoystickButton leftBumper;

  // private final SendableChooser<Command> autoChooser;
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

   
    padA = new JoystickButton(pad, 1);
    padB = new JoystickButton(pad, 2);
    padX = new JoystickButton(pad, 3);
    padY = new JoystickButton(pad, 4);
    rightBumper = new JoystickButton(pad, 6);
    leftBumper = new JoystickButton(pad, 5);

    swerveSubsystem = new SwerveSubsystem();
    // swerveSubsystem.setDefaultCommand(new AutoAlign(swerveSubsystem, limelety, led));
    // swerveSubsystem.setDefaultCommand(new PadDrive(swerveSubsystem, pad, true)); // , limelety, led

    //Configure auto chooser
    configureBindings();
    // autoChooser = AutoBuilder.buildAutoChooser("New Auto");
    // SmartDashboard.putData("Auto Chooser", autoChooser);
  }
  
  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    // PathPlannerPath path = PathPlannerPath.fromPathFile("Test Path");
    // return AutoBuilder.followPath(path);

    return new PathPlannerAuto("Straight Auto");
  }

  /**
    * Gets the test command
    *
    * @return the command to run in test initial
  //   */
}