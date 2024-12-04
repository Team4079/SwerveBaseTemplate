//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[LimelightHelpers](index.md)/[SetFiducialDownscalingOverride](-set-fiducial-downscaling-override.md)

# SetFiducialDownscalingOverride

[jvm]\

public static void[SetFiducialDownscalingOverride](-set-fiducial-downscaling-override.md)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)limelightName, floatdownscale)

Sets the downscaling factor for AprilTag detection. Increasing downscale can improve performance at the cost of potentially reduced detection range.

#### Parameters

jvm

| | |
|---|---|
| limelightName | Name/identifier of the Limelight |
| downscale | Downscale factor. Valid values: 1.0 (no downscale), 1.5, 2.0, 3.0, 4.0. Set to 0 for pipeline control. |
