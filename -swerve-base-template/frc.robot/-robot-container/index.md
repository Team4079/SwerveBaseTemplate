//[SwerveBaseTemplate](../../../index.md)/[frc.robot](../index.md)/[RobotContainer](index.md)

# RobotContainer

[jvm]\
open class [RobotContainer](index.md)

This class is where the bulk of the robot should be declared. Since Command-based is a &quot;declarative&quot; paradigm, very little robot logic should actually be handled in the [Robot](../-robot/index.md) periodic methods (other than the scheduler calls). Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.

## Constructors

| | |
|---|---|
| [RobotContainer](-robot-container.md) | [jvm]<br>constructor()<br>The container for the robot. |

## Functions

| Name | Summary |
|---|---|
| [getAutonomousCommand](get-autonomous-command.md) | [jvm]<br>open fun [getAutonomousCommand](get-autonomous-command.md)(): Command<br>Use this to pass the autonomous command to the main [Robot](../-robot/index.md) class. |
