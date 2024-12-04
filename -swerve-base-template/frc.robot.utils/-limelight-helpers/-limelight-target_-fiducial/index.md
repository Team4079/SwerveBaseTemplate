---
title: LimelightTarget_Fiducial
---
//[SwerveBaseTemplate](../../../../index.html)/[frc.robot.utils](../../index.html)/[LimelightHelpers](../index.html)/[LimelightTarget_Fiducial](index.html)



# LimelightTarget_Fiducial



[jvm]\
open class [LimelightTarget_Fiducial](index.html)

Represents an AprilTag/Fiducial Target Result extracted from JSON Output



## Constructors


| | |
|---|---|
| [LimelightTarget_Fiducial](-limelight-target_-fiducial.html) | [jvm]<br>constructor() |


## Properties


| Name | Summary |
|---|---|
| [fiducialFamily](fiducial-family.html) | [jvm]<br>open var [fiducialFamily](fiducial-family.html): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [fiducialID](fiducial-i-d.html) | [jvm]<br>open var [fiducialID](fiducial-i-d.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [ta](ta.html) | [jvm]<br>open var [ta](ta.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [ts](ts.html) | [jvm]<br>open var [ts](ts.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [tx](tx.html) | [jvm]<br>open var [tx](tx.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [tx_nocrosshair](tx_nocrosshair.html) | [jvm]<br>open var [tx_nocrosshair](tx_nocrosshair.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [tx_pixels](tx_pixels.html) | [jvm]<br>open var [tx_pixels](tx_pixels.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [ty](ty.html) | [jvm]<br>open var [ty](ty.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [ty_nocrosshair](ty_nocrosshair.html) | [jvm]<br>open var [ty_nocrosshair](ty_nocrosshair.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [ty_pixels](ty_pixels.html) | [jvm]<br>open var [ty_pixels](ty_pixels.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |


## Functions


| Name | Summary |
|---|---|
| [getCameraPose_TargetSpace](get-camera-pose_-target-space.html) | [jvm]<br>open fun [getCameraPose_TargetSpace](get-camera-pose_-target-space.html)(): Pose3d |
| [getCameraPose_TargetSpace2D](get-camera-pose_-target-space2-d.html) | [jvm]<br>open fun [getCameraPose_TargetSpace2D](get-camera-pose_-target-space2-d.html)(): Pose2d |
| [getRobotPose_FieldSpace](get-robot-pose_-field-space.html) | [jvm]<br>open fun [getRobotPose_FieldSpace](get-robot-pose_-field-space.html)(): Pose3d |
| [getRobotPose_FieldSpace2D](get-robot-pose_-field-space2-d.html) | [jvm]<br>open fun [getRobotPose_FieldSpace2D](get-robot-pose_-field-space2-d.html)(): Pose2d |
| [getRobotPose_TargetSpace](get-robot-pose_-target-space.html) | [jvm]<br>open fun [getRobotPose_TargetSpace](get-robot-pose_-target-space.html)(): Pose3d |
| [getRobotPose_TargetSpace2D](get-robot-pose_-target-space2-d.html) | [jvm]<br>open fun [getRobotPose_TargetSpace2D](get-robot-pose_-target-space2-d.html)(): Pose2d |
| [getTargetPose_CameraSpace](get-target-pose_-camera-space.html) | [jvm]<br>open fun [getTargetPose_CameraSpace](get-target-pose_-camera-space.html)(): Pose3d |
| [getTargetPose_CameraSpace2D](get-target-pose_-camera-space2-d.html) | [jvm]<br>open fun [getTargetPose_CameraSpace2D](get-target-pose_-camera-space2-d.html)(): Pose2d |
| [getTargetPose_RobotSpace](get-target-pose_-robot-space.html) | [jvm]<br>open fun [getTargetPose_RobotSpace](get-target-pose_-robot-space.html)(): Pose3d |
| [getTargetPose_RobotSpace2D](get-target-pose_-robot-space2-d.html) | [jvm]<br>open fun [getTargetPose_RobotSpace2D](get-target-pose_-robot-space2-d.html)(): Pose2d |

