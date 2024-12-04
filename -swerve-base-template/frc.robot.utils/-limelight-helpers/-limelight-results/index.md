---
title: LimelightResults
---
//[SwerveBaseTemplate](../../../../index.html)/[frc.robot.utils](../../index.html)/[LimelightHelpers](../index.html)/[LimelightResults](index.html)



# LimelightResults



[jvm]\
open class [LimelightResults](index.html)

Limelight Results object, parsed from a Limelight's JSON results output.



## Constructors


| | |
|---|---|
| [LimelightResults](-limelight-results.html) | [jvm]<br>constructor() |


## Properties


| Name | Summary |
|---|---|
| [botpose](botpose.html) | [jvm]<br>open var [botpose](botpose.html): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [botpose_avgarea](botpose_avgarea.html) | [jvm]<br>open var [botpose_avgarea](botpose_avgarea.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [botpose_avgdist](botpose_avgdist.html) | [jvm]<br>open var [botpose_avgdist](botpose_avgdist.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [botpose_span](botpose_span.html) | [jvm]<br>open var [botpose_span](botpose_span.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [botpose_tagcount](botpose_tagcount.html) | [jvm]<br>open var [botpose_tagcount](botpose_tagcount.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [botpose_wpiblue](botpose_wpiblue.html) | [jvm]<br>open var [botpose_wpiblue](botpose_wpiblue.html): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [botpose_wpired](botpose_wpired.html) | [jvm]<br>open var [botpose_wpired](botpose_wpired.html): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [camerapose_robotspace](camerapose_robotspace.html) | [jvm]<br>open var [camerapose_robotspace](camerapose_robotspace.html): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [error](error.html) | [jvm]<br>open var [error](error.html): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [latency_capture](latency_capture.html) | [jvm]<br>open var [latency_capture](latency_capture.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [latency_jsonParse](latency_json-parse.html) | [jvm]<br>open var [latency_jsonParse](latency_json-parse.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [latency_pipeline](latency_pipeline.html) | [jvm]<br>open var [latency_pipeline](latency_pipeline.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [pipelineID](pipeline-i-d.html) | [jvm]<br>open var [pipelineID](pipeline-i-d.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [targets_Barcode](targets_-barcode.html) | [jvm]<br>open var [targets_Barcode](targets_-barcode.html): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.LimelightTarget_Barcode](../-limelight-target_-barcode/index.html)&gt; |
| [targets_Classifier](targets_-classifier.html) | [jvm]<br>open var [targets_Classifier](targets_-classifier.html): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.LimelightTarget_Classifier](../-limelight-target_-classifier/index.html)&gt; |
| [targets_Detector](targets_-detector.html) | [jvm]<br>open var [targets_Detector](targets_-detector.html): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.LimelightTarget_Detector](../-limelight-target_-detector/index.html)&gt; |
| [targets_Fiducials](targets_-fiducials.html) | [jvm]<br>open var [targets_Fiducials](targets_-fiducials.html): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.LimelightTarget_Fiducial](../-limelight-target_-fiducial/index.html)&gt; |
| [targets_Retro](targets_-retro.html) | [jvm]<br>open var [targets_Retro](targets_-retro.html): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.LimelightTarget_Retro](../-limelight-target_-retro/index.html)&gt; |
| [timestamp_LIMELIGHT_publish](timestamp_-l-i-m-e-l-i-g-h-t_publish.html) | [jvm]<br>open var [timestamp_LIMELIGHT_publish](timestamp_-l-i-m-e-l-i-g-h-t_publish.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [timestamp_RIOFPGA_capture](timestamp_-r-i-o-f-p-g-a_capture.html) | [jvm]<br>open var [timestamp_RIOFPGA_capture](timestamp_-r-i-o-f-p-g-a_capture.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [valid](valid.html) | [jvm]<br>open var [valid](valid.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |


## Functions


| Name | Summary |
|---|---|
| [getBotPose2d](get-bot-pose2d.html) | [jvm]<br>open fun [getBotPose2d](get-bot-pose2d.html)(): Pose2d |
| [getBotPose2d_wpiBlue](get-bot-pose2d_wpi-blue.html) | [jvm]<br>open fun [getBotPose2d_wpiBlue](get-bot-pose2d_wpi-blue.html)(): Pose2d |
| [getBotPose2d_wpiRed](get-bot-pose2d_wpi-red.html) | [jvm]<br>open fun [getBotPose2d_wpiRed](get-bot-pose2d_wpi-red.html)(): Pose2d |
| [getBotPose3d](get-bot-pose3d.html) | [jvm]<br>open fun [getBotPose3d](get-bot-pose3d.html)(): Pose3d |
| [getBotPose3d_wpiBlue](get-bot-pose3d_wpi-blue.html) | [jvm]<br>open fun [getBotPose3d_wpiBlue](get-bot-pose3d_wpi-blue.html)(): Pose3d |
| [getBotPose3d_wpiRed](get-bot-pose3d_wpi-red.html) | [jvm]<br>open fun [getBotPose3d_wpiRed](get-bot-pose3d_wpi-red.html)(): Pose3d |

