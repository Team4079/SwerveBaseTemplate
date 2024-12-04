//[SwerveBaseTemplate](../../../../index.md)/[frc.robot.utils](../../index.md)/[LimelightHelpers](../index.md)/[LimelightTarget_Fiducial](index.md)

# LimelightTarget_Fiducial

[jvm]\
open class [LimelightTarget_Fiducial](index.md)

Represents an AprilTag/Fiducial Target Result extracted from JSON Output

## Constructors

| | |
|---|---|
| [LimelightTarget_Fiducial](-limelight-target_-fiducial.md) | [jvm]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [fiducialFamily](fiducial-family.md) | [jvm]<br>open var [fiducialFamily](fiducial-family.md): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [fiducialID](fiducial-i-d.md) | [jvm]<br>open var [fiducialID](fiducial-i-d.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [ta](ta.md) | [jvm]<br>open var [ta](ta.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [ts](ts.md) | [jvm]<br>open var [ts](ts.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [tx](tx.md) | [jvm]<br>open var [tx](tx.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [tx_nocrosshair](tx_nocrosshair.md) | [jvm]<br>open var [tx_nocrosshair](tx_nocrosshair.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [tx_pixels](tx_pixels.md) | [jvm]<br>open var [tx_pixels](tx_pixels.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [ty](ty.md) | [jvm]<br>open var [ty](ty.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [ty_nocrosshair](ty_nocrosshair.md) | [jvm]<br>open var [ty_nocrosshair](ty_nocrosshair.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [ty_pixels](ty_pixels.md) | [jvm]<br>open var [ty_pixels](ty_pixels.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |

## Functions

| Name | Summary |
|---|---|
| [getCameraPose_TargetSpace](get-camera-pose_-target-space.md) | [jvm]<br>open fun [getCameraPose_TargetSpace](get-camera-pose_-target-space.md)(): Pose3d |
| [getCameraPose_TargetSpace2D](get-camera-pose_-target-space2-d.md) | [jvm]<br>open fun [getCameraPose_TargetSpace2D](get-camera-pose_-target-space2-d.md)(): Pose2d |
| [getRobotPose_FieldSpace](get-robot-pose_-field-space.md) | [jvm]<br>open fun [getRobotPose_FieldSpace](get-robot-pose_-field-space.md)(): Pose3d |
| [getRobotPose_FieldSpace2D](get-robot-pose_-field-space2-d.md) | [jvm]<br>open fun [getRobotPose_FieldSpace2D](get-robot-pose_-field-space2-d.md)(): Pose2d |
| [getRobotPose_TargetSpace](get-robot-pose_-target-space.md) | [jvm]<br>open fun [getRobotPose_TargetSpace](get-robot-pose_-target-space.md)(): Pose3d |
| [getRobotPose_TargetSpace2D](get-robot-pose_-target-space2-d.md) | [jvm]<br>open fun [getRobotPose_TargetSpace2D](get-robot-pose_-target-space2-d.md)(): Pose2d |
| [getTargetPose_CameraSpace](get-target-pose_-camera-space.md) | [jvm]<br>open fun [getTargetPose_CameraSpace](get-target-pose_-camera-space.md)(): Pose3d |
| [getTargetPose_CameraSpace2D](get-target-pose_-camera-space2-d.md) | [jvm]<br>open fun [getTargetPose_CameraSpace2D](get-target-pose_-camera-space2-d.md)(): Pose2d |
| [getTargetPose_RobotSpace](get-target-pose_-robot-space.md) | [jvm]<br>open fun [getTargetPose_RobotSpace](get-target-pose_-robot-space.md)(): Pose3d |
| [getTargetPose_RobotSpace2D](get-target-pose_-robot-space2-d.md) | [jvm]<br>open fun [getTargetPose_RobotSpace2D](get-target-pose_-robot-space2-d.md)(): Pose2d |
