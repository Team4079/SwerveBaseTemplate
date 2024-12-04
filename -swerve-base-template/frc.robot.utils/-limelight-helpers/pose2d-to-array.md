---
title: pose2dToArray
---
//[SwerveBaseTemplate](../../../index.html)/[frc.robot.utils](../index.html)/[LimelightHelpers](index.html)/[pose2dToArray](pose2d-to-array.html)



# pose2dToArray



[jvm]\
open fun [pose2dToArray](pose2d-to-array.html)(pose: Pose2d): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;



Converts a Pose2d object to an array of doubles in the format [x, y, z, roll, pitch, yaw]. Translation components are in meters, rotation components are in degrees. Note: z, roll, and pitch will be 0 since Pose2d only contains x, y, and yaw.



#### Return



A 6-element array containing [x, y, 0, 0, 0, yaw]



#### Parameters


jvm

| | |
|---|---|
| pose | The Pose2d object to convert |




