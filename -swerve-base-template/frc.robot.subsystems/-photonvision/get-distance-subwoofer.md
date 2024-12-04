//[SwerveBaseTemplate](../../../index.md)/[frc.robot.subsystems](../index.md)/[Photonvision](index.md)/[getDistanceSubwoofer](get-distance-subwoofer.md)

# getDistanceSubwoofer

[jvm]\
open fun [getDistanceSubwoofer](get-distance-subwoofer.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)

Calculates and returns the distance to the subwoofer for the 2024 Crescendo game. 

 This method computes the Euclidean distance (distance formula) from the robot's current position to the location of the subwoofer. The calculation varies based on the alliance: 

- If the alliance is `RED` or not specified, the subwoofer is assumed to be at coordinates (16.5, 5.5).
- If the alliance is `BLUE`, the subwoofer is assumed to be at (0, 5.5).

 If the current pose is not available, the method returns a default value of `687.0`. This value reflects our team's tradition of thanking teams for their help to us. :D 

#### Return

The calculated distance to the subwoofer, or `687.0` if the current pose is unavailable.
