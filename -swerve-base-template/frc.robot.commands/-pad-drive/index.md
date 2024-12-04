//[SwerveBaseTemplate](../../../index.md)/[frc.robot.commands](../index.md)/[PadDrive](index.md)

# PadDrive

[jvm]\
open class [PadDrive](index.md) : Command

Command to control the robot's swerve drive using a Logitech gaming pad.

## Constructors

| | |
|---|---|
| [PadDrive](-pad-drive.md) | [jvm]<br>constructor(swerveSubsystem: [SwerveSubsystem](../../frc.robot.subsystems/-swerve-subsystem/index.md), pad: [LogitechGamingPad](../../frc.robot.utils/-logitech-gaming-pad/index.md), isFieldOriented: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Constructs a new PadDrive command. |

## Types

| Name | Summary |
|---|---|
| [Coordinate](-coordinate/index.md) | [jvm]<br>class [Coordinate](-coordinate/index.md) : [Record](https://docs.oracle.com/javase/8/docs/api/java/lang/Record.html)<br>Record representing a coordinate with x and y values. |

## Functions

| Name | Summary |
|---|---|
| [execute](execute.md) | [jvm]<br>open fun [execute](execute.md)()<br>Called every time the scheduler runs while the command is scheduled. |
| [isFinished](is-finished.md) | [jvm]<br>open fun [isFinished](is-finished.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true when the command should end. |
| [positionSet](position-set.md) | [jvm]<br>open fun [positionSet](position-set.md)(pad: [LogitechGamingPad](../../frc.robot.utils/-logitech-gaming-pad/index.md)): [PadDrive.Coordinate](-coordinate/index.md)<br>Sets the position based on the input from the Logitech gaming pad. |
