//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[LimelightHelpers](index.md)/[SetFiducialIDFiltersOverride](-set-fiducial-i-d-filters-override.md)

# SetFiducialIDFiltersOverride

[jvm]\

public static void[SetFiducialIDFiltersOverride](-set-fiducial-i-d-filters-override.md)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)limelightName, [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;int&gt;validIDs)

Overrides the valid AprilTag IDs that will be used for localization. Tags not in this list will be ignored for robot pose estimation.

#### Parameters

jvm

| | |
|---|---|
| limelightName | Name/identifier of the Limelight |
| validIDs | Array of valid AprilTag IDs to track |
