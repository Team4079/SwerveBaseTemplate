//[SwerveBaseTemplate](../../../index.md)/[frc.robot.commands](../index.md)/[PadDrive](index.md)

# PadDrive

[jvm]\
public class [PadDrive](index.md) extends Command

Command to control the robot's swerve drive using a Logitech gaming pad.

## Constructors

| | |
|---|---|
| [PadDrive](-pad-drive.md) | [jvm]<br>public void[PadDrive](-pad-drive.md)([SwerveSubsystem](../../frc.robot.subsystems/-swerve-subsystem/index.md)swerveSubsystem, [LogitechGamingPad](../../frc.robot.utils/-logitech-gaming-pad/index.md)pad, booleanisFieldOriented)<br>Constructs a new PadDrive command. |

## Types

| Name | Summary |
|---|---|
| [Coordinate](-coordinate/index.md) | [jvm]<br>public final class [Coordinate](-coordinate/index.md) extends [Record](https://docs.oracle.com/javase/8/docs/api/java/lang/Record.html)<br>Record representing a coordinate with x and y values. |

## Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | [jvm]<br>public void[execute](execute.md)()<br>Called every time the scheduler runs while the command is scheduled. |
| [isFinished](is-finished.md) | [jvm]<br>public boolean[isFinished](is-finished.md)()<br>Returns true when the command should end. |
| [positionSet](position-set.md) | [jvm]<br>public static [PadDrive.Coordinate](-coordinate/index.md)[positionSet](position-set.md)([LogitechGamingPad](../../frc.robot.utils/-logitech-gaming-pad/index.md)pad)<br>Sets the position based on the input from the Logitech gaming pad. |
