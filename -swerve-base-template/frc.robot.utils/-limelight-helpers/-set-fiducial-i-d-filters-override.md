---
title: SetFiducialIDFiltersOverride
---
//[SwerveBaseTemplate](../../../index.html)/[frc.robot.utils](../index.html)/[LimelightHelpers](index.html)/[SetFiducialIDFiltersOverride](-set-fiducial-i-d-filters-override.html)



# SetFiducialIDFiltersOverride



[jvm]\
open fun [SetFiducialIDFiltersOverride](-set-fiducial-i-d-filters-override.html)(limelightName: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), validIDs: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;)



Overrides the valid AprilTag IDs that will be used for localization. Tags not in this list will be ignored for robot pose estimation.



#### Parameters


jvm

| | |
|---|---|
| limelightName | Name/identifier of the Limelight |
| validIDs | Array of valid AprilTag IDs to track |




