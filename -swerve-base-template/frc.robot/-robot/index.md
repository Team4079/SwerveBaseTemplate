//[SwerveBaseTemplate](../../../index.md)/[frc.robot](../index.md)/[Robot](index.md)

# Robot

[jvm]\
open class [Robot](index.md) : LoggedRobot

The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the TimedRobot documentation. If you change the name of this class or the package after creating this project, you must also update the build.gradle file in the project.

## Constructors

| | |
|---|---|
| [Robot](-robot.md) | [jvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [autonomousInit](autonomous-init.md) | [jvm]<br>open fun [autonomousInit](autonomous-init.md)()<br>This autonomous runs the autonomous command selected by your [RobotContainer](../-robot-container/index.md) class. |
| [robotInit](robot-init.md) | [jvm]<br>open fun [robotInit](robot-init.md)()<br>This function is run when the robot is first started up and should be used for any initialization code. |
| [robotPeriodic](robot-periodic.md) | [jvm]<br>open fun [robotPeriodic](robot-periodic.md)()<br>This function is called every 20 ms, no matter the mode. |
| [teleopInit](teleop-init.md) | [jvm]<br>open fun [teleopInit](teleop-init.md)()<br>This function is called once when teleop mode is initialized. |
| [testInit](test-init.md) | [jvm]<br>open fun [testInit](test-init.md)()<br>This function is called once when test mode is initialized. |
