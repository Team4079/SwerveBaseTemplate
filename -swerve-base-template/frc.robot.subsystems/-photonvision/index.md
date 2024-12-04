//[SwerveBaseTemplate](../../../index.md)/[frc.robot.subsystems](../index.md)/[Photonvision](index.md)

# Photonvision

[jvm]\
open class [Photonvision](index.md) : SubsystemBase

The PhotonVision subsystem handles vision processing using PhotonVision cameras.

## Constructors

| | |
|---|---|
| [Photonvision](-photonvision.md) | [jvm]<br>constructor()<br>Constructs a new PhotonVision subsystem. |

## Functions

| Name | Summary |
|---|---|
| [getDistanceSubwoofer](get-distance-subwoofer.md) | [jvm]<br>open fun [getDistanceSubwoofer](get-distance-subwoofer.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates and returns the distance to the subwoofer for the 2024 Crescendo game. |
| [getEstimatedGlobalPose](get-estimated-global-pose.md) | [jvm]<br>open fun [getEstimatedGlobalPose](get-estimated-global-pose.md)(): Transform3d<br>open fun [getEstimatedGlobalPose](get-estimated-global-pose.md)(prevEstimatedRobotPose: Pose2d): EstimatedRobotPose<br>Gets the estimated global pose of the robot. |
| [getPivotPosition](get-pivot-position.md) | [jvm]<br>open fun [getPivotPosition](get-pivot-position.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the forward distance to the target. |
| [getSubwooferYaw](get-subwoofer-yaw.md) | [jvm]<br>open fun [getSubwooferYaw](get-subwoofer-yaw.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the yaw of the subwoofer. |
| [hasTag](has-tag.md) | [jvm]<br>open fun [hasTag](has-tag.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if there is a tag. |
| [periodic](periodic.md) | [jvm]<br>open fun [periodic](periodic.md)()<br>This method is called periodically by the scheduler. |
