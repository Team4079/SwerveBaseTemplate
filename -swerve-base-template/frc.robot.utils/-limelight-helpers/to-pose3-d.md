//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[LimelightHelpers](index.md)/[toPose3D](to-pose3-d.md)

# toPose3D

[jvm]\
open fun [toPose3D](to-pose3-d.md)(inData: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;): Pose3d

Takes a 6-length array of pose data and converts it to a Pose3d object. Array format: [x, y, z, roll, pitch, yaw] where angles are in degrees.

#### Return

Pose3d object representing the pose, or empty Pose3d if invalid data

#### Parameters

jvm

| | |
|---|---|
| inData | Array containing pose data [x, y, z, roll, pitch, yaw] |