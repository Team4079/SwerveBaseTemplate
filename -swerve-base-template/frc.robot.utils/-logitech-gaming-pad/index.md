//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[LogitechGamingPad](index.md)

# LogitechGamingPad

[jvm]\
open class [LogitechGamingPad](index.md) : XboxController

A class representing a Logitech Gaming Pad.

## Constructors

| | |
|---|---|
| [LogitechGamingPad](-logitech-gaming-pad.md) | [jvm]<br>constructor(usbPort: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Constructor for the LogitechGamingPad. |

## Types

| Name | Summary |
|---|---|
| [Axis](-axis/index.md) | [jvm]<br>enum [Axis](-axis/index.md)<br>Enum representing the axes on the gamepad. |
| [Button](-button/index.md) | [jvm]<br>enum [Button](-button/index.md)<br>Enum representing the buttons on the gamepad. |
| [DPad](-d-pad/index.md) | [jvm]<br>enum [DPad](-d-pad/index.md)<br>Enum representing the DPad directions. |
| [Trigger](-trigger/index.md) | [jvm]<br>enum [Trigger](-trigger/index.md)<br>Enum representing the triggers on the gamepad. |

## Functions

| Name | Summary |
|---|---|
| [checkDPad](check-d-pad.md) | [jvm]<br>open fun [checkDPad](check-d-pad.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the DPad is pressed in a specific direction.<br>[jvm]<br>open fun [checkDPad](check-d-pad.md)(angle: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), inDegrees: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the DPad is pressed at a specific angle. |
| [dPadIsPressed](d-pad-is-pressed.md) | [jvm]<br>open fun [dPadIsPressed](d-pad-is-pressed.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if the DPad is pressed. |
| [getAButton](get-a-button.md) | [jvm]<br>open fun [getAButton](get-a-button.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getBackButton](get-back-button.md) | [jvm]<br>open fun [getBackButton](get-back-button.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getBButton](get-b-button.md) | [jvm]<br>open fun [getBButton](get-b-button.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getDPad](get-d-pad.md) | [jvm]<br>open fun [getDPad](get-d-pad.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Gets the DPad value.<br>[jvm]<br>open fun [getDPad](get-d-pad.md)(inDegrees: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the DPad value in degrees or radians. |
| [getLeftAnalogXAxis](get-left-analog-x-axis.md) | [jvm]<br>open fun [getLeftAnalogXAxis](get-left-analog-x-axis.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the left analog X axis. |
| [getLeftAnalogYAxis](get-left-analog-y-axis.md) | [jvm]<br>open fun [getLeftAnalogYAxis](get-left-analog-y-axis.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the left analog Y axis. |
| [getLeftTriggerValue](get-left-trigger-value.md) | [jvm]<br>open fun [getLeftTriggerValue](get-left-trigger-value.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the left trigger. |
| [getPOV](get-p-o-v.md) | [jvm]<br>open fun [getPOV](get-p-o-v.md)(pov: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getRawAxis](get-raw-axis.md) | [jvm]<br>open fun [getRawAxis](get-raw-axis.md)(which: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [getRawButton](get-raw-button.md) | [jvm]<br>open fun [getRawButton](get-raw-button.md)(button: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getRightAnalogXAxis](get-right-analog-x-axis.md) | [jvm]<br>open fun [getRightAnalogXAxis](get-right-analog-x-axis.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the right analog X axis. |
| [getRightAnalogYAxis](get-right-analog-y-axis.md) | [jvm]<br>open fun [getRightAnalogYAxis](get-right-analog-y-axis.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the right analog Y axis. |
| [getRightTriggerValue](get-right-trigger-value.md) | [jvm]<br>open fun [getRightTriggerValue](get-right-trigger-value.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the value of the right trigger. |
| [getStartButton](get-start-button.md) | [jvm]<br>open fun [getStartButton](get-start-button.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getThrottle](get-throttle.md) | [jvm]<br>open fun [getThrottle](get-throttle.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the throttle value. |
| [getTwist](get-twist.md) | [jvm]<br>open fun [getTwist](get-twist.md)(): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Gets the twist value. |
| [getXButton](get-x-button.md) | [jvm]<br>open fun [getXButton](get-x-button.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getYButton](get-y-button.md) | [jvm]<br>open fun [getYButton](get-y-button.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [setRumble](set-rumble.md) | [jvm]<br>open fun [setRumble](set-rumble.md)(amount: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html))<br>Sets the rumble amount for the gamepad. |
