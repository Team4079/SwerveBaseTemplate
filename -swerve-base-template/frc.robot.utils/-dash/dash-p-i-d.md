//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[Dash](index.md)/[dashPID](dash-p-i-d.md)

# dashPID

[jvm]\
open fun [dashPID](dash-p-i-d.md)(prefix: [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), pid: [PID](../-p-i-d/index.md), velocity: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), changeV: [DoubleConsumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleConsumer.html))

Function to update PIDV values from the SmartDashboard.

#### Parameters

jvm

| | |
|---|---|
| pid | The PID object to update. |
| velocity | The velocity to update. |
| prefix | The prefix for the SmartDashboard keys. |
| changeV | The function to change the velocity. |
