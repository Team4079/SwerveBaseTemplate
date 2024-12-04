//[SwerveBaseTemplate](../../../index.md)/[frc.robot.subsystems](../index.md)/[SwerveSubsystem](index.md)

# SwerveSubsystem

[jvm]\
public class [SwerveSubsystem](index.md) extends SubsystemBase

The SwerveSubsystem class represents the swerve drive subsystem of the robot. It handles the control and state estimation of the swerve drive modules.

## Constructors

| | |
|---|---|
| [SwerveSubsystem](-swerve-subsystem.md) | [jvm]<br>public void[SwerveSubsystem](-swerve-subsystem.md)([Photonvision](../-photonvision/index.md)photonvision)<br>Constructs a new SwerveSubsystem. |

## Functions

| Name | Summary |
|---|---|
| [chassisSpeedsDrive](chassis-speeds-drive.md) | [jvm]<br>public void[chassisSpeedsDrive](chassis-speeds-drive.md)(ChassisSpeedschassisSpeeds)<br>Drives the robot using chassis speeds. |
| [getAutoSpeeds](get-auto-speeds.md) | [jvm]<br>public ChassisSpeeds[getAutoSpeeds](get-auto-speeds.md)()<br>Gets the chassis speeds for autonomous driving. |
| [getHeading](get-heading.md) | [jvm]<br>public double[getHeading](get-heading.md)()<br>Gets the heading of the robot. |
| [getModulePositions](get-module-positions.md) | [jvm]<br>public [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;SwerveModulePosition&gt;[getModulePositions](get-module-positions.md)()<br>Gets the positions of the swerve modules. |
| [getModuleStates](get-module-states.md) | [jvm]<br>public [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;SwerveModuleState&gt;[getModuleStates](get-module-states.md)()<br>Gets the states of the swerve modules. |
| [getPidgeyRotation](get-pidgey-rotation.md) | [jvm]<br>public Rotation2d[getPidgeyRotation](get-pidgey-rotation.md)()<br>Gets the rotation of the Pigeon2 IMU. |
| [getPidgeyYaw](get-pidgey-yaw.md) | [jvm]<br>public double[getPidgeyYaw](get-pidgey-yaw.md)()<br>Gets the yaw of the Pigeon2 IMU. |
| [getPose](get-pose.md) | [jvm]<br>public Pose2d[getPose](get-pose.md)()<br>Gets the current pose of the robot from he pose estimator. |
| [getRotationPidggy](get-rotation-pidggy.md) | [jvm]<br>public Rotation2d[getRotationPidggy](get-rotation-pidggy.md)()<br>Gets the rotation of the Pigeon2 IMU for PID control. |
| [newPose](new-pose.md) | [jvm]<br>public void[newPose](new-pose.md)(Pose2dpose)<br>Sets a new pose for the robot. |
| [periodic](periodic.md) | [jvm]<br>public void[periodic](periodic.md)()<br>This method is called periodically by the scheduler. |
| [resetDrive](reset-drive.md) | [jvm]<br>public void[resetDrive](reset-drive.md)()<br>Resets the drive positions of all swerve modules. |
| [resetPidgey](reset-pidgey.md) | [jvm]<br>public void[resetPidgey](reset-pidgey.md)()<br>Resets the Pigeon2 IMU. |
| [setAutoPID](set-auto-p-i-d.md) | [jvm]<br>public void[setAutoPID](set-auto-p-i-d.md)()<br>Sets the PID constants for autonomous driving. |
| [setCustomDrivePID](set-custom-drive-p-i-d.md) | [jvm]<br>public void[setCustomDrivePID](set-custom-drive-p-i-d.md)()<br>Sets custom PID constants for the drive. |
| [setDriveSpeeds](set-drive-speeds.md) | [jvm]<br>public void[setDriveSpeeds](set-drive-speeds.md)(doubleforwardSpeed, doubleleftSpeed, doubleturnSpeed, booleanisFieldOriented)<br>Sets the drive speeds for the swerve modules. |
| [setInitialHeading](set-initial-heading.md) | [jvm]<br>public void[setInitialHeading](set-initial-heading.md)()<br>Sets the initial heading of the robot based on the alliance color. |
| [setModuleStates](set-module-states.md) | [jvm]<br>public void[setModuleStates](set-module-states.md)([Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;SwerveModuleState&gt;states)<br>Sets the states of the swerve modules. |
| [setTelePID](set-tele-p-i-d.md) | [jvm]<br>public void[setTelePID](set-tele-p-i-d.md)()<br>Sets the PID constants for teleoperated driving. |
| [stop](stop.md) | [jvm]<br>public void[stop](stop.md)()<br>Stops all swerve modules. |
| [zeroPose](zero-pose.md) | [jvm]<br>public void[zeroPose](zero-pose.md)()<br>Resets the pose of the robot to zero. |
