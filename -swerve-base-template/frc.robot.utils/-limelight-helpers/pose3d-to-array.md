---
title: pose3dToArray
---
//[SwerveBaseTemplate](../../../index.html)/[frc.robot.utils](../index.html)/[LimelightHelpers](index.html)/[pose3dToArray](pose3d-to-array.html)



# pose3dToArray



[jvm]\
open fun [pose3dToArray](pose3d-to-array.html)(pose: Pose3d): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;



Converts a Pose3d object to an array of doubles in the format [x, y, z, roll, pitch, yaw]. Translation components are in meters, rotation components are in degrees.



#### Return



A 6-element array containing [x, y, z, roll, pitch, yaw]



#### Parameters


jvm

| | |
|---|---|
| pose | The Pose3d object to convert |




