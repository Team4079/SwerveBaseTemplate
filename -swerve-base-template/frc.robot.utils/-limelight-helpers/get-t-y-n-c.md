//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[LimelightHelpers](index.md)/[getTYNC](get-t-y-n-c.md)

# getTYNC

[jvm]\
open fun [getTYNC](get-t-y-n-c.md)(limelightName: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)

Gets the vertical offset from the principal pixel/point to the target in degrees. This is the most accurate 2d metric if you are using a calibrated camera and you don't need adjustable crosshair functionality.

#### Return

Vertical offset angle in degrees

#### Parameters

jvm

| | |
|---|---|
| limelightName | Name of the Limelight camera (&quot;&quot; for default) |