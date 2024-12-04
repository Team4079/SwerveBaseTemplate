//[SwerveBaseTemplate](../../../../index.md)/[frc.robot.utils](../../index.md)/[LimelightHelpers](../index.md)/[PoseEstimate](index.md)

# PoseEstimate

[jvm]\
open class [PoseEstimate](index.md)

Represents a 3D Pose Estimate.

## Constructors

| | |
|---|---|
| [PoseEstimate](-pose-estimate.md) | [jvm]<br>constructor()<br>Instantiates a PoseEstimate object with default values<br>constructor(pose: Pose2d, timestampSeconds: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), latency: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), tagCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tagSpan: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), avgTagDist: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), avgTagArea: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), rawFiducials: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.RawFiducial](../-raw-fiducial/index.md)&gt;, isMegaTag2: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [avgTagArea](avg-tag-area.md) | [jvm]<br>open var [avgTagArea](avg-tag-area.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [avgTagDist](avg-tag-dist.md) | [jvm]<br>open var [avgTagDist](avg-tag-dist.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [isMegaTag2](is-mega-tag2.md) | [jvm]<br>open var [isMegaTag2](is-mega-tag2.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [latency](latency.md) | [jvm]<br>open var [latency](latency.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [pose](pose.md) | [jvm]<br>open var [pose](pose.md): Pose2d |
| [rawFiducials](raw-fiducials.md) | [jvm]<br>open var [rawFiducials](raw-fiducials.md): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.RawFiducial](../-raw-fiducial/index.md)&gt; |
| [tagCount](tag-count.md) | [jvm]<br>open var [tagCount](tag-count.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [tagSpan](tag-span.md) | [jvm]<br>open var [tagSpan](tag-span.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [timestampSeconds](timestamp-seconds.md) | [jvm]<br>open var [timestampSeconds](timestamp-seconds.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
