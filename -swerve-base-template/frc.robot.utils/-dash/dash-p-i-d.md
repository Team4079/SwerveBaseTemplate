//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[Dash](index.md)/[dashPID](dash-p-i-d.md)

# dashPID

[jvm]\

public static void[dashPID](dash-p-i-d.md)([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)prefix, [PID](../-p-i-d/index.md)pid, doublevelocity, [DoubleConsumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleConsumer.html)changeV)

Function to update PIDV values from the SmartDashboard.

#### Parameters

jvm

| | |
|---|---|
| pid | The PID object to update. |
| velocity | The velocity to update. |
| prefix | The prefix for the SmartDashboard keys. |
| changeV | The function to change the velocity. |
