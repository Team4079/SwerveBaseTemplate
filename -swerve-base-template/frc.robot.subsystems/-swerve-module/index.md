//[SwerveBaseTemplate](../../../index.md)/[frc.robot.subsystems](../index.md)/[SwerveModule](index.md)

# SwerveModule

[jvm]\
public class [SwerveModule](index.md)

Represents a swerve module used in a swerve drive system.

## Constructors

| | |
|---|---|
| [SwerveModule](-swerve-module.md) | [jvm]<br>public void[SwerveModule](-swerve-module.md)(intdriveId, intsteerId, intcanCoderID, doublecanCoderDriveStraightSteerSetPoint)<br>Constructs a new SwerveModule. |

## Properties

| Name | Summary |
|---|---|
| [state](index.md#1076622687%2FProperties%2F-1216412040) | [jvm]<br>public SwerveModuleState[state](index.md#1076622687%2FProperties%2F-1216412040) |

## Functions

| Name | Summary |
|---|---|
| [getPosition](get-position.md) | [jvm]<br>public SwerveModulePosition[getPosition](get-position.md)()<br>Gets the current position of the swerve module. |
| [getState](get-state.md) | [jvm]<br>public SwerveModuleState[getState](get-state.md)()<br>Gets the current state of the swerve module. |
| [resetDrivePosition](reset-drive-position.md) | [jvm]<br>public void[resetDrivePosition](reset-drive-position.md)()<br>Resets the drive motor position to zero. |
| [setAUTOPID](set-a-u-t-o-p-i-d.md) | [jvm]<br>public void[setAUTOPID](set-a-u-t-o-p-i-d.md)()<br>Sets the PID values for autonomous mode. |
| [setDrivePID](set-drive-p-i-d.md) | [jvm]<br>public void[setDrivePID](set-drive-p-i-d.md)([PID](../../frc.robot.utils/-p-i-d/index.md)pid, doublevelocity)<br>Sets the drive PID values. |
| [setState](set-state.md) | [jvm]<br>public void[setState](set-state.md)(SwerveModuleStatevalue)<br>Sets the state of the swerve module. |
| [setSteerPID](set-steer-p-i-d.md) | [jvm]<br>public void[setSteerPID](set-steer-p-i-d.md)([PID](../../frc.robot.utils/-p-i-d/index.md)pid, doublevelocity)<br>Sets the steer PID values. |
| [setTELEPID](set-t-e-l-e-p-i-d.md) | [jvm]<br>public void[setTELEPID](set-t-e-l-e-p-i-d.md)()<br>Sets the PID values for teleoperation mode. |
| [stop](stop.md) | [jvm]<br>public void[stop](stop.md)()<br>Stops the swerve module motors. |
