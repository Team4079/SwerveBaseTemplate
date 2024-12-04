//[SwerveBaseTemplate](../../../index.md)/[frc.robot.subsystems](../index.md)/[SwerveModule](index.md)

# SwerveModule

[jvm]\
open class [SwerveModule](index.md)

Represents a swerve module used in a swerve drive system.

## Constructors

| | |
|---|---|
| [SwerveModule](-swerve-module.md) | [jvm]<br>constructor(driveId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), steerId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), canCoderID: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), canCoderDriveStraightSteerSetPoint: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Constructs a new SwerveModule. |

## Properties

| Name | Summary |
|---|---|
| [state](state.md) | [jvm]<br>open var [state](state.md): SwerveModuleState |

## Functions

| Name | Summary |
|---|---|
| [getPosition](get-position.md) | [jvm]<br>open fun [getPosition](get-position.md)(): SwerveModulePosition<br>Gets the current position of the swerve module. |
| [resetDrivePosition](reset-drive-position.md) | [jvm]<br>open fun [resetDrivePosition](reset-drive-position.md)()<br>Resets the drive motor position to zero. |
| [setAUTOPID](set-a-u-t-o-p-i-d.md) | [jvm]<br>open fun [setAUTOPID](set-a-u-t-o-p-i-d.md)()<br>Sets the PID values for autonomous mode. |
| [setDrivePID](set-drive-p-i-d.md) | [jvm]<br>open fun [setDrivePID](set-drive-p-i-d.md)(pid: [PID](../../frc.robot.utils/-p-i-d/index.md), velocity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Sets the drive PID values. |
| [setSteerPID](set-steer-p-i-d.md) | [jvm]<br>open fun [setSteerPID](set-steer-p-i-d.md)(pid: [PID](../../frc.robot.utils/-p-i-d/index.md), velocity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Sets the steer PID values. |
| [setTELEPID](set-t-e-l-e-p-i-d.md) | [jvm]<br>open fun [setTELEPID](set-t-e-l-e-p-i-d.md)()<br>Sets the PID values for teleoperation mode. |
| [stop](stop.md) | [jvm]<br>open fun [stop](stop.md)()<br>Stops the swerve module motors. |
