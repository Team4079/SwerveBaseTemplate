---
title: Photonvision
---
//[SwerveBaseTemplate](../../../index.html)/[frc.robot.subsystems](../index.html)/[Photonvision](index.html)



# Photonvision



[jvm]\
open class [Photonvision](index.html) : SubsystemBase

The PhotonVision subsystem handles vision processing using PhotonVision cameras.



## Constructors


| | |
|---|---|
| [Photonvision](-photonvision.html) | [jvm]<br>constructor()<br>Constructs a new PhotonVision subsystem. |


## Functions


| Name | Summary |
|---|---|
| [getDistanceSubwoofer](get-distance-subwoofer.html) | [jvm]<br>open fun [getDistanceSubwoofer](get-distance-subwoofer.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates and returns the distance to the subwoofer for the 2024 Crescendo game. |
| [getEstimatedGlobalPose](get-estimated-global-pose.html) | [jvm]<br>open fun [getEstimatedGlobalPose](get-estimated-global-pose.html)(): Transform3d<br>open fun [getEstimatedGlobalPose](get-estimated-global-pose.html)(prevEstimatedRobotPose: Pose2d): EstimatedRobotPose<br>Gets the estimated global pose of the robot. |
| [getPivotPosition](get-pivot-position.html) | [jvm]<br>open fun [getPivotPosition](get-pivot-position.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the forward distance to the target. |
| [getSubwooferYaw](get-subwoofer-yaw.html) | [jvm]<br>open fun [getSubwooferYaw](get-subwoofer-yaw.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the yaw of the subwoofer. |
| [hasTag](has-tag.html) | [jvm]<br>open fun [hasTag](has-tag.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if there is a tag. |
| [periodic](periodic.html) | [jvm]<br>open fun [periodic](periodic.html)()<br>This method is called periodically by the scheduler. |

