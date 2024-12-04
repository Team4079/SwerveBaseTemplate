//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[LimelightHelpers](index.md)/[pose3dToArray](pose3d-to-array.md)

# pose3dToArray

[jvm]\

public static [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;double&gt;[pose3dToArray](pose3d-to-array.md)(Pose3dpose)

Converts a Pose3d object to an array of doubles in the format [x, y, z, roll, pitch, yaw]. Translation components are in meters, rotation components are in degrees.

#### Return

A 6-element array containing [x, y, z, roll, pitch, yaw]

#### Parameters

jvm

| | |
|---|---|
| pose | The Pose3d object to convert |
