//[SwerveBaseTemplate](../../../../index.md)/[frc.robot.utils](../../index.md)/[LimelightHelpers](../index.md)/[LimelightResults](index.md)

# LimelightResults

[jvm]\
open class [LimelightResults](index.md)

Limelight Results object, parsed from a Limelight's JSON results output.

## Constructors

| | |
|---|---|
| [LimelightResults](-limelight-results.md) | [jvm]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [botpose](botpose.md) | [jvm]<br>open var [botpose](botpose.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [botpose_avgarea](botpose_avgarea.md) | [jvm]<br>open var [botpose_avgarea](botpose_avgarea.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [botpose_avgdist](botpose_avgdist.md) | [jvm]<br>open var [botpose_avgdist](botpose_avgdist.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [botpose_span](botpose_span.md) | [jvm]<br>open var [botpose_span](botpose_span.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [botpose_tagcount](botpose_tagcount.md) | [jvm]<br>open var [botpose_tagcount](botpose_tagcount.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [botpose_wpiblue](botpose_wpiblue.md) | [jvm]<br>open var [botpose_wpiblue](botpose_wpiblue.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [botpose_wpired](botpose_wpired.md) | [jvm]<br>open var [botpose_wpired](botpose_wpired.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [camerapose_robotspace](camerapose_robotspace.md) | [jvm]<br>open var [camerapose_robotspace](camerapose_robotspace.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [error](error.md) | [jvm]<br>open var [error](error.md): [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) |
| [latency_capture](latency_capture.md) | [jvm]<br>open var [latency_capture](latency_capture.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [latency_jsonParse](latency_json-parse.md) | [jvm]<br>open var [latency_jsonParse](latency_json-parse.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [latency_pipeline](latency_pipeline.md) | [jvm]<br>open var [latency_pipeline](latency_pipeline.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [pipelineID](pipeline-i-d.md) | [jvm]<br>open var [pipelineID](pipeline-i-d.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [targets_Barcode](targets_-barcode.md) | [jvm]<br>open var [targets_Barcode](targets_-barcode.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.LimelightTarget_Barcode](../-limelight-target_-barcode/index.md)&gt; |
| [targets_Classifier](targets_-classifier.md) | [jvm]<br>open var [targets_Classifier](targets_-classifier.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.LimelightTarget_Classifier](../-limelight-target_-classifier/index.md)&gt; |
| [targets_Detector](targets_-detector.md) | [jvm]<br>open var [targets_Detector](targets_-detector.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.LimelightTarget_Detector](../-limelight-target_-detector/index.md)&gt; |
| [targets_Fiducials](targets_-fiducials.md) | [jvm]<br>open var [targets_Fiducials](targets_-fiducials.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.LimelightTarget_Fiducial](../-limelight-target_-fiducial/index.md)&gt; |
| [targets_Retro](targets_-retro.md) | [jvm]<br>open var [targets_Retro](targets_-retro.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.LimelightTarget_Retro](../-limelight-target_-retro/index.md)&gt; |
| [timestamp_LIMELIGHT_publish](timestamp_-l-i-m-e-l-i-g-h-t_publish.md) | [jvm]<br>open var [timestamp_LIMELIGHT_publish](timestamp_-l-i-m-e-l-i-g-h-t_publish.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [timestamp_RIOFPGA_capture](timestamp_-r-i-o-f-p-g-a_capture.md) | [jvm]<br>open var [timestamp_RIOFPGA_capture](timestamp_-r-i-o-f-p-g-a_capture.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [valid](valid.md) | [jvm]<br>open var [valid](valid.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [getBotPose2d](get-bot-pose2d.md) | [jvm]<br>open fun [getBotPose2d](get-bot-pose2d.md)(): Pose2d |
| [getBotPose2d_wpiBlue](get-bot-pose2d_wpi-blue.md) | [jvm]<br>open fun [getBotPose2d_wpiBlue](get-bot-pose2d_wpi-blue.md)(): Pose2d |
| [getBotPose2d_wpiRed](get-bot-pose2d_wpi-red.md) | [jvm]<br>open fun [getBotPose2d_wpiRed](get-bot-pose2d_wpi-red.md)(): Pose2d |
| [getBotPose3d](get-bot-pose3d.md) | [jvm]<br>open fun [getBotPose3d](get-bot-pose3d.md)(): Pose3d |
| [getBotPose3d_wpiBlue](get-bot-pose3d_wpi-blue.md) | [jvm]<br>open fun [getBotPose3d_wpiBlue](get-bot-pose3d_wpi-blue.md)(): Pose3d |
| [getBotPose3d_wpiRed](get-bot-pose3d_wpi-red.md) | [jvm]<br>open fun [getBotPose3d_wpiRed](get-bot-pose3d_wpi-red.md)(): Pose3d |
