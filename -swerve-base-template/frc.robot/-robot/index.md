---
title: Robot
---
//[SwerveBaseTemplate](../../../index.html)/[frc.robot](../index.html)/[Robot](index.html)



# Robot



[jvm]\
open class [Robot](index.html) : LoggedRobot

The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the TimedRobot documentation. If you change the name of this class or the package after creating this project, you must also update the build.gradle file in the project.



## Constructors


| | |
|---|---|
| [Robot](-robot.html) | [jvm]<br>constructor() |


## Functions


| Name | Summary |
|---|---|
| [autonomousInit](autonomous-init.html) | [jvm]<br>open fun [autonomousInit](autonomous-init.html)()<br>This autonomous runs the autonomous command selected by your [RobotContainer](../-robot-container/index.html) class. |
| [robotInit](robot-init.html) | [jvm]<br>open fun [robotInit](robot-init.html)()<br>This function is run when the robot is first started up and should be used for any initialization code. |
| [robotPeriodic](robot-periodic.html) | [jvm]<br>open fun [robotPeriodic](robot-periodic.html)()<br>This function is called every 20 ms, no matter the mode. |
| [teleopInit](teleop-init.html) | [jvm]<br>open fun [teleopInit](teleop-init.html)()<br>This function is called once when teleop mode is initialized. |
| [testInit](test-init.html) | [jvm]<br>open fun [testInit](test-init.html)()<br>This function is called once when test mode is initialized. |

