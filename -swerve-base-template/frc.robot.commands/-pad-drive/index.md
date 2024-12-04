---
title: PadDrive
---
//[SwerveBaseTemplate](../../../index.html)/[frc.robot.commands](../index.html)/[PadDrive](index.html)



# PadDrive



[jvm]\
open class [PadDrive](index.html) : Command

Command to control the robot's swerve drive using a Logitech gaming pad.



## Constructors


| | |
|---|---|
| [PadDrive](-pad-drive.html) | [jvm]<br>constructor(swerveSubsystem: [SwerveSubsystem](../../frc.robot.subsystems/-swerve-subsystem/index.html), pad: [LogitechGamingPad](../../frc.robot.utils/-logitech-gaming-pad/index.html), isFieldOriented: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Constructs a new PadDrive command. |


## Types


| Name | Summary |
|---|---|
| [Coordinate](-coordinate/index.html) | [jvm]<br>class [Coordinate](-coordinate/index.html) : [Record](https://docs.oracle.com/javase/8/docs/api/java/lang/Record.html)<br>Record representing a coordinate with x and y values. |


## Functions


| Name | Summary |
|---|---|
| [execute](execute.html) | [jvm]<br>open fun [execute](execute.html)()<br>Called every time the scheduler runs while the command is scheduled. |
| [isFinished](is-finished.html) | [jvm]<br>open fun [isFinished](is-finished.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true when the command should end. |
| [positionSet](position-set.html) | [jvm]<br>open fun [positionSet](position-set.html)(pad: [LogitechGamingPad](../../frc.robot.utils/-logitech-gaming-pad/index.html)): [PadDrive.Coordinate](-coordinate/index.html)<br>Sets the position based on the input from the Logitech gaming pad. |

