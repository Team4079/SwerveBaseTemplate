//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[LimelightHelpers](index.md)/[toPose2D](to-pose2-d.md)

# toPose2D

[jvm]\
open fun [toPose2D](to-pose2-d.md)(inData: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;): Pose2d

Takes a 6-length array of pose data and converts it to a Pose2d object. Uses only x, y, and yaw components, ignoring z, roll, and pitch. Array format: [x, y, z, roll, pitch, yaw] where angles are in degrees.

#### Return

Pose2d object representing the pose, or empty Pose2d if invalid data

#### Parameters

jvm

| | |
|---|---|
| inData | Array containing pose data [x, y, z, roll, pitch, yaw] |
