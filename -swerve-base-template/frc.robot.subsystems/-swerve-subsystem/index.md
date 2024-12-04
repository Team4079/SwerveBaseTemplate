//[SwerveBaseTemplate](../../../index.md)/[frc.robot.subsystems](../index.md)/[SwerveSubsystem](index.md)

# SwerveSubsystem

[jvm]\
open class [SwerveSubsystem](index.md) : SubsystemBase

The SwerveSubsystem class represents the swerve drive subsystem of the robot. It handles the control and state estimation of the swerve drive modules.

## Constructors

| | |
|---|---|
| [SwerveSubsystem](-swerve-subsystem.md) | [jvm]<br>constructor(photonvision: [Photonvision](../-photonvision/index.md))<br>Constructs a new SwerveSubsystem. |

## Functions

| Name | Summary |
|---|---|
| [chassisSpeedsDrive](chassis-speeds-drive.md) | [jvm]<br>open fun [chassisSpeedsDrive](chassis-speeds-drive.md)(chassisSpeeds: ChassisSpeeds)<br>Drives the robot using chassis speeds. |
| [getAutoSpeeds](get-auto-speeds.md) | [jvm]<br>open fun [getAutoSpeeds](get-auto-speeds.md)(): ChassisSpeeds<br>Gets the chassis speeds for autonomous driving. |
| [getHeading](get-heading.md) | [jvm]<br>open fun [getHeading](get-heading.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the heading of the robot. |
| [getModulePositions](get-module-positions.md) | [jvm]<br>open fun [getModulePositions](get-module-positions.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;SwerveModulePosition&gt;<br>Gets the positions of the swerve modules. |
| [getModuleStates](get-module-states.md) | [jvm]<br>open fun [getModuleStates](get-module-states.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;SwerveModuleState&gt;<br>Gets the states of the swerve modules. |
| [getPidgeyRotation](get-pidgey-rotation.md) | [jvm]<br>open fun [getPidgeyRotation](get-pidgey-rotation.md)(): Rotation2d<br>Gets the rotation of the Pigeon2 IMU. |
| [getPidgeyYaw](get-pidgey-yaw.md) | [jvm]<br>open fun [getPidgeyYaw](get-pidgey-yaw.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the yaw of the Pigeon2 IMU. |
| [getPose](get-pose.md) | [jvm]<br>open fun [getPose](get-pose.md)(): Pose2d<br>Gets the current pose of the robot from he pose estimator. |
| [getRotationPidggy](get-rotation-pidggy.md) | [jvm]<br>open fun [getRotationPidggy](get-rotation-pidggy.md)(): Rotation2d<br>Gets the rotation of the Pigeon2 IMU for PID control. |
| [newPose](new-pose.md) | [jvm]<br>open fun [newPose](new-pose.md)(pose: Pose2d)<br>Sets a new pose for the robot. |
| [periodic](periodic.md) | [jvm]<br>open fun [periodic](periodic.md)()<br>This method is called periodically by the scheduler. |
| [resetDrive](reset-drive.md) | [jvm]<br>open fun [resetDrive](reset-drive.md)()<br>Resets the drive positions of all swerve modules. |
| [resetPidgey](reset-pidgey.md) | [jvm]<br>open fun [resetPidgey](reset-pidgey.md)()<br>Resets the Pigeon2 IMU. |
| [setAutoPID](set-auto-p-i-d.md) | [jvm]<br>open fun [setAutoPID](set-auto-p-i-d.md)()<br>Sets the PID constants for autonomous driving. |
| [setCustomDrivePID](set-custom-drive-p-i-d.md) | [jvm]<br>open fun [setCustomDrivePID](set-custom-drive-p-i-d.md)()<br>Sets custom PID constants for the drive. |
| [setDriveSpeeds](set-drive-speeds.md) | [jvm]<br>open fun [setDriveSpeeds](set-drive-speeds.md)(forwardSpeed: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), leftSpeed: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), turnSpeed: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), isFieldOriented: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Sets the drive speeds for the swerve modules. |
| [setInitialHeading](set-initial-heading.md) | [jvm]<br>open fun [setInitialHeading](set-initial-heading.md)()<br>Sets the initial heading of the robot based on the alliance color. |
| [setModuleStates](set-module-states.md) | [jvm]<br>open fun [setModuleStates](set-module-states.md)(states: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;SwerveModuleState&gt;)<br>Sets the states of the swerve modules. |
| [setTelePID](set-tele-p-i-d.md) | [jvm]<br>open fun [setTelePID](set-tele-p-i-d.md)()<br>Sets the PID constants for teleoperated driving. |
| [stop](stop.md) | [jvm]<br>open fun [stop](stop.md)()<br>Stops all swerve modules. |
| [zeroPose](zero-pose.md) | [jvm]<br>open fun [zeroPose](zero-pose.md)()<br>Resets the pose of the robot to zero. |
