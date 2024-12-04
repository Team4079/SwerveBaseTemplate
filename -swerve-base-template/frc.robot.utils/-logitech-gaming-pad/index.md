---
title: LogitechGamingPad
---
//[SwerveBaseTemplate](../../../index.html)/[frc.robot.utils](../index.html)/[LogitechGamingPad](index.html)



# LogitechGamingPad



[jvm]\
open class [LogitechGamingPad](index.html) : XboxController

A class representing a Logitech Gaming Pad.



## Constructors


| | |
|---|---|
| [LogitechGamingPad](-logitech-gaming-pad.html) | [jvm]<br>constructor(usbPort: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Constructor for the LogitechGamingPad. |


## Types


| Name | Summary |
|---|---|
| [Axis](-axis/index.html) | [jvm]<br>enum [Axis](-axis/index.html)<br>Enum representing the axes on the gamepad. |
| [Button](-button/index.html) | [jvm]<br>enum [Button](-button/index.html)<br>Enum representing the buttons on the gamepad. |
| [DPad](-d-pad/index.html) | [jvm]<br>enum [DPad](-d-pad/index.html)<br>Enum representing the DPad directions. |
| [Trigger](-trigger/index.html) | [jvm]<br>enum [Trigger](-trigger/index.html)<br>Enum representing the triggers on the gamepad. |


## Functions


| Name | Summary |
|---|---|
| [checkDPad](check-d-pad.html) | [jvm]<br>open fun [checkDPad](check-d-pad.html)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the DPad is pressed in a specific direction.<br>[jvm]<br>open fun [checkDPad](check-d-pad.html)(angle: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), inDegrees: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the DPad is pressed at a specific angle. |
| [dPadIsPressed](d-pad-is-pressed.html) | [jvm]<br>open fun [dPadIsPressed](d-pad-is-pressed.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the DPad is pressed. |
| [getAButton](get-a-button.html) | [jvm]<br>open fun [getAButton](get-a-button.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getBackButton](get-back-button.html) | [jvm]<br>open fun [getBackButton](get-back-button.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getBButton](get-b-button.html) | [jvm]<br>open fun [getBButton](get-b-button.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getDPad](get-d-pad.html) | [jvm]<br>open fun [getDPad](get-d-pad.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Gets the DPad value.<br>[jvm]<br>open fun [getDPad](get-d-pad.html)(inDegrees: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the DPad value in degrees or radians. |
| [getLeftAnalogXAxis](get-left-analog-x-axis.html) | [jvm]<br>open fun [getLeftAnalogXAxis](get-left-analog-x-axis.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the left analog X axis. |
| [getLeftAnalogYAxis](get-left-analog-y-axis.html) | [jvm]<br>open fun [getLeftAnalogYAxis](get-left-analog-y-axis.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the left analog Y axis. |
| [getLeftTriggerValue](get-left-trigger-value.html) | [jvm]<br>open fun [getLeftTriggerValue](get-left-trigger-value.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the left trigger. |
| [getPOV](get-p-o-v.html) | [jvm]<br>open fun [getPOV](get-p-o-v.html)(pov: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getRawAxis](get-raw-axis.html) | [jvm]<br>open fun [getRawAxis](get-raw-axis.html)(which: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [getRawButton](get-raw-button.html) | [jvm]<br>open fun [getRawButton](get-raw-button.html)(button: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getRightAnalogXAxis](get-right-analog-x-axis.html) | [jvm]<br>open fun [getRightAnalogXAxis](get-right-analog-x-axis.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the right analog X axis. |
| [getRightAnalogYAxis](get-right-analog-y-axis.html) | [jvm]<br>open fun [getRightAnalogYAxis](get-right-analog-y-axis.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the right analog Y axis. |
| [getRightTriggerValue](get-right-trigger-value.html) | [jvm]<br>open fun [getRightTriggerValue](get-right-trigger-value.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the right trigger. |
| [getStartButton](get-start-button.html) | [jvm]<br>open fun [getStartButton](get-start-button.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getThrottle](get-throttle.html) | [jvm]<br>open fun [getThrottle](get-throttle.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the throttle value. |
| [getTwist](get-twist.html) | [jvm]<br>open fun [getTwist](get-twist.html)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the twist value. |
| [getXButton](get-x-button.html) | [jvm]<br>open fun [getXButton](get-x-button.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getYButton](get-y-button.html) | [jvm]<br>open fun [getYButton](get-y-button.html)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [setRumble](set-rumble.html) | [jvm]<br>open fun [setRumble](set-rumble.html)(amount: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html))<br>Sets the rumble amount for the gamepad. |

