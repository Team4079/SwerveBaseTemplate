---
title: PoseEstimate
---
//[SwerveBaseTemplate](../../../../index.html)/[frc.robot.utils](../../index.html)/[LimelightHelpers](../index.html)/[PoseEstimate](index.html)



# PoseEstimate



[jvm]\
open class [PoseEstimate](index.html)

Represents a 3D Pose Estimate.



## Constructors


| | |
|---|---|
| [PoseEstimate](-pose-estimate.html) | [jvm]<br>constructor()<br>Instantiates a PoseEstimate object with default values<br>constructor(pose: Pose2d, timestampSeconds: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), latency: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), tagCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tagSpan: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), avgTagDist: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), avgTagArea: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), rawFiducials: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.RawFiducial](../-raw-fiducial/index.html)&gt;, isMegaTag2: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |


## Properties


| Name | Summary |
|---|---|
| [avgTagArea](avg-tag-area.html) | [jvm]<br>open var [avgTagArea](avg-tag-area.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [avgTagDist](avg-tag-dist.html) | [jvm]<br>open var [avgTagDist](avg-tag-dist.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [isMegaTag2](is-mega-tag2.html) | [jvm]<br>open var [isMegaTag2](is-mega-tag2.html): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [latency](latency.html) | [jvm]<br>open var [latency](latency.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [pose](pose.html) | [jvm]<br>open var [pose](pose.html): Pose2d |
| [rawFiducials](raw-fiducials.html) | [jvm]<br>open var [rawFiducials](raw-fiducials.html): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[LimelightHelpers.RawFiducial](../-raw-fiducial/index.html)&gt; |
| [tagCount](tag-count.html) | [jvm]<br>open var [tagCount](tag-count.html): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [tagSpan](tag-span.html) | [jvm]<br>open var [tagSpan](tag-span.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [timestampSeconds](timestamp-seconds.html) | [jvm]<br>open var [timestampSeconds](timestamp-seconds.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |

