---
title: SwerveSubsystem
---
//[SwerveBaseTemplate](../../../index.html)/[frc.robot.subsystems](../index.html)/[SwerveSubsystem](index.html)



# SwerveSubsystem



[jvm]\
open class [SwerveSubsystem](index.html) : SubsystemBase

The SwerveSubsystem class represents the swerve drive subsystem of the robot. It handles the control and state estimation of the swerve drive modules.



## Constructors


| | |
|---|---|
| [SwerveSubsystem](-swerve-subsystem.html) | [jvm]<br>constructor(photonvision: [Photonvision](../-photonvision/index.html))<br>Constructs a new SwerveSubsystem. |


## Functions


| Name | Summary |
|---|---|
| [chassisSpeedsDrive](chassis-speeds-drive.html) | [jvm]<br>open fun [chassisSpeedsDrive](chassis-speeds-drive.html)(chassisSpeeds: ChassisSpeeds)<br>Drives the robot using chassis speeds. |
| [getAutoSpeeds](get-auto-speeds.html) | [jvm]<br>open fun [getAutoSpeeds](get-auto-speeds.html)(): ChassisSpeeds<br>Gets the chassis speeds for autonomous driving. |
| [getHeading](get-heading.html) | [jvm]<br>open fun [getHeading](get-heading.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the heading of the robot. |
| [getModulePositions](get-module-positions.html) | [jvm]<br>open fun [getModulePositions](get-module-positions.html)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;SwerveModulePosition&gt;<br>Gets the positions of the swerve modules. |
| [getModuleStates](get-module-states.html) | [jvm]<br>open fun [getModuleStates](get-module-states.html)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;SwerveModuleState&gt;<br>Gets the states of the swerve modules. |
| [getPidgeyRotation](get-pidgey-rotation.html) | [jvm]<br>open fun [getPidgeyRotation](get-pidgey-rotation.html)(): Rotation2d<br>Gets the rotation of the Pigeon2 IMU. |
| [getPidgeyYaw](get-pidgey-yaw.html) | [jvm]<br>open fun [getPidgeyYaw](get-pidgey-yaw.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the yaw of the Pigeon2 IMU. |
| [getPose](get-pose.html) | [jvm]<br>open fun [getPose](get-pose.html)(): Pose2d<br>Gets the current pose of the robot from he pose estimator. |
| [getRotationPidggy](get-rotation-pidggy.html) | [jvm]<br>open fun [getRotationPidggy](get-rotation-pidggy.html)(): Rotation2d<br>Gets the rotation of the Pigeon2 IMU for PID control. |
| [newPose](new-pose.html) | [jvm]<br>open fun [newPose](new-pose.html)(pose: Pose2d)<br>Sets a new pose for the robot. |
| [periodic](periodic.html) | [jvm]<br>open fun [periodic](periodic.html)()<br>This method is called periodically by the scheduler. |
| [resetDrive](reset-drive.html) | [jvm]<br>open fun [resetDrive](reset-drive.html)()<br>Resets the drive positions of all swerve modules. |
| [resetPidgey](reset-pidgey.html) | [jvm]<br>open fun [resetPidgey](reset-pidgey.html)()<br>Resets the Pigeon2 IMU. |
| [setAutoPID](set-auto-p-i-d.html) | [jvm]<br>open fun [setAutoPID](set-auto-p-i-d.html)()<br>Sets the PID constants for autonomous driving. |
| [setCustomDrivePID](set-custom-drive-p-i-d.html) | [jvm]<br>open fun [setCustomDrivePID](set-custom-drive-p-i-d.html)()<br>Sets custom PID constants for the drive. |
| [setDriveSpeeds](set-drive-speeds.html) | [jvm]<br>open fun [setDriveSpeeds](set-drive-speeds.html)(forwardSpeed: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), leftSpeed: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), turnSpeed: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), isFieldOriented: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Sets the drive speeds for the swerve modules. |
| [setInitialHeading](set-initial-heading.html) | [jvm]<br>open fun [setInitialHeading](set-initial-heading.html)()<br>Sets the initial heading of the robot based on the alliance color. |
| [setModuleStates](set-module-states.html) | [jvm]<br>open fun [setModuleStates](set-module-states.html)(states: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;SwerveModuleState&gt;)<br>Sets the states of the swerve modules. |
| [setTelePID](set-tele-p-i-d.html) | [jvm]<br>open fun [setTelePID](set-tele-p-i-d.html)()<br>Sets the PID constants for teleoperated driving. |
| [stop](stop.html) | [jvm]<br>open fun [stop](stop.html)()<br>Stops all swerve modules. |
| [zeroPose](zero-pose.html) | [jvm]<br>open fun [zeroPose](zero-pose.html)()<br>Resets the pose of the robot to zero. |

