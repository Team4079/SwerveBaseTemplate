---
title: SwerveModule
---
//[SwerveBaseTemplate](../../../index.html)/[frc.robot.subsystems](../index.html)/[SwerveModule](index.html)



# SwerveModule



[jvm]\
open class [SwerveModule](index.html)

Represents a swerve module used in a swerve drive system.



## Constructors


| | |
|---|---|
| [SwerveModule](-swerve-module.html) | [jvm]<br>constructor(driveId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), steerId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), canCoderID: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), canCoderDriveStraightSteerSetPoint: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Constructs a new SwerveModule. |


## Properties


| Name | Summary |
|---|---|
| [state](state.html) | [jvm]<br>open var [state](state.html): SwerveModuleState |


## Functions


| Name | Summary |
|---|---|
| [getPosition](get-position.html) | [jvm]<br>open fun [getPosition](get-position.html)(): SwerveModulePosition<br>Gets the current position of the swerve module. |
| [resetDrivePosition](reset-drive-position.html) | [jvm]<br>open fun [resetDrivePosition](reset-drive-position.html)()<br>Resets the drive motor position to zero. |
| [setAUTOPID](set-a-u-t-o-p-i-d.html) | [jvm]<br>open fun [setAUTOPID](set-a-u-t-o-p-i-d.html)()<br>Sets the PID values for autonomous mode. |
| [setDrivePID](set-drive-p-i-d.html) | [jvm]<br>open fun [setDrivePID](set-drive-p-i-d.html)(pid: [PID](../../frc.robot.utils/-p-i-d/index.html), velocity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Sets the drive PID values. |
| [setSteerPID](set-steer-p-i-d.html) | [jvm]<br>open fun [setSteerPID](set-steer-p-i-d.html)(pid: [PID](../../frc.robot.utils/-p-i-d/index.html), velocity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Sets the steer PID values. |
| [setTELEPID](set-t-e-l-e-p-i-d.html) | [jvm]<br>open fun [setTELEPID](set-t-e-l-e-p-i-d.html)()<br>Sets the PID values for teleoperation mode. |
| [stop](stop.html) | [jvm]<br>open fun [stop](stop.html)()<br>Stops the swerve module motors. |

